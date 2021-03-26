import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class App {
    
    /** Main function of program
     * @param args
     */
    public static void main(String[] args) {

        try {
            BoardModelManager boardManager = BoardModelManager.getInstance();
            ServerSocket ss = new ServerSocket(4441);
            System.out.println("Listening on the port 4441");
            ArrayList<Player> players = new ArrayList<Player>();

            while (true) {
                Socket socket = ss.accept();
                Thread socketThread = new Thread(new ChessServer(socket, players.size()));
                socketThread.start();
                Player player = null;
                if ((players.size() % 2) == 0) {
                	player = new Player(Color.White);
                } else {
                	player = new Player(Color.Black);
                } 
                players.add(player);
                boardManager.addPlayer(player);
            }    
        } catch (IOException e) {
            System.out.println("Fatal error: " + e.toString());
            System.exit(0);
        }
    }
}
