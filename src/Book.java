import java.time.LocalDate;

/**
 * Book.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This class represents a book in the Library Management System.
 * It holds details about a book, such as its ID, title, author, status, and due date.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String status; // "available", "checked out"
    private LocalDate dueDate; // Due date for the book

    // Constructor
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = "available"; // Initially, all books are available
        this.dueDate = null; // No due date initially
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    // toString method to display book details
    @Override
    public String toString() {
        return id + ", " + title + ", " + author + ", Status: " + status +
                (dueDate != null ? ", Due Date: " + dueDate : "");
    }
}
