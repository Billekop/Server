package database;

import com.google.gson.Gson;
import config.Config;
import model.Book;
import model.Curriculum;
import model.User;

import java.sql.*;
import java.util.ArrayList;



/**
 * Created by mortenlaursen on 17/10/2016.
 */
public class DBConnector {
    /**
     * Constructor for establishing connection.
     *
     * @throws Exception
     */
    // JDBC driver name and database URL
            //useSSL=false, grundet error
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + Config.getDbUrl() + ":" + Config.getDbPort() + "/" + Config.getDbName()+"?verifyServerCertificate=false&useSSL=true";

    //  Database credentials
    static final String USER = Config.getDbUserName();
    static final String PASS = Config.getDbPassword();

    //
    Connection conn = null;
    Statement stmt = null;

    public DBConnector() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER).newInstance();

            //STEP 3: Open a connection
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            this.stmt = conn.createStatement();

            System.out.println("Connected");

            //STEP 6: Clean-up environment
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*5 user methods*/

    public ArrayList getUsers() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getUsers = conn.prepareStatement("SELECT * FROM Users ");
            resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                try {

                    User users = new User(
                            resultSet.getInt("UserID"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("Username"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password"),
                            resultSet.getBoolean("Usertype")
                    );

                    results.add(users);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    public User getUser(int id) throws IllegalArgumentException {
        User user = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getUser = conn.prepareStatement("SELECT * FROM Users WHERE UserID=?");
            getUser.setInt(1, id);
            resultSet = getUser.executeQuery();


            while (resultSet.next()) {
                try {

                    user = new User(
                            resultSet.getInt("UserID"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("Username"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password"),
                            resultSet.getBoolean("Usertype")
                    );

                } catch (Exception e) {

                }
            }


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return user;
    }
//måske ny?
    public boolean editUser(int id, String data) throws SQLException {
        User u = new Gson().fromJson(data,User.class);
        PreparedStatement editUserStatement = conn
                .prepareStatement("UPDATE Users SET First_Name = ?, Last_Name = ?, Username = ?, Email = ?, Usertype = ? WHERE userID =?");

        try {
            editUserStatement.setString(1, u.getFirstName());
            editUserStatement.setString(2, u.getLastName());
            editUserStatement.setString(3, u.getUsername());
            editUserStatement.setString(4, u.getEmail());
            editUserStatement.setBoolean(5, u.getUserType());
            editUserStatement.setInt(6, id);

            editUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addUser(User u) throws Exception {

        PreparedStatement addUserStatement =
                conn.prepareStatement("INSERT INTO Users (First_Name, Last_Name, Username, Email, Password, Usertype) VALUES (?, ?, ?, ?, ?, ?)");

        try {
            addUserStatement.setString(1, u.getFirstName());
            addUserStatement.setString(2, u.getLastName());
            addUserStatement.setString(3, u.getUsername());
            addUserStatement.setString(4, u.getEmail());
            addUserStatement.setString(5, u.getPassword());
            addUserStatement.setBoolean(6, u.getUserType());

            addUserStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteUser(int id) throws SQLException {

        PreparedStatement deleteUserStatement = conn.prepareStatement("DELETE FROM Users WHERE UserID=?"); //conn.prepareStatement("UPDATE Users SET Deleted = 1 WHERE UserID=?");
        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    /*Curriculum methods*/
    public ArrayList getCurriculums() throws IllegalArgumentException {
        ArrayList<Curriculum> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            PreparedStatement getCurriculums = conn.prepareStatement("SELECT * FROM Curriculum ");
            resultSet = getCurriculums.executeQuery();

            while (resultSet.next()) {
                try {

                    Curriculum cul = new Curriculum(
                            resultSet.getInt("CurriculumID"),
                            resultSet.getString("School"),
                            resultSet.getString("Education"),
                            resultSet.getInt("Semester")
                    );

                    results.add(cul);

                    String test = resultSet.getString("School");
                } catch (Exception e) {
                    Integer test = resultSet.getInt("CurriculumID");

                    System.out.println(test + "WORKS");
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    public Curriculum getCurriculum(int curriculumID) throws IllegalArgumentException {
        ResultSet resultSet = null;
        Curriculum curriculum = null;

        try {
            PreparedStatement getCurriculum = conn.prepareStatement("SELECT * FROM Curriculum WHERE CurriculumID=?");
            getCurriculum.setInt(1, curriculumID);
            resultSet = getCurriculum.executeQuery();

            while (resultSet.next()) {
                try {

                    curriculum = new Curriculum(
                            resultSet.getInt("CurriculumID"),
                            resultSet.getString("School"),
                            resultSet.getString("Education"),
                            resultSet.getInt("Semester")
                    );

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {

            System.out.println(sqlException.getMessage());
        }
        return curriculum;

    }

    public boolean editCurriculum(int id, String data) throws SQLException {
        PreparedStatement editCurriculumStatement = conn.prepareStatement("UPDATE Curriculum SET School = ?, Education = ?, Semester = ? WHERE curriculumID = ?");

        Curriculum c = new Gson().fromJson(data,Curriculum.class);

        try {
            editCurriculumStatement.setString(1, c.getSchool());
            editCurriculumStatement.setString(2, c.getEducation());
            editCurriculumStatement.setInt(3, c.getSemester());
            editCurriculumStatement.setInt(4, id);

            editCurriculumStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addCurriculum(Curriculum c) throws SQLException {
        PreparedStatement addCurriculumStatement = conn.prepareStatement("INSERT INTO Curriculum (School, Education, Semester) VALUES (?, ?, ?)");

        try {

            addCurriculumStatement.setString(1, c.getSchool());
            addCurriculumStatement.setString(2, c.getEducation());
            addCurriculumStatement.setInt(3, c.getSemester());

            addCurriculumStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteCurriculum(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE Curriculum SET Deleted = 1 WHERE CurriculumID=?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    //skal skiftes
    public ArrayList<Book> getCurriculumBooks(int curriculumID) {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;


        try {
            PreparedStatement getCurriculumBooks = conn.prepareStatement("SELECT * FROM Books INNER JOIN BooksCurriculum ON Books.BookID=BooksCurriculum.BookID WHERE CurriculumID = ? ");
            getCurriculumBooks.setInt(1, curriculumID);
            resultSet = getCurriculumBooks.executeQuery();

            while ( resultSet.next() ) {
                try {

                    Book books = new Book(
                            resultSet.getInt("BookID"),
                            resultSet.getString("Publisher"),
                            resultSet.getString("Title"),
                            resultSet.getString("Author"),
                            resultSet.getInt("Version"),
                            resultSet.getDouble("ISBN"),
                            resultSet.getDouble("PriceAB"),
                            resultSet.getDouble("PriceSAXO"),
                            resultSet.getDouble("PriceCDON")
                    );

                    results.add(books);

                }catch(Exception e){

                }
            }
        }
        catch ( SQLException sqlException ){
            System.out.println(sqlException.getMessage());
        }
        return results;
    }

    /*books methods*/

    public ArrayList getBooks() throws IllegalArgumentException {
        ArrayList results = new ArrayList();
        ResultSet resultSet = null;

        try {
            PreparedStatement getBooks = conn.prepareStatement("SELECT * FROM Books WHERE Deleted = 0 ");
            resultSet = getBooks.executeQuery();

            while (resultSet.next()) {
                try {

                    Book books = new Book(
                            resultSet.getInt("BookID"),
                            resultSet.getString("Publisher"),
                            resultSet.getString("Title"),
                            resultSet.getString("Author"),
                            resultSet.getInt("Version"),
                            resultSet.getDouble("ISBN"),
                            resultSet.getDouble("PriceAB"),
                            resultSet.getDouble("PriceSAXO"),
                            resultSet.getDouble("PriceCDON")
                    );

                    results.add(books);

                } catch (Exception e) {

                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return results;

    }

    public Book getBook(int id) throws IllegalArgumentException {
        Book book = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement getBook = conn.prepareStatement("SELECT * FROM Books WHERE BookID=? ");
            getBook.setInt(1, id);
            resultSet = getBook.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("BookID"),
                    resultSet.getString("Publisher"),
                    resultSet.getString("Title"),
                    resultSet.getString("Author"),
                    resultSet.getInt("Version"),
                    resultSet.getDouble("ISBN"),
                    resultSet.getDouble("PriceAB"),
                    resultSet.getDouble("PriceSAXO"),
                    resultSet.getDouble("PriceCDON")
            );
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return book;

    }

    public boolean editBook(int id, String data) throws SQLException {
        PreparedStatement editBookStatement = conn.prepareStatement("UPDATE Books SET Title = ?, Version = ?, ISBN = ?, PriceAB = ?, PriceSAXO = ?, PriceCDON = ?, Publisher = ?, Author = ? WHERE bookID =?");
        Book b = new Gson().fromJson(data, Book.class);
        try {
            editBookStatement.setString(1, b.getTitle());
            editBookStatement.setInt(2, b.getVersion());
            editBookStatement.setDouble(3, b.getISBN());
            editBookStatement.setDouble(4, b.getPriceAB());
            editBookStatement.setDouble(5, b.getPriceSAXO());
            editBookStatement.setDouble(6, b.getPriceCDON());
            editBookStatement.setString(7, b.getPublisher());
            editBookStatement.setString(8, b.getAuthor());
            editBookStatement.setInt(9, id);

            editBookStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean addCurriculumBook(int curriculumID, String data) throws SQLException {
        int id;
        PreparedStatement addBookStatement = conn.prepareStatement("INSERT INTO Books (Title, Version, ISBN, PriceAB, PriceSAXO, PriceCDON, Publisher, Author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        Book b = new Gson().fromJson(data, Book.class);
        try {
            addBookStatement.setString(1, b.getTitle());
            addBookStatement.setInt(2, b.getVersion());
            addBookStatement.setDouble(3, b.getISBN());
            addBookStatement.setDouble(4, b.getPriceAB());
            addBookStatement.setDouble(5, b.getPriceSAXO());
            addBookStatement.setDouble(6, b.getPriceCDON());
            addBookStatement.setString(7, b.getPublisher());
            addBookStatement.setString(8, b.getAuthor());

            addBookStatement.executeUpdate();
            ResultSet rs = addBookStatement.getGeneratedKeys();

            if(rs.next()){
                id = rs.getInt(1);

                PreparedStatement addToBooksCurriculum = conn.prepareStatement("INSERT INTO BooksCurriculum (BookID, CurriculumID) VALUES (?,?)");
                addToBooksCurriculum.setInt(1, id);
                addToBooksCurriculum.setInt(2, curriculumID);
                addToBooksCurriculum.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }



    public boolean deleteBook(int id) throws SQLException {
        PreparedStatement deleteUserStatement = conn.prepareStatement("UPDATE Books SET Deleted = 1 WHERE BookID = ?");
        PreparedStatement deleteBooksCurriculumRows = conn.prepareStatement("DELETE FROM BooksCurriculum WHERE BookID = ?");

        try {
            deleteUserStatement.setInt(1, id);
            deleteUserStatement.executeUpdate();

            deleteBooksCurriculumRows.setInt(1, id);
            deleteBooksCurriculumRows.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User authenticate(String username, String password) {

        ResultSet resultSet = null;
        User userFound = null;

        try {
            PreparedStatement authenticate = conn.prepareStatement("select * from Users where username = ? AND Password = ?");
            authenticate.setString(1, username);
            authenticate.setString(2, password);


            resultSet = authenticate.executeQuery();

            while (resultSet.next()) {
                try {
                    userFound = new User();
                    userFound.setUserID(resultSet.getInt("UserID"));

                } catch (SQLException e) {

                }


            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return userFound;

    }

    public User getUserFromToken(String token) throws SQLException {
        ResultSet resultSet = null;
        User userFromToken = null;

//http://www.w3schools.com/sql/sql_join_inner.asp
        //Inner JOIN vælger alle rows fra tables så længe der er match mellem kolonerne i de forskellige tables.

        int userid=-1;
        boolean usertype=false;
        String firstname="",lastname="",username="",email="",password="";
        try {

            PreparedStatement getUserFromToken = conn
                    .prepareStatement("select Tokens.user_id, Users.Usertype, Users.first_name, Users.last_name, Users.username, Users.email, Users.password from Tokens inner join Users on Tokens.user_id = Users.UserID where Tokens.token = ?");
            getUserFromToken.setString(1, token);
            //executer query i Databasen (svarer til refresh-og-kør)
            resultSet = getUserFromToken.executeQuery();
//http://www.journaldev.com/2489/jdbc-statement-vs-preparedstatement-sql-injection-example
            // modificeret fra den forrige metode, som blev lavet til hackathon. I den forrige metode var der kun user_id og usertype.
            while (resultSet.next()) {

                userFromToken = new User();
                userid 		= resultSet.getInt("user_id");
                usertype 	= resultSet.getBoolean("Usertype");
                firstname 	= resultSet.getString("first_name");
                lastname 	= resultSet.getString("last_name");
                username 	= resultSet.getString("Username");
                email 		= resultSet.getString("email");
                password 	= resultSet.getString("password");

                firstname 	= firstname==null ? "":firstname;
                lastname 	= lastname==null ? "":lastname;
                username	= username==null ? "":username;
                email 		= email==null ? "":email;
                password 	= password==null ? "":password;

                userFromToken.setUserID(userid);
                userFromToken.setUserType(usertype);
                userFromToken.setFirstName(firstname);
                userFromToken.setLastName(lastname);
                userFromToken.setUsername(username);
                userFromToken.setEmail(email);
                userFromToken.setPassword(password);

            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return userFromToken;

    }




      //Prepared statement er flyttet fra try catch. Sammen måde som DeleteToken metoden.
    public void addToken(String token, int userId) throws SQLException {
        System.out.println("========"+token+"---"+userId);
        PreparedStatement addTokenStatement = conn.prepareStatement("INSERT INTO Tokens (token, user_id) VALUES (?,?)");
        try {

            addTokenStatement.setString(1, token);
            addTokenStatement.setInt(2, userId);
            addTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteToken(String token) throws SQLException {

        PreparedStatement deleteTokenStatement = conn.prepareStatement("DELETE FROM Tokens WHERE token=?");

        try {
            deleteTokenStatement.setString(1, token);
            deleteTokenStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void close(){
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}