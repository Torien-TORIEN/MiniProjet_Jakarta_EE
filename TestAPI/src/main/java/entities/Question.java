package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Questions")

public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question,reponse,rep1,rep2,rep3;
	
	@ManyToOne
	@JoinColumn(name="id_test")
	private Test test;
	
	public Question() {
		
	}
	
	public Question(int id, String question, String reponse, String rep1, String rep2, String rep3) {
		super();
		this.id = id;
		this.question = question;
		this.reponse = reponse;
		this.rep1 = rep1;
		this.rep2 = rep2;
		this.rep3 = rep3;
	}
	
	
	//Getters & Setters
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


	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
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

	@Override
	public String toString() {
		return "Question {\n\tid=" + id + "\n\tquestion=" + question + "\n\treponse=" + reponse + "\n\trep1=" + rep1 + "\n\trep2="
				+ rep2 + "\n\trep3=" + rep3 + "\n\ttest=" + test + "\n}";
	}


	
	
	
}
