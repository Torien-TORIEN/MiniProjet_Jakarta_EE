package entities;

import java.sql.Date;
//import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity @Table(name="utilisateurs")
public class Utilisateur {
	
	@Id
	private int id;
	private String nom,prenom,email,sexe,password,role;
	private Date datenaiss;
	
	@OneToMany(mappedBy="user")
	private List<Score> scores;
	
	public Utilisateur() {
		id=0;
		nom="null";prenom="prenom";email="email";sexe="M";password="";role="USER";
		datenaiss=datenaiss = new Date(System.currentTimeMillis());
	}

	public Utilisateur(int id, String nom, String prenom, String email, String sexe, String pwd,String role, Date datenaiss) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.sexe = sexe;
		this.datenaiss = datenaiss;
		this.password=pwd;
		this.role=role;
	}




	//Getters  Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public Date getDatenaiss() {
		return datenaiss;
	}
	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", sexe=" + sexe
				+ ", password=" + password  + ", datenaiss=" + datenaiss + "]";
	}
	
}
