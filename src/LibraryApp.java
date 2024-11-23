/**
 * LibraryApp.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [11/3/24]
 *
 * This is the main entry point for the Library Management System application.
 * It initializes and displays the GUI, providing users with an interface to interact with the system.
 */
public class LibraryApp {
    /**
     * The main method launches the Library Management System GUI.
     *
     * @param args Optional command-line arguments (currently unused)
     */
    public static void main(String[] args) {
        // Safely initialize and display the GUI on the Event Dispatch Thread
        java.awt.EventQueue.invokeLater(() -> {
            try {
                // Create an instance of the Library GUI form and make it visible
                LibraryGUIForm gui = new LibraryGUIForm();
                gui.setVisible(true);
            } catch (Exception e) {
                // Log or handle potential errors during GUI initialization
                System.err.println("Failed to initialize the Library GUI: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
