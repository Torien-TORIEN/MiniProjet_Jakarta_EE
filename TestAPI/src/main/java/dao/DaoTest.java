package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Question;
import entities.Test;

public class DaoTest implements IDaoTest {

    private EntityManager entityManager;
    
    public DaoTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniteQCM");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Test> getAllTests() {
        Query query = entityManager.createQuery("FROM Test");
        return query.getResultList();
    }

    @Override
    public List<Question> getQuestionByCategory(String categorie) {
    	// Créez une requête SQL pour sélectionner un échantillon aléatoire de questions
        String sql = "SELECT q.* FROM questions q INNER JOIN tests t ON q.id_test = t.id WHERE t.categorie = :category";
        
        // Créez la requête JPA en utilisant la requête SQL
        Query query = entityManager.createNativeQuery(sql, Question.class);
        
        // Définissez le paramètre pour le nombre de questions à récupérer
        query.setParameter("category", categorie);
        
        // Exécutez la requête et retournez le résultat
        return query.getResultList();
    }

    @Override
    public List<Question> getRandomQuestionByCategorie(String category, int nombreQuestion) {
        // Créer une requête SQL pour sélectionner un échantillon aléatoire de questions
        String sql = "SELECT q.* FROM Questions q INNER JOIN Tests t ON q.id_test = t.id WHERE t.categorie = :category ORDER BY RAND() LIMIT :nb";

        // Créer la requête JPA en utilisant la requête SQL
        Query query = entityManager.createNativeQuery(sql, Question.class);
        
        // Définir le paramètre pour le nombre de questions à récupérer
        query.setParameter("category", category);
        query.setParameter("nb", nombreQuestion);
        
        // Exécuter la requête et retourner le résultat
        return query.getResultList();
    }


    @Override
    public Test getTestById(int id) {
        return entityManager.find(Test.class, id);
    }

    @Override
    public void addTest(Test test) {
        entityManager.getTransaction().begin();
        entityManager.persist(test);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Test test = entityManager.find(Test.class, id);
        entityManager.remove(test);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateTest(int id, Test test) {
        entityManager.getTransaction().begin();
        Test existingTest = entityManager.find(Test.class, id);
        if (existingTest != null) {
            existingTest.setCategorie(test.getCategorie());
            entityManager.merge(existingTest);
        }
        entityManager.getTransaction().commit();
    }
}
