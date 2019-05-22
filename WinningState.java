import com.sun.tools.javac.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;


public class WinningState extends GameState {
    private BackgroundElement bg;
    private String  menu = "Quit";
    private String  gagnant = "";


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
        init();
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Graphics2D g) {
        //draw background
        bg.draw(g);

        //draw menu
        g.setColor(Color.white);
        g.setFont(new Font ("Century Gothic",Font.PLAIN , 19 ));
        g.drawString(gagnant,15,70);
        g.setFont(new Font ("Century Gothic",Font.PLAIN , 16 ));
        g.drawString(menu,145,150);
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

    @Override
    public void init() {
        new Thread(new Runnable() {
            public void run() {

                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("/Resources/Sounds/son.wav"));
                    clip.open(inputStream);
                    System.out.println("ko");
                    clip.start();
                    System.out.println("ko");
                } catch (
                        Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        }).start();
    }
}


