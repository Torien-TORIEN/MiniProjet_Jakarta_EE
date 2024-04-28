package qcm.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Question {
	private int id;
	private String question,reponse,rep1,rep2,rep3;
	private Test test;
	
	public Question() {}
	
	public Question(HttpServletRequest request) {
        //this.id = Integer.parseInt(request.getParameter("id"));
        this.question = request.getParameter("question");
        this.reponse = request.getParameter("reponse");
        this.rep1 = request.getParameter("rep1");
        this.rep2 = request.getParameter("rep2");
        this.rep3 = request.getParameter("rep3");
        //this.test = (Test)request.getParameter("test");
	}
	
	// Méthode pour récupérer les réponses mélangées
    public List<String> getShuffledResponses() {
        List<String> responses = new ArrayList<>();
        responses.add(reponse);
        responses.add(rep1);
        responses.add(rep2);
        responses.add(rep3);
        Collections.shuffle(responses); // Mélanger les réponses
        return responses;
    }
    
    // Retourne V ou F selon la chaine passée
    public String TrueORFalse(String rep) {
    	if(rep.equalsIgnoreCase(this.getReponse())) {
    		return "V";
    	}
    	return "F";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getRep1() {
		return rep1;
	}

	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}

	public String getRep2() {
		return rep2;
	}

	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	public String getRep3() {
		return rep3;
	}

	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}
	
	
	
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "Question {\n\tid=" + id + "\n\tquestion=" + question + "\n\treponse=" + reponse + "\n\trep1=" + rep1 + "\n\trep2="
				+ rep2 + "\n\trep3=" + rep3 +"\n}";
	}
	
	

}
