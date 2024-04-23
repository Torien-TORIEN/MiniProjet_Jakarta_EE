package dao;

import java.util.List;

import entities.Nation;

public interface INationDAO {
	public List<Nation> nationsPluscitoyens(int n);//une m�thode qui liste toutes nations qui ont plus n citoyens,
	public void modifierNbPopulation(int id , int nbPop);//une m�thode qui permet de modifier le nombre d�habitants d�une nation donn�e.

}
