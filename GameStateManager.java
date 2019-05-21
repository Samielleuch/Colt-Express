import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameState> gameStates;
    public static final int MENUSTATE = 0;
    public static final int GAMECONTROLLERSTATE = 1;
    public static final int WINNINGSTATE = 2;
    private int currentState;
    private GamePanel f;

    public GameStateManager(GamePanel f) {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        this.f = f;
        gameStates.add(new MenuState(this));
        gameStates.add(new GameController(this));
        gameStates.add(new WinningState(this));
    }

    public GamePanel getPanel() {
        return f;
    }

    public void setGameState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public ArrayList<GameState> getGameStates() {
        return gameStates;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);

    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

    public void update() {
        gameStates.get(currentState).update();

    }
}
