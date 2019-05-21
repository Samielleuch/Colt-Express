import javax.imageio.ImageIO;
import java.awt.*;

public class Bijoux extends Butin {

    public Bijoux(String src) {

        //on prend tous l'image
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //on prend ce qui nous concerne
        image = tileSet.getSubimage(
                60,
                95,
                33,
                54
        );

        this.valeur = 500;
        this.xposition = Positions.returnRandomXPostion().getAction();
        //les Butin sont a l'interieur
        this.yposition = Positions.POSITION_BOTTOM_Y.getAction();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, xposition, yposition, null);
    }

}
