public class Snake { //Snake Class, contains the basics of a class and is used for the snakes of the game

    int snakeId, headId, tailId;

    public Snake(Snake S1){ //Copy Constructor

        snakeId = S1.snakeId;
        headId = S1.headId;
        tailId = S1.tailId;
    }

    public Snake(int snakeId, int headId, int tailId){ //Parameterized Constructor

        this.tailId = tailId;
        this.headId = headId;
        this.snakeId = snakeId;
    }

    public Snake(){ //Void constructor, setting variables to zero

        snakeId = 0;
        headId = 0;
        tailId = 0;
    }

    public int getSnakeId(){
        return snakeId;
    } //Getter for the snake's id

    public int getHeadId(){
        return headId;
    } //Getter for the id of the snake's head

    public int getTailId(){ return tailId; } //Getter for the id of the snake's tail

    public void setSnakeId(int snakeId) {
        this.snakeId = snakeId;
    } //Setter for the snake's id

    public void setHeadId(int headId) {
        this.headId = headId;
    } //Setter for the id of the snake's head

    public void setTailId(int tailId) {
        this.tailId = tailId;
    } //Setter for the id of the snake's tail
}