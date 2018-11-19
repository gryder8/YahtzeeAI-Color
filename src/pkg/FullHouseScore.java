package pkg;
public class FullHouseScore extends Score {

	private int[] tallyAllValues(int[] diceRolls) {
		int[] diceCount = new int[6];
		for (int i = 0; i < diceRolls.length; i++) {
			//System.out.println("DiceRolls[i]-1 in 3 of kind is: "+((diceRolls[i])-1));
			diceCount[(diceRolls[i])-1] += 1; //increment the count by 1 for index pointed to
		}
		return diceCount;
	}
	
	private int calculateScore(int[] values) { //return 40 if there any two places hold 2 and 3, respectively
		boolean hasTwo = false;
		boolean hasThree = false;
		for (int item : values){
			if (item == 2){
				hasTwo = true;
			} else if (item == 3){
				hasThree = true;
			}
		}
		if (hasThree && hasTwo){
			return 25;
		}
		return 0;
	}

	public String getName(){
		return "Full House";
	}

	public int getValue(){
		return calculateScore(tallyAllValues(myDiceValues));
	}
}
