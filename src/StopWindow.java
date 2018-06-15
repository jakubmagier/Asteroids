import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Okno pauzy, wyświetla informacje o liczbie punktow i zachowanych szansach
 */
public class StopWindow extends JFrame implements KeyListener {
    private GridBagConstraints gc = new GridBagConstraints();

    public StopWindow() {
        setSize(350, 200);
        setLayout(new GridBagLayout());
        int live = GameApp.getLives();
        int point = GameApp.getPoints();
        String name = Player.getName();

        JLabel nam = new JLabel("Gracz: "+name);
        JLabel score = new JLabel("Wynik: " + point);
        JLabel lives = new JLabel("Zycia: " + live);
        JLabel info = new JLabel("Wciśnij dwukrotnie 'q' aby zamknąć okno");

        gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(nam, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(score, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(lives, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(info, gc);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_Q) {
                dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
