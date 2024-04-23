package dao;

import java.util.List;

import entities.Nation;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import java.util.*;

public class NationDAO implements INationDAO {
	private EntityManager entityManager;
	
	public NationDAO() {
		EntityManagerFactory emf ;
		emf = Persistence.createEntityManagerFactory("UniteQCM");
		entityManager = emf.createEntityManager();
	}

	@Override
	public List<Nation> nationsPluscitoyens(int n) {
		Query q = entityManager.createQuery( "from Nation" , Nation.class );
		List<Nation> nations ;
		nations = q.getResultList();
		
		ArrayList<Nation> citoyens= new ArrayList<Nation>();
		
		for (Nation na : nations) {
			//System.out.println( na.toString() );
			if(na.getPopulation()>=n) {
				//System.out.println( na.toString() );
				citoyens.add(na);
			}
		}
		
		return citoyens;
	}

	@Override
	public void modifierNbPopulation(int id , int nbPop) {
		// Mise � jour d'un enregistrement
		entityManager.getTransaction().begin(); // D�but de la transaction
		Nation nationToUpdate = entityManager.find(Nation.class, id); // R�cup�ration de l'entit� � mettre � jour
		nationToUpdate.setPopulation(nbPop); // Modification de l'attribut
		entityManager.getTransaction().commit(); // Validation de la transaction
		
	}
}
