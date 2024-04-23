package dao;

import entities.Score;

import java.util.List;

public interface IDaoScore {
    List<Score> getAllScores();
    List<Score> getSortedScoresByUser(int id_user);
    List<Score> getSortedScoresByTest(int id_test);
    void addScore(Score score);
    void deleteScore(int id);
    void updateScore(Score score, int id);
    Score getScoreById(int id);
    List<Score> getScoresByUserId(int userId);
}
