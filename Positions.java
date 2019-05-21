import java.util.Random;

public enum Positions {

    POSITION_1X(49),
    POSITION_2X(115),
    POSITION_3X(180),
    POSITION_4X(243),
    POSITION_BOTTOM_Y(171),
    POSITION_TOP_Y(147);

    // declaring private variable for getting values
    private int action;

    // getter method
    public int getAction() {
        return this.action;
    }

    Positions(int action) {
        this.action = action;
    }

    public static Positions returnRandomXPostion() {

        Positions[] tab =
                {POSITION_1X,
                        POSITION_2X,
                        POSITION_3X,
                        POSITION_4X};

        Random rand = new Random();
        return (tab[rand.nextInt(4)]);

    }

}
