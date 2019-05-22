import javax.imageio.ImageIO;

public class Magot extends Butin {

    public Magot(String src) {

        //on prend tous l'image
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.valeur = 1000;

        //toujours dans la locomotive
        this.xposition = Positions.POSITION_4X.getAction();
        //les Butin sont a l'interieur
        this.yposition = Positions.POSITION_BOTTOM_Y.getAction();
    }

    @Override
    public void update() {

    }

}
