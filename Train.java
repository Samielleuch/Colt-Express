import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Train {

    private BufferedImage tileSet ;
    private BufferedImage trainHead ;
    private BufferedImage wagons;
    private float xhead;
    private float yhead;
    private float[] xwagons = new float[3];
    private float[] ywagons = new float[3];

    public Train(float xhead, float yhead , String src ) {
        try {
            tileSet = ImageIO.read(
                    getClass().getResourceAsStream(src));
        }catch(Exception e ) {
            e.printStackTrace();
        }

        trainHead = tileSet.getSubimage(
                16,
                27,
                259,
                130
        );
        wagons = tileSet.getSubimage(
                9,
                300,
                259,
                130
        );
        this.xhead = xhead;
        this.yhead = yhead;

        xwagons[0] = xhead - 64 ;
        ywagons[0] = yhead;

        for(int i = 1 ; i<3 ; i++ ){
            xwagons[i] = xwagons[i-1]-64 ;
            ywagons[i] = ywagons[i-1] ;
        }
    }


    public void draw(Graphics2D g) {

        g.drawImage(
                trainHead,
                (int)xhead,
                (int)yhead,
                72, 40
                ,
                null
        );

        //draw wagons
        for (int i = 0; i < 3; i++) {
        g.drawImage(
                wagons,
                (int)xwagons[i],
                (int)ywagons[i],
                72, 40
                ,
                null
        );
        }


    }







}
