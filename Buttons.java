import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel {

    JButton haut = new JButton("Haut");
    JButton bas = new JButton("Bas");
    JButton droite = new JButton("Droite");
    JButton gauche = new JButton("Gauche");
    JButton tire_haut = new JButton("Tire Haut");
    JButton tire_bas = new JButton("Tire Bas");
    JButton tire_droite = new JButton("Tire Droite");
    JButton tire_gauche = new JButton("Tire Gauche");
    JButton braque = new JButton("Braque");
    JButton go = new JButton("Action !");
    GamePanel gp;
    GameController gc;

    public Buttons(GamePanel gp) {
        // associe le GamePanel!

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
                System.out.println("droite dude ");
            }
        });
        tire_bas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("droite dude ");
            }
        });
        tire_droite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("droite dude ");
            }
        });
        tire_gauche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("droite dude ");
            }
        });
        braque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("droite dude ");
            }
        });
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(gc.getPlayer1().getQueue());
                // if its player 1 turn
                if ((gc.getTurns().get(gc.getPlayer1()) == Boolean.TRUE)) {
                    if (gc.getPlayer1().getQueue().size() == 2) {
                        // he is ready !
                        gc.getPlayer1().setReadyForAction(Boolean.TRUE);
                    }


                } else {
                    System.out.println("il faut terminer la phase de planification !! ");
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

