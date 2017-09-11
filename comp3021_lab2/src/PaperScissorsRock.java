import java.util.Random;
import java.util.Scanner;

public class PaperScissorsRock {
    private static int ROCK = 0, PAPER = 1, SCISSORS = 2; // 0/1/2 to represent ROCK/PAPER/SCISSORS
    private static int turn = 0; // the number of the current game
    private static int cResult = 0; // how many turns did computer wins
    private static int uResult = 0; // how many turns did user wins
    private static String result = ""; // string that store the winner of each turn

    public static int printChoice(char player, int choice) // 'C' for computer and 'U' for user
    {
        if (player == 'C')
            System.out.print("Computer");
        else if (player == 'U')
            System.out.print("User");
        else
        {
            System.err.println("Error: Invalid user --- " + player);
            return -1;
        }
        System.out.print(" picks ");

        if (choice == ROCK)
            System.out.println("rock");
        else if (choice == PAPER)
            System.out.println("paper");
        else if (choice == SCISSORS)
            System.out.println("scissors");
        else
        {
            System.err.println("Error: Invalid game choice --- " + choice);
            return -1;
        }

        return 0;
    }

    public static void printGameResult(int computerChoice, int userChoice)
    {
        if(computerChoice == userChoice) {
            System.out.println("\tDRAW!");
            result+='D';
        } else if((computerChoice == ROCK && userChoice == SCISSORS)
                || (computerChoice == SCISSORS && userChoice == PAPER)
                || (computerChoice == PAPER && userChoice == ROCK)){
            System.out.println("\tCOMPUTER WINS!");
            cResult++;
            result+='C';
        } else {
            System.out.println("\tUSER WINS!");
            uResult++;
            result+='U';
        }
    }


    public static void printEnding() {
        if (cResult > uResult) {
            System.out.println("You Lose!");
        } else if (cResult < uResult) {
            System.out.println("You Win!");
        } else {
            System.out.println("Draw!");
        }

        System.out.println("The overall result is Computer " + cResult + ": You " + uResult);
        result = result.replaceAll(".(?=.)", "$0 ");
        System.out.println(result);
    }

    public static void main (String[] args) {
        int input;
        char answer='N';
        Boolean play = true;
        int MAX_TURN = 10;
        Scanner reader = new Scanner(System.in);
        Random rand = new Random();

        while(play && turn < MAX_TURN) {
            System.out.print("Enter an integer (ROCK:0, PAPER:1, SCISSORS:2): ");
            input = reader.nextInt();

            int computerChoice = rand.nextInt(18)%3; // rand() produces an integer which is
            int userChoice = input%3; // then converted to ROCK/PAPER/SCISSORS

            if (printChoice('C', computerChoice) != 0 || printChoice('U', userChoice) != 0){
                System.exit(-1); // -1 signals an error
            }

            printGameResult(computerChoice, userChoice);

            if ((turn + 1) < MAX_TURN) {
                System.out.print("Do you want to play again? (Y/N) ");
                answer = reader.next(".").charAt(0);
            } else {
                printEnding();
                break;
            }

            if (answer == 'Y' || answer == 'y') {
                play = true;
            } else {
                play = false;
                printEnding();
            }

            turn++;
        }

        turn = 0;
        result = "";
    }
}
