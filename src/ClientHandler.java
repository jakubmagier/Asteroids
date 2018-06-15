import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler {
    private String hostname;
    private int port;

    public ClientHandler() {
        this("localhost", 12345);
    }

    ClientHandler(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }


    public InitData getInit() {
        String[] dataIn;
        try
        {
            //create client socket, connect to server
            Socket clientSocket = new Socket("localhost",12345);
            //create output stream attached to socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //create input stream attached to socket
            DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

            //send line to server
            //outToServer.writeBytes(temp);
            outToServer.writeUTF("init");
            outToServer.flush();


            dataIn = inFromServer.readUTF().split(" ");
            clientSocket.close();
            return new InitData(Integer.parseInt(dataIn[0]),
                    Integer.parseInt(dataIn[1]),
                    Integer.parseInt(dataIn[2]),
                    Integer.parseInt(dataIn[3]));
        }
        catch(Exception ex)
        {
            System.out.println("blad ClientHandlera");
        }

        return new InitData();
    }

    public InitData getLevelInfo() {
        return new InitData();
    }

    public int gameover(String username, int score) {
        return 0;
    }

    public void highscores() {

    }



}
