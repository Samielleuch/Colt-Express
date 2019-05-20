


import java.awt.*;

public class Level1State extends GameState {

    private TileMap tileMap;
    private Train train;
    private Bandit player1 ;
    private  Positions p;


    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap();
        train = new Train(230,160,"/Resources/Background/Train.png");
        player1 = new Bandit(p.POSITION_TOP_1X.getAction() ,p.POSITION_TOP_1Y.getAction(),"/Resources/Background/char.png");

        tileMap=new TileMap();
    }


    public void update() {
        tileMap.update();
        player1.update();

    }

    public void draw(Graphics2D g) {

        // clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw background

        tileMap.draw(g);

        // draw train
        train.draw(g);

        // draw bandit

        player1.draw(g);

    }

    public void keyPressed(int k) {
    }

    public void keyReleased(int k) {
    }

}











