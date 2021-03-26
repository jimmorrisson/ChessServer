import java.util.ArrayList;
import org.json.*;
import chess.com.*;

public class BoardModelManager implements Observable{
	private Player currentPlayer = null;
	private Figure currentChosenFigure = null;
	private static BoardModelManager instance = null;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();

	
	/** Private constructor to prevent other instances
	 */
	private BoardModelManager() {
	}

	
	/** Gets the only one instance of the BoardManager.
	 * This sould be used to acces BoardModelManager.
	 * @return BoardModelManager
	 */
	public static BoardModelManager getInstance() {
		if (instance == null) {
			instance = new BoardModelManager();
		}
		return instance;
	}

	
	/** Gets current context of the board.
	 * @return ArrayList All figures currently on the board.
	 */
	public ArrayList<Figure> getContext() {
		ArrayList<Figure> allFigures = new ArrayList<>();
		for (Player player : players) {
			allFigures.addAll(player.getFigures());
		}
		return allFigures;
	}

	
	/** Handles player join.
	 * @param player
	 */
	public void addPlayer(Player player) {
		if (players.size() >= 2) {
			System.out.println("Can't add more than 2 players!");
			return;
		}
		if (currentPlayer == null) {
			currentPlayer = player;
			currentPlayer.setYourTurn(true);
		}
		players.add(player);
		addObserver(player);
	}

	private void changePlayer() {
		for (Player player : players) {
			if (currentPlayer.getColor().equals(Color.White) && player.getColor().equals(Color.Black)) {
				currentPlayer.setYourTurn(false);
				currentPlayer = player;
				currentPlayer.setYourTurn(true);
				break;
			} else if (currentPlayer.getColor().equals(Color.Black) && player.getColor().equals(Color.White)) {
				currentPlayer.setYourTurn(false);
				currentPlayer = player;
				currentPlayer.setYourTurn(true);
				break;
			}
		}
	}

	
	/** Trying to find figure on the board.
	 * @param position of the figure
	 * @param color of the player
	 * @return Figure 
	 */
	public Figure findFigure(Position position, Color color) {
		for (Player player : players) {
			for (Figure figure : player.getFigures()) {
				if (figure.getPosition().equals(position) && figure.getColor().equals(color)) {
					return figure;
				}
			}
		}
		return null;
	}

	
	/** Tries to move figure from one position to another
	 * @param from
	 * @param to
	 * @param color
	 * @return String that is suppose to inform user if move failed or was success.
	 */
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
				currentChosenFigure.move(to);
				System.out.println(figureToRemove + " Removed");
				remove(figureToRemove);
				changePlayer();
				return "yes";
			}
		}
		return "No";
	}

	
	/** Creates JSONObject for current board context.
	 * @return JSONObject
	 */
	public JSONObject toJSon() {
		JSONObject rootObj = new JSONObject();
		JSONArray array = new JSONArray();
		for (Figure figure : getContext()) {
			array.put(figure.toString());
		}
		for (Player player : players) {
			rootObj.put("player_" + player.getColor().toString(), player.getState());
		}
		rootObj.put("board", array);
		rootObj.put("time_white", (players.size() > 0) ? players.get(0).getTimeLeft() : 1000);
		rootObj.put("time_black", (players.size() > 1) ? players.get(1).getTimeLeft() : 1000);
		return rootObj;
	}

	
	/** 
	 * @param o
	 */
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	
	/** 
	 * @param o
	 */
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	
	/** 
	 * @param figure
	 */
	@Override
	public void remove(Figure figure) {
		for (Observer observer : observers) {
			observer.remove(figure);
		}
	}
}
