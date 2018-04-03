import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener
{
    public GameWindow() {

        setSize(600,600);
        setTitle("Nowa gra");
        setLayout(null);
        JButton bStart = new JButton("START");
        bStart.setBounds(100,100,100,50);
        add(bStart);
        bStart.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Okno gry");
    }
}