import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Okno gry, obsługuje jej uruchamianie, wczytywanie danych z pliku init oraz pauzę
 */
public class GameWindow extends JFrame implements KeyListener, ComponentListener {
    private GridBagConstraints gc = new GridBagConstraints();
    private GameApp game;
    private int height=800,width=1024;

    public GameWindow() {
        setSize(width, height);
        setLayout(new GridBagLayout());
        addKeyListener(this);
        addComponentListener(this);

        game = new GameApp();
        game.setPreferredSize(new Dimension (width-50, height-50));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 1;
        gc.gridwidth = 1;

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        add(game, gc);
    }

    public void componentHidden(ComponentEvent ce) {};
    public void componentShown(ComponentEvent ce) {};
    public void componentMoved(ComponentEvent ce) { };
    public void componentResized(ComponentEvent ce) {
        height = this.getHeight();
        width = this.getWidth();
        setSize(width,height);
        game.setPreferredSize(new Dimension (width-50, height-50));
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
        if (key == KeyEvent.VK_Q){
                dispose();
                game.stop();
        }
     }

    /**
     * @see KeyListener
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}