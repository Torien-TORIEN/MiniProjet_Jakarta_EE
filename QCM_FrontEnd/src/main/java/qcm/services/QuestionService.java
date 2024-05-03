package qcm.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;

public class QuestionService {
	HttpURLConnection connection;
	public static final String API_URL = "http://localhost:8080/TestAPI/api/questions";
	public static final String API_URL_TEST = "http://localhost:8080/TestAPI/api/tests/";
	
	public ArrayList<Question> getAllQuestionsByIdTest(int idTest) throws Exception{
        
        // Faire quelque chose avec l'ID du test (par exemple, l'imprimer pour le tester)
        System.out.println("ID du test sélectionné : " + idTest);
        
        // Appel à l'API pour récupérer les questions associées à ce test
        String apiUrl =API_URL_TEST+ idTest + "/questions";
        URL url = new URL(apiUrl);
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

        // Convertir la réponse JSON en une liste d'objets Question
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Question> questions = objectMapper.readValue(jsonResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Question.class));
        
        
        return questions;
        
		
	}
	
	
	public List<Question> getRandomQuestionsByTest(int idTest, int count) throws Exception{
		String apiUrl = API_URL_TEST + idTest + "/randomQuestions?count="+count;

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

        // Convertir la réponse JSON en une liste d'objets Question
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = objectMapper.readValue(jsonResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));

        // Afficher les question
        for(Question q :questions) {
        	System.out.println(q);
        }
        return questions;

	}
	
	
	@SuppressWarnings("unchecked")
	public ReponseCode addQuestion(Question question) throws Exception {

        // Créer un objet JSONObject pour représenter la question
        JSONObject questionJson = new JSONObject();
        questionJson.put("id", question.getId());
        questionJson.put("question", question.getQuestion());
        questionJson.put("reponse", question.getReponse());
        questionJson.put("rep1", question.getRep1());
        questionJson.put("rep2", question.getRep2());
        questionJson.put("rep3", question.getRep3());

        // Convertir l'objet JSONObject en chaîne JSON
        String jsonString = questionJson.toString();
        //System.out.println(jsonString);

        // Appeler l'API pour ajouter la question au test spécifié
        String apiUrl = API_URL+"?id_test="+question.getTest().getId();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(jsonString.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        ReponseCode res = new ReponseCode(responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            System.out.println("Question ajoutée avec succès !");
            res.setMessage(responseCode, "La question est ajoutée au test << "+question.getTest().getCategorie()+" >> avec succès !");
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
            res.setMessage(responseCode,  errorMessage.toString());
        	
        }else {
            System.out.println("Erreur lors de l'ajout de la question !" + responseCode);
            res.setMessage(responseCode, "Erreur d'ajout, Vérifiez si le serveur est en marche !");
        }
        
        return res;
	}
	
	public ReponseCode deleteQuestion(int idQuestion) throws Exception {
		
	    // URL de l'API pour supprimer la question
	    URL url = new URL(API_URL+"/" + idQuestion);
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("DELETE");

	    // Vérifier la réponse de l'API
	    int responseCode = connection.getResponseCode();
	    ReponseCode res = new ReponseCode(responseCode);
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        // Suppression réussie
	        System.out.println("Question deleted successfully.");
	        res.setMessage(responseCode, "Question a été bien supprimée !");
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
            res.setMessage(responseCode, errorMessage.toString());
        	
        } else {
	        // Gérer les erreurs éventuelles
	        System.out.println("Error deleting question. Response code: " + responseCode);
	        res.setMessage(responseCode, "Une erreur s'est produite lors de la suppression :"+responseCode);
	    }

	    // Fermer la connexion
	    connection.disconnect();
	    
	    return res;
	}
	
	@SuppressWarnings("unchecked")
	public ReponseCode updateQuestion(Question question) throws Exception {
        
        
        System.out.println("Updating Question :\n"+question);
        
     // Créer un objet JSONObject pour représenter la question
        JSONObject questionJson = new JSONObject();
        questionJson.put("id", question.getId());
        questionJson.put("question", question.getQuestion());
        questionJson.put("reponse", question.getReponse());
        questionJson.put("rep1", question.getRep1());
        questionJson.put("rep2", question.getRep2());
        questionJson.put("rep3", question.getRep3());

        // Convertir l'objet JSONObject en chaîne JSON
        String jsonString = questionJson.toString();
        //System.out.println(jsonString);
        
     // Appeler l'API pour ajouter la question au test spécifié
        URL url = new URL(API_URL+"/"+question.getId());
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(jsonString.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        
        ReponseCode res =new ReponseCode(responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Question modifiée avec succès !");
	        res.setMessage(responseCode, "Une question  de test << "+question.getTest().getCategorie()+" >> est modifiée avec succès !");

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
            res.setMessage(responseCode, "Echèc, Une eurreur s'est produite lors de la modification !");
        }
        
        return res;
	}
}
