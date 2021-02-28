package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FileServer implements Runnable {
    private final Socket clientSocket;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final int players;
    private model.Color color;

    public FileServer(final Socket socket, final int players) throws IOException {
        clientSocket = socket;
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.players = players;
    }

    @Override
    public void run() {
        try {
            Command cmd = (Command) objectInputStream.readObject();
            if (cmd.getContext()) {
                dataOutputStream.writeUTF(BoardModelManager.getInstance().toJSon().toString());
            }
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
                    dataOutputStream.writeUTF(BoardModelManager.getInstance().toJSon().toString());
                    // dataOutputStream.writeUTF(boardModelManager.toJSon().toString());
                    continue;
                }
                System.out.println("Move from: " + cmd.getFrom() + " to: " + cmd.getTo());
                dataOutputStream.writeUTF(BoardModelManager.getInstance().moveFigure(cmd.getFrom(), cmd.getTo(), color));
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