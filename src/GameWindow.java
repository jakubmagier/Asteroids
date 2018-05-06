import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener {
    private GridBagConstraints gc = new GridBagConstraints();
    private GameApp game;
    public GameWindow() {
        setSize(1024,800);
        setLayout(new GridBagLayout());
        addKeyListener(this);
        InitData init = FileParser.parseInit(1);

        game=new GameApp(init);
        game.setPreferredSize(new Dimension(900, 700));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 4;

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.WEST;
        add(game, gc);
        JLabel score = new JLabel("Wynik: 0");
        JLabel time = new JLabel("Czas: 0:50");
        JLabel lives = new JLabel("Zycia: 2");

        gc.gridheight = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(score, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(time, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(lives, gc);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_P){
            game.stop();
            int option=JOptionPane.showConfirmDialog(null, "Czy chcesz opuścić grę?", "Pauza", JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION)
                dispose();
            else
                game.start();
        }
        game.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}