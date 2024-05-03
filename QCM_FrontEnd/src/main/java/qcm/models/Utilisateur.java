package qcm.models;



import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletRequest;

public class Utilisateur implements Serializable   {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String nom;
    private String prenom;
    private String email;
    private String sexe;
    private String password;
    private String role;
    
    //@JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty("datenaiss")
    private Date dateNaissance;

    // Constructeur par défaut
    public Utilisateur() {}
    
    // Constructeur prenant un HttpServletRequest
    public Utilisateur(HttpServletRequest request) {
        //this.id = Integer.parseInt(request.getParameter("id"));
        this.nom = request.getParameter("nom");
        this.prenom = request.getParameter("prenom");
        this.email = request.getParameter("email");
        this.sexe = request.getParameter("sexe");
        this.password = request.getParameter("password");
        this.role = request.getParameter("role");
        
        // Conversion de la date de naissance (format "yyyy-MM-dd")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateNaissance = dateFormat.parse(request.getParameter("datenaiss"));
        } catch (ParseException e) {
        	System.out.println("Problème de conversion de Date dans le model utilisateur !");
            e.printStackTrace(); // Gérer l'erreur de conversion
        }
    }
    
 // Constructeur prenant un HttpServletRequest
    public Utilisateur(HttpServletRequest request,int id) {
        this.id = id;
        this.nom = request.getParameter("nom");
        this.prenom = request.getParameter("prenom");
        this.email = request.getParameter("email");
        this.sexe = request.getParameter("sexe");
        this.password = request.getParameter("password");
        this.role = request.getParameter("role");
        
        // Conversion de la date de naissance (format "yyyy-MM-dd")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.dateNaissance = dateFormat.parse(request.getParameter("datenaiss"));
        } catch (ParseException e) {
            e.printStackTrace(); // Gérer l'erreur de conversion
        }
    }
    
    

    public Utilisateur(int id, String nom, String prenom, String email, String sexe, String password, String role,
		Date dateNaissance) {
			super();
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.sexe = sexe;
			this.password = password;
			this.role = role;
			this.dateNaissance = dateNaissance;
		}

	// Getters et setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", sexe=" + sexe
				+ ", password=" + password + ", role=" + role + ", dateNaissance=" + dateNaissance + "]";
	}
    
    
    
}
