import javax.imageio.ImageIO;

public class Droppedbutin extends Butin {
    public Droppedbutin(String src ,int val, int xpos, int ypos) {

        //on prend tous l'image
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.valeur = val;
        this.xposition = xpos;
        //les Butin sont a l'interieur
        this.yposition = ypos;
    }

    @Override
    public void update() {

    }


}
