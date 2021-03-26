public class GameController {
    private Player currentPlayer;
    private static GameController instance;

    
    /** 
     * @param player
     */
    private GameController(Player player) {
        this.currentPlayer = player;
    }

    
    /** 
     * @return GameController
     */
    public static GameController getInstance() {
        return instance;
    }

    
    /** 
     * @return Player
     */
    public static Player getCurrentPlayer() {
        return instance.currentPlayer;
    }

    
    /** 
     * @param startingPlayer
     */
    public static void init(Player startingPlayer) {
        instance = new GameController(startingPlayer);
    }
}
