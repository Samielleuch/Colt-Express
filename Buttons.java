import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Buttons extends JPanel {

    private JButton haut = new JButton("Haut");
    private JButton bas = new JButton("Bas");
    private JButton droite = new JButton("Droite");
    private JButton gauche = new JButton("Gauche");
    private JButton tire_haut = new JButton("Tire Haut");
    private JButton tire_bas = new JButton("Tire Bas");
    private JButton tire_droite = new JButton("Tire Droite");
    private JButton tire_gauche = new JButton("Tire Gauche");
    private JButton braque = new JButton("Braque");
    private JButton go = new JButton("Action !");
    private GameEngine gp;
    private GameController gc;

    public Buttons(GameEngine gp) {
        // associe le GameEngine!

        this.gp = gp;
        gc = (GameController) gp.getGsm().getGameStates().get(1);
        //ajout des bouttons
        droite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gc.QueuemoveRight();
            }
        });

        gauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueuemoveLeft();
            }
        });
        haut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueuemoveUP();
            }
        });
        bas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueuemoveDown();
            }
        });
        tire_haut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueueTireHaut();
            }
        });
        tire_bas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueueTireBas();
            }
        });
        tire_droite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueueTireDroite();
            }
        });
        tire_gauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueueTireGauche();
            }
        });
        braque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.QueueBraque();
            }
        });
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if its player 1 turn
                if ((gc.getTurns().get(gc.getPlayer1()) == Boolean.TRUE)) {
                    if (gc.getPlayer1().getQueue().size() == 2) {
                        // he is ready !
                        gc.getPlayer1().setReadyForAction(Boolean.TRUE);
                        gc.setPhase(" *ACTION* ");
                    } else {
                        System.out.println("il faut terminer la phase de planification !! ");
                    }
                } else {
                    //player 2 turn
                    if (gc.getPlayer2().getQueue().size() == 2) {
                        // he is ready !
                        gc.getPlayer2().setReadyForAction(Boolean.TRUE);

                        gc.setPhase(" *ACTION* ");

                    } else {
                        System.out.println("il faut terminer la phase de planification !! ");
                    }
                }
            }

        });
        this.add(haut);
        this.add(bas);
        this.add(droite);
        this.add(gauche);
        this.add(tire_haut);
        this.add(tire_bas);
        this.add(tire_droite);
        this.add(tire_gauche);
        this.add(braque);
        this.add(go);
        this.setBackground(Color.GRAY);
    }
}

