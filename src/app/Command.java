

import java.io.Serializable;

public class Command implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Position from;
    private Position to;
    private boolean context = false;

    public Command(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Command(boolean context) {
        this.context = context;
    }

    public boolean getContext() {
        return context;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}