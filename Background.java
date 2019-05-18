import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage image ;

    private double x;
    private double y;
    private double dx;
    private double dy;

    public Background(String s , double ms) {
        try {
            //importing resources
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void setPosition(double x, double y ) {

            this.x = x%GamePanel.WIDTH;
            this.y = y%GamePanel.HEIGHT;
    }

        public void setVector(double dx, double dy ) {
            this.dx = dx;
            this.dy = dy;
        }

        public void update() {
            x+=dx;
            y+=dy;
        }

        public void draw (Graphics2D g){
        //draw the background
        g.drawImage(image,(int)x ,(int)y,null );

        //keep redrawing new image if moves away
            if(x < 0) {
                g.drawImage(
                        image,
                        (int)x + GamePanel.WIDTH,
                        (int)y,
                        null
                );
            }
            if(x > 0) {
                g.drawImage(
                        image,
                        (int)x - GamePanel.WIDTH,
                        (int)y,
                        null
                );
            }

    }
}
