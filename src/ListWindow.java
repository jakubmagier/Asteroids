import javax.swing.*;
import java.awt.*;

/**
 * Okno z listą wyników, wczytuje dane z pliku lista
 */
public class ListWindow extends JFrame
{
    JTextArea ltext;
    public ListWindow() {
        setSize(300,500);
        setLayout(new GridLayout(2,1));
        ltext=new JTextArea(FileParser.parseList());
        ltext.setEditable(false);
        add(ltext);
    }
}