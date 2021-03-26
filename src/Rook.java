import chess.com.Position;

public class Rook extends Figure {

    
    /** 
     * @param position
     * @param color
     */
    public Rook(Position position, Color color) {
        super(position, color, "Rook");
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Rook at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Rook at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♜";
        }
        return "♖";
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean isValid(Position position) {
        if ((this.position.getX() - position.getX()) != 0 && (this.position.getY() - position.getY()) == 0) {
            return checkHorizontalPosition(position);
        } else if ((this.position.getY() - position.getY()) != 0 && (this.position.getX() - position.getX()) == 0) {
            return checkVerticalPosition(position);
        }
        return false;
    }
}
