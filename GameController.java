import java.awt.*;
import java.util.HashMap;

public class GameController extends GameState {

    private Background background;
    private Train train;
    private Bandit player1;
    private Bandit player2;
    private HashMap<Bandit, Boolean> turns;

    public GameController(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {

        background = new Background();
        train = new Train(230, 160, "/Resources/Background/Train.png");
        player1 = new Bandit(Positions.POSITION_1X.getAction(), Positions.POSITION_TOP_Y.getAction(), "/Resources/Background/char.png",
                "PLAYER 1 ");
        player2 = new Bandit(Positions.POSITION_2X.getAction(), Positions.POSITION_TOP_Y.getAction(), "/Resources/Background/char.png",
                "PLAYER 2 ");

        // if true means c'est son tour
        turns = new HashMap<>();
        //Bandit 1 Commence le premier !
        turns.put(player1, Boolean.TRUE);
        turns.put(player2, Boolean.FALSE);


    }


    public void update() {
        background.update();

        player1.update();
        player2.update();

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
        player2.draw(g);

    }

    public void keyPressed(int k) {
    }

    public void keyReleased(int k) {
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Bandit getPlayer1() {
        return player1;
    }

    public void setPlayer1(Bandit player1) {
        this.player1 = player1;
    }

    public Bandit getPlayer2() {
        return player2;
    }

    public void setPlayer2(Bandit player2) {
        this.player2 = player2;
    }

    public HashMap<Bandit, Boolean> getTurns() {
        return turns;
    }

    public void setTurns(HashMap<Bandit, Boolean> turns) {
        this.turns = turns;
    }

    public void QueuemoveRight() {
        System.out.println(turns.get(player1));
        if (turns.get(player1).equals(Boolean.TRUE)) {
            if (player1.getQueue().size() != 2) {
                System.out.println("went in ");
                // le tour de player 1
                System.out.println(player1.getNom() + " veut aller a droite ");
                player1.getQueue().add(Actions.AVANT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        } else {

            if (player2.getQueue().size() != 2) {
                System.out.println("went in ");
                // le tour de player 2
                System.out.println(player2.getNom() + " veut aller a droite ");
                player2.getQueue().add(Actions.AVANT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        }
    }


    public void QueuemoveLeft() {
        System.out.println(turns.get(player1));
        if (turns.get(player1).equals(Boolean.TRUE)) {
            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut aller a gauche ");
                player1.getQueue().add(Actions.ARRIERE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {
                // le tour de player 2
                System.out.println(player2.getNom() + " veut aller a gauche ");
                player2.getQueue().add(Actions.ARRIERE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        }

    }

    public void QueuemoveUP() {
        System.out.println(turns.get(player1));
        if (turns.get(player1).equals(Boolean.TRUE)) {
            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut aller sur le toit ");
                player1.getQueue().add(Actions.HAUT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {
                // le tour de player 2
                System.out.println(player2.getNom() + " veut aller sur le toit ");
                player2.getQueue().add(Actions.HAUT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueuemoveDown() {


        System.out.println(turns.get(player1));
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut descendre ");
                player1.getQueue().add(Actions.BAS);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        } else {
            if (player2.getQueue().size() != 2) {
                // le tour de player 2
                System.out.println(player2.getNom() + " veut descendre  ");
                player2.getQueue().add(Actions.BAS);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueueBraque() {
        System.out.println(turns.get(player1));
        if (turns.get(player1).equals(Boolean.TRUE)) {
            if (player1.getQueue().size() != 2) {

                // le tour de player 1
                System.out.println(player1.getNom() + " veut descendre ");
                player1.getQueue().add(Actions.BRAQUE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut descendre ");
                player2.getQueue().add(Actions.BRAQUE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }
}












