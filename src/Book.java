import java.time.LocalDate;

/**
 * Book.java
 * Name: [Your Name]
 * Course: [CEN-3024C]
 * Date: [Today's Date]
 *
 * This class represents a book in the Library Management System,
 * with properties like ID, title, author, status, and due date.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String status; // "checked in" or "checked out"
    private LocalDate dueDate;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = "checked in";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Status: " + status + (dueDate != null ? ", Due Date: " + dueDate : "");
    }
}
