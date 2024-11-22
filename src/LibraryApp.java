public class LibraryApp {
    public static void main(String[] args) {
        // Create and show the GUI
        java.awt.EventQueue.invokeLater(() -> {
            LibraryGUIForm gui = new LibraryGUIForm();
            gui.setVisible(true);
        });
    }
}
