import java.awt.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController extends GameState {

    private Background background;
    private Train train;
    private Bandit player1;
    private Bandit player2;
    private Marshall Ai ;
    private CopyOnWriteArrayList<Butin> butins;
    private HashMap<Bandit, Boolean> turns;
    private String currentTurn = "";
    private String phase = "";
    private String score = "";
    //nombre de tours maximal
    public static final int NB_MAX_TOURS = 2;

    public GameController(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {

        background = new Background();
        train = new Train(230, 160, "/Resources/Background/Train.png");
        player1 = new Bandit(Positions.POSITION_1X.getAction(), Positions.POSITION_TOP_Y.getAction(), "/Resources/Background/char.png"
                ,60,95,"PLAYER 1 ", this);

        //set x to 395
        // 532
        player2 = new Bandit(Positions.POSITION_2X.getAction(), Positions.POSITION_TOP_Y.getAction(), "/Resources/Background/char.png"
                ,395,95,"PLAYER 2 ", this);

        Ai = new Marshall(Positions.POSITION_4X.getAction(),Positions.POSITION_BOTTOM_Y.getAction(),"/Resources/Background/char.png",
                203,95,this);

        // if true means c'est son tour
        turns = new HashMap<>();
        //Bandit 1 Commence le premier !
        turns.put(player1, Boolean.TRUE);
        turns.put(player2, Boolean.FALSE);

        // declare les butins
        butins = new CopyOnWriteArrayList<>();

        // on les instancie

        // 1 Magot dans la locomotive ;
        butins.add(new Magot("/Resources/Butins/Magot.png"));

        String[] possibilities = {"Bources", "Bijoux"};
        Random rand = new Random();
        int x;
        for (int i = 1; i < Butin.NB_BUTTIN; i++) {
            x = rand.nextInt(2);
            if (possibilities[x].equals("Bources"))
                butins.add(new Bourses("/Resources/Butins/Bource.png"));
            if (possibilities[x].equals("Bijoux"))
                butins.add(new Bijoux("/Resources/Butins/Bijou.png"));
        }
        miseAjoursScore();
    }

    public CopyOnWriteArrayList<Butin> getButins() {
        return butins;
    }

    public Marshall getAi() {
        return Ai;
    }

    public void update() {

        background.update();
        Ai.update();
        player1.update();
        player2.update();

if(turns != null &&  turns.get(player1) != null ) {
    if (turns.get(player1).equals(Boolean.TRUE)) {

        if (player1.getQueue().isEmpty()) {
            phase = " * PLANIFICATION * ";
        }
        currentTurn = " PLAYER 1's TURN !";
    } else {
        if (player2.getQueue().isEmpty()) {
            phase = " * PLANIFICATION * ";
        }
        currentTurn = " PLAYER 2's TURN !";
    }
}

    }

    public void draw(Graphics2D g) {
        // clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // draw background

        background.draw(g);

        // draw train
        train.draw(g);

        //draw Butin
        for (Butin b : butins) {
            if (b == null) System.out.println("im null lol ");
            b.draw(g);

        }

        //draw Marshall
        Ai.draw(g);

        // draw bandit

        player1.draw(g);
        player2.draw(g);


        // draw current Phase !

        g.setColor(new Color(128, 24, 38));
        g.setFont(new Font("Helvetica", Font.PLAIN, 13));
        g.drawString(phase, 105, 50);

        // draw Score  !

        g.setColor(new Color(255, 251, 195));
        g.setFont(new Font("Helvetica", Font.PLAIN, 10));
        g.drawString(score, 80, 15);

        // Draw The Turn string
        g.setColor(new Color(128, 68, 38));
        g.setFont(new Font("Helvetica", Font.PLAIN, 18));
        g.drawString(currentTurn, 80, 70);


    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(String currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void keyPressed(int k) {
    }

    public void keyReleased(int k) {
    }


    public String getFinalResult(){
        if(player1.getScore() > player2.getScore()) {
         return("LE GAGNAT EST "+ player1.getNom());
        }else if(player1.getScore() < player2.getScore()) {
         return("LE GAGNAT EST "+ player2.getNom());
        }else return ("LE RESULTAT EST : EGALITE :D  ");

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

    public void miseAjoursScore() {
        score = player1.getNom() + " = " + player1.getScore() + "$ : " + player2.getNom() + " = " + player2.getScore() + "$";
    }

    public void QueuemoveRight() {
        if (turns.get(player1).equals(Boolean.TRUE)) {
            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut aller a droite ");
                player1.getQueue().add(Actions.AVANT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        } else {

            if (player2.getQueue().size() != 2) {
                // le tour de player 2
                System.out.println(player2.getNom() + " veut aller a droite ");
                player2.getQueue().add(Actions.AVANT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        }
    }


    public void QueuemoveLeft() {

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
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut braquer  ");
                player1.getQueue().add(Actions.BRAQUE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut braquer ");
                player2.getQueue().add(Actions.BRAQUE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueueTireDroite() {
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut Tirer a droite  ");
                player1.getQueue().add(Actions.TIRE_DROITE);

            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut Tirer a droite ");
                player2.getQueue().add(Actions.TIRE_DROITE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueueTireGauche() {
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut Tirer a gauche  ");
                player1.getQueue().add(Actions.TIRE_GAUCHE);

            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut Tirer a gauche ");
                player2.getQueue().add(Actions.TIRE_GAUCHE);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueueTireHaut() {
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut Tirer vers le haut   ");
                player1.getQueue().add(Actions.TIRE_HAUT);

            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut Tirer vers le haut ");
                player2.getQueue().add(Actions.TIRE_HAUT);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }

    public void QueueTireBas() {
        if (turns.get(player1).equals(Boolean.TRUE)) {

            if (player1.getQueue().size() != 2) {
                // le tour de player 1
                System.out.println(player1.getNom() + " veut Tirer vers le bas   ");
                player1.getQueue().add(Actions.TIRE_BAS);

            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }
        } else {
            if (player2.getQueue().size() != 2) {

                // le tour de player 2
                System.out.println(player2.getNom() + " veut Tirer vers le bas ");
                player2.getQueue().add(Actions.TIRE_BAS);
            } else {
                System.out.println("on peut seleuemnt choisir 2 Actions ! veuiller Actionner avant ");
            }

        }
    }
}












