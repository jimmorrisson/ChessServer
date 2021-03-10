

public class Utils {
    private Utils() {
    }

    public static Color toColor(String color) {
        if (color.toLowerCase().equals("white")) {
            return Color.White;
        }else if (color.toLowerCase().equals("black")) {
            return Color.Black;
        }
        return null;
    }

    public static Figure toFigure(String type, Position position, Color color) {
        if (type.equals("Pawn")) {
            return new Pawn(position, color);
        } else if (type.equals("Knight")) {
            return new Knight(position, color);
        }
        return null;
    }

    public static Figure toFigure(String type, Position position, String color) {
        return Utils.toFigure(type, position, Utils.toColor(color));
    }

    public static Figure toFigure(String type, int x, int y, Color color) {
        return Utils.toFigure(type, new Position(x, y), color);
    }

    public static Figure toFigure(String type, int x, int y, String color) {
        return Utils.toFigure(type, new Position(x, y), Utils.toColor(color));
    }

    public static Color getOpposedColor(Color color) {
        if (color.equals(Color.White)) {
            return Color.Black;
        }
        return Color.White;
    }
}