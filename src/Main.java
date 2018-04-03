import javax.swing.JFrame;
import javax.swing.JButton;

public class Main {
    public static void main(String args[]) {
        Window mainWindow = new Window();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }
}