package dao;


import java.util.ArrayList;

import entities.*;

public interface IDaoUtilisateur {
	ArrayList<Utilisateur> getAllUsers();
    void addUser(Utilisateur user);
    void deleteUser(int id);
    Utilisateur getUserById(int id);
    Utilisateur getUserByEmail(String email);
    void updateUser(int id,Utilisateur user) throws Exception;
    Utilisateur login(String email, String pwd) throws Exception;

}
