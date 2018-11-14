package pkg;

public class ThreeOfAKindScore extends Score {

    private int[] tallyAllValues(int[] diceRolls) {
        int[] diceCount = new int[6];
        for (int i = 0; i < diceRolls.length; i++) {
            diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
        }
        return diceCount;
    }

    private int sumValues(int[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }

    private int calculateScore(int[] diceCount) {
        for (int diceValue : diceCount) {
            if (diceValue >= 3) {
                return sumValues(myDiceValues);
            }
        }
        return 0;
    }

    public String getName() {
        return "Three of a Kind";
    }

    public int getValue(){
        return calculateScore(tallyAllValues(myDiceValues));
    }
}

