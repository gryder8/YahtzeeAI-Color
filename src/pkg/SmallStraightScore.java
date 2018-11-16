package pkg;
public class SmallStraightScore extends Score {

	private int[] tallyAllValues(int[] diceRolls) {
		int[] diceCount = new int[6];
		for (int i = 0; i < diceRolls.length; i++) {
			diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
		}
		return diceCount;
	}
	
	private int calculateScore(int[] values) {
		int counter = 0;
		final int LAST_ARRAY_PLACE = values.length-1;
		for (int i = 0; i<values.length-1; i++){
			if (values[i] >= 1){
				counter++;
			} else if (values[i] == 0 && i!=LAST_ARRAY_PLACE){ //if the value is 0 and it's not in the last array place, a small straight is not possible
				return 0;
			}
			if (counter >= 4){ //also returns true if a large straight occurs
				return 30;
			}
		}
		return 0;
	}

	public String getName(){
		return "Small Straight";
	}

	public int getValue(){
		return calculateScore(tallyAllValues(myDiceValues)); //tally the values, calculate them, then return them
	}
}
