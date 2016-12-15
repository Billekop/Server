package controllers;

import com.google.gson.Gson;
import database.DBConnector;
import model.Book;
import model.Curriculum;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Opretter en instans af DBConnector og kalder alle metoder til Curriculum.
 * Klassen modtager datasættet fra DBConnector som videresendes til CurriculumEndpoint.
 * Hver metode er forklaret med kommentarer i DBConnector.
 */
public class CurriculumController {

    public ArrayList<Curriculum> getCurriculums()throws IllegalAccessException{
        DBConnector db = new DBConnector();
        ArrayList<Curriculum> curriculums = db.getCurriculums();
        db.close();
        return curriculums;
    }

    public Curriculum getCurriculum(int id) {
        DBConnector db = new DBConnector();
        Curriculum curriculum = db.getCurriculum(id);
        db.close();
        return curriculum;
    }

    public boolean editCurriculum(int id, String data) throws SQLException {
        DBConnector db = new DBConnector();
        boolean editCurriculum = db.editCurriculum(id, data);
        db.close();
        return editCurriculum;
    }

    public boolean deleteCurriculum(int id) throws SQLException {
        DBConnector db = new DBConnector();
        boolean deleteCurriculum =  db.deleteCurriculum(id);
        db.close();
        return deleteCurriculum;
    }

    public boolean addCurriculum(String data) throws SQLException {
        DBConnector db = new DBConnector();
        Curriculum c = new Gson().fromJson(data,Curriculum.class);
        boolean addCurriculum = db.addCurriculum(c);
        db.close();
        return addCurriculum;
    }
    // Stavefejl - Rettet fra curricilum til curriculum
    public ArrayList<Book> getCurriculumBooks(int curriculumID) {
        DBConnector db = new DBConnector();
        ArrayList<Book> curriculumBooks = db.getCurriculumBooks(curriculumID);
        db.close();
        return curriculumBooks;

    }

    public boolean addCurriculumBook(int curriculumID, String data) throws SQLException {
        DBConnector db = new DBConnector();
        boolean addCurriculumBook = db.addCurriculumBook(curriculumID, data);
        db.close();
        return addCurriculumBook;
    }
}
