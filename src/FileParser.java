import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * parser plików gry
 * @see GameWindow
 * @see ListWindow
 */

public class FileParser {

    public static String parseList() {
        String out = "";
        try {
            File inputFile = new File("../lista.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            out += doc.getDocumentElement().getAttribute("title");
            out += "\n";
            NodeList nList = doc.getElementsByTagName("player");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    out += temp+1 + ". " +
                            eElement.getElementsByTagName("name").item(0).getTextContent() + " " +
                            eElement.getElementsByTagName("score").item(0).getTextContent() + "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    static InitData parseInit(int level) {
        InitData initData = new InitData();
        try {
            File inputFile = new File("../init.txt");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("level");

            Node nNode = nList.item(level);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                int lives = Integer.parseInt(eElement.getElementsByTagName("lives").item(0).getTextContent());
                int asteroids = Integer.parseInt(eElement.getElementsByTagName("asteroids").item(0).getTextContent());
                int hitScore = Integer.parseInt(eElement.getElementsByTagName("hitscore").item(0).getTextContent());
                int asteroidsSpeed = Integer.parseInt(eElement.getElementsByTagName("asteroidsspeed").item(0).getTextContent());
                int bullets = Integer.parseInt(eElement.getElementsByTagName("bullets").item(0).getTextContent());
                initData = new InitData(lives, asteroids, hitScore, asteroidsSpeed, bullets);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return initData;
    }
}
