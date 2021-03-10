



public class GameController {
    private Player currentPlayer;
    private static GameController instance;
    
    private GameController(Player player) {
        this.currentPlayer = player;
    }

    public static GameController getInstance() {
        return instance;
    }

    public static Player getCurrentPlayer() {
        return instance.currentPlayer;
    }

    public static void init(Player startingPlayer) {
        instance = new GameController(startingPlayer);
    }
}