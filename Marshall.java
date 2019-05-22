import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Marshall {

    private BufferedImage tileSet;
    private BufferedImage character;
    public static final float NERVOSITE_MARSHALL = 0.3f;
    private float xposition;
    private float yposition;
    private float targetXPosition=Positions.POSITION_4X.getAction();
    private float dx = 0.65f;
    private GameController gp;
    private Actions[] possibilities = {Actions.AVANT, Actions.ARRIERE};
    private Random rand = new Random();

    // indique que le marshall est arrivé
    private Boolean arriveDestination = Boolean.FALSE;


    public Marshall(float xposition, float yposition, String src, int x, int y, GameController gp) {
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }
        character = tileSet.getSubimage(
                x,
                y,
                33,
                54
        );
        this.xposition = xposition;
        this.yposition = yposition;
        this.gp = gp;

    }

    private void calculerPosition() {

        //probabilité = nervositéMarchall
        if (rand.nextDouble() <= Marshall.NERVOSITE_MARSHALL) {
            int ind = rand.nextInt(2);

            if (possibilities[ind].equals(Actions.AVANT)) {

                // le Marshall avence (a droite)
                int result = Positions.returnRightpos((int) xposition);
                if (result != 0)
                    targetXPosition = result;
            } else {
                // le Marshall recule (a gauche)
                int result = Positions.returnLeftpos((int) xposition);

                if (result != 0)
                    targetXPosition = result;
            }
        }
    }

    public float getXposition() {
        return xposition;
    }

    public float getYposition() {
        return yposition;
    }

    public void update() {
        calculerPosition();
        if (xposition < targetXPosition) {
            xposition += dx;
        }
            else if (xposition > targetXPosition) {
            xposition -= dx;
        }

    }




    public void draw(Graphics2D g) {

        g.drawImage(character, (int) xposition, (int) yposition, 16, 23, null);
    }
}