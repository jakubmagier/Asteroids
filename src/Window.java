import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener
{
    JButton bNewGame,bListOfResults,bHelp,bExit;
    public Window() {

        setSize(400,500);
        setTitle("Asteroids");
        setLayout(null);
        bNewGame = new JButton("Nowa gra");
        bNewGame.setBounds(100,100,200,50);
        add(bNewGame);
        bNewGame.addActionListener(this);

        bListOfResults = new JButton("Lista najlepszych wyników");
        bListOfResults.setBounds(100,200,200,50);
        add(bListOfResults);
        bListOfResults.addActionListener(this);

        bHelp = new JButton("Pomoc");
        bHelp.setBounds(100,300,200,50);
        add(bHelp);
        bHelp.addActionListener(this);

        bExit = new JButton("Wyjście");
        bExit.setBounds(100,400,200,50);
        add(bExit);
        bExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source==bNewGame) {
            GameWindow gameWindow = new GameWindow();
            gameWindow.setVisible(true);
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