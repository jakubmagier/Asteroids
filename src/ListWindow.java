import javax.swing.*;

public class ListWindow extends JFrame
{
    JLabel lListaWynikow;
    public ListWindow() {

        setSize(500,500);
        setTitle("Lista wyników");
        setLayout(null);
        lListaWynikow = new JLabel("Lista wyników:");
        lListaWynikow.setBounds(50,50,100,30);
        add(lListaWynikow);
    }

}