package pkg;
public class ChanceScore extends Score {

	private int sumValues(int[] values) {
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return sum;
	}

	public String getName(){
		return "Chance";
	}

	public int getValue(){
		return sumValues(myDiceValues);
	}
}
