import chess.com.Position;

public class Rook extends Figure {

    public Rook(Position position, Color color) {
        super(position, color, "Rook");
    }

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

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♜";
        }
        return "♖";
    }

    @Override
    public boolean isValid(Position position) {
        if ((this.position.getX() - position.getX()) != 0 && (this.position.getY() - position.getY()) == 0) {
            boolean isPositiveVal = ((position.getX() - this.position.getX()) >= 0) ? true : false;
            int x = Math.abs(position.getX() - this.position.getX());
            for (int i = 1; i < x; i++) {
                Position pos = new Position(this.position.getX() + i * ((isPositiveVal) ? 1 : -1), this.position.getY());
                if (BoardModelManager.getInstance().findFigure(pos, getColor()) != null) {
                    return false;
                }
            }    
            return true;
        } else if ((this.position.getY() - position.getY()) != 0 && (this.position.getX() - position.getX()) == 0) {
            boolean isPositiveVal = ((position.getY() - this.position.getY()) >= 0) ? true : false;
            int y = Math.abs(position.getY() - this.position.getY());
            for (int i = 1; i < y; i++) {
                Position pos = new Position(this.position.getX(), this.position.getY() + i * ((isPositiveVal) ? 1 : -1));
                if (BoardModelManager.getInstance().findFigure(pos, getColor()) != null) {
                    return false;
                }
            }    
            return true;
        }
        return false;
    }
}