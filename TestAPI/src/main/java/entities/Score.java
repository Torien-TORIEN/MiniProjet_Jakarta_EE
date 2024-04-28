package entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Scores")
public class Score {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double score;
	
	@Column(name = "done_at")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private Utilisateur user;
	
	@ManyToOne
	@JoinColumn(name="id_test")
	private Test test;
	
	public Score() {
        this.date = new Date(); // Obtient la date et l'heure actuelles
    }
	
	
	public Score(int id, double score) {
		super();
		this.id = id;
		this.score = score;
		this.date = new Date();
	}

	//Setters & Getters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Utilisateur getUser() {
		return user;
	}


	public void setUser(Utilisateur user) {
		this.user = user;
	}


	public Test getTest() {
		return test;
	}


	public void setTest(Test test) {
		this.test = test;
	}
	
	
}
