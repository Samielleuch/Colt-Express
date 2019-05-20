public enum Positions {

    POSITION_BOTTOM_1X(49),
    POSITION_BOTTOM_1Y(171),
    POSITION_BOTTOM_2X(115),
    POSITION_BOTTOM_2Y(171),
    POSITION_BOTTOM_3X(180),
    POSITION_BOTTOM_3Y(171),
    POSITION_BOTTOM_4X(243),
    POSITION_BOTTOM_4Y(170),
    POSITION_TOP_1X(49),
    POSITION_TOP_1Y(147),
    POSITION_TOP_2X(115),
    POSITION_TOP_2Y(147),
    POSITION_TOP_3X(180),
    POSITION_TOP_3Y(147),
    POSITION_TOP_4X(243),
    POSITION_TOP_4Y(147);

    // declaring private variable for getting values
    private int action;

    // getter method
    public int getAction() {
        return this.action;
    }

    Positions(int action) {
        this.action = action;
    }
}
