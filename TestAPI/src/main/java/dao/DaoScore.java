package dao;

import entities.Score;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoScore implements IDaoScore {

    private EntityManager entityManager;

    public DaoScore() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniteQCM");
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public List<Score> getAllScores() {
        Query query = entityManager.createQuery("FROM Score");
        return query.getResultList();
    }
    
    @Override
    public List<Score> getSortedScoresByUser(int id_user) {
        Query query = entityManager.createQuery("SELECT s FROM Score s WHERE s.user.id = :id_user ORDER BY s.date DESC");
        query.setParameter("id_user", id_user);
        return query.getResultList();
    }
    
    @Override
    public List<Score> getSortedScoresByTest(int id_test) {
        Query query = entityManager.createQuery("SELECT s FROM Score s WHERE s.test.id = :id_test ORDER BY s.score DESC");
        query.setParameter("id_test", id_test);
        return query.getResultList();
    }

    @Override
    public void addScore(Score score) {
        entityManager.getTransaction().begin();
        entityManager.persist(score);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteScore(int id) {
        entityManager.getTransaction().begin();
        Score score = entityManager.find(Score.class, id);
        entityManager.remove(score);
        entityManager.getTransaction().commit();
    }

    @Override
    public Score getScoreById(int id) {
        return entityManager.find(Score.class, id);
    }

    @Override
    public List<Score> getScoresByUserId(int userId) {
        Query query = entityManager.createQuery("FROM Score WHERE user.id = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }
    
    @Override
    public void updateScore(Score score, int id) {
        entityManager.getTransaction().begin();
        Score existingScore = entityManager.find(Score.class, id);
        if (existingScore != null) {
            existingScore.setScore(score.getScore());
            existingScore.setDate(score.getDate());
            existingScore.setUser(score.getUser());
            existingScore.setTest(score.getTest());
            entityManager.merge(existingScore);
        }
        entityManager.getTransaction().commit();
    }

}
