package qcm.models;

import javax.servlet.http.HttpServletRequest;

public class Test {
	
	private int id;
	private String categorie;
	
	public Test() {}
	
	public Test(HttpServletRequest request) {
        //this.id = Integer.parseInt(request.getParameter("id"));
        this.categorie = request.getParameter("categorie");
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

	@Override
	public String toString() {
		return "Test [id=" + id + ", categorie=" + categorie + "]";
	}
	
	
	

}
