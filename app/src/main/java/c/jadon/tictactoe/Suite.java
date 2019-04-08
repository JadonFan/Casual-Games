package c.jadon.tictactoe;

public enum Suite {
    HEART(1),
    DIAMOND(2),
    CLUB(3),
    SPADE(4);

    private int typeID;

    private Suite(int typeID) {
        this.typeID = typeID;
    }
}
