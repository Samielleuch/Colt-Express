import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Marshall {

    private BufferedImage tileSet;
    private BufferedImage character;
    private static final float NERVOSITE_MARSHALL = 0.3f;
    private float xposition;
    private float yposition;
    private float targetXPosition = Positions.POSITION_4X.getAction();
    private float dx = 0.45f;
    private GameController gp;
    private Actions[] possibilities = {Actions.AVANT, Actions.ARRIERE};
    private Random rand = new Random();
    private HashMap<Actions, BufferedImage> sprite;


    //for animation

    private Boolean versAvant = true;
    private Boolean versDeriere = false;


    public Marshall(float xposition, float yposition, String src, int x, int y, GameController gp) {
        sprite = new HashMap<>();
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }

        character = Objects.requireNonNull(tileSet).getSubimage(
                x,
                y + 45,
                33,
                54
        );

        sprite.put(Actions.HAUT, character);

        character = Objects.requireNonNull(tileSet).getSubimage(
                x,
                y - 90,
                33,
                54
        );

        sprite.put(Actions.BAS, character);

        character = Objects.requireNonNull(tileSet).getSubimage(
                x,
                y - 45,
                33,
                54
        );

        sprite.put(Actions.ARRIERE, character);


        character = Objects.requireNonNull(tileSet).getSubimage(
                x,
                y,
                33,
                54
        );

        sprite.put(Actions.AVANT, character);

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
            versAvant=true;
            versDeriere=false;
            xposition += dx;
        } else if (xposition > targetXPosition) {
            xposition -= dx;
            versAvant=false;
            versDeriere=true;

        }

    }

    private void drawHelper(Graphics2D g) {

        if (versAvant) {

            g.drawImage(
                    sprite.get(Actions.AVANT),
                    (int) xposition,
                    (int) yposition,
                    16, 23
                    ,
                    null
            );

        } else if (versDeriere) {

            g.drawImage(
                    sprite.get(Actions.ARRIERE),
                    (int) xposition,
                    (int) yposition,
                    16, 23
                    ,
                    null
            );
        }

    }


    public void draw(Graphics2D g) {

        drawHelper(g);
    }
}