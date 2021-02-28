package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.BoardModelManager;
import model.FileServer;

public class App {
    public static void main(String[] args) {

        try {
            BoardModelManager boardManager = BoardModelManager.getInstance();
            boardManager.initializeBoard();
            ServerSocket ss = new ServerSocket(4441);
            System.out.println("Listening on the port 4441");
            int players = 0;
            while (true) {
                Socket socket = ss.accept();
                Thread socketThread = new Thread(new FileServer(socket, players));
                socketThread.start();
                players += 1;
            }    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}