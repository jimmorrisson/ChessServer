import chess.com.Position;

public class Bishop extends Figure {

    public Bishop(Position position, Color color) {
        super(position, color, "Bishop");
    }

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

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♝";
        }
        return "♗";
    }

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