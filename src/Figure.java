
import org.json.JSONObject;
import chess.com.*;

public abstract class Figure {
    protected Position position;
    private Color color;

    public Figure(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public abstract boolean move(Position position);

    public abstract String getIcon();

    protected boolean setPosition(Position position) {
        if (!isValid(position)) {
            return false;
        }
        this.position = position;
        return true;
    }

    public abstract boolean isValid(Position position);

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("type", "Null");
        object.put("color", getColor().toString());
        object.put("x", getPosition().getX());
        object.put("y", getPosition().getY());
        return object.toString();
    }
}