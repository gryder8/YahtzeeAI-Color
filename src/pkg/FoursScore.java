package pkg;
public class FoursScore extends Score{

	private int tallyFours(int[] diceRolls){
		int sum = 0;
		for (int i = 0; i<diceRolls.length; i++){
			if (diceRolls[i] == 4){
				sum++;
			}
		}
		return sum;
	}

	public String getName(){
		return "Fours";
	}

	public int getValue(){
		return tallyFours(myDiceValues)*4;
	}
}
