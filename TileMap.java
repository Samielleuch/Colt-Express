import java.awt.*;


public class TileMap {

    private Background bg;


    public  TileMap() {

        try {

            bg = new Background("/Resources/Background/menubg.gif", 1);
            bg.setVector(-0.1, 0);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {

        bg.draw(g);

    }

    public void update() {

        bg.update();

    }

}






















