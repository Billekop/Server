package controllers;

import Encrypters.*;
import com.google.gson.Gson;
import database.DBConnector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Opretter en instans af DBConnector og kalder alle metoder til User.
 * Klassen modtager datasættet fra DBConnector som videresendes til UserEndpoint.
 * Hver metode er forklaret med kommentarer i DBConnector.
 */

public class UserController {
  Gson gson;
  DBConnector db = new DBConnector();


  public UserController() {
    this.gson = new Gson();
  }


  public ArrayList<User> getUsers() {
    DBConnector db = new DBConnector();
    ArrayList<User> users = db.getUsers();
    db.close();
    return users;

  }

  public User getUser(int id) {
    DBConnector db = new DBConnector();
    User user = db.getUser(id);
    db.close();
    return user;
  }

  public boolean editUser(int id, String data) throws SQLException {
    DBConnector db = new DBConnector();
    boolean editUser = db.editUser(id, data);
    db.close();
    return editUser;
  }

  public boolean deleteUser(int id) throws SQLException {
    DBConnector db = new DBConnector();
    boolean deleteUser = db.deleteUser(id);
    db.close();
    return deleteUser;
  }

  public boolean addUser(String data) throws Exception {
    User u = gson.fromJson(data, User.class);
    String hashedPassword = Digester.hashWithSalt(u.getPassword());
    u.setPassword(hashedPassword);
    return db.addUser(u);
  }

}
