import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Okno gry, obsługuje jej uruchamianie, wczytywanie danych z pliku init oraz pauzę
 */
public class GameWindow extends JFrame implements KeyListener {
    private GridBagConstraints gc = new GridBagConstraints();
    private GameApp game;

    public GameWindow() {
        setSize(1024, 800);
        setLayout(new GridBagLayout());
        addKeyListener(this);
        InitData init = FileParser.parseInit(1);

        game = new GameApp(init);
        game.setPreferredSize(new Dimension(900, 700));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 4;

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.WEST;
        add(game, gc);
    }

    /**
     * @see KeyListener
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * obsługa pauzy
     *
     * @see KeyListener
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_P) {
            game.stop();
            int option = JOptionPane.showConfirmDialog(null, "Czy chcesz opuścić grę?", "Pauza", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                StopWindow stopWindow = new StopWindow();
                stopWindow.setLocationRelativeTo(null);
                stopWindow.setUndecorated(true);
                stopWindow.setResizable(false);
                stopWindow.setVisible(true);
                dispose();
            }
            else
                game.start();
        }
        game.keyPressed(e);
        if (key == KeyEvent.VK_E)
            dispose();
     }

    /**
     * @see KeyListener
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}