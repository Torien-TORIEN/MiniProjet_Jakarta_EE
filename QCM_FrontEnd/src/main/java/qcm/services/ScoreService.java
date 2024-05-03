package qcm.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Score;
import qcm.models.Utilisateur;

public class ScoreService {
	HttpURLConnection connection;
	public static final String API_URL = "http://localhost:8080/TestAPI/api/scores";
	
	public ArrayList<Score> getAllScoresByUser(Utilisateur user) throws Exception {
		ArrayList<Score> scores = null;
        String apiUrl = API_URL+"/sort/user/"+user.getId();
     // Créer une URL pour l'API
        URL url = new URL(apiUrl);

        // Ouvrir une connexion HTTP avec l'API
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Récupérer la réponse de l'API
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8");

        // Lire la réponse JSON
        StringBuilder jsonResponse = new StringBuilder();
        while (scanner.hasNextLine()) {
            jsonResponse.append(scanner.nextLine());
        }

        // Fermer les ressources
        scanner.close();
        inputStream.close();
        connection.disconnect();

        // Convertir la réponse JSON en une liste d'objets Test
        ObjectMapper objectMapper = new ObjectMapper();
        scores = objectMapper.readValue(jsonResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Score.class));
        
        /*for(Score sc :scores)
        {
        	System.out.println(sc);
        }*/
        
        return scores;
	}
	
	public ReponseCode addScore(Score score)throws Exception {
		// Enregistrer le score dans la base de données
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Convertir l'objet Score en JSON
        ObjectMapper mapper = new ObjectMapper();
        String scoreJson = mapper.writeValueAsString(score);

        // Envoyer le JSON à l'API
        OutputStream os = connection.getOutputStream();
        os.write(scoreJson.getBytes());
        os.flush();

        // Vérifier le code de réponse
        int responseCode = connection.getResponseCode();
        ReponseCode res =new ReponseCode(responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
        	res.setMessage(responseCode, "Score enregistré avec succès !");
        }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
        	// Lire le corps de la réponse pour récupérer le message d'erreur
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder errorMessage = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                errorMessage.append(line);
            }
            reader.close();
            
         // Vérifier la longueur et le début du message d'erreur
            if (errorMessage.length() > 30 && !errorMessage.toString().startsWith("Error")) {
                // Générer une exception si la condition n'est pas respectée
                throw new Exception("Erreur du serveur(API) , Vérifier que vous avez bien la connexion à la base de données (XAMP, WAMP, MySQL ,ect...) et que l'API TestAPI est bien lancé sur le serveur le meme serveur Tomcat que celui-ci. Si vous ne trouvez pas de solution consulter l'instruction sur ce GITHUB https://github.com/Torien-TORIEN/MiniProjet_Jakarta_EE :"+errorMessage.toString());
            }
            res.setMessage(responseCode, errorMessage.toString());
        	
        } else {
            System.out.println("Erreur lors de l'enregistrement du score !");
            res.setMessage(responseCode, "Erreur lors de l'enregistrement du score !");
        }
        
        return res;
    
	}
	
	public ReponseCode deleteScore(int idScore) throws Exception {
		
		URL url = new URL(API_URL+"/"+idScore);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("DELETE");

	    // Vérifier la réponse de l'API
	    int responseCode = connection.getResponseCode();
	    ReponseCode res = new ReponseCode(responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
        	res.setMessage(responseCode, "Score est supprimé avec succès !");
        }else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
        	// Lire le corps de la réponse pour récupérer le message d'erreur
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder errorMessage = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                errorMessage.append(line);
            }
            reader.close();
            
         // Vérifier la longueur et le début du message d'erreur
            if (errorMessage.length() > 30 && !errorMessage.toString().startsWith("Error")) {
                // Générer une exception si la condition n'est pas respectée
                throw new Exception("Erreur du serveur(API) , Vérifier que vous avez bien la connexion à la base de données (XAMP, WAMP, MySQL ,ect...) et que l'API TestAPI est bien lancé sur le serveur le meme serveur Tomcat que celui-ci. Si vous ne trouvez pas de solution consulter l'instruction sur ce GITHUB https://github.com/Torien-TORIEN/MiniProjet_Jakarta_EE :"+errorMessage.toString());
            }
            res.setMessage(responseCode, errorMessage.toString());
        	
        } else {
            System.out.println("Erreur lors de l'enregistrement du score !");
            res.setMessage(responseCode, "Erreur lors de la suppression du score !");
        }
        
     // Fermer la connexion
	    connection.disconnect();
        return res;
		
	}
}
