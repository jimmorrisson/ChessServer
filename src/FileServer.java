
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chess.com.*;

public class FileServer implements Runnable {
    private final Socket clientSocket;
    private final int players;

    public FileServer(final Socket socket, final int players) throws IOException {
        clientSocket = socket;
        this.players = players;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
        try {
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Color color = ((players % 2) == 0) ? Color.White : Color.Black;
            while (true) {
                    Command command = (Command) objectInputStream.readObject();
                    if (command instanceof CommandGetPlayerColor) {
                        String playerName = ((players % 2) == 0) ? "Player white" : "Player black";
                        objectOutputStream.writeObject(new Response(command, playerName));
                        objectOutputStream.flush();
                    } else if (command instanceof CommandGetBoardContext) {
                        String boardContext = BoardModelManager.getInstance().toJSon().toString();
                        Response response = new Response((CommandGetBoardContext) command, boardContext);
                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                    } else if (command instanceof CommandSetPosition) {
                        System.out.println("Move from: " + command.getFrom() + " to: " + command.getTo());
                        Response response = new Response((CommandSetPosition) command,
                                BoardModelManager.getInstance().moveFigure(command.getFrom(), command.getTo(), color));
                        objectOutputStream.writeObject(response);
                        objectOutputStream.flush();
                    } else {
                        objectOutputStream.writeObject(new Response(command, null));
                        objectOutputStream.flush();
                    }
            }
            // objectInputStream.close();
            // dataInputStream.close();
            // objectOutputStream.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}