

import java.util.ArrayList;


import org.json.*;

public class BoardModelManager implements Observer {
    private ArrayList<Figure> board = new ArrayList<>();
    private Color currentPlayer = Color.White;
    private Figure currentChosenFigure = null;
    private static BoardModelManager instance = null;

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
        board.add(new Knight(new Position(1, 0), Color.White));
        board.add(new Pawn(new Position(1, 1), Color.White));
        board.add(new Pawn(new Position(2, 1), Color.White));
        board.add(new Pawn(new Position(3, 1), Color.White));
        board.add(new Pawn(new Position(4, 1), Color.White));
        board.add(new Pawn(new Position(5, 1), Color.White));
        board.add(new Knight(new Position(6, 0), Color.White));
        board.add(new Pawn(new Position(6, 1), Color.White));
        board.add(new Pawn(new Position(7, 1), Color.White));

        board.add(new Pawn(new Position(0, 6), Color.Black));
        board.add(new Knight(new Position(1, 7), Color.Black));
        board.add(new Pawn(new Position(1, 6), Color.Black));
        board.add(new Pawn(new Position(2, 6), Color.Black));
        board.add(new Pawn(new Position(3, 6), Color.Black));
        board.add(new Pawn(new Position(4, 6), Color.Black));
        board.add(new Pawn(new Position(5, 6), Color.Black));
        board.add(new Knight(new Position(6, 7), Color.Black));
        board.add(new Pawn(new Position(6, 6), Color.Black));
        board.add(new Pawn(new Position(7, 6), Color.Black));
    }

    public ArrayList<Figure> getContext() {
        return board;
    }

    public Figure findFigure(Position position, Color color) {
        for (Figure figure : board) {
            if (figure.getPosition().equals(position)) {
                if (figure.getColor().equals(color)) {
                    System.out.println("Your Figure!");
                    return figure;
                }
                else {
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
        if (currentPlayer.equals(color) == false) {
            return "Not your turn";
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
                currentChosenFigure.move(to);
                currentPlayer =opposedColor;
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
        return rootObj;
    }
}