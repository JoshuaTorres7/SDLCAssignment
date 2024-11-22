import java.time.LocalDate;

/**
 * Book.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This class represents a book in the Library Management System.
 * It encapsulates details about a book, such as its ID, title, author, status, and due date.
 */
public class Book {
    // Properties of the book
    private int id; // Unique identifier for the book
    private String title; // Title of the book
    private String author; // Author of the book
    private String status; // Status of the book: "checked in" or "checked out"
    private LocalDate dueDate; // Due date for returning the book (if checked out)

    /**
     * Constructor to initialize a book with its ID, title, and author.
     * By default, the book is set to "checked in" and has no due date.
     *
     * @param id     Unique identifier for the book
     * @param title  Title of the book
     * @param author Author of the book
     */
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = "checked in"; // Initial status is "checked in"
        this.dueDate = null; // No due date initially
    }

    // Getters: Provide access to the book's properties
    public int getId() { return id; } // Returns the book's ID
    public String getTitle() { return title; } // Returns the book's title
    public String getAuthor() { return author; } // Returns the book's author
    public String getStatus() { return status; } // Returns the book's current status
    public LocalDate getDueDate() { return dueDate; } // Returns the book's due date

    // Setters: Allow modification of the book's properties
    public void setStatus(String status) { this.status = status; } // Updates the book's status
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; } // Sets a new due date for the book

    /**
     * toString method to provide a string representation of the book.
     * Includes the ID, title, author, status, and due date (if applicable).
     *
     * @return A formatted string representing the book's details
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Status: " + status +
                (dueDate != null ? ", Due Date: " + dueDate : ""); // Adds due date only if not null
    }
}
