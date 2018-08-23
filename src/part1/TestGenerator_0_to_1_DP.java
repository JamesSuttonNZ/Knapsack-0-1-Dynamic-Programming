package part1;

import java.util.Random;

/**
 * Takes inputs in order to generate values for the knapsack
 * @author James Sutton
 *
 */
public class TestGenerator_0_to_1_DP {

	public static void main(String[] args){
		
		String testArgs[] = new String[3];
		
		String weights = "";
		String values = "";
		
		int noObjects = Integer.parseInt(args[0]);
		
		String[] wRange = args[1].split(",");
		int minWeight = Integer.parseInt(wRange[0]);
		int maxWeight = Integer.parseInt(wRange[1]);
		
		String[] vRange = args[2].split(",");
		int minValue = Integer.parseInt(vRange[0]);
		int maxValue = Integer.parseInt(vRange[1]);
		
		Random r = new Random(1);
		int w = r.nextInt((maxWeight-minWeight)+1) + minWeight;
		int v = r.nextInt((maxValue-minValue)+1) + minValue;
		weights += w;
		values += v;
		
		for(int i = 1; i < noObjects; i++){
			int w2 = r.nextInt((maxWeight-minWeight)+1) + minWeight;
			int v2 = r.nextInt((maxValue-minValue)+1) + minValue;
			weights += ","+w2;
			values += ","+v2;
		}
		
		testArgs[0] = weights;
		testArgs[1] = values;
		testArgs[2] = args[3]; // weight limit
		
		Knapsack_0_to_1_DP.main(testArgs);
	}
	
}
