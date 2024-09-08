/**
 * Book.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This class represents a book in the Library Management System.
 * It holds details about a book, such as its ID, title, and author.
 */
public class Book {
    private int id;
    private String title;
    private String author;

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    // toString method to display book details
    @Override
    public String toString() {
        return id + ", " + title + ", " + author;
    }
}
