package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entities.Question;
import entities.Test;

public class DaoQuestion implements IDaoQuestion {

    private EntityManager entityManager;

    public DaoQuestion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniteQCM");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Question> getAllQuestions() {
        Query query = entityManager.createQuery("FROM Question");
        return query.getResultList();
    }

    @Override
    public List<Question> getRandomQuestion(int nb) {
        // Créez une requête SQL pour sélectionner un échantillon aléatoire de questions
        String sql = "SELECT * FROM Questions ORDER BY RAND() LIMIT :nb";
        
        // Créez la requête JPA en utilisant la requête SQL
        Query query = entityManager.createNativeQuery(sql, Question.class);
        
        // Définissez le paramètre pour le nombre de questions à récupérer
        query.setParameter("nb", nb);
        
        // Exécutez la requête et retournez le résultat
        return query.getResultList();
    }


    @Override
    public void addQuestion(Question q, int idTest) {
        entityManager.getTransaction().begin();
        Test test = entityManager.find(Test.class, idTest);
        if (test != null) {
            q.setTest(test);
            entityManager.persist(q);
            entityManager.getTransaction().commit();
        } else {
            throw new IllegalArgumentException("Test with ID " + idTest + " not found");
        }
    }



    @Override
    public void deleteQuestion(int id) {
        entityManager.getTransaction().begin();
        Question q = entityManager.find(Question.class, id);
        entityManager.remove(q);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateQuestion(Question q, int id) {
        entityManager.getTransaction().begin();
        Question existingQuestion = entityManager.find(Question.class, id);
        if (existingQuestion != null) {
            existingQuestion.setQuestion(q.getQuestion());
            existingQuestion.setReponse(q.getReponse());
            existingQuestion.setRep1(q.getRep1());
            existingQuestion.setRep2(q.getRep2());
            existingQuestion.setRep3(q.getRep3());
            entityManager.merge(existingQuestion);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Question getQuestionById(int id) {
        return entityManager.find(Question.class, id);
    }
}
