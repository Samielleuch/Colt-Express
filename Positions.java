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

    public static int returnRightpos(int posx) {
        int result = 0;
        switch (posx) {
            case 49: {
                result = Positions.POSITION_2X.getAction();
                break;
            }
            case 115: {
                result = Positions.POSITION_3X.getAction();

                break;
            }
            case 180: {
                result = Positions.POSITION_4X.getAction();

                break;
            }
            case 243: {
                System.out.println("Nothing to the right !");
                break;
            }
        }
        return result;
    }

    public static int returnLeftpos(int posx) {
        int result = 0;
        switch (posx) {
            case 49: {
                System.out.println("Nothing to the Left !");
                break;
            }
            case 115: {

                result = Positions.POSITION_1X.getAction();
                break;
            }
            case 180: {
                result = Positions.POSITION_2X.getAction();

                break;
            }
            case 243: {
                result = Positions.POSITION_3X.getAction();

                break;
            }
        }
        return result;
    }

    public static int returnToppos(int posy) {
        int result = 0;
        switch (posy) {
            case 171: {
                result = Positions.POSITION_TOP_Y.getAction();
                break;
            }
            case 147: {
                System.out.println("Nothing to shoot in the skys !");
                break;
            }

        }
        return result;
    }

    public static int returnBottompos(int posy) {
        int result = 0;
        switch (posy) {
            case 171: {
                System.out.println(" Nothing to shoot  in the Ground  !");
                break;
            }
            case 147: {
                result = Positions.POSITION_BOTTOM_Y.getAction();
                break;
            }

        }
        return result;
    }





}
