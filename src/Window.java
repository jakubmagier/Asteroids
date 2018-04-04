import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener
{   String name;
    JButton bNewGame,bListOfResults,bHelp,bExit;
    public Window() {

        setSize(400,500);
        setTitle("Asteroids");
        setLayout(new GridLayout(4,1,0,20));

        bNewGame = new JButton("Nowa gra");
        bNewGame.addActionListener(this);

        bListOfResults = new JButton("Lista najlepszych wyników");
        bListOfResults.addActionListener(this);

        bHelp = new JButton("Pomoc");
        bHelp.addActionListener(this);

        bExit = new JButton("Wyjście");
        bExit.addActionListener(this);

        Container container = getContentPane();
        container.add(bNewGame);
        container.add(bListOfResults);
        container.add(bHelp);
        container.add(bExit);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source==bNewGame) {
            GameWindow gameWindow = new GameWindow();
            name=JOptionPane.showInputDialog("Podaj imie:");
            if(name.isEmpty()){
                gameWindow.setVisible(false);
            }else{
                gameWindow.setVisible(true);
            }
            gameWindow.setLocationRelativeTo(null);
        }else if (source==bListOfResults) {
            ListWindow listWindow = new ListWindow();
            listWindow.setVisible(true);
            listWindow.setLocationRelativeTo(null);
        }else if (source==bHelp) {
            HelpWindow helpWindow = new HelpWindow();
            helpWindow.setVisible(true);
            helpWindow.setLocationRelativeTo(null);
        }else if (source==bExit) {
            dispose();
        }
    }
}