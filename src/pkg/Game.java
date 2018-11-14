package pkg;

import java.util.Random;
import java.util.Scanner;

public class Game {

    //INSTANCE VARIABLES
    //CONSTRUCTORS
    private Scoreboard playerScoreboard = new Scoreboard();
    private AIScoreboard AIScoreboard = new AIScoreboard();

    private AllDice gameDice = new AllDice();

    /**
     * INSTANCE METHODS
     */

    private void releaseAllDice() { //when this is called, all Dice become rollable
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
            gameDice.rollDiceNumber(i);
        }
    }

    private void checkHold() { //ask the user
        char userChoice = ' ';
        boolean done;
        for (int i = 0; i < gameDice.diceInContainer(); i++) {
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

    private void checkHoldAI() { //TODO: Don't use random choice
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


    private int getNumFromUser() {
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

    private char getCharFromUser() {
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


    private boolean keepRolling() {
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

    private boolean AIKeepRolling() {
        int AITotalScoreForRoll = AIScoreboard.getTotalScoreOfRollForAI(AIScoreboard.getTempScoresFromValuesAI(gameDice.getDiceValues()));
        if (AITotalScoreForRoll >= 40) { //possible addition: change threshold based on chosen difficulty
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

    private int getUserScoreChoice() {
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

    private int AIScoreChoice() {
        int highestValueIndex = AIScoreboard.getHighestScoreIndex(AIScoreboard.getTempScoresFromValuesAI(gameDice.getDiceValues()));
        while (!AIScoreboard.isAIScoreChoiceAvailable(highestValueIndex)) {
            highestValueIndex++;
        }
        return highestValueIndex;
    }

    private void calculateScoreboard() {
        System.out.println("");
        playerScoreboard.printTemporaryScorecardForGameDice(gameDice);
        playerScoreboard.setScoreForAllDice(gameDice, getUserScoreChoice());
        playerScoreboard.printCurrentScorecardForGameDice();
    }


    private void calculateAIScoreboard() {
        AIScoreboard.setScoreforAllDiceAI(gameDice, AIScoreChoice());
        AIScoreboard.printCurrentScorecardForGameDiceAI();
    }


    private void playRound() { //runs the game
        int rollsRemaining;
        boolean rollAgain;
        rollsRemaining = 3;
        releaseAllDice();
        while (!playerScoreboard.isBoardFilled()) {
            gameDice.rollAll();
            rollsRemaining--;
            PrintWithColor.green(outputDiceValues());
            if (rollsRemaining > 0) {
                System.out.println("(" + rollsRemaining + " rolls remaining)");
                System.out.println("");
                rollAgain = keepRolling();
                if (!rollAgain) {
                    PrintWithColor.purple("You ended your turn. Choose your score.");
                    calculateScoreboard();
                    rollsRemaining = 3;
                    releaseAllDice();
                    if (playerScoreboard.isBoardFilled()) {
                        playerScoreboard.printFinalScorecardForGameDice();
                    }
                    if (!AIScoreboard.isBoardFilledAI()) {
                        PrintWithColor.yellow("***COMPUTER'S TURN!***");
                        AIRound();
                    }
                } else {
                    checkHold();
                }
            } else if (rollsRemaining == 0) {
                PrintWithColor.purple("No more rolls remaining. Choose your score.");
                calculateScoreboard();
                rollsRemaining = 3;
                releaseAllDice();
                if (playerScoreboard.isBoardFilled()) {
                    playerScoreboard.printFinalScorecardForGameDice();
                }
                if (!AIScoreboard.isBoardFilledAI()) {
                    PrintWithColor.yellow("***COMPUTER'S TURN!***");
                    AIRound();
                }
            }
        }
        if (!AIScoreboard.isBoardFilledAI()) {
            PrintWithColor.yellow("***COMPUTER'S TURN!***");
            AIRound();
        }
    }


    private void AIRound() {
        System.out.println("");
        int rollsRemainingAI;
        boolean rollAgainAI;
        rollsRemainingAI = 3;
        releaseAllDice();
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
                    if (AIScoreboard.isBoardFilledAI()) {
                        AIScoreboard.printFinalScorecardForGameDiceAI();
                    }
                    if (!playerScoreboard.isBoardFilled()) {
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
                if (AIScoreboard.isBoardFilledAI()) {
                    AIScoreboard.printFinalScorecardForGameDiceAI();
                }
                if (!playerScoreboard.isBoardFilled()) {
                    PrintWithColor.yellow("***YOUR TURN!***");
                    playRound();
                }
            }
        }
        if (!playerScoreboard.isBoardFilled()) {
            System.out.println("***YOUR TURN!***");
            playRound();
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.playRound(); //rest of the game is handled from here
    }
}
