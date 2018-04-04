import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ListWindow extends JFrame
{
    JTextArea ltext;
    public ListWindow() {

        setSize(300,500);
        setTitle("Lista wyników");
        setLayout(new GridLayout(1,1));
        ltext=new JTextArea("1.Zasasy gry:");
        ltext.setEditable(false);
        String filename = "lista.txt";
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader br = new BufferedReader(reader);
            ltext.read(br, null);
            br.close();
            ltext.requestFocus();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

        add(ltext);
    }

}