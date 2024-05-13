import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
public class Game { //Game class, contains the basics of a class, the main method, setTurns and ConsoleColors

    int round;

    public Game(){ //Void constructor

        round = 0;
    }

    public void setRound(int round) {
        this.round = round;
    } //Setter for the game's rounds

    public int getRound() {
        return round;
    }//Getter for the game's rounds

    public Map<Integer,Integer> setTurns(ArrayList<Object> players){ //Method for randomly setting the first player

        int dice1; //Variable for Player's(1) dice
        int dice2; //Variable for Player's(2) dice
        Map<Integer, Integer> set = new HashMap<>(); //Map for returning a set of the Player's index and his dice

        do{ //Randomly getting 2 dice numbers
            dice1 = ((int) (Math.random() * (5)) + 1);
            dice2 = ((int) (Math.random() * (5)) + 1);
        }while(dice1 != dice2);

        set.put(1, dice1);
        set.put(2, dice2);

        return(set);
    }

    public static void main(String[] args){ //Main method

        int dice; //Variable for rolling the dice of Player1
        int square1 = 0; //Position on the board for the Player1
        int square2 = 0; //Position on the board for the Player2
        int turn; //Counter
        int f = 0; //Counter

        Scanner SPlayer1 = new Scanner(System.in);

        Game Game = new Game(); //Creating the game
        Player Player1 = new Player(); //Creating Player1
        HeuristicPlayer Player2 = new HeuristicPlayer(); //Creating the HeuristicPlayer
        Board Board = new Board(10, 20, 3, 3, 6); //Creating the board of the game
        ArrayList<Object> players = new ArrayList<Object>(2);

        Player1.setPlayerId(1);
        System.out.println("Enter the name of the first Player:"); //Getting the name of Player1 from the keyboard
        Player1.setName(SPlayer1.nextLine());
        players.add(Player1);

        Player2.setPlayerId(2);
        Player2.setName("HeuristicPlayer"); //Setting the name of Player2 to HeuristicPlayer
        players.add(Player2);

        Player1.setBoard(Board);
        Player2.setBoard(Board);

        Board.CreateBoard();
        Board.createElementBoard(); //Printing the boards

        if(Game.setTurns(players).get(1) < Game.setTurns(players).get(2)) { //Setting which player is going to play first
            turn = 1;
            System.out.println("The first player is going to be " + Player1.getName());
        }
        else {
            turn = 2;
            System.out.println("The first player is going to be " + Player2.getName());
        }


        System.out.println(ConsoleColors.PURPLE_BOLD + "\n//Press Enter to continue to the game//" + ConsoleColors.RESET);
        try {
            System.in.read();
        } catch (Exception e) {
        }

        do{
            Game.setRound(Game.getRound() + 1);

            System.out.println("\nThis is round number " + Game.getRound() + ":" + "\n");

            if(turn == 1) { //This is the game for when the first player is Player1

                dice = ((int) (Math.random() * (5)) + 1);
                System.out.println(ConsoleColors.GREEN + "//" + ConsoleColors.RESET +  Player1.getName());
                square1 = Player1.move(square1, dice)[0];
                if(square1 < 200)
                    System.out.println("Dice: " + dice + " Your position on the board is number:" + square1);
                else
                    System.out.println("Dice: " + dice +  " Your position on the board is number 200");

                System.out.println(ConsoleColors.GREEN + "//" + ConsoleColors.RESET + Player2.getName());
                square2 = Player2.getNextMove(square2, Game.getRound()); //Getting the final position
                if(square2 < 200)
                    System.out.println("Dice: " + Player2.getPath().get(f)[0] + ".Your position on the board is number:" + square2);
                else
                    System.out.println("Dice: " + Player2.getPath().get(f)[0] + ".Your position on the board is number 200");

            }
            else {//This is the game for when the first player is Player2

                System.out.println(ConsoleColors.GREEN + "//" + ConsoleColors.RESET + Player2.getName());
                square2 = Player2.getNextMove(square2, Game.getRound()); //Getting the final position
                if(square2 < 200)
                    System.out.println("Dice: " + Player2.getPath().get(f)[0] + ".Your position on the board is number:" + square2);
                else
                    System.out.println("Dice: " + Player2.getPath().get(f)[0] + ".Your position on the board is number 200");

                dice = ((int) (Math.random() * (5)) + 1);

                System.out.println(ConsoleColors.GREEN + "//" + ConsoleColors.RESET + Player1.getName());
                square1 = Player1.move(square1, dice)[0]; //Getting the final position
                if(square1 < 200)
                    System.out.println("Dice: " + dice + ".Your position on the board is number:" + square1);
                else
                    System.out.println("Dice: " + dice + ".Your position on the board is number 200");
            }

            //Code for pausing the game between rounds
            if(square1 < (Board.M * Board.N) && square2 < (Board.M * Board.N)) {
                System.out.println(ConsoleColors.PURPLE + "\n//Press Enter to continue to the next round//" + ConsoleColors.RESET);
            }
            else{
                System.out.println(ConsoleColors.PURPLE + "\n//Press Enter to continue//" + ConsoleColors.RESET);
            }
            try {
                System.in.read();
            } catch (Exception e) {
            }

            f++;
        }while(((square1 < (Board.M * Board.N)) && (square2 < (Board.M * Board.N))) && Game.getRound() < 23);

        if(Game.getRound() >= 23 && (square2 < 200 || square1 < 200)) { //The game ends if the round is greater than 23
            System.out.println("You exceeded the allowed number of rounds!\n");
            if (square1 > square2 && Player1.getScore() >= Player2.getScore()) { //Player1 wins with higher score and his position closer to the end than Player's(2)
                System.out.println("The winner is " + ConsoleColors.PURPLE_BRIGHT + Player1.getName() + ConsoleColors.RESET + "!!!");
            } else if (square1 < square2 && Player1.getScore() <= Player2.getScore()) { //Player2 wins with higher score and his position closer to the end than Player's(1)
                System.out.println("The winner is " + ConsoleColors.PURPLE_BRIGHT + Player2.getName() + ConsoleColors.RESET + "!!!");
            } else //No winner, either because of same scores and positions or because one player has a bigger score but the other one is in a closer to the end position
                System.out.println(ConsoleColors.PURPLE_BRIGHT + "Game over. There is no winner! " + ConsoleColors.RESET);
        }

        if(Game.getRound() <= 23 && (square2 >= 200 || square1 >= 200)) {
            if (square1 >= ((Board.M) * (Board.N)) && Player1.getScore() >= Player2.getScore()) { //Player1 wins with higher score and his position closer to the end than Player's(2)
                System.out.println("The winner is " + ConsoleColors.PURPLE_BRIGHT + Player1.getName() + ConsoleColors.RESET + "!!!");
            } else if(square1 <= ((Board.M) * (Board.N)) && Player1.getScore() <= Player2.getScore()) { //Player2 wins with higher score and his position closer to the end than Player's(1)
                System.out.println("The winner is " + ConsoleColors.PURPLE_BRIGHT + Player2.getName() + ConsoleColors.RESET + "!!!");
            }else //No winner, either because of same scores and positions or because one player has a bigger score but the other one is in a closer to the end position
                System.out.println(ConsoleColors.PURPLE_BRIGHT + "Game over. There is no winner! " + ConsoleColors.RESET);
        }

        System.out.println("The total number of rounds was:" + Game.getRound()); //Printing the total rounds
        System.out.println("For " + Player1.getName() + ":\n" + "Score = " + Player1.getScore()); //Printing scores
        System.out.println("For " + Player2.getName() + ":\n" + "Score = " + Player2.getScore()); //Printing scores
        System.out.println(ConsoleColors.PURPLE_BOLD + "\nStatistics for Heuristic Player: " + ConsoleColors.RESET); //Printing Heuristic's Player statistics
        Player2.statistics();
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