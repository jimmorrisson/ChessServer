
import org.json.JSONObject;
import chess.com.*;

public abstract class Figure {
    protected Position position;
    private Color color;
    private String name;

    
    /** Base interface for all Figures
     * @param position start position
     * @param color owner color
     * @param name of the figure
     */
    public Figure(Position position, Color color, String name) {
        this.position = position;
        this.color = color;
        this.name = name;
    }

    
    /** Gets position of the figure
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    
    /** Gets color of the figure
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    
    /** Gets name of the figure
     * @return String
     */
    protected String getName() {
        return name;
    }

    
    /** Handles move from current position 
     * to the passed position
     * @param position to move to
     * @return boolean True if move was successfull. False otherwise.
     */
    public abstract boolean move(Position position);

    
    /** Gets unicode for the specfic figure
     * @return String
     */
    public abstract String getIcon();

    
    /** Sets position of the figure to the position passed
     * @param position
     * @return boolean
     */
    protected boolean setPosition(Position position) {
        if (!isValid(position)) {
            return false;
        }
        this.position = position;
        return true;
    }

    
    /** Checs if passed position is valid for the current figure
     * @param position
     * @return boolean
     */
    public abstract boolean isValid(Position position);

    
    /** Checks if position is in the vertical axis of the figure
     * @param position
     * @return boolean
     */
    protected boolean checkVerticalPosition(Position position) {
        boolean isPositiveVal = ((position.getY() - this.position.getY()) >= 0) ? true : false;
        int y = Math.abs(position.getY() - this.position.getY());
        for (int i = 1; i < y; i++) {
            Position pos = new Position(this.position.getX(), this.position.getY() + i * ((isPositiveVal) ? 1 : -1));
            if (BoardModelManager.getInstance().findFigure(pos, Color.Black) != null
                    || BoardModelManager.getInstance().findFigure(pos, Color.White) != null) {
                return false;
            }
        }
        return true;
    }

    
    /** Checks if position is in the horizontal axis of the figure
     * @param position
     * @return boolean
     */
    protected boolean checkHorizontalPosition(Position position) {
        boolean isPositiveVal = ((position.getX() - this.position.getX()) >= 0) ? true : false;
        int x = Math.abs(position.getX() - this.position.getX());
        for (int i = 1; i < x; i++) {
            Position pos = new Position(this.position.getX() + i * ((isPositiveVal) ? 1 : -1), this.position.getY());
            if (BoardModelManager.getInstance().findFigure(pos, Color.Black) != null
                    || BoardModelManager.getInstance().findFigure(pos, Color.White) != null) {
                return false;
            }
        }
        return true;
    }

    
    /** Checks if position is in the bias of the figure
     * @param position
     * @param xDifference
     * @param yDifference
     * @return boolean
     */
    protected boolean checkBiasPosition(Position position, int xDifference, int yDifference) {
        int xDirection = (xDifference < 0) ? -1 : 1;
        int yDirection = (yDifference < 0) ? -1 : 1;

        for (int iX = xDirection, iY = yDirection; iX != xDifference
                && iY != yDifference; iX += xDirection, iY += yDirection) {
            if (BoardModelManager.getInstance().findFigure(
                    new Position(this.getPosition().getX() + iX, this.getPosition().getY() + iY), Color.Black) != null
                    || BoardModelManager.getInstance().findFigure(
                            new Position(this.getPosition().getX() + iX, this.getPosition().getY() + iY),
                            Color.White) != null) {
                return false;
            }
        }
        return true;
    }

    
    /** Converts figure to the string of JSON type
     * @return String
     */
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("type", name);
        object.put("color", getColor().toString());
        object.put("x", getPosition().getX());
        object.put("y", getPosition().getY());
        return object.toString();
    }
}
