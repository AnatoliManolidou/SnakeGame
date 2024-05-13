import java.util.ArrayList;

public class HeuristicPlayer extends Player{ //HeuristicPlayer class, inherits Player class and contains the basics of a class and the methods evaluate, getNextMove and statistics

    private ArrayList<Integer[]> Path; //Each array of Path contains the dice, score, steps, number of presents he got, number of snakes he got bit by, number of ladders he climbed and the number of each round

    public HeuristicPlayer(){ //Void constructor, invoking the parent class constructor with super() and initializing Path ArrayList

        super();
        Path = new ArrayList<>(7);
    }

    public HeuristicPlayer(int playerId, String name, int score, Board board, ArrayList<Integer[]> Path){ //Parameterized Constructor

        super.playerId = playerId;
        super.name = name;
        super.score = score;
        super.board = board;
        this.Path = Path;
    }

    public void setPath(ArrayList<Integer[]> Path){
        this.Path = Path;
    } //Setter for ArrayList Path

    public ArrayList<Integer[]> getPath(){
        return Path;
    }  //Getter for ArrayList Path

    public double evaluate(int currentPos, int dice){ //Method for evaluating which dice is better

        int nextPos = currentPos + dice; //This is where the player is going to land after rolling the dice
        int scorePos = 0; //Variable for storing the score after a movement on the board
        int steps; //Variable for storing the steps after each movement on the board
        int j = 0; //counter

        for(int i = 0; i < board.snakes.length; i++){ //Checking for snakes

            if(nextPos == board.snakes[i].getHeadId()){
                nextPos = board.snakes[i].getTailId();
            }
        }

        for(int i = 0; i < board.ladders.length; i++){ //Checking for ladders

            if(nextPos == board.ladders[i].getBottomSquareId() && !board.ladders[i].isBroken()){

                //if(board.ladders[i].getBottomSquareId() < board.ladders[j].getBottomSquareId() && board.ladders[i].getTopSquareId() > board.ladders[j].getTopSquareId())
                    nextPos = board.ladders[i].getTopSquareId();
            }
        }

        for(int i = 0; i < board.presents.length; i++){ //Checking for presents

            if(nextPos == board.presents[i].getPresentSquareId() && board.presents[i].getPoints() != 0) {
                scorePos = board.presents[i].getPoints();
            }
        }

        steps = (nextPos - currentPos); //Calculating the steps

        if(nextPos >= (board.getM() * board.getN()) * 0.75) //If the player is in a position higher than the three quarters of the board it is more important for him to grab more presents than making bigger steps
            return (steps * 0.3 + scorePos * 0.7); //Score has a bigger coefficient than steps in order for the player to choose tiles with presents over bigger steps
        else
            return (steps * 0.7 + scorePos * 0.3); //Score has a smaller coefficient than steps in order for the player to choose bigger steps over tiles with presents
    }

    public int getNextMove(int currentPos, int round){ //Choosing the best option for moving the player

        double[][] Move = new double[2][6]; //Dynamic array for storing data(possible position and evaluation)
        Integer[] path =  {0, 0, 0, 0, 0, 0, 0};  //Array for storing data and then transfer them into the Path variable of the class
        int nextPos; //Variable for storing the final position
        int dice = 0; //Variable for storing the dice
        double max; //Variable for storing the final evaluation
        int temp = 0; //Counter

        for(int i = 1; i <=6; i++){

            nextPos = (currentPos + i); //This is where the player lands after rolling the dice

            for(int j = 0; j < board.snakes.length; j++){

                if(nextPos == board.snakes[j].getHeadId())
                    Move[0][temp] = board.snakes[j].getTailId(); //Final placement
                else
                    Move[0][temp] = nextPos; //Final placement
            }

            for(int j = 0; j < board.ladders.length; j++){

                if(nextPos == board.ladders[j].getBottomSquareId() && !board.ladders[j].isBroken())
                    Move[0][temp] = board.ladders[j].getTopSquareId(); //Final placement
                else
                    Move[0][temp] = nextPos; //Final placement
            }

            Move[1][temp] = evaluate(currentPos, i); //Evaluation, this method returns an evaluation for each dice
            temp++;
        }

        max = Move[1][0]; //Setting max to the first evaluation

        for(int j = 0; j < 6; j++) { //For-loop for calculating the max evaluation

            if (Move[1][j] > max) {
                max = Move[1][j];
                dice = j + 1;
            }
        }

        if(max == Move[1][0])
            dice = 1;

        nextPos = currentPos + dice;

        for(int i = 0; i < board.snakes.length; i++){ //Checking if there are any snakes at the landing square(tile)

            if(nextPos == board.snakes[i].getHeadId()){
                nextPos = board.snakes[i].getTailId();
                System.out.println("You just got bit by a snake! ");
                path[3]++;
            }
        }

        for(int i = 0; i < board.ladders.length; i++){ //Checking if there are any ladders at the landing square(tile)

            if(nextPos == board.ladders[i].getBottomSquareId() && !board.ladders[i].isBroken()){
                nextPos= board.ladders[i].getTopSquareId();
                board.ladders[i].setBroken(true);
                System.out.println("You just got lifted! ");
                path[4]++;
            }
        }

        for(int i = 0; i < board.presents.length; i++){ //Checking if there are any presents at the landing square(tile)

            if(nextPos== board.presents[i].getPresentSquareId() && board.presents[i].getPoints() != 0) {

                score = board.presents[i].getPoints() + score;
                System.out.println("You just won a present! Your bonus is " + board.presents[i].getPoints() + " points");
                board.presents[i].setPoints(0);
                path[5]++;
            }
        }

        //if(path[5] == 0 && path[3] == 0 && path[4] == 0) //If the player was not bit, lifted, or he did not win any presents in this round, a "-" is going to appear at the console
            //System.out.println("---");

        path[0] = dice;
        path[1] = score;
        path[2] = (nextPos - currentPos);
        path[6] = round;
        Path.add(path); //Transferring data from path array to Path arraylist

        return nextPos; //Returning the final placement of the player
    }

    public void statistics(){ //Calculating the statistics

        int snakeV = 0; //Total snake head visits
        int laddersV = 0; //Total bottom level of ladders visits
        int presentsV = 0; //Total present visits

        System.out.println(Board.ConsoleColors.GREEN + "\nRound" + Board.ConsoleColors.RESET + " || " + Board.ConsoleColors.GREEN + " Snake Bites" + Board.ConsoleColors.RESET + " || " + Board.ConsoleColors.GREEN + " Lifts " + Board.ConsoleColors.RESET + " || " + Board.ConsoleColors.GREEN + " Presents" + Board.ConsoleColors.RESET);

        for(int i = 0; i < Path.size(); i++){
            snakeV = Path.get(i)[3] + snakeV;
            laddersV = Path.get(i)[4] + laddersV;
            presentsV = Path.get(i)[5] + presentsV;
            if(Path.get(i)[6] < 10)
                System.out.println("  " + Path.get(i)[6] + "   ||       " +  Path.get(i)[3] + "       ||    " + Path.get(i)[4] + "    ||      "  + Path.get(i)[5]);
            else
                System.out.println("  " + Path.get(i)[6] + "  ||       " +  Path.get(i)[3] + "       ||    " + Path.get(i)[4] + "    ||      "  + Path.get(i)[5]);
        }

        System.out.println("\nTotal visits of snake heads: " + snakeV);
        System.out.println("Total visits of the bottom level of ladders: " + laddersV);
        System.out.println("Total visits of presents: " + presentsV);
    }
}
