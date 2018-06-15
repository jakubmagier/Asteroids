import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHandler {

    public static void main(String[] args) {
        // The name of the file to open.
        String fileName = "input.txt";
        // This will reference one line at a time
        String line = null;
        String holder=null;
        int bytNumber;

        try(ServerSocket welcomeSocket = new ServerSocket(12345)) {
            while(true) {
                System.out.println("czekam");

                //wait, on welcoming socket for contact by client
                Socket connectionSocket = welcomeSocket.accept();
                //create input stream, attached to socket
                DataInputStream inFromClient =
                        new DataInputStream(connectionSocket.getInputStream());
                //create output stream, attached to socket
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                //read in line from socket
                String[] clientWord = inFromClient.readUTF().split(" ");
                System.out.println(clientWord[0]);

                switch (clientWord[0]) {
                    case "init":
                        init(outToClient);
                        break;
                    case "level":
                        break;
                    case "gameover":
                        break;
                    case "highscores":
                        break;
                    default:
                        System.out.println("Syntax error");
                }

                /*if(clientWord.equals("query")) {
                    try {
                        // FileReader reads text files in the default encoding.
                        FileReader fileReader = new FileReader(fileName);

                        // Always wrap FileReader in BufferedReader.
                        BufferedReader buffer = new BufferedReader(fileReader);

                        while((line = buffer.readLine()) != null) {
                            System.out.println(line);
                            bytNumber = line.getBytes().length;
                            holder=Integer.toString(bytNumber);
                            //pr.println(bytNumber);//only printing first line not going until eof
                            outToClient.writeBytes(holder);
                            // line = buffer.readLine();
                            // pr.flush();
                        }

                        // Always close files.
                        buffer.close();
                    }
                    catch(FileNotFoundException ex) {
                        System.out.println("Unable to open file '" + fileName + "'");
                    }
                    catch(IOException ex) {
                        System.out.println ("Error reading file '" + fileName + "'");
                        // Or we could just do this:
                        // ex.printStackTrace();
                    }
            }*/

            }// end if statement
            //welcomeSocket.close();
        }
        catch(Exception ex) {

            System.out.println(ex.toString());
        }

    }

    private static void init(DataOutputStream out) {
        try {
                // FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader("server_init.txt");

                // Always wrap FileReader in BufferedReader.
                BufferedReader buffer = new BufferedReader(fileReader);

                String toClient = buffer.readLine();

                out.writeUTF(toClient);
                buffer.close();
            }
            catch (Exception ex) {
                System.out.println("błąd init()");
            }


            // Always close files.

        }

    private void level() {

    }

    private void gameover() {

    }

    private void highscores() {

    }
}