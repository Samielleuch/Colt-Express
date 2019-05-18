import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;


public class TileMap {

    // position
    private double x;
    private double y;

    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;


    private BufferedImage train ;


    public void loadTiles(String s) {

        try {

            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );

            train = tileset.getSubimage(
                        16,
                        27,
                        259,
                        130
                );



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g) {

        g.drawImage(
                train,
                230,
                160,
                64, 32
              ,
                null
        );

    }


    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }




        }






















