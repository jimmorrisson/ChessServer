import chess.com.Position;

public class King extends Figure {

    
    /** 
     * @param position
     * @param color
     */
    public King(Position position, Color color) {
        super(position, color, "King");
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. King at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! King at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    
    /** 
     * @return String
     */
    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♚";
        }
        return "♔";
    }

    
    /** 
     * @param position
     * @return boolean
     */
    @Override
    public boolean isValid(Position position) {
        int xDifference = position.getX() - this.getPosition().getX();
        int yDifference = position.getY() - this.getPosition().getY();
        if (xDifference == 0 && yDifference == 0) {
            return false;
        }
        if (Math.abs(xDifference) <= 1 && Math.abs(yDifference) <= 1) {
            if (BoardModelManager.getInstance().findFigure(position, getColor()) != null) {
                return false;
            }
            return true;
        }
        return false;
    }

}
