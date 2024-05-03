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



import com.fasterxml.jackson.databind.ObjectMapper;

import qcm.models.Question;
import qcm.models.Test;

public class TestService {
	
	HttpURLConnection connection;
	public static final String API_URL = "http://localhost:8080/TestAPI/api/tests";
	
	public ArrayList<Test> getAllTests() throws Exception{
		
		//
		ArrayList<Test> tests = null;
		
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
        tests = objectMapper.readValue(jsonResponse.toString(),
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Test.class));
        
        System.out.println("Service Test :");
        /*for(Test t :tests)
        {
        	System.out.println(t);
        }*/
        
        return tests;
	}
	
	
	public ReponseCode addTest(Test test) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        ObjectMapper mapper = new ObjectMapper();
        String testJson = mapper.writeValueAsString(test);

        OutputStream os = connection.getOutputStream();
        os.write(testJson.getBytes());
        os.flush();
        
        
        
        int responseCode = connection.getResponseCode();
        
        ReponseCode res =new ReponseCode(responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
           res.setMessage(responseCode, "Test est bien enregistré !");
         
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
            res.setMessage(responseCode, "Le test n'a pu être créé !");
        }
        
        return res;
	}
	
	public ReponseCode deleteTest(int idTest)throws Exception {
		List<Question> questions =this.getRandomQuestionsByTest(idTest, 2);
		
		if(questions!=null && questions.size()>0) {
			return new ReponseCode(500,"Ce test a déjà des questions associées ! Vous ne pouvez donc  pas le supprimer .");
		}else {
		    URL url = new URL(API_URL+"/"+idTest);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("DELETE");
	
		    // Vérifier la réponse de l'API
		    int responseCode = connection.getResponseCode();
		    ReponseCode res = new ReponseCode(responseCode);
		    
		    if (responseCode == HttpURLConnection.HTTP_OK) {
		        // Suppression réussie
		        System.out.println("Question deleted successfully.");
		        res.setMessage(responseCode, "Test a été bien supprimé !");
		        
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
	    
	}
	
	
	public ReponseCode updateTest(Test test) throws Exception  {
		
     // Appeler l'API pour ajouter la question au test spécifié
        String apiUrl = API_URL+"/"+test.getId();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

     // Créer un objet JSONObject pour représenter la question
 		ObjectMapper mapper = new ObjectMapper();
        String testJson = mapper.writeValueAsString(test);
        
        OutputStream os = connection.getOutputStream();
        os.write(testJson.getBytes());
        os.flush();

        int responseCode = connection.getResponseCode();
        ReponseCode res = new ReponseCode(responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Test modifiée avec succès !");
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
            res.setMessage(responseCode, "Echèc , Une erreur s'est produite lors de la modification du test "+ test.getCategorie());
        }
        
        return res;
	}
	
	
	public List<Question> getRandomQuestionsByTest(int idTest, int count) throws Exception{
		String apiUrl = API_URL+ "/" + idTest + "/randomQuestions?count="+count;

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
        /*for(Question q :questions) {
        	System.out.println(q);
        }*/
        return questions;

	}
	
	
	
}
