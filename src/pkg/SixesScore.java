package pkg;
public class SixesScore extends Score{

	private int tallySixes(int[] diceRolls){
		int sum = 0;
		for (int i = 0; i<diceRolls.length; i++){
			if (diceRolls[i] == 6){
				sum++;
			}
		}
		return sum;
	}

	public String getName(){
		return "Sixes";
	}

	public int getValue(){
		return tallySixes(myDiceValues)*6;
	}}
