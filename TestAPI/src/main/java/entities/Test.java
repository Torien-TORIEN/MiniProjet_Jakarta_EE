package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Tests")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categorie;
	

	@OneToMany(mappedBy="test")
	private List<Question> questions;
	
	@OneToMany(mappedBy="test")
	private List<Question> scores;
	
	public Test() {
		
	}
	
	@Override
	public String toString() {
		return "Test [id=" + id + ", categorie=" + categorie + "]";
	}

	public Test(int id, String categorie) {
		super();
		this.id = id;
		this.categorie = categorie;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	

}
