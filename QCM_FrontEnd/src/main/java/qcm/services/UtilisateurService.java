package qcm.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import com.fasterxml.jackson.databind.ObjectMapper;


import qcm.models.Utilisateur;

public class UtilisateurService {
	HttpURLConnection connection;
	public static final String API_URL = "http://localhost:8080/TestAPI/api/users";
	
	
	public ArrayList<Utilisateur> getAllUsers() throws Exception{
		//
		ArrayList<Utilisateur> users = null;
		
		// Créer une URL pour l'API
        URL url = new URL(API_URL);

        // Ouvrir une connexion HTTP avec l'API
         this.connection = (HttpURLConnection) url.openConnection();
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
        users = objectMapper.readValue(jsonResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Utilisateur.class));
        
        System.out.println("Service Utilisateur :");
        /*for(Utilisateur u :users)
        {
        	System.out.println(u);
        }*/
        
        return users;
	}
	public ReponseCode createAccount(Utilisateur user) throws Exception{

        // Appelle l'API pour ajouter l'utilisateur
        URL url = new URL(API_URL);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        OutputStream os = connection.getOutputStream();
        os.write(userJson.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        ReponseCode res =new ReponseCode(responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            // L'utilisateur a été ajouté avec succès
            System.out.println("Utilisateur ajouté avec succès !");
            res.setMessage(responseCode, "Utilisateur ajouté avec succès !");
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
            
            //Si la longueur de errorMessage est supérieur à 30 et elle ne commen pas par "Error" ,  générer une exception 
            
            // Afficher le message d'erreur ou le stocker pour une utilisation ultérieure
            System.out.println("Erreur serveur : " + errorMessage.toString());
            if(errorMessage.toString().contains("ConstraintViolationException"))
            	res.setMessage(responseCode, "L'adresse e-mail est déjà utilisée !");
            else
            	res.setMessage(responseCode, errorMessage.toString());
        	
        } else {
            // Erreur lors de l'ajout de l'utilisateur
            res.setMessage(responseCode, "Votre compte n'a pu être créé !");
        }
        
        return res;
	}
	
	public ReponseCode deleteUser(int idUser) throws Exception{
		URL url = new URL(API_URL+"/"+idUser);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("DELETE");

	    // Vérifier la réponse de l'API
	    int responseCode = connection.getResponseCode();
	    ReponseCode res = new ReponseCode(responseCode);
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        // Suppression réussie
	        System.out.println("Question deleted successfully.");
	        res.setMessage(responseCode, "Compte a été bien supprimé !");
	        
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
            
            // Afficher le message d'erreur ou le stocker pour une utilisation ultérieure
            System.out.println("Erreur serveur : " + errorMessage.toString());
            // Ajoutez le message d'erreur à l'attribut "error" pour le transmettre à la page JSP
            res.setMessage(responseCode,errorMessage.toString());
        	
        } else {
	        // Gérer les erreurs éventuelles
	        System.out.println("Error deleting question. Response code: " + responseCode);
	        res.setMessage(responseCode, "Une erreur s'est produite lors de la suppression :"+responseCode);
	    }

	    // Fermer la connexion
	    connection.disconnect();
	    
	    return res;
		
	}
	
	
	public ReponseCode updateUser(Utilisateur user) throws Exception{
		// Appeler l'API pour ajouter la question au test spécifié
        URL url = new URL(API_URL+"/"+user.getId());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

     // Créer un objet JSONObject pour représenter la question
 		ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        
        OutputStream os = connection.getOutputStream();
        os.write(userJson.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        ReponseCode res = new ReponseCode(responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("User modifiée avec succès !");
            res.setMessage(responseCode, "La modification est bien reussie .");
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
        	
        }else {
            System.out.println("Erreur lors de l'ajout de la question !" + responseCode);
            res.setMessage(responseCode, "Echèc , Une erreur s'est produite lors de la modification du compte "+ user.getEmail());
        }
        
        return res;
	}
	
	
	public Map<String, Object> login(String email, String password) throws Exception {
	    // Créer les paramètres de la requête HTTP
	    String apiUrl = API_URL + "/login";
	    String params = "email=" + URLEncoder.encode(email, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");

	    // Créer la connexion HTTP
	    URL url = new URL(apiUrl);
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    connection.setDoOutput(true);

	    // Envoyer les paramètres de la requête
	    try (OutputStream os = connection.getOutputStream()) {
	        byte[] input = params.getBytes("utf-8");
	        os.write(input, 0, input.length);
	    }

	    // Lire la réponse de l'API
	    int responseCode = connection.getResponseCode();
	    Map<String, Object> result = new HashMap<>();
	    ReponseCode res = new ReponseCode(responseCode);
	    Utilisateur userLogged =null;

	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        // Connexion réussie
	        System.out.println("Logged in successfully");
	        res.setMessage(responseCode, "Vous etes authentifié(e) !");
	        // Lire la réponse JSON
	        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
	            String inputLine;
	            StringBuffer responseBuffer = new StringBuffer();
	            while ((inputLine = in.readLine()) != null) {
	                responseBuffer.append(inputLine);
	            }
	            // Convertir la réponse JSON en objet Utilisateur
	            ObjectMapper mapper = new ObjectMapper();
	            Utilisateur utilisateur = mapper.readValue(responseBuffer.toString(), Utilisateur.class);
	            userLogged=utilisateur;
	        }
	    } else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
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
            
            System.out.println("Erreur serveur login : " + errorMessage.toString());
        	
            res.setMessage(responseCode, errorMessage.toString());
        }else {
            // Connexion échouée, rediriger vers la page de connexion avec un message d'erreur
            System.out.println("Echec de de connexion !");
            res.setMessage(responseCode, "Echec de de connexion !");
        
	    }
	    

        result.put("user", userLogged);
        result.put("responseCode", res);
	    return result;
	}

}
