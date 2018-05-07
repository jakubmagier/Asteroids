import javax.swing.JFrame;

/**
 * klasa główna programu, uruchamia okno menu
 */
public class Main {
    public static void main(String args[]) {
        Window mainWindow = new Window();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setUndecorated (true);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }
}