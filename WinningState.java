import java.awt.*;
import java.awt.event.KeyEvent;


public class WinningState extends GameState {
    private BackgroundElement bg;
    private String menu = "Quit";
    private String gagnant = "";
    static AudioPlayer ap;
    Boolean b =false;


    public void setGagnant(String gagnant) {
        this.gagnant = gagnant;
    }

    public WinningState(GameStateManager gsm) {
        this.gsm = gsm;
        try {

            bg = new BackgroundElement("/Resources/Background/win.gif", 1);
            //moves to the left
            bg.setVector(-3, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        bg.update();
       init();
    }

    @Override
    public void draw(Graphics2D g) {
        //draw background
        bg.draw(g);

        //draw menu
        g.setColor(Color.white);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 19));
        g.drawString(gagnant, 15, 70);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        g.drawString(menu, 145, 150);
    }


    private void select() {
        //quit
        System.exit(0);
    }


    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
    }

    public void keyReleased(int k) {
    }


    public void init() {
        if (!b) {
            GameController gc = (GameController) gsm.getGameStates().get(GameStateManager.GAMECONTROLLERSTATE);
            gc.getSfx().get("Theme").close();
            ap = new AudioPlayer("/Resources/Sounds/son.mp3");
            ap.play();
        }
    }
}


