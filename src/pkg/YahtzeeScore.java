package pkg;
public class YahtzeeScore extends Score {

	private int[] tallyAllValues(int[] diceRolls) {
		int[] diceCount = new int[6];
		for (int i = 0; i < diceRolls.length; i++) {
			diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
		}
		return diceCount;
	}
	
	private int calculateScore(int[] values) {
		for (int item : values){
			if (item == 5){ //all items are the same number
				return 50;
			}
		}
		return 0;
	}

	public String getName(){
		return "Yahtzee";
	}


	public int getValue(){
		return calculateScore(tallyAllValues(myDiceValues));
	}
}
