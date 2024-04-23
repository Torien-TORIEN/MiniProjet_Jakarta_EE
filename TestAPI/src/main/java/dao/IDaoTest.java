package dao;

import entities.*;
import java.util.*;

public interface IDaoTest {
	public List<Test> getAllTests();
	public List<Question> getQuestionByCategory(String categorie);
	public List<Question> getRandomQuestionByCategorie(String category, int nombreQuestion);
	public Test getTestById(int id);
	
	public void addTest(Test test);
	public void delete (int id);
	public void updateTest(int id, Test test);
	
}
