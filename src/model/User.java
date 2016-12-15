package model;

/**
 * Variabler til User oprettes samt deres getters/setters.
 */

public class User {
    int userID;
    String firstName, lastName, userName, email, password;
    Boolean userType;

    public User(){

    }

   /* public User(int userID){
        this.userID = userID;

    }*/
    public User(String firstName, String lastName, String userName, String email, String password, Boolean userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public User(int userID, String firstName, String lastName, String userName, String email, String password, Boolean userType) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean admin) {
        userType = admin;
    }

    /*
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + firstName + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}


