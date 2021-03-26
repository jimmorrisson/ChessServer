import chess.com.Position;

public class Bishop extends Figure {

    /** Constructor for bishop
     * @param position starting position
     * @param color owner color
     */
    public Bishop(Position position, Color color) {
        super(position, color, "Bishop");
    }

    
    /** Moves bishop from one position to another
     * @param position
     * @return boolean true if position is valid
     * and move was succesfull. False otherwise
     */
    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Bishop at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Bishop at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    
    /** Gets icon of bishop
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♝";
        }
        return "♗";
    }

    
    /** Check if bishop position is valid
     * If it's valid it means that user is able to move to it.
     * @param position
     * @return boolean
     */
    @Override
    public boolean isValid(Position position) {
        int xDifference = position.getX() - this.getPosition().getX();
        int yDifference = position.getY() - this.getPosition().getY();
        if (xDifference != 0 && yDifference != 0 && (Math.abs(xDifference) == Math.abs(yDifference))) {
            return checkBiasPosition(position, xDifference, yDifference);
        }
        return false;
    }

}
