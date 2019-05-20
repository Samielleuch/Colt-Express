import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundElement {
    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    public BackgroundElement(String s, double ms) {
        try {
            //importing resources
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BackgroundElement(BufferedImage image) {
        this.image = image;
    }

    public void setPosition(double x, double y) {

        this.x = x % GamePanel.WIDTH;
        this.y = y % GamePanel.HEIGHT;
    }


    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
        if (x < -GamePanel.WIDTH) {
            x = 0;
        }

    }
    //adds more control on the dimensions of the draw

    public void drawSpecific(Graphics2D g, int width, int height) {

        //draw the background
        g.drawImage(image, (int) x, (int) y, width, height, null);
        //keep redrawing new image if moves away
        if (x < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y,
                    width, height, null);
        }
        if ((int) x > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH,
                    (int) y, width, height, null);
        }

    }

    public void draw(Graphics2D g) {
        //draw the background
        g.drawImage(image, (int) x, (int) y, null);

        g.fillRect((int) x, (int) x, (int) x, (int) x);
        //keep redrawing new image if moves away
        if (x < 0) {
            g.drawImage(
                    image,
                    (int) x + GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }
        if (x > 0) {
            g.drawImage(
                    image,
                    (int) x - GamePanel.WIDTH,
                    (int) y,
                    null
            );
        }

    }

}
