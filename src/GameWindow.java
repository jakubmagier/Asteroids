import javax.swing.*;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener
{
    public GameWindow() {
        setSize(600,600);
        setTitle("Nowa gra");
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int znak=e.getKeyCode();
        if(znak==KeyEvent.VK_P){
            int option=JOptionPane.showConfirmDialog(null, "Czy chcesz opuścić grę?", "Pauza", JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION)
                dispose();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

}