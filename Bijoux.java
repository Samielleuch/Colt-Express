import javax.imageio.ImageIO;

public class Bijoux extends Butin {

    public Bijoux(String src) {

        //on prend tous l'image
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.valeur = 500;
        this.xposition = Positions.returnRandomXPostion().getAction();
        //les Butin sont a l'interieur
        this.yposition = Positions.POSITION_BOTTOM_Y.getAction();
    }

    @Override
    public void update() {

    }


}
