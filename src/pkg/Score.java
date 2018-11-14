package pkg;

public abstract class Score {

    public Score() {
        myDiceValues = new int[0];
    }

    public int[] myDiceValues;

    void setDiceValuesForScoring(int[] diceValueInput) {
        myDiceValues = diceValueInput;
    }

    public abstract String getName(); //each Score object must override this!

    public abstract int getValue(); //each Score object must override this!


    boolean getIsUsed() {
        if (myDiceValues == null) {
            return false;
        } else if (myDiceValues.length == 0) {
            return false;
        }
        return true;
    }
}
