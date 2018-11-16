package pkg;

import java.util.Random; //for AI holding "decision"
import java.util.Scanner; //for user input

public class Game {

    //INSTANCE VARIABLES
    //CONSTRUCTORS
    private Scoreboard playerScoreboard = new Scoreboard(); //player's scoreboard
    private AIScoreboard AIScoreboard = new AIScoreboard(); //AI's scoreboard

    private AllDice gameDice = new AllDice(); //gameDice object

    /**
     * INSTANCE METHODS
     */

    private void releaseAllDice() { //when this is called, all Dice become rollable
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            gameDice.rollDiceNumber(i);
        }
    }

    private void checkHold() { //ask the user what dice they want to hold, whilst forcing a valid input
        char userChoice = ' ';
        boolean done;
        for (int i = 0; i < gameDice.diceInContainer(); i++) { //walk through each dice
            PrintWithColor.brightYellow("Would you like to hold Die #" + (i + 1) + "? (y/n) [Value:" + gameDice.getSpecificValueOfDice(i) + "]");
            done = false;
            while (!done) {
                userChoice = getCharFromUser();
                if (userChoice != 'y' && userChoice != 'n') {
                    PrintWithColor.brightRed("Invalid! Please try again.");
                } else if (userChoice == 'y') {
                    gameDice.dontRollDiceNumber(i);
                    done = true;
                } else {
                    gameDice.rollDiceNumber(i);
                    done = true;
                }
            }
        }
    }

    private void checkHoldAI() { //AI randomly decides what dice to hold
        Random randomNum = new Random();
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            int decision = randomNum.nextInt(2);
            if (decision == 0) {
                gameDice.dontRollDiceNumber(i);
            } else {
                gameDice.rollDiceNumber(i);
            }
        }
    }


    private int getNumFromUser() { //forces user to input a valid int
        int userNum;
        Scanner userNumberInput = new Scanner(System.in);
        while (!userNumberInput.hasNextInt()) {
            PrintWithColor.brightRed("Invalid input! Try again.");
            userNumberInput.nextLine();
            userNumberInput.reset();
        }
        userNum = userNumberInput.nextInt();
        return userNum;
    }

    private char getCharFromUser() { //forces user to input a valid char
        char userChar = ' ';
        Scanner userCharInput = new Scanner(System.in);
        while (!userCharInput.hasNextLine()) {
            PrintWithColor.brightRed("Invalid input! Please try again.");
            userCharInput.nextLine();
            userCharInput.reset();
        }
        userChar = userCharInput.nextLine().charAt(0);
        return userChar;
    }


    private boolean keepRolling() { //get a valid user input and return it [validation mostly handled by getCharFromUser()]
        char userChoice = ' ';
        boolean done = false;
        PrintWithColor.blue("Would you like to roll again (y/n)?");
        while (!done) {
            userChoice = getCharFromUser();
            if (userChoice != 'y' && userChoice != 'n') {
                PrintWithColor.brightRed("Invalid! Please try again.");
            } else {
                done = true;
            }
        }
        return userChoice == 'y';
    }

    private boolean AIKeepRolling() { //AI will roll until the sum of all the possible scores is greater than 30
        int AITotalScoreForRoll = AIScoreboard.getTotalScoreOfRollForAI(AIScoreboard.getTempScoresFromValuesAI(gameDice.getDiceValues()));
        if (AITotalScoreForRoll >= 30) { //30 is optimal
            return false;
        }
        return true;
    }

    private String outputDiceValues() {
        return "Your dice values are: " + gameDice.toString().substring(0, gameDice.toString().length() - 1); //return dice values without end comma
    }

    private String outputAIDiceValues() {
        return "AI rolled: " + gameDice.toString().substring(0, gameDice.toString().length() - 1); //return dice values without end comma
    }

    private int getUserScoreChoice() { //get a valid user input and return it [validation mostly handled by getNumFromUser()]
        int userChoice = 0;
        boolean done = false;
        System.out.println("Input the number of your score choice.");
        while (!done) {
            userChoice = getNumFromUser();
            if (userChoice > 13 || userChoice < 0) {
                PrintWithColor.brightRed("Number out of valid range!");
            } else if (!playerScoreboard.isUserScoreChoiceAvailable(userChoice)) {
                PrintWithColor.brightRed("Score already taken! Try again.");
            } else {
                done = true;
            }
        }
        return userChoice;
    }

    private int AIScoreChoice() { //run through the array and find the most valuable index, then return that index
        int highestValueIndex = 0;
        highestValueIndex = AIScoreboard.getHighestScoreIndex(AIScoreboard.getTempScoresFromValuesAI(gameDice.getDiceValues()));
        while (!AIScoreboard.isAIScoreChoiceAvailable(highestValueIndex)) {
            highestValueIndex++;
        }
        return highestValueIndex;
    }

    private void calculateScoreboard() { //print options and ask for input
        System.out.println("");
        playerScoreboard.printTemporaryScorecardForGameDice(gameDice);
        playerScoreboard.setScoreForAllDice(gameDice, getUserScoreChoice());
        playerScoreboard.printCurrentScorecardForGameDice();
    }


    private void calculateAIScoreboard() { //take in the AI's pre-validated choice then show the AI's scorecard
        AIScoreboard.setScoreforAllDiceAI(gameDice, AIScoreChoice());
        AIScoreboard.printCurrentScorecardForGameDiceAI();
    }


    private void playRound() { //runs the game
        int rollsRemaining;
        boolean rollAgain;
        rollsRemaining = 3;
        releaseAllDice(); //activate all the dice
        while (!playerScoreboard.isBoardFilled()) { //only runs when board is not full
            gameDice.rollAll();
            rollsRemaining--;
            PrintWithColor.green(outputDiceValues());
            if (rollsRemaining > 0) {
                System.out.println("(" + rollsRemaining + " rolls remaining)");
                System.out.println("");
                rollAgain = keepRolling();
                if (!rollAgain) { //if they choose no
                    PrintWithColor.purple("You ended your turn. Choose your score.");
                    calculateScoreboard();
                    rollsRemaining = 3;
                    releaseAllDice();
                    if (playerScoreboard.isBoardFilled()) { //check if the player's board is full
                        playerScoreboard.printFinalScorecardForGameDice();
                    }
                    if (!AIScoreboard.isBoardFilledAI()) { //check if the AI's board is full; if not then rotate turns
                        PrintWithColor.yellow("***COMPUTER'S TURN!***");
                        AIRound();
                    }
                } else { //if the player's turn is not over, ask what dice they want to hold
                    checkHold();
                }
            } else if (rollsRemaining == 0) { //player out of rolls; force them to choose score
                PrintWithColor.purple("No more rolls remaining. Choose your score.");
                calculateScoreboard();
                rollsRemaining = 3;
                releaseAllDice();
                if (playerScoreboard.isBoardFilled()) { //check if the player's board is full
                    playerScoreboard.printFinalScorecardForGameDice();
                }
                if (!AIScoreboard.isBoardFilledAI()) { //if AI board is not full, rotate turns
                    PrintWithColor.yellow("***COMPUTER'S TURN!***");
                    AIRound();
                }
            }
        }
        if (!AIScoreboard.isBoardFilledAI()) { //final check
            PrintWithColor.yellow("***COMPUTER'S TURN!***");
            AIRound();
        }
    }


    private void AIRound() { //similar to player loop but incorporates some different methods
        System.out.println("");
        int rollsRemainingAI;
        boolean rollAgainAI;
        rollsRemainingAI = 3;
        releaseAllDice(); //activate all dice
        while (!AIScoreboard.isBoardFilledAI()) {
            gameDice.rollAll();
            rollsRemainingAI--;
            PrintWithColor.green(outputAIDiceValues());
            if (rollsRemainingAI > 0) {
                System.out.println("(AI has " + rollsRemainingAI + " rolls remaining)");
                System.out.println("");
                rollAgainAI = AIKeepRolling();
                if (!rollAgainAI) {
                    PrintWithColor.cyan("AI ended its turn. Choosing score...");
                    calculateAIScoreboard();
                    rollsRemainingAI = 3;
                    releaseAllDice();
                    if (AIScoreboard.isBoardFilledAI()) { //check if the AI has a full scoreboard
                        AIScoreboard.printFinalScorecardForGameDiceAI();
                    }
                    if (!playerScoreboard.isBoardFilled()) { //rotate turns if the player's scoreboard is not full
                        PrintWithColor.yellow("***YOUR TURN!***");
                        playRound();
                    }
                } else {
                    checkHoldAI();
                }
            } else if (rollsRemainingAI == 0) {
                PrintWithColor.cyan("AI has no more rolls remaining. Choosing score...");
                calculateAIScoreboard();
                rollsRemainingAI = 3;
                releaseAllDice();
                if (AIScoreboard.isBoardFilledAI()) { //check if the AI has a full scoreboard
                    AIScoreboard.printFinalScorecardForGameDiceAI();
                }
                if (!playerScoreboard.isBoardFilled()) { //rotate turns if the player's scoreboard is not full
                    PrintWithColor.yellow("***YOUR TURN!***");
                    playRound();
                }
            }
        }
        if (!playerScoreboard.isBoardFilled()) { //final check
            PrintWithColor.yellow("***YOUR TURN!***");
            playRound();
        }
    }

    public static void main(String[] args) { //initiates the game (MUST BE PUBLIC)
        Game newGame = new Game(); //create a new game object to call playRound() on
        newGame.playRound(); //rest of the game is handled from here
    }
}
