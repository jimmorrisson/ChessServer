

import java.io.Serializable;

public class Position implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;

    public Position(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Position) {
            Position otherPosition = (Position) obj;
            return x == otherPosition.x &&
                   y == otherPosition.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 17 * x + 31 * y;
    }

    @Override
    public String toString() {
        return String.format(x + "," + y);
    }
}