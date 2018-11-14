package pkg;

class Scoreboard {



    /**
     * INSTANCE VARS
     */
    private static int finalPlayerScore;
    private int totalPlayerScore;
    private Score[] scores = new Score[13];

    private OnesScore ones = new OnesScore(); //create each object for the array
    private TwosScore twos = new TwosScore();
    private ThreesScore threes = new ThreesScore();
    private FoursScore fours = new FoursScore();
    private FivesScore fives = new FivesScore();
    private SixesScore sixes = new SixesScore();
    private ThreeOfAKindScore threeOfAKind = new ThreeOfAKindScore();
    private FourOfAKindScore fourOfAKind = new FourOfAKindScore();
    private FullHouseScore fullHouse = new FullHouseScore();
    private SmallStraightScore smallStraight = new SmallStraightScore();
    private LargeStraightScore largeStraight = new LargeStraightScore();
    private ChanceScore chance = new ChanceScore();
    private YahtzeeScore yahtzee = new YahtzeeScore();


    static int getFinalPlayerScore() { //getter
        return finalPlayerScore;
    }

    private void setup() { //create the array of Score objects
        scores[0] = ones;
        scores[1] = twos;
        scores[2] = threes;
        scores[3] = fours;
        scores[4] = fives;
        scores[5] = sixes;
        scores[6] = threeOfAKind;
        scores[7] = fourOfAKind;
        scores[8] = fullHouse;
        scores[9] = smallStraight;
        scores[10] = largeStraight;
        scores[11] = chance;
        scores[12] = yahtzee;
    }

    void setScoreForAllDice(AllDice gameDice, int scoreChoiceIndex) {
        int[] diceValues = gameDice.getDiceValues();
        scores[scoreChoiceIndex].setDiceValuesForScoring(diceValues);
        PrintWithColor.brightGreen("You chose " + scores[scoreChoiceIndex].getName() + " and scored " + scores[scoreChoiceIndex].getValue() + " points.");
    }


    Scoreboard() {
        setup();
    }

    @SuppressWarnings("Duplicates")
    private Score[] getTempScoresFromValues(int[] diceValues) {
        Score[] tempScores = new Score[13];
        if (!scores[0].getIsUsed()) {
            OnesScore tempOnes = new OnesScore();
            tempOnes.setDiceValuesForScoring(diceValues);
            tempScores[0] = tempOnes;
        }
        if (!scores[1].getIsUsed()) {
            TwosScore tempTwos = new TwosScore();
            tempTwos.setDiceValuesForScoring(diceValues);
            tempScores[1] = tempTwos;
        }
        if (!scores[2].getIsUsed()) {
            ThreesScore tempThrees = new ThreesScore();
            tempThrees.setDiceValuesForScoring(diceValues);
            tempScores[2] = tempThrees;
        }
        if (!scores[3].getIsUsed()) {
            FoursScore tempFours = new FoursScore();
            tempFours.setDiceValuesForScoring(diceValues);
            tempScores[3] = tempFours;
        }
        if (!scores[4].getIsUsed()) {
            FivesScore tempFives = new FivesScore();
            tempFives.setDiceValuesForScoring(diceValues);
            tempScores[4] = tempFives;
        }
        if (!scores[5].getIsUsed()) {
            SixesScore tempSixes = new SixesScore();
            tempSixes.setDiceValuesForScoring(diceValues);
            tempScores[5] = tempSixes;
        }
        if (!scores[6].getIsUsed()) {
            ThreeOfAKindScore tempThreeOfKind = new ThreeOfAKindScore();
            tempThreeOfKind.setDiceValuesForScoring(diceValues);
            tempScores[6] = tempThreeOfKind;
        }
        if (!scores[7].getIsUsed()) {
            FourOfAKindScore tempFourOfKind = new FourOfAKindScore();
            tempFourOfKind.setDiceValuesForScoring(diceValues);
            tempScores[7] = tempFourOfKind;
        }
        if (!scores[8].getIsUsed()) {
            FullHouseScore tempFullHouse = new FullHouseScore();
            tempFullHouse.setDiceValuesForScoring(diceValues);
            tempScores[8] = tempFullHouse;
        }
        if (!scores[9].getIsUsed()) {
            SmallStraightScore tempSmallStraight = new SmallStraightScore();
            tempSmallStraight.setDiceValuesForScoring(diceValues);
            tempScores[9] = tempSmallStraight;
        }
        if (!scores[10].getIsUsed()) {
            LargeStraightScore tempLargeStraight = new LargeStraightScore();
            tempLargeStraight.setDiceValuesForScoring(diceValues);
            tempScores[10] = tempLargeStraight;
        }
        if (!scores[11].getIsUsed()) {
            ChanceScore tempChance = new ChanceScore();
            tempChance.setDiceValuesForScoring(diceValues);
            tempScores[11] = tempChance;
        }
        if (!scores[12].getIsUsed()) {
            YahtzeeScore tempYahtzee = new YahtzeeScore();
            tempYahtzee.setDiceValuesForScoring(diceValues);
            tempScores[12] = tempYahtzee;
        }
        return tempScores;
    } //if a space is not used, calculate its possible score and build it into an array of Score objects


    void printTemporaryScorecardForGameDice(AllDice gameDice) {
        PrintWithColor.brightCyan("***SCORE OPTIONS***");
        Score[] tempScoresForPrinting = getTempScoresFromValues(gameDice.getDiceValues());
        String tempScorecard = generateScorecard(tempScoresForPrinting);
        System.out.println(tempScorecard);
    }

    void printFinalScorecardForGameDice() {
        PrintWithColor.brightMagenta("Your Game Is Over!");
        PrintWithColor.brightBlue("***Your Final Scorecard***");
        String finalScorecard = generateScorecard(scores);
        System.out.println(finalScorecard);
        finalPlayerScore = totalPlayerScore;
        PrintWithColor.brightYellow("Your final score is " + totalPlayerScore + " points!");
}

    void printCurrentScorecardForGameDice() {
        PrintWithColor.cyan("***Your Current Scorecard***");
        String currentScorecard = generateScorecard(scores);
        System.out.println(currentScorecard);
        PrintWithColor.brightWhite("TOTAL SCORE: " + totalPlayerScore);
        System.out.println(""); //whitespace
    }

    private String generateScorecard(Score[] creationArray) {
        String scoreString = "";
        totalPlayerScore = 0;
        for (int i = 0; i < creationArray.length; i++) {
            if (creationArray[i] != null) { //make sure to avoid NPE by checking if the object has been removed b/c it's taken (making that index null)
                scoreString += i + ") " + creationArray[i].getName() + ": " + creationArray[i].getValue() + "\n";
                totalPlayerScore += creationArray[i].getValue();
            }
        }
        return scoreString;
    }

    boolean isUserScoreChoiceAvailable(int ScoreChoiceIndex) {
        if (scores[ScoreChoiceIndex].getIsUsed()) {
            return false;
        }
        return true;
    }

    boolean isBoardFilled() {
        for (int i = 0; i < scores.length; i++) {
            if (!scores[i].getIsUsed()) {
                return false;
            }
        }
        return true;
    }

}
