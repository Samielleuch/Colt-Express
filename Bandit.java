import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bandit {
    private String nom;
    private BufferedImage tileSet;
    private BufferedImage character;
    private int score = 0;
    private Vector<Butin> Loot;
    private float xposition;
    private float targetXPosition;
    private float targetYPosition;
    private float yposition;
    private float dx = 0.5f;
    private float dy = 0.5f;
    private GameController gp;
    private Positions p;

    //n'est pas entrain d'animer
    private Boolean animated = Boolean.FALSE;
    // on peut ecrire un message
    private Boolean donneMessage = Boolean.FALSE;
    // on a pas terminer l'action
    private Boolean actionFinished = Boolean.FALSE;
    // on a pas  trouver le butin ou le joueur
    private Boolean found = Boolean.FALSE;

    // il doit choisir 2 actions avant de les lancer
    private Boolean readyForAction = Boolean.FALSE;
    private ArrayList<Actions> Queue = new ArrayList<>();
    private int counter = 0;


    public Bandit(float xposition, float yposition, String src, String nom, GameController gp) {
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }
        character = tileSet.getSubimage(
                60,
                95,
                33,
                54
        );

        this.xposition = xposition;
        this.yposition = yposition;
        this.nom = nom;
        this.gp = gp;
        this.Loot = new Vector<>();

    }

    public int getScore() {
        return score;
    }

    private void calculeScore() {
        score = 0;
        for (Butin b : Loot) {
            score += b.getValeur();
        }

    }

    public void Braquer(Butin bt) {

        Loot.add(bt);
        calculeScore();
        gp.miseAjoursScore();
        gp.getButins().remove(bt);

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BufferedImage getTileSet() {
        return tileSet;
    }

    public void setTileSet(BufferedImage tileSet) {
        this.tileSet = tileSet;
    }

    public float getXposition() {
        return xposition;
    }

    public void setXposition(float xposition) {
        this.xposition = xposition;
    }

    public float getYposition() {
        return yposition;
    }

    public void setYposition(float yposition) {
        this.yposition = yposition;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public BufferedImage getCharacter() {
        return character;
    }

    public void setCharacter(BufferedImage character) {
        this.character = character;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public Positions getP() {
        return p;
    }

    public void setP(Positions p) {
        this.p = p;
    }

    public float getTargetXPosition() {
        return targetXPosition;
    }

    public void setTargetXPosition(float targetXPosition) {
        this.targetXPosition = targetXPosition;
    }

    public Boolean getReadyForAction() {
        return readyForAction;
    }

    public void setReadyForAction(Boolean readyForAction) {
        this.readyForAction = readyForAction;
    }

    public ArrayList<Actions> getQueue() {
        return Queue;
    }

    public void setQueue(ArrayList<Actions> queue) {
        Queue = queue;
    }

    public void update() {
        if (readyForAction && !Queue.isEmpty()) {

            if (!animated) {
                System.out.println("...Executing Action");
                System.out.println(Queue.size());
                System.out.println(Queue);
                counter++;
            }
            Actions a;
            //we go to the right
            if ((a = Queue.get(0)) == Actions.AVANT) {
                // sets the position
                if (!animated) {
                    doAction(a);
                }

                if (xposition < targetXPosition) {
                    xposition += dx;

                    if (!donneMessage) System.out.println(nom + " goes right!");

                    animated = Boolean.TRUE;
                    donneMessage = Boolean.TRUE;


                } else {
                    Queue.remove(0);
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;

                }

            }
            //we go to the Left
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.ARRIERE)) {
                // sets the position
                if (!animated)
                    doAction(a);
                if (xposition > targetXPosition) {

                    xposition -= dx;

                    if (!donneMessage) System.out.println(nom + " goes Left !");

                    animated = Boolean.TRUE;
                    donneMessage = Boolean.TRUE;


                } else {
                    Queue.remove(0);
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;

                }

            }
            //Go Down !
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.BAS)) {
                // sets the position
                if (!animated)
                    doAction(a);
                if (yposition < targetYPosition) {

                    yposition += dy;

                    if (!donneMessage) System.out.println(nom + " goes Down !");

                    animated = Boolean.TRUE;
                    donneMessage = Boolean.TRUE;


                } else {
                    Queue.remove(0);
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;

                }
            }
            //GO UP !
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.HAUT)) {
                // sets the position
                if (!animated)
                    doAction(a);
                if (yposition > targetYPosition) {

                    yposition -= dy;
                    if (!donneMessage) System.out.println(nom + " goes UP !");

                    animated = Boolean.TRUE;
                    donneMessage = Boolean.TRUE;


                } else {
                    Queue.remove(0);
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;

                }

            }
            //Braque
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.BRAQUE)) {

                //  Braque !

                doAction(a);
                if (!found) {
                    System.out.println(" Pas de Butin a Braquer ! ");
                    Queue.remove(0);
                }
                found = Boolean.FALSE;

            }
            //Tire a droite
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.TIRE_DROITE)) {

                //  Tire !

                doAction(a);

                //clean up

                if (!found) {
                    System.out.println(" il n' y a personne ! ");
                    Queue.remove(0);
                }
                found = Boolean.FALSE;

            }
            //tire a gauche
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.TIRE_GAUCHE)) {

                //  Tire !

                doAction(a);

                //clean up

                if (!found) {
                    System.out.println(" il n 'y a personne ! ");
                    Queue.remove(0);
                }
                found = Boolean.FALSE;

            }
            //tire a l'haut
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.TIRE_HAUT)) {

                //  Tire !

                doAction(a);

                //clean up

                if (!found) {
                    System.out.println(" il n 'y a personne ! ");
                    Queue.remove(0);
                }
                found = Boolean.FALSE;

            }
            //tirer vers le bas
            else if ((!Queue.isEmpty()) && ((a = Queue.get(0)) == Actions.TIRE_BAS)) {

                //  Tire !
                doAction(a);
                //clean up
                if (!found) {
                    System.out.println(" il n 'y a personne ! ");
                    Queue.remove(0);
                }
                found = Boolean.FALSE;
            }
            System.out.println(actionFinished);
        } else {

            // we Arrived !
            animated = Boolean.FALSE;
            readyForAction = Boolean.FALSE;
            //when the counter is 2 means 2 actions have been executed and we can change turns
            if (counter == 2) {
                actionFinished = Boolean.TRUE;
                counter = 0;
            }
            // change the Turn !
            if (actionFinished) {
                System.out.println(" SWITCH :D ");
                if (gp.getTurns().get(gp.getPlayer1()).equals(Boolean.TRUE)) {

                    gp.getTurns().put(gp.getPlayer1(), Boolean.FALSE);
                    gp.getTurns().put(gp.getPlayer2(), Boolean.TRUE);

                } else {
                    gp.getTurns().put(gp.getPlayer1(), Boolean.TRUE);
                    gp.getTurns().put(gp.getPlayer2(), Boolean.FALSE);
                }
                actionFinished = Boolean.FALSE;
            }

        }

    }

    public void doAction(Actions a) {

        switch (a) {
            // for positions we set position
            case AVANT: {
                if (xposition == Positions.POSITION_1X.getAction()) {
                    targetXPosition = Positions.POSITION_2X.getAction();
                }

                if (xposition == Positions.POSITION_2X.getAction()) {
                    targetXPosition = Positions.POSITION_3X.getAction();
                }
                if (xposition == Positions.POSITION_3X.getAction()) {
                    targetXPosition = Positions.POSITION_4X.getAction();
                }
                if (xposition == Positions.POSITION_4X.getAction()) {
                    System.out.println("you can't go further to the right");
                }

                break;
            }
            case ARRIERE: {
                if (xposition == Positions.POSITION_2X.getAction()) {
                    targetXPosition = Positions.POSITION_1X.getAction();
                }

                if (xposition == Positions.POSITION_3X.getAction()) {
                    targetXPosition = Positions.POSITION_2X.getAction();
                }
                if (xposition == Positions.POSITION_4X.getAction()) {
                    targetXPosition = Positions.POSITION_3X.getAction();
                }
                if (xposition == Positions.POSITION_1X.getAction()) {
                    System.out.println("you can't go further to the Left ");
                }

                break;
            }
            case HAUT: {

                if (yposition == Positions.POSITION_BOTTOM_Y.getAction()) {
                    targetYPosition = Positions.POSITION_TOP_Y.getAction();

                }
                if (yposition == Positions.POSITION_TOP_Y.getAction()) {
                    targetYPosition = Positions.POSITION_TOP_Y.getAction();
                    System.out.println("cant go further up");

                }
                break;
            }
            case BAS: {
                if (yposition == Positions.POSITION_TOP_Y.getAction()) {

                    targetYPosition = Positions.POSITION_BOTTOM_Y.getAction();
                }

                if (yposition == Positions.POSITION_BOTTOM_Y.getAction()) {
                    System.out.println("cant go further Down ");
                }
                break;
            }
            case BRAQUE: {

                List<Butin> bt = gp.getButins();

                for (Butin b : bt) {
                    if ((b.getClass().getName() != "Droppedbutin")) {

                        //sinon il doit etre dans l'interieur du train
                        if (xposition == b.getXposition() && (yposition == Positions.POSITION_BOTTOM_Y.getAction())) {

                            found = true;
                            if (!donneMessage) {
                                System.out.println(nom + " BRAQUE !");
                                Braquer(b);
                                Queue.remove(0);
                            }
                            animated = Boolean.FALSE;
                            donneMessage = Boolean.FALSE;
                        }
                    } else {

                        // si c'est un DroppedButin il peut etre sur la toit
                        // donc on verifie les deux
                        if (xposition == b.getXposition() && (yposition == b.getYposition())) {
                            // je suis situé dans la meme position que celle du Butin
                            found = true;
                            if (!donneMessage) {
                                System.out.println(nom + " BRAQUE !");
                                Braquer(b);
                                Queue.remove(0);
                            }
                            animated = Boolean.FALSE;
                            donneMessage = Boolean.FALSE;

                        }
                    }

                }
                break;
            }
            case TIRE_DROITE: {

                int vise = Positions.returnRightpos((int) xposition);
                if (vise == getOtherPlayer().getXposition() && vise != 0) {
                    //we found him

                    found = true;

                    if (!donneMessage) {
                        System.out.println(nom + " BOOM !");
                        //the other player drops his loot
                        Droppedbutin bt = getOtherPlayer().dropLoot(getOtherPlayer().getScore(),
                                getOtherPlayer().getXposition(), getOtherPlayer().getYposition());
                        //Loot added to GameController
                        gp.getButins().add(bt);
                        // the other player is knocked back
                        int nouvelpos = Positions.returnRightpos((int) getOtherPlayer().getXposition());
                        if (nouvelpos != 0)
                            getOtherPlayer().setXposition(nouvelpos);
                        //Clean up
                        Queue.remove(0);

                    }
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;
                }

                break;
            }
            case TIRE_GAUCHE: {

                int vise = Positions.returnLeftpos((int) xposition);
                if (vise == getOtherPlayer().getXposition() && vise != 0) {
                    //we found him

                    found = true;

                    if (!donneMessage) {
                        System.out.println(nom + " BOOM !");
                        //the other player drops his loot
                        Droppedbutin bt = getOtherPlayer().dropLoot(getOtherPlayer().getScore(),
                                getOtherPlayer().getXposition(), getOtherPlayer().getYposition());
                        //Loot added to GameController
                        gp.getButins().add(bt);
                        // the other player is knocked back
                        int nouvelpos = Positions.returnLeftpos((int) getOtherPlayer().getXposition());
                        if (nouvelpos != 0)
                            getOtherPlayer().setXposition(nouvelpos);
                        //Clean up
                        Queue.remove(0);

                    }
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;
                }

                break;
            }
            case TIRE_HAUT: {

                int vise = Positions.returnToppos((int) yposition);
                if (vise == getOtherPlayer().getYposition() && vise != 0) {

                    //we found him

                    found = true;

                    if (!donneMessage) {
                        System.out.println(nom + " BOOM !");
                        //the other player drops his loot
                        Droppedbutin bt = getOtherPlayer().dropLoot(getOtherPlayer().getScore(),
                                getOtherPlayer().getXposition(), getOtherPlayer().getYposition());

                        //Loot added to GameController
                        gp.getButins().add(bt);

                        //Clean up
                        Queue.remove(0);

                    }
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;
                }

                break;
            }
            case TIRE_BAS: {

                int vise = Positions.returnBottompos((int) yposition);
                if (vise == getOtherPlayer().getYposition() && vise != 0) {
                    //we found him

                    found = true;

                    if (!donneMessage) {
                        System.out.println(nom + " BOOM !");
                        //the other player drops his loot
                        Droppedbutin bt = getOtherPlayer().dropLoot(getOtherPlayer().getScore(),
                                getOtherPlayer().getXposition(), getOtherPlayer().getYposition());

                        //Loot added to GameController
                        gp.getButins().add(bt);

                        //Clean up
                        Queue.remove(0);

                    }
                    animated = Boolean.FALSE;
                    donneMessage = Boolean.FALSE;
                }

                break;
            }


        }
    }

    private Bandit getOtherPlayer() {
        if (this != gp.getPlayer2()) {
            return gp.getPlayer2();
        } else {
            return gp.getPlayer1();
        }

    }


    public void draw(Graphics2D g) {

        g.drawImage(
                character,
                (int) xposition,
                (int) yposition,
                16, 23
                ,
                null
        );
    }

    public Droppedbutin dropLoot(int val, float xpos, float ypos) {
        Droppedbutin bt = new Droppedbutin("/Resources/Butins/Dropped.png", val, (int) xpos, (int) ypos);
        return (bt);
    }
}
