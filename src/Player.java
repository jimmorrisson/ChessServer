import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import chess.com.Position;

public class Player implements Observer {
    private Color color;
    private int timeLeft = 1000;
    private Timer timer = new Timer();
    private ArrayList<Figure> figures = new ArrayList<>();
    private boolean isYourTurn = false;
    private boolean lost = false;

    
    /** Constructor of player
     * @param color player color 
     * Could be black/white
     */
    public Player(Color color) {
        this.color = color;
        initializeFigures();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isYourTurn) {
                    if (timeLeft > 0) {
                        timeLeft -= 1;
                    } else {
                        lost = true;
                        timeLeft = 0;
                    }
                }
            }
        }, 1000, 1000);
    }

    
    /** Gets player color
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    
    /** Gets time left for player
     * @return int
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    
    /** Gets figures of player
     * @return ArrayList
     */
    public ArrayList<Figure> getFigures() {
        return figures;
    }

    
    /** Sets player turn
     * @param value
     */
    public void setYourTurn(boolean value) {
        isYourTurn = value;
    }

    private void initializeFigures() {
        if (color.equals(Color.White)) {
            figures.add(new Pawn(new Position(0, 1), Color.White));
            figures.add(new Rook(new Position(0, 0), Color.White));
            figures.add(new Knight(new Position(1, 0), Color.White));
            figures.add(new Pawn(new Position(1, 1), Color.White));
            figures.add(new Pawn(new Position(2, 1), Color.White));
            figures.add(new Bishop(new Position(2, 0), Color.White));
            figures.add(new Queen(new Position(3, 0), Color.White));
            figures.add(new Pawn(new Position(3, 1), Color.White));
            figures.add(new King(new Position(4, 0), Color.White));
            figures.add(new Pawn(new Position(4, 1), Color.White));
            figures.add(new Bishop(new Position(5, 0), Color.White));
            figures.add(new Pawn(new Position(5, 1), Color.White));
            figures.add(new Knight(new Position(6, 0), Color.White));
            figures.add(new Pawn(new Position(6, 1), Color.White));
            figures.add(new Rook(new Position(7, 0), Color.White));
            figures.add(new Pawn(new Position(7, 1), Color.White));
        } else {
            figures.add(new Pawn(new Position(0, 6), Color.Black));
            figures.add(new Rook(new Position(0, 7), Color.Black));
            figures.add(new Knight(new Position(1, 7), Color.Black));
            figures.add(new Pawn(new Position(1, 6), Color.Black));
            figures.add(new Pawn(new Position(2, 6), Color.Black));
            figures.add(new Bishop(new Position(2, 7), Color.Black));
            figures.add(new Queen(new Position(3, 7), Color.Black));
            figures.add(new Pawn(new Position(3, 6), Color.Black));
            figures.add(new King(new Position(4, 7), Color.Black));
            figures.add(new Pawn(new Position(4, 6), Color.Black));
            figures.add(new Bishop(new Position(5, 7), Color.Black));
            figures.add(new Pawn(new Position(5, 6), Color.Black));
            figures.add(new Knight(new Position(6, 7), Color.Black));
            figures.add(new Pawn(new Position(6, 6), Color.Black));
            figures.add(new Pawn(new Position(7, 6), Color.Black));
            figures.add(new Rook(new Position(7, 7), Color.Black));
        }
    }

    
    /** Handles figure removal from player
     * @param figure
     */
    @Override
    public void remove(Figure figure) {
        if (figure != null && figure.getColor().equals(color)) {
            if (figure instanceof King) {
                Color opposedColor = Utils.getOpposedColor(color);
                lost = true;
                System.out.println("Player " + opposedColor.toString() + " won");
            }
            figures.remove(figure);
        }
    }

    
    /** Get current state of player
     * Possible states are lost/playing
     * @return String
     */
    public String getState() {
        return (lost) ? "lost" : "playing";
    }
}
