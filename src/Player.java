public class Player { //Player class, contains the basics of a class and the method move

    int playerId;
    String name;
    int score;
    Board board;

    public Player(){ //Void constructor, setting variables to zero

        playerId = 0;
        score = 0;
    }

    public Player(int playerId, String name, int score, Board board){ //Parameterized Constructor

        this.playerId = playerId;
        this.name = name;
        this.score = score;
        this.board = board;
    }
    public void setBoard(Board board) {
        this.board = board;
    } //Setter for the player's board

    public void setName(String name) {
        this.name = name;
    } //Setter for the player's name

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    } //Setter for the player's id

    public void setScore(int score) {
        this.score = score;
    } //Setter for the player's score

    public Board getBoard() {
        return board;
    } //Getter for the player's board

    public int getPlayerId() {
        return playerId;
    } //Getter for the player's id

    public int getScore() {
        return score;
    } //Getter for the player's score

    public String getName() {
        return name;
    }//Getter for the player's name

    public int[] move(int id, int die){ //Method for moving the player on the board

        int tile = (id + die); //This is where the player is going to land after rolling the dice
        int[] status = {0, 0, 0, 0}; //Array for storing the statistics of each round

        for(int i = 0; i < board.snakes.length; i++){ //Checking if there are any snakes at the landing square(tile)

            if(tile == board.snakes[i].getHeadId()){
                tile = board.snakes[i].getTailId();
                System.out.println("You just got bit by a snake! ");
                status[1]++;
            }
        }

        for(int i = 0; i < board.ladders.length; i++){ //Checking if there are any ladders at the landing square(tile)

            if(tile == board.ladders[i].getBottomSquareId() && !board.ladders[i].isBroken()){
                tile = board.ladders[i].getTopSquareId();
                board.ladders[i].setBroken(true); //Breaking the ladder after one use
                System.out.println("You just got lifted! ");
                status[2]++;
            }
        }

        for(int i = 0; i < board.presents.length; i++){ //Checking if there are any presents at the landing square(tile)

            if(tile == board.presents[i].getPresentSquareId() && board.presents[i].getPoints() != 0) {

                score = board.presents[i].getPoints() + score;
                System.out.println("You just won a present!Your bonus is " + board.presents[i].getPoints() + " points");
                board.presents[i].setPoints(0); //Deleting the present after one use
                status[3]++;
            }
        }

        //if(status[3] == 0 && status[1]== 0 && status[2] == 0) //If the player was not bit, lifted, or he did not win any presents in this round, a "-" is going to appear at the console
            //System.out.println("---");

        status[0] = tile;

        return(status);
    }
}