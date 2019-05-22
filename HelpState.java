import java.awt.*;
import java.awt.event.KeyEvent;


public class HelpState extends GameState {
    private String goBack = "Go Back <PressEnter> !";
    private String[] rules;


    public HelpState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        rules = new String[]{"                                        LES REGLES", "",
                "", "Chaque joueur est un bandit et le but du jeu est de terminer ",
                " la partie en ayant récolté le plus gros magot.", "", "",

                "--Attention il faut éviter le Marshall qui se déplace aléatoirement à ",
                "l'intérieur des wagons !", "", "",
                "Le jeu se déroule en 3 tours. A chaque tour chaque bandit choisit ",
                "2 actionspuis les exécute", "",
                "Parmi les action possible :",
                "- déplacer son bandit (haut, bas, gauche, droite) ",
                "- ramasser un magot là où votre bandit se trouve (s'il en reste!)",
                "- tirer sur un bandit adverse, qui perd un magot"

        };
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        // clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameEngine.WIDTH, GameEngine.HEIGHT);
        //draw Rules
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 9));
        int i = 0;
        for (String rule : rules) {
            g.drawString(rule, 5, 10 + i);
            i += 10;
        }

        g.setColor(Color.magenta);

        g.drawString(goBack, 80, 220);

    }


    private void select() {

        gsm.setGameState(GameStateManager.MENUSTATE);
    }


    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
    }

    public void keyReleased(int k) {
    }
}
