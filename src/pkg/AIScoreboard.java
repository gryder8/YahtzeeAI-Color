package pkg;

class AIScoreboard {
    private int totalScoreAI;
    private Score[] AIscores = new Score[13];

    private OnesScore AIones = new OnesScore(); //create each object for the array
    private TwosScore AItwos = new TwosScore();
    private ThreesScore AIthrees = new ThreesScore();
    private FoursScore AIfours = new FoursScore();
    private FivesScore AIfives = new FivesScore();
    private SixesScore AIsixes = new SixesScore();
    private ThreeOfAKindScore AIthreeOfAKind = new ThreeOfAKindScore();
    private FourOfAKindScore AIfourOfAKind = new FourOfAKindScore();
    private FullHouseScore AIfullHouse = new FullHouseScore();
    private SmallStraightScore AIsmallStraight = new SmallStraightScore();
    private LargeStraightScore AIlargeStraight = new LargeStraightScore();
    private ChanceScore AIchance = new ChanceScore();
    private YahtzeeScore AIyahtzee = new YahtzeeScore();

    AIScoreboard() {
        setupAI();
    }

    private void setupAI() { //create the array of the AI's Score objects
        AIscores[0] = AIones;
        AIscores[1] = AItwos;
        AIscores[2] = AIthrees;
        AIscores[3] = AIfours;
        AIscores[4] = AIfives;
        AIscores[5] = AIsixes;
        AIscores[6] = AIthreeOfAKind;
        AIscores[7] = AIfourOfAKind;
        AIscores[8] = AIfullHouse;
        AIscores[9] = AIsmallStraight;
        AIscores[10] = AIlargeStraight;
        AIscores[11] = AIchance;
        AIscores[12] = AIyahtzee;
    }

    void setScoreforAllDiceAI(AllDice gameDice, int scoreChoiceIndex) {
        int[] diceValues = gameDice.getDiceValues();
        AIscores[scoreChoiceIndex].setDiceValuesForScoring(diceValues);
        PrintWithColor.brightGreen("AI chose " + AIscores[scoreChoiceIndex].getName() + " and scored " + AIscores[scoreChoiceIndex].getValue() + " points.");
    }

    Score[] getTempScoresFromValuesAI(int[] diceValues) {
        Score[] tempScoresAI = new Score[13];
        if (!AIscores[0].getIsUsed()) {
            OnesScore tempOnes = new OnesScore();
            tempOnes.setDiceValuesForScoring(diceValues);
            tempScoresAI[0] = tempOnes;
        }
        if (!AIscores[1].getIsUsed()) {
            TwosScore tempTwos = new TwosScore();
            tempTwos.setDiceValuesForScoring(diceValues);
            tempScoresAI[1] = tempTwos;
        }
        if (!AIscores[2].getIsUsed()) {
            ThreesScore tempThrees = new ThreesScore();
            tempThrees.setDiceValuesForScoring(diceValues);
            tempScoresAI[2] = tempThrees;
        }
        if (!AIscores[3].getIsUsed()) {
            FoursScore tempFours = new FoursScore();
            tempFours.setDiceValuesForScoring(diceValues);
            tempScoresAI[3] = tempFours;
        }
        if (!AIscores[4].getIsUsed()) {
            FivesScore tempFives = new FivesScore();
            tempFives.setDiceValuesForScoring(diceValues);
            tempScoresAI[4] = tempFives;
        }
        if (!AIscores[5].getIsUsed()) {
            SixesScore tempSixes = new SixesScore();
            tempSixes.setDiceValuesForScoring(diceValues);
            tempScoresAI[5] = tempSixes;
        }
        if (!AIscores[6].getIsUsed()) {
            ThreeOfAKindScore tempThreeOfKind = new ThreeOfAKindScore();
            tempThreeOfKind.setDiceValuesForScoring(diceValues);
            tempScoresAI[6] = tempThreeOfKind;
        }
        if (!AIscores[7].getIsUsed()) {
            FourOfAKindScore tempFourOfKind = new FourOfAKindScore();
            tempFourOfKind.setDiceValuesForScoring(diceValues);
            tempScoresAI[7] = tempFourOfKind;
        }
        if (!AIscores[8].getIsUsed()) {
            FullHouseScore tempFullHouse = new FullHouseScore();
            tempFullHouse.setDiceValuesForScoring(diceValues);
            tempScoresAI[8] = tempFullHouse;
        }
        if (!AIscores[9].getIsUsed()) {
            SmallStraightScore tempSmallStraight = new SmallStraightScore();
            tempSmallStraight.setDiceValuesForScoring(diceValues);
            tempScoresAI[9] = tempSmallStraight;
        }
        if (!AIscores[10].getIsUsed()) {
            LargeStraightScore tempLargeStraight = new LargeStraightScore();
            tempLargeStraight.setDiceValuesForScoring(diceValues);
            tempScoresAI[10] = tempLargeStraight;
        }
        if (!AIscores[11].getIsUsed()) {
            ChanceScore tempChance = new ChanceScore();
            tempChance.setDiceValuesForScoring(diceValues);
            tempScoresAI[11] = tempChance;
        }
        if (!AIscores[12].getIsUsed()) {
            YahtzeeScore tempYahtzee = new YahtzeeScore();
            tempYahtzee.setDiceValuesForScoring(diceValues);
            tempScoresAI[12] = tempYahtzee;
        }
        return tempScoresAI;
    } //if a space is not used, calculate its possible score and build it into an array of Score objects

    void printFinalScorecardForGameDiceAI() {
        int AIFinalScore;

        PrintWithColor.brightMagenta("Computer's Game Over!");
        PrintWithColor.brightCyan("***AI's Final Scorecard***");
        String finalScorecard = generateScorecardAI(AIscores);
        System.out.println(finalScorecard);
        AIFinalScore = totalScoreAI;
        System.out.println("AI's final score is " + totalScoreAI + " points!");
        System.out.println(""); //whitespace

        System.out.println("Your Score: " + Scoreboard.getFinalPlayerScore());
        System.out.println("AI's Score: " + AIFinalScore);

        if (Scoreboard.getFinalPlayerScore() > AIFinalScore) {
            PrintWithColor.brightGreen("You won!");
        } else if (Scoreboard.getFinalPlayerScore() == AIFinalScore) {
            PrintWithColor.brightYellow("Game is a tie!!!");
        } else {
            PrintWithColor.brightRed("You lost! Better luck next time.");
        }
    }

    void printCurrentScorecardForGameDiceAI() {
        PrintWithColor.brightMagenta("***AI's Current Scorecard***");
        String currentScorecard = generateScorecardAI(AIscores);
        System.out.println(currentScorecard);
        PrintWithColor.brightWhite("AI's TOTAL SCORE: " + totalScoreAI);
        System.out.println(""); //whitespace
    }

    private String generateScorecardAI(Score[] creationArray) {
        String AIscoreString = "";
        totalScoreAI = 0;
        for (int i = 0; i < creationArray.length; i++) {
            if (creationArray[i] != null) { //make sure to avoid NPE by checking if the object has been removed b/c it's taken (making that index null)
                AIscoreString += i + ") " + creationArray[i].getName() + ": " + creationArray[i].getValue() + "\n";
                totalScoreAI += creationArray[i].getValue();
            }
        }
        return AIscoreString;
    }

    int getHighestScoreIndex(Score[] creationArray) {
        int maxScoreIndex = 0;
        int maxScore = 0;
        for (int i = 0; i < creationArray.length; i++) {
            if (creationArray[i] != null) {
                if (creationArray[i].getValue() > maxScore) {
                    maxScore = creationArray[i].getValue();
                    maxScoreIndex = i;
                }
            }
        }
        return maxScoreIndex;
    }

    int getTotalScoreOfRollForAI(Score[] creationArray) {
        int tempScore = 0;
        for (int i = 0; i < creationArray.length; i++) {
            if (creationArray[i] != null) { //make sure to avoid NPE by checking if the object has been removed b/c it's taken (making that index null)
                tempScore += creationArray[i].getValue();
            }
        }
        return tempScore;
    }

    boolean isAIScoreChoiceAvailable(int ScoreChoiceIndex) {
        if (AIscores[ScoreChoiceIndex].getIsUsed()) {
            return false;
        }
        return true;
    }

    boolean isBoardFilledAI() {
        for (int i = 0; i < AIscores.length; i++) {
            if (!AIscores[i].getIsUsed()) {
                return false;
            }
        }
        return true;
    }

}
