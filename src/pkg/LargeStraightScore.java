package pkg;
public class LargeStraightScore extends Score {

	private int[] tallyAllValues(int[] diceRolls) {
		int[] diceCount = new int[6];
		for (int i = 0; i < diceRolls.length; i++) {
			diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
		}
		return diceCount;
	}

	private int calculateScore(int[] values) {
		int counter = 0;
		for (int i = 0; i<values.length-1; i++){
			if (values[i] == 1){
				counter ++;
			}
			else {
				return 0;
			}
		}
		if (counter >= 5){
			return 40;
		} else {
			return 0;
		}
	}

	public String getName(){
		return "Large Straight";
	}

	public int getValue(){
		return calculateScore(tallyAllValues(myDiceValues));
	}
}
