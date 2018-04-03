import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpWindow extends JFrame implements ActionListener
{
    JButton bZasady,bObsluga,bSterowanie;
    JLabel lPomoc;
    public HelpWindow() {

        setSize(500,500);
        setTitle("Pomoc");
        setLayout(null);
        bZasady = new JButton("Zasady gry");
        bZasady.setBounds(50,50,100,30);
        bObsluga= new JButton("Obsługa menu");
        bObsluga.setBounds(50,100,100,30);
        bSterowanie= new JButton("Sterowanie");
        bSterowanie.setBounds(50,150,100,30);

        add(bZasady);
        add(bObsluga);
        add(bSterowanie);
        bZasady.addActionListener(this);
        bObsluga.addActionListener(this);
        bSterowanie.addActionListener(this);
        lPomoc = new JLabel();
        lPomoc.setBounds(50,200,100,100);
        add(lPomoc);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source==bZasady) {
            lPomoc.setText("Zasady gry w Asteroids:");
        }else if (source==bObsluga) {
            lPomoc.setText("Obsługa menu mojej gry:");
        }else if (source==bSterowanie) {
            lPomoc.setText("Sterowanie w grze");
        }
    }
}