package pkg;

public class AllDice {

    /**
     * CONSTRUCTORS
     */
    private Die[] dice = new Die[5]; //array of 5 dice

    //overloaded constructor
    public AllDice(int numSides) {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die(numSides); //dice[i] points to a new dice object with specified number of sides passed
        }
    }

    AllDice() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    /**
     * INSTANCE METHODS
     */
    int diceInContainer() {
        return dice.length;
    }

    void dontRollDiceNumber(int diceNumber) {
        dice[diceNumber].hold();
    }

    void rollDiceNumber(int diceNumber) {
        dice[diceNumber].release();
    }


    void rollAll() { //no return needed
        for (int i = 0; i < dice.length; i++) {
            dice[i].roll();
        }
    }

    int getSpecificValueOfDice(int index) {
        return dice[index].getCurrentValue();
    }


    // return the int values of each "Die" object
    int[] getDiceValues() {
        int[] output = new int[dice.length]; //stores dice values
        for (int i = 0; i < dice.length; i++) {
            output[i] = dice[i].getCurrentValue();
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < dice.length; i++) {
            output.append(dice[i].toString() + ","); //value of given object toString()
        }
        return output.toString();
    }
}