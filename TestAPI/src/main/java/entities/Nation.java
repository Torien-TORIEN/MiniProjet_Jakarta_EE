package entities;

import javax.persistence.*;

@Entity @Table(name="Nations")
public class Nation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private int population;
	
	public Nation(int id , String nom, int pop) {
		this.id=id;this.nom=nom;population=pop;
	}
	
	public Nation() {
		id=0;
		nom="unknown";
		population=0;
	}

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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Nation [id=" + id + ", nom=" + nom + ", population=" + population + "]";
	}
	
}