import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Background {

    private BackgroundElement sky;
    private BackgroundElement mountain1;
    private BackgroundElement mountain2;
    private BackgroundElement tree;
    private BackgroundElement bush;
    private BackgroundElement rails;


    public Background() {

        try {

            sky = new BackgroundElement("/Resources/Background/bg.png", 1);
            sky.setVector(-0.05, 0);
            mountain1 = new BackgroundElement("/Resources/Background/mnt1.png", 1);
            mountain1.setVector(-0.1, 0);
            mountain2 = new BackgroundElement("/Resources/Background/mnt2.png", 1);
            mountain2.setVector(-0.1, 0);
            tree = new BackgroundElement("/Resources/Background/tree.png", 1);
            bush = new BackgroundElement("/Resources/Background/effect.png", 1);
            tree.setVector(-0.1, 0);
            tree.setPosition(0, GameEngine.HEIGHT + 81);
            bush.setPosition(0, GameEngine.HEIGHT + 83);

                //importing resources
                BufferedImage image = ImageIO.read(
                        getClass().getResourceAsStream("/Resources/Background/Train.png")
                );

                BufferedImage im = image.getSubimage(0,
                        925,
                        288,
                        100
                );

            rails = new BackgroundElement(im);
                rails.setPosition(230,183);
                rails.setVector(-2,0);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {

        sky.drawSpecific(g, GameEngine.WIDTH+25, GameEngine.HEIGHT);
        mountain1.drawSpecific(g, GameEngine.WIDTH+25, GameEngine.HEIGHT);
        mountain2.drawSpecific(g, GameEngine.WIDTH+25, GameEngine.HEIGHT);
        rails.drawSpecific(g, GameEngine.WIDTH,25);
        bush.draw(g);tree.draw(g);
    }

    public void update() {

        sky.update();
        mountain1.update();
        mountain2.update();
        tree.update();
        rails.update();

    }


}






















