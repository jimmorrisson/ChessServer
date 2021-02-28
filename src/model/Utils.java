package model;

public class Utils {
    private Utils() {
    }

    public static model.Color toColor(String color) {
        if (color.toLowerCase().equals("white")) {
            return model.Color.White;
        }else if (color.toLowerCase().equals("black")) {
            return model.Color.Black;
        }
        return null;
    }

    public static Figure toFigure(String type, Position position, model.Color color) {
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

    public static Figure toFigure(String type, int x, int y, model.Color color) {
        return Utils.toFigure(type, new Position(x, y), color);
    }

    public static Figure toFigure(String type, int x, int y, String color) {
        return Utils.toFigure(type, new Position(x, y), Utils.toColor(color));
    }

    public static model.Color getOpposedColor(model.Color color) {
        if (color.equals(model.Color.White)) {
            return model.Color.Black;
        }
        return model.Color.White;
    }
}