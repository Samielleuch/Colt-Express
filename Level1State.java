


import javax.swing.*;
import java.awt.*;

public class Level1State extends GameState {

    private Background background;
    private Train train;
    private Bandit player1 ;
    private  Positions p;
    private JButton btn = new JButton("press me ");

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        background = new Background();
        train = new Train(230,160,"/Resources/Background/Train.png");
        player1 = new Bandit(Positions.POSITION_TOP_1X.getAction(), Positions.POSITION_TOP_1Y.getAction(), "/Resources/Background/char.png");

        background = new Background();
    }


    public void update() {
        background.update();
        player1.update();

    }

    public void draw(Graphics2D g) {

        // clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw background

        background.draw(g);

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











