import java.lang.Math;
public class Board { //Board class, contains the basics of a class and the methods CreateBoard, createElementBoard and ConsoleColors

    int N, M;
    int [][] squares;
    Snake[] snakes;
    Ladder[] ladders;
    Present[] presents;

    public Board(int N, int M, int snakes, int ladders, int presents){ //Parameterized Constructor

        this.N = N;
        this.M = M;
        this.snakes = new Snake[snakes];
        this.ladders = new Ladder[ladders];
        this.presents = new Present[presents];
        squares = new int[N][M];

        for(int i = 0; i < snakes; i++)
            this.snakes[i] = new Snake();

        for(int i = 0; i < ladders; i++)
            this.ladders[i] = new Ladder();

        for(int i = 0; i < presents; i++)
            this.presents[i] = new Present();

        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                squares[i][j] = 0;
    }

    public Board(){ //Void constructor, setting variables to zero

        int N = 0;
        int M = 0;
    }

    public Board(Board B1){ //Copy Constructor

        N = B1.N;
        M = B1.M;
        squares = B1.squares;
        snakes = B1.snakes;
        ladders = B1.ladders;
        presents = B1.presents;
    }

    public void setM(int m) {
        M = m;
    } //Setter for the rows of the board

    public void setN(int n) {
        N = n;
    } //Setter for the columns of the board

    public void setSnakes(Snake[] snakes) {
        this.snakes = snakes;
    } //Setter for the array of snakes

    public void setLadders(Ladder[] ladders) {
        this.ladders = ladders;
    } //Setter for the array of ladders

    public void setPresents(Present[] presents) {
        this.presents = presents;
    } //Setter for the array of presents

    public void setSquares(int[][] squares) {
        this.squares = squares;
    } //Setter for the 2d array of the board's squares

    public int[][] getSquares() {
        return squares;
    } //Getter for the 2d array of the board's squares

    public int getM() {
        return M;
    } //Getter for the rows of the board

    public int getN() {
        return N;
    } //Getter for the columns of the board

    public Ladder[] getLadders() {
        return ladders;
    } //Getter for the array of ladders

    public Present[] getPresents() {
        return presents;
    } //Getter for the array of presents

    public Snake[] getSnakes() {
        return snakes;
    } //Setter for the array of snakes

    public void CreateBoard() { //In this method all the snakes, ladders and presents are being placed on the board

        int count = 1; //Variable used for setting the ids of the board's squares
        int track; //Variable used for the correct placement of ladders and snakes on the board

        for (int i = (N - 1); i >= 0; i--) { //Creating the snake pattern of the board
            if (((i - (N - 1)) % 2) == 0) {
                for (int j = 0; j < M; j++) {
                    squares[i][j] = count;
                    count++;
                }
                System.out.print("\n");
            }else
                for (int j = (M - 1); j >= 0; j--) {
                    squares[i][j] = count;
                    count++;
            }
        }

        /*Commented out coded, just for checking

        for (int i = 0; i < N; i++) { //Printing the board just with the square ids
            for (int j = 0; j < M; j++)
                System.out.format("%3d  ", squares[i][j]);
            System.out.print("\n");
        }

         */

        System.out.print("\n");

        for(int i = 0; i < snakes.length; i++){ //Placing the snakes

            do{
                snakes[i].setTailId((int)(Math.random()* (N * M - 1)) + 1);
                snakes[i].setHeadId((int)(Math.random()* (N * M - 2 - M)) + 1);

                if(i != 0) //Checking if there are any snakes with the same head and tail id
                    for(int x = (i - 1); x >= 0; x--){
                        if((snakes)[i].getHeadId() == snakes[x].getHeadId() || snakes[i].getTailId() == snakes[x].getTailId() || snakes[i].getHeadId() == snakes[x].getTailId() || snakes[i].getTailId() == snakes[x].getHeadId())
                            do{
                                snakes[i].setTailId((int)(Math.random()* (N * M - 1)) +1);
                                snakes[i].setHeadId((int)(Math.random()* (N * M - 2 - M)) +1);
                            }while((snakes)[i].getHeadId() == snakes[x].getHeadId() || snakes[i].getTailId() == snakes[x].getTailId() || snakes[i].getHeadId() == snakes[x].getTailId() || snakes[i].getTailId() == snakes[x].getHeadId());
                    }

                track = (snakes[i].getTailId());

                do{//Do-while loop for placing the head higher than the tail and not on the same line
                    track++;
                }while((track % M) != 0);

            }while(snakes[i].getTailId() >= snakes[i].getHeadId() || snakes[i].getHeadId() <= track);//Do-while loop for placing the head higher than the tail and not on the same line

            snakes[i].setSnakeId(i);

        }

        for(int j = 0; j < ladders.length; j++){//Placing the ladders

            do{
                ladders[j].setBottomSquareId((int)(Math.random()* (N * M - M - 1)) +1);
                ladders[j].setTopSquareId((int)(Math.random()* (N * M - 2 - M)) +1);

                if(j != 0)//Checking if there are any ladders with the same top and bottom square id
                    for(int k = (j - 1); k >= 0; k--){
                        if(ladders[j].getTopSquareId() == ladders[k].getTopSquareId() || ladders[j].getBottomSquareId() == ladders[k].getBottomSquareId() || ladders[j].getBottomSquareId() == ladders[k].getTopSquareId() || ladders[k].getBottomSquareId() == ladders[j].getTopSquareId())
                            do{
                                ladders[j].setBottomSquareId((int)(Math.random()* (N * M - M - 1)) +1);
                                ladders[j].setTopSquareId((int)(Math.random()* (N * M - 2 - M)) +1);
                            }while(ladders[j].getTopSquareId() == ladders[k].getTopSquareId() || ladders[j].getBottomSquareId() == ladders[k].getBottomSquareId() || ladders[j].getBottomSquareId() == ladders[k].getTopSquareId() || ladders[k].getBottomSquareId() == ladders[j].getTopSquareId());
                    }

                track = (ladders[j].getBottomSquareId());

                do{//Do-while loop for placing the top level of the ladder higher than the bottom level and not on the same line
                    track++;
                }while((track % M) != 0);

            }while(ladders[j].getBottomSquareId() >= ladders[j].getTopSquareId() || ladders[j].getTopSquareId() <= track);//Do-while loop for placing the top level of the ladder higher than the bottom level and not on the same line

            ladders[j].setLadderId(j);

         }

        for(int k = 0; k < presents.length; k++){ //Placing the presents

            presents[k].setPresentId(k);
            presents[k].setPresentSquareId((int)(Math.random() * (N * M - 1)) + 1);

            if(k != 0)//Checking if there are any presents with the same square id
                for(int i = (k - 1); i >= 0; i--){
                    if(presents[k].getPresentSquareId() == presents[i].getPresentSquareId())
                        do{
                            presents[k].setPresentSquareId((int)(Math.random() * (N * M - 1)) + 1);
                        }while(presents[k].getPresentSquareId() == presents[i].getPresentSquareId());
                }

            presents[k].setPoints((int)(Math.random() * 11) + 1); //Randomly setting the points of each present
        }
    }

    public void createElementBoard() { //In this method the boards are being printed

        boolean block = false; //Variable for controlling if an element(snake, ladder or present) has been printed or not
        String[][] ElementBoardSnakes = new String[N][M]; //Snake board
        String[][] ElementBoardLadders = new String[N][M]; //Ladder board
        String[][] ElementBoardPresents = new String[N][M]; //Present board

        System.out.println("ElementBoardSnakes:");

        for(int i = 0; i < N; i++){ //Printing the snake board
            for(int j = 0; j < M; j++){

                for(int a = 0; a < snakes.length; a++){
                    if(snakes[a].getTailId() == squares[i][j]){
                        ElementBoardSnakes[i][j] = "ST";
                        System.out.print(ConsoleColors.GREEN_BOLD + ElementBoardSnakes[i][j] + snakes[a].getSnakeId() + ConsoleColors.RESET + " ");
                        block = true;
                    }
                    else if(snakes[a].getHeadId() == squares[i][j]){
                        ElementBoardSnakes[i][j] = "SH";
                        System.out.print(ConsoleColors.GREEN_BOLD + ElementBoardSnakes[i][j] + snakes[a].getSnakeId() + ConsoleColors.RESET + " ");
                        block = true;
                    }
                }

                if(!block){
                    ElementBoardSnakes[i][j] = "___";
                    System.out.print(ElementBoardSnakes[i][j] + " ");
                }

                block = false;
            }
            System.out.print("\n\n");
        }

        System.out.println("ElementBoardLadders:");

        for(int i = 0; i < N; i++){//Printing the ladder board
            for(int j = 0; j < M; j++){

                for(int a = 0; a < ladders.length; a++){
                    if(ladders[a].getBottomSquareId() == squares[i][j]){
                        ElementBoardLadders[i][j] = "LD";
                        System.out.print(ConsoleColors.GREEN_BOLD + ElementBoardLadders[i][j] + ladders[a].getLadderId() + ConsoleColors.RESET + " ");
                        block = true;
                    }
                    else if(ladders[a].getTopSquareId() == squares[i][j]){
                        ElementBoardLadders[i][j] = "LU";
                        System.out.print(ConsoleColors.GREEN_BOLD + ElementBoardLadders[i][j] + ladders[a].getLadderId() + ConsoleColors.RESET + " ");
                        block = true;
                    }
                }

                if(!block){
                    ElementBoardLadders[i][j] = "___";
                    System.out.print(ElementBoardLadders[i][j] + " ");
                }

                block = false;
            }
            System.out.print("\n\n");

        }

        System.out.println("ElementBoardPresents:");

        for(int i = 0; i < N; i++){//Printing the present board
            for(int j = 0; j < M; j++){

                for(int a = 0; a < presents.length; a++){
                    if(presents[a].getPresentSquareId() == squares[i][j]){
                        ElementBoardPresents[i][j] = "PR";
                        System.out.print(ConsoleColors.GREEN_BOLD + ElementBoardPresents[i][j] + presents[a].getPresentId() + ConsoleColors.RESET + " ");
                        block = true;
                    }
                }

                if(!block){
                    ElementBoardPresents[i][j] = "___";
                    System.out.print(ElementBoardPresents[i][j] + " ");
                }

                block = false;
            }
            System.out.print("\n\n");
        }
    }

    public class ConsoleColors { //Class for changing text colours at the console
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }
}