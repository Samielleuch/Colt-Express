import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bandit {

    private BufferedImage tileSet ;
    private BufferedImage character ;
    private float xposition ;
    private float yposition ;
    private float dx = 0.5f;
    private float dy = 0.5f;

    public Bandit(float xposition, float yposition , String src ) {
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        }catch(Exception e ) {
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

    }

    public void update() {
  if(xposition < 250 )
     xposition+=dx;
    }

    public void draw(Graphics2D g) {

        g.drawImage(
                character,
                (int)xposition ,
                (int)yposition ,
                16, 23
                ,
                null
        );



    }
}
