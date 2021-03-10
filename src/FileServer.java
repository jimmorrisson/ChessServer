

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FileServer implements Runnable {
    private final Socket clientSocket;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private static ObjectInputStream objectInputStream;
//    private static ObjectOutputStream objectOutputStream;
    private final int players;
    private Color color;

    public FileServer(final Socket socket, final int players) throws IOException {
        clientSocket = socket;
        FileServer.dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        FileServer.objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        FileServer.dataInputStream = new DataInputStream(clientSocket.getInputStream());
//        FileServer.objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        this.players = players;
    }
    
//    public void setDataOutputStream(DataOutputStream dataOutputStream) {
//    	FileServer.dataOutputStream = dataOutputStream;
//    }    
//    
//    public void setDataInputStream(DataInputStream dataInputStream) {
//    	FileServer.dataInputStream = dataInputStream;
//    }
//    
//    public void setObjectInputStream(ObjectInputStream objectInputStream) {
//    	FileServer.objectInputStream = objectInputStream;
//    }

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
                color = Color.White;
                dataOutputStream.writeUTF("Player white");
            } else {
                color = Color.Black;
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
                    continue;
                }
                System.out.println("Move from: " + cmd.getFrom() + " to: " + cmd.getTo());
                dataOutputStream.writeUTF(BoardModelManager.getInstance().moveFigure(cmd.getFrom(), cmd.getTo(), color));
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