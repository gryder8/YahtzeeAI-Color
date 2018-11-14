package pkg;
public class FourOfAKindScore extends Score {

	private int[] tallyAllValues(int[] diceRolls) {
		int[] diceCount = new int[6];
		for (int i = 0; i < diceRolls.length; i++) {
			//System.out.println("DiceRolls[i]-1 in 3 of kind is: "+((diceRolls[i])-1));
			diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
		}
		return diceCount;
	}

	private int sumValues (int[] values){
		int sum= 0;
		for (int i = 0; i<values.length; i++){
			sum+=values[i];
		}
		return sum;
	}

	private int calculateScore (int[] values) {
		int sum = 0;
		for (int item : values){
			if (item>=4){
				return sumValues(myDiceValues);
			}
		}
		return 0;
	}

	public String getName(){
		return "Four of a Kind";
	}

	public int getValue(){
		return calculateScore(tallyAllValues(myDiceValues));
	}
}


