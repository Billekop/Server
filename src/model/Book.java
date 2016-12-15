package model;

/**
 * Variabler til Book oprettes samt deres getters/setters.
 */

public class Book {

    private int bookID;
    private double ISBN;
    private String publisher;
    private String title;
    private String author;
    private double priceAB;
    private double priceSAXO;
    private double priceCDON;
    private int version;

    public Book(int bookID, String publisher, String title, String author, int version, double ISBN, double priceAB, double priceSAXO, double priceCDON) {
        this.bookID = bookID;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.priceAB = priceAB;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;
    }

    public Book(String publisher, String title, String author, int version, double ISBN, double priceAB, double priceSAXO, double priceCDON) {
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.version = version;
        this.ISBN = ISBN;
        this.priceAB = priceAB;
        this.priceSAXO = priceSAXO;
        this.priceCDON = priceCDON;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public double getISBN() {
        return ISBN;
    }

    public void setISBN(double ISBN) {
        this.ISBN = ISBN;
    }

    public double getPriceAB() {
        return priceAB;
    }

    public void setPriceAB(double priceAB) {
        this.priceAB = priceAB;
    }

    public double getPriceSAXO() {
        return priceSAXO;
    }

    public void setPriceSAXO(double priceSAXO) {
        this.priceSAXO = priceSAXO;
    }

    public double getPriceCDON() {
        return priceCDON;
    }

    public void setPriceCDON(double priceCDON) {
        this.priceCDON = priceCDON;
    }

    //    @Override
//    public String toString() {
//        return "model.Book{" +
//                "name='" + name + '\'' +
//                ", publisher='" + publisher + '\'' +
//                ", ISBN='" + ISBN + '\'' +
//                '}';
//    }
}
