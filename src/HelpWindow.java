import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class HelpWindow extends JFrame
{
    private JTextArea ltext;
    public HelpWindow() {
        setSize(800, 500);
        setLayout(new GridLayout(2,1));
        ltext=new JTextArea("1.Zasasy gry:");
        ltext.setEditable(false);
        String filename = "zasady.txt";
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