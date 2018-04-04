import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener
{
    public GameWindow() {

        setSize(600,600);
        setTitle("Nowa gra");
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton bPauza = new JButton("PAUZA");
        add(bPauza);
        bPauza.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Okno gry");
        JOptionPane.showMessageDialog(null, "Gra wstrzymana", "Pauza", JOptionPane.PLAIN_MESSAGE);
    }

}