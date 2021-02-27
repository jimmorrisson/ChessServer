package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FileServer implements Runnable {
    private final Socket clientSocket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private ObjectInputStream objectInputStream;
    private final int players;
    private BoardModelManager boardModelManager;
    private model.Color color;

    public FileServer(final Socket socket, final int players, BoardModelManager boardModelManager) throws IOException {
        clientSocket = socket;
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.players = players;
        this.boardModelManager = boardModelManager;
    }

    @Override
    public void run() {
        try {
            System.out.print(dataInputStream.readUTF());
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            if (players % 2 == 0) {
                color = model.Color.White;
                dataOutputStream.writeUTF("Player white");
            } else {
                color = model.Color.Black;
                dataOutputStream.writeUTF("Player black");
            }
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (true) {
            try {
                Command cmd = (Command) objectInputStream.readObject();
                if (cmd.getContext()) {
                    dataOutputStream.writeUTF(boardModelManager.toJSon().toString());
                    continue;
                }
                System.out.println("Move from: " + cmd.getFrom() + " to: " + cmd.getTo());
                dataOutputStream.writeUTF(boardModelManager.moveFigure(cmd.getFrom(), cmd.getTo(), color));
                // System.out.print(dataInputStream.readUTF());
                
                // dataOutputStream.writeUTF("Yes");
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}