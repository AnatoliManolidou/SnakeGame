public class Present {//Present Class, contains the basics of a class and is used for the presents of the game

    private int presentId, presentSquareId, points;

    public Present(Present P1){ //Copy Constructor

        presentId = P1.presentId;
        presentSquareId = P1.presentSquareId;
        points = P1.points;
    }

    public Present(int presentId, int presentSquareId, int points){ //Parameterized Constructor

        this.presentSquareId = presentSquareId;
        this.presentId = presentId;
        this.points = points;
    }
    public Present(){ //Void constructor, setting variables to zero

        presentId = 0;
        presentSquareId = 0;
        points = 0;
    }

    public int getPresentId() {
        return presentId;
    } //Getter for the present's id

    public int getPresentSquareId() {
        return presentSquareId;
    } //Getter for the present's square id

    public int getPoints() {
        return points;
    } //Getter for the points of the present

    public void setPresentId(int presentId) {
        this.presentId = presentId;
    } //Setter for the present's id

    public void setPresentSquareId(int presentSquareId) {
        this.presentSquareId = presentSquareId;
    } //Setter for the present's square id

    public void setPoints(int points) {
        this.points = points;
    } //Setter for the points of the present
}