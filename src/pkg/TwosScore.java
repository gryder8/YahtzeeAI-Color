package pkg;
public class TwosScore extends Score {

	private int tallyTwos(int[] diceRolls){
		int sum = 0;
		for (int i = 0; i<diceRolls.length; i++){
			if (diceRolls[i] == 2){
				sum++;
			}
		}
		return sum;
	}

	public String getName(){
		return "Twos";
	}

	public int getValue(){
		return tallyTwos(myDiceValues)*2;
	}
}