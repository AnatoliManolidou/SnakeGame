public class Ladder { //Ladder Class, contains the basics of a class and is used for the ladders of the game

    int ladderId, topSquareId, bottomSquareId;
    boolean broken;

    public Ladder(Ladder L1){ //Copy Constructor

        bottomSquareId = L1.bottomSquareId;
        ladderId = L1.ladderId;
        topSquareId = L1.topSquareId;
        broken = L1.broken;
    }

    public Ladder(int ladderId, int topSquareId, int bottomSquareId){ //Parameterized Constructor

        this.ladderId = ladderId;
        this.topSquareId = topSquareId;
        this.bottomSquareId = bottomSquareId;
    }
    public Ladder(){ //Void Constructor, setting variables to zero and false

        bottomSquareId = 0;
        ladderId = 0;
        topSquareId = 0;
        broken = false;
    }

    public void setLadderId(int ladderId) {
        this.ladderId = ladderId;
    } //Setter for the ladder's id

    public void setTopSquareId(int topSquareId) {
        this.topSquareId = topSquareId;
    } //Setter for the id of the ladder's top square

    public void setBottomSquareId(int bottomSquareId) {
        this.bottomSquareId = bottomSquareId;
    } //Setter for the id of the ladder's bottom square

    public void setBroken(boolean broken) {
        this.broken = broken;
    } //Setter for when the ladder is broken

    public int getLadderId() {
        return ladderId;
    } //Getter for the ladder's id

    public int getTopSquareId() {
        return topSquareId;
    }//Getter for the id of the ladder's top square

    public int getBottomSquareId() {
        return bottomSquareId;
    }//Getter for the id of the ladder's bottom square

    public boolean isBroken() {
        return broken;
    }//Returns true if the ladder is broken
}