package dao;
import java.util.List;

import entities.*;

public interface IDaoQuestion {
	public List<Question> getAllQuestions();
	public List<Question> getRandomQuestion(int nb);
	public void addQuestion(Question q, int test); // Ajout du paramètre Test
	public void deleteQuestion( int id);
	public void updateQuestion(Question q, int id);
	public Question getQuestionById(int id);
	
	
}
