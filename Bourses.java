import javax.imageio.ImageIO;
import java.util.Random;

public class Bourses extends Butin {

    public Bourses(String src) {

        //on prend tous l'image
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Random rand = new Random();

        this.valeur = rand.nextInt(501);

        this.xposition = Positions.returnRandomXPostion().getAction();
        //les Butin sont a l'interieur
        this.yposition = Positions.POSITION_BOTTOM_Y.getAction();
    }

    @Override
    public void update() {

    }




}
