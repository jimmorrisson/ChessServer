package model;

import java.util.ArrayList;

import controller.Observer;
import org.json.*;

public class BoardModelManager implements Observer {
    private ArrayList<Figure> board = new ArrayList<>();
    private model.Color currentPlayer = model.Color.White;
    private Figure currentChosenFigure = null;

    public BoardModelManager() {
    }

    public void initializeBoard() {
        board.add(new Pawn(new Position(0, 1), model.Color.White));
        board.add(new Knight(new Position(1, 0), model.Color.White));
        board.add(new Pawn(new Position(1, 1), model.Color.White));
        board.add(new Pawn(new Position(2, 1), model.Color.White));
        board.add(new Pawn(new Position(3, 1), model.Color.White));
        board.add(new Pawn(new Position(4, 1), model.Color.White));
        board.add(new Pawn(new Position(5, 1), model.Color.White));
        board.add(new Knight(new Position(6, 0), model.Color.White));
        board.add(new Pawn(new Position(6, 1), model.Color.White));
        board.add(new Pawn(new Position(7, 1), model.Color.White));

        board.add(new Pawn(new Position(0, 6), model.Color.Black));
        board.add(new Knight(new Position(1, 7), model.Color.Black));
        board.add(new Pawn(new Position(1, 6), model.Color.Black));
        board.add(new Pawn(new Position(2, 6), model.Color.Black));
        board.add(new Pawn(new Position(3, 6), model.Color.Black));
        board.add(new Pawn(new Position(4, 6), model.Color.Black));
        board.add(new Pawn(new Position(5, 6), model.Color.Black));
        board.add(new Knight(new Position(6, 7), model.Color.Black));
        board.add(new Pawn(new Position(6, 6), model.Color.Black));
        board.add(new Pawn(new Position(7, 6), model.Color.Black));
    }

    public ArrayList<Figure> getContext() {
        return board;
    }

    public Figure findFigure(Position position, model.Color color) {
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

    public Figure findFigure(Position position) {
        return findFigure(position, model.Color.White);
    }

    @Override
    public void update(Position position) {
        if (currentChosenFigure != null) {
            currentChosenFigure.move(position);
            currentChosenFigure = null;
        }

        currentChosenFigure = findFigure(position);
    }

    public String moveFigure(Position from, Position to, Color color) {
        if (currentPlayer.equals(color)) {
            return "Not your turn!";
        }
        currentChosenFigure = findFigure(from, color);
        if (currentChosenFigure != null) {
            if (currentChosenFigure.move(to)) {
                if (currentPlayer.equals(model.Color.White)) {
                    currentPlayer = model.Color.Black;
                } else {
                    currentPlayer = model.Color.White;
                }
                return "Yes";
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