import java.util.ArrayList;
import org.json.*;
import chess.com.*;

public class BoardModelManager implements Observer {
	private ArrayList<Figure> board = new ArrayList<>();
	private Player currentPlayer = null;
	private Figure currentChosenFigure = null;
	private static BoardModelManager instance = null;
	private ArrayList<Player> players = new ArrayList<>();

	private BoardModelManager() {
	}

	public static BoardModelManager getInstance() {
		if (instance == null) {
			instance = new BoardModelManager();
		}
		return instance;
	}

	public void initializeBoard() {
		board.add(new Pawn(new Position(0, 1), Color.White));
		board.add(new Rook(new Position(0, 0), Color.White));
		board.add(new Knight(new Position(1, 0), Color.White));
		board.add(new Pawn(new Position(1, 1), Color.White));
		board.add(new Pawn(new Position(2, 1), Color.White));
		board.add(new Bishop(new Position(2, 0), Color.White));
		board.add(new Queen(new Position(3, 0), Color.White));
		board.add(new Pawn(new Position(3, 1), Color.White));
		board.add(new King(new Position(4, 0), Color.White));
		board.add(new Pawn(new Position(4, 1), Color.White));
		board.add(new Bishop(new Position(5, 0), Color.White));
		board.add(new Pawn(new Position(5, 1), Color.White));
		board.add(new Knight(new Position(6, 0), Color.White));
		board.add(new Pawn(new Position(6, 1), Color.White));
		board.add(new Rook(new Position(7, 0), Color.White));
		board.add(new Pawn(new Position(7, 1), Color.White));

		board.add(new Pawn(new Position(0, 6), Color.Black));
		board.add(new Rook(new Position(0, 7), Color.Black));
		board.add(new Knight(new Position(1, 7), Color.Black));
		board.add(new Pawn(new Position(1, 6), Color.Black));
		board.add(new Pawn(new Position(2, 6), Color.Black));
		board.add(new Bishop(new Position(2, 7), Color.Black));
		board.add(new Queen(new Position(3, 7), Color.Black));
		board.add(new Pawn(new Position(3, 6), Color.Black));
		board.add(new King(new Position(4, 7), Color.Black));
		board.add(new Pawn(new Position(4, 6), Color.Black));
		board.add(new Bishop(new Position(5, 7), Color.Black));
		board.add(new Pawn(new Position(5, 6), Color.Black));
		board.add(new Knight(new Position(6, 7), Color.Black));
		board.add(new Pawn(new Position(6, 6), Color.Black));
		board.add(new Pawn(new Position(7, 6), Color.Black));
		board.add(new Rook(new Position(7, 7), Color.Black));
	}

	public ArrayList<Figure> getContext() {
		return board;
	}

	public void addPlayer(Player player) {
		if (players.size() >= 2) {
			System.out.println("Can't add more than 2 players!");
			return;
		}
		if (currentPlayer == null) {
			currentPlayer = player;
		}
		players.add(player);
	}

	private void changePlayer() {
		for (Player player : players) {
			if (currentPlayer.getColor().equals(Color.White) && player.getColor().equals(Color.Black)) {
				currentPlayer = player;
				break;
			} else if (currentPlayer.getColor().equals(Color.Black) && player.getColor().equals(Color.White)) {
				currentPlayer = player;
				break;
			}
		}
	}

	public Figure findFigure(Position position, Color color) {
		for (Figure figure : board) {
			if (figure.getPosition().equals(position)) {
				if (figure.getColor().equals(color)) {
					System.out.println("Your Figure!");
					return figure;
				} else {
					System.out.println("Not your Figure!");
				}
			}
		}
		return null;
	}

	@Override
	public void update(Position position) {
		if (currentChosenFigure != null) {
			currentChosenFigure.move(position);
			currentChosenFigure = null;
		}
	}

	public String moveFigure(Position from, Position to, Color color) {
		if (currentPlayer.getColor().equals(color) == false) {
			return "Not your turn";
		}
		if (players.size() < 2) {
			return "Wait for second player";
		}
		currentChosenFigure = findFigure(from, color);
		Color opposedColor = Utils.getOpposedColor(color);
		if (currentChosenFigure != null) {
			if (currentChosenFigure.isValid(to)) {
				Figure figureToRemove = findFigure(to, opposedColor);
				if (findFigure(to, color) != null) {
					return "Field taken";
				}
				System.out.println(figureToRemove + " Removed");
				board.remove(figureToRemove);
				if (figureToRemove instanceof King) {
					return "Player " + currentPlayer.getColor().toString() + " won";
				}
				currentChosenFigure.move(to);
				changePlayer();
				return "yes";
			}
		}
		return "No";
	}

	public JSONObject toJSon() {
		JSONObject rootObj = new JSONObject();
		JSONArray array = new JSONArray();
		for (Figure figure : getContext()) {
			array.put(figure.toString());
		}
		rootObj.put("board", array);

		rootObj.put("time_white", (players.size() > 0) ? players.get(0).getTimeLeft() : 1000);
		rootObj.put("time_black", (players.size() > 1) ? players.get(1).getTimeLeft() : 1000);
		return rootObj;
	}
}