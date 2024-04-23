package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Utilisateur;

public class DaoUtilisateur implements IDaoUtilisateur{
	
	private EntityManager entityManager;
	
	public DaoUtilisateur() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("UniteQCM");
		this.entityManager=emf.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Utilisateur> getAllUsers() {
		// TODO Auto-generated method stub
		ArrayList<Utilisateur> users=new ArrayList<>();
		
		Query q= entityManager.createQuery("from Utilisateur", Utilisateur.class); 
		
		List<Utilisateur> utilisateurs= q.getResultList();
		
		for(Utilisateur u : utilisateurs) {
			users.add(u);
		}		
		return users;
	}

    @Override
    public void addUser(Utilisateur user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.getTransaction().begin();
        Utilisateur user = entityManager.find(Utilisateur.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Utilisateur getUserById(int id) {
        Utilisateur user = entityManager.find(Utilisateur.class, id);
        entityManager.close();
        return user;
    }

    @Override
    public Utilisateur login(String email, String pwd) throws Exception {
        try {
            Query query = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email AND u.password = :password", Utilisateur.class);
            query.setParameter("email", email);
            query.setParameter("password", pwd);
            return (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
        	throw new Exception("Email or passwor incorrect !");
            // Aucun utilisateur trouvé avec les informations d'identification fournies
        }
    }
    
    @Override
    public Utilisateur getUserByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class);
        query.setParameter("email", email);
        return (Utilisateur) query.getSingleResult();
    }

    @Override
    public void updateUser(int id, Utilisateur user) throws Exception {
        entityManager.getTransaction().begin();
        Utilisateur existingUser = entityManager.find(Utilisateur.class, id);
        if (existingUser != null) {
            existingUser.setNom(user.getNom());
            existingUser.setPrenom(user.getPrenom());
            existingUser.setEmail(user.getEmail());
            existingUser.setSexe(user.getSexe());
            existingUser.setDatenaiss(user.getDatenaiss());
            existingUser.setPassword(user.getPassword());
            entityManager.merge(existingUser);
            entityManager.getTransaction().commit();
        } else {
            throw new Exception(" User Not Found ");
        }
        entityManager.close();
    }



}
