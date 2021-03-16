import chess.com.Position;

public class Queen extends Figure {

    public Queen(Position position, Color color) {
        super(position, color, "Queen");
    }

    @Override
    public boolean move(Position position) {
        if (isValid(position)) {
            setPosition(position);
            System.out.print("Move correct to. Queen at position " + this.position.getX() + "," + this.position.getY());
            return true;
        }
        System.out.print("Move incorrect! Queen at position " + this.position.getX() + "," + this.position.getY());
        return false;
    }

    @Override
    public String getIcon() {
        if (this.getColor() == Color.Black) {
            return "♛";
        }
        return "♕";
    }

    @Override
    public boolean isValid(Position position) {
        int xDifference = position.getX() - this.getPosition().getX();
        int yDifference = position.getY() - this.getPosition().getY();
        if (xDifference != 0 && yDifference != 0 && (Math.abs(xDifference) == Math.abs(yDifference))) {
            return checkBiasPosition(position, xDifference, yDifference);
        } else if ((this.position.getX() - position.getX()) != 0 && (this.position.getY() - position.getY()) == 0) {
            return checkHorizontalPosition(position);
        } else if ((this.position.getY() - position.getY()) != 0 && (this.position.getX() - position.getX()) == 0) {
            return checkVerticalPosition(position);
        }

        return false;
    }

}
