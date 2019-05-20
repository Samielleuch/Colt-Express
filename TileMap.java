import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class TileMap {

    private Background sky;
    private Background mountain1;
    private Background mountain2;
    private Background tree;
    private Background bush;
    private Background rails;


    public  TileMap() {

        try {

            sky = new Background("/Resources/Background/bg.png", 1);
            sky.setVector(-0.1, 0);
            mountain1 = new Background("/Resources/Background/mnt1.png", 1);
            mountain1.setVector(-0.05, 0);
            mountain2 = new Background("/Resources/Background/mnt2.png", 1);
            mountain2.setVector(-0.05, 0);
            tree = new Background("/Resources/Background/tree.png", 1);
            bush = new Background("/Resources/Background/effect.png", 1);
            tree.setVector(-0.05, 0);
            tree.setPosition(0,GamePanel.HEIGHT+70);
            bush.setPosition(0,GamePanel.HEIGHT+70);

                //importing resources
                BufferedImage image = ImageIO.read(
                        getClass().getResourceAsStream("/Resources/Background/Train.png")
                );

                BufferedImage im = image.getSubimage(0,
                        925,
                        288,
                        100
                );

                rails= new Background(im);
                rails.setPosition(230,183);
                rails.setVector(-2,0);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {

        sky.drawSpecific(g,GamePanel.WIDTH+25,GamePanel.HEIGHT);
        mountain1.drawSpecific(g,GamePanel.WIDTH+25,GamePanel.HEIGHT);
        mountain2.drawSpecific(g,GamePanel.WIDTH+25,GamePanel.HEIGHT);
        rails.drawSpecific(g,GamePanel.WIDTH,25);
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






















