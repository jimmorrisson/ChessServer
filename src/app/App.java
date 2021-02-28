package app;

import java.net.ServerSocket;
import java.net.Socket;

import model.BoardModelManager;
import model.FileServer;

public class App {
    public static void main(String[] args) throws Exception {
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
    }
}