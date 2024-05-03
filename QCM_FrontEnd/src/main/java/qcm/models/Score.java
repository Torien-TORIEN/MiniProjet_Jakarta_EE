package qcm.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class Score implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double score;
	
	//@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    //@JsonProperty("date")
	private Date date;
	
	private Utilisateur user;
	private Test test;
	
	
	public Score() {
		this.date=new Date();
	}
	
	// Constructeur prenant un HttpServletRequest
    public Score(HttpServletRequest request) {
        //this.id = Integer.parseInt(request.getParameter("id"));
        this.score = Double.parseDouble(request.getParameter("score"));;
        
        // Conversion de la date de naissance (format "yyyy-MM-dd")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = dateFormat.parse(request.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace(); // Gérer l'erreur de conversion
        }
    }
	
	
	
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
	
	// Méthode toString qui retourne l'objet sous forme de JSON
    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
