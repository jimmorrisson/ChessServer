
import chess.com.*;

public class Pawn extends Figure {
    private boolean hasMoved = false;

    public Pawn(Position position, Color color) {
        super(position, color, "Pawn");
    }

    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Pawn at position " + this.position.getX() + "," + this.position.getY());
            hasMoved = true;
            return true;
        }
        System.out.print("Move incorrect! Pawn at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♟︎";
        }
        return "♙";
    }

    @Override
    public boolean isValid(Position position) {
        Color opposedColor = Utils.getOpposedColor(getColor());
        if (getColor().equals(Color.Black)) {
            if (hasMoved == false) {
                if ((this.position.getX() == position.getX() && (this.position.getY() - 1) == position.getY())
                        || (this.position.getX() == position.getX() && (this.position.getY() - 2) == position.getY())) {
                    if (BoardModelManager.getInstance().findFigure(position, Color.White) != null
                            || BoardModelManager.getInstance().findFigure(position, Color.Black) != null
                            || BoardModelManager.getInstance()
                                    .findFigure(new Position(position.getX(), this.position.getY() - 1), Color.White) != null
                            || BoardModelManager.getInstance().findFigure(
                                    new Position(position.getX(), this.position.getY() - 1), Color.Black) != null) {
                        return false;
                    }
                    return true;
                }
            }
            if (this.position.getX() == position.getX() && (this.position.getY() - 1) == position.getY()) {
                if (BoardModelManager.getInstance().findFigure(position, opposedColor) != null) {
                    return false;
                }
                return true;
            } else if ((this.position.getX() == position.getX() - 1 || this.position.getX() == position.getX() + 1)
                    && (this.position.getY() - 1) == position.getY()) {
                if (BoardModelManager.getInstance().findFigure(position, opposedColor) != null) {
                    return true;
                }
                return false;
            }
        }
        if (hasMoved == false) {
            if ((this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY())
                    || (this.position.getX() == position.getX() && (this.position.getY() + 2) == position.getY())) {
                if (BoardModelManager.getInstance().findFigure(position, Color.White) != null
                        || BoardModelManager.getInstance().findFigure(position, Color.Black) != null
                        || BoardModelManager.getInstance()
                                .findFigure(new Position(position.getX(), this.position.getY() + 1), Color.White) != null
                        || BoardModelManager.getInstance()
                                .findFigure(new Position(position.getX(), this.position.getY() + 1), Color.Black) != null) {
                    return false;
                }
                return true;
            }
        }
        if (this.position.getX() == position.getX() && (this.position.getY() + 1) == position.getY()) {
            if (BoardModelManager.getInstance().findFigure(position, opposedColor) != null) {
                return false;
            }
            return true;
        }
        if ((this.position.getX() == position.getX() - 1 || this.position.getX() == position.getX() + 1)
                && (this.position.getY() + 1) == position.getY()) {
            if (BoardModelManager.getInstance().findFigure(position, opposedColor) != null) {
                return true;
            }
            return false;
        }
        return false;
    }
}
