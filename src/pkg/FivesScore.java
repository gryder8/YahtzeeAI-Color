package pkg;

public class FivesScore extends Score {

    private int tallyFives(int[] diceRolls) {
        int sum = 0;
        for (int i = 0; i < diceRolls.length; i++) {
            if (diceRolls[i] == 5) {
                sum++;
            }
        }
        return sum;
    }

    /*public int calculateScore(int[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == 5) {
                sum += values[i];
            }
        }
        return sum;
    }*/

    public String getName() {
        return "Fives";
    }

    public int getValue() {
        return tallyFives(myDiceValues) * 5;
    }
}
