import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private Color color;
    private int timeLeft = 1000;
    private Timer timer = new Timer();

    public Player(Color color) {
        this.color = color;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft -= 1;
            }
        }, 1000, 1000);
    }

    public Color getColor() {
        return color;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}