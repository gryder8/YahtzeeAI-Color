package pkg;

public class OnesScore extends Score {

    private int tallyOnes(int[] diceRolls) {
        int sum = 0;
        for (int i = 0; i < diceRolls.length; i++) {
            if (diceRolls[i] == 1) {
                sum++;
            }
        }
        return sum;
    }

    public String getName() {
        return "Ones";
    }

    public int getValue() {
        return tallyOnes(myDiceValues);
    }
}
