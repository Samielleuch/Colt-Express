import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Butin {

    public static final int NB_BUTTIN = 4;
    int valeur;

    int xposition;
    int yposition;
    BufferedImage tileSet;
    private Boolean exists = Boolean.TRUE;

    public void draw(Graphics2D g) {
        if (exists)
            g.drawImage(tileSet, xposition, yposition, 17, 17, null);

    }

    public abstract void update();

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void braquer() {
        exists = Boolean.FALSE;
    }

    public int getXposition() {
        return xposition;
    }

    public int getYposition() {
        return yposition;
    }
}
