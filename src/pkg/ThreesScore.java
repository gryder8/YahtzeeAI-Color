package pkg;

public class ThreesScore extends Score {

    private int tallyThrees(int[] diceRolls) {
        int sum = 0;
        for (int i = 0; i < diceRolls.length; i++) {
            if (diceRolls[i] == 3) {
                sum++;
            }
        }
        return sum;
    }

    public String getName() {
        return "Threes";
    }

    public int getValue() {
        return tallyThrees(myDiceValues) * 3;
    }
}