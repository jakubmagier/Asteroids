import javax.swing.JFrame;

public class Main {
    public static void main(String args[]) {
        Window mainWindow = new Window();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }
}