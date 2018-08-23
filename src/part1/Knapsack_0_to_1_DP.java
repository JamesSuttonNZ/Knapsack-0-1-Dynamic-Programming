package part1;

import java.util.Arrays;

/**
 * Dynamic Programming Algorithm to solve a 0-1 Knapsack Problem
 * @author James Sutton
 *
 */
public class Knapsack_0_to_1_DP {
	
	private static int[] weights;
	private static int[] values;
	private static int[] choices;
	private static int weightLimit;
	
	public static void main(String[] args){
		
		String[] w = args[0].split(",");
		weights = new int[w.length];
		for(int i = 0; i < w.length; i++){
			weights[i] = Integer.parseInt(w[i]);
		}
		
		String[] v = args[1].split(",");
		values = new int[v.length];
		for(int i = 0; i < v.length; i++){
			values[i] = Integer.parseInt(v[i]);
		}

		weightLimit = Integer.parseInt(args[2]);
		
		if(values.length != weights.length){
			throw new Error("number of values and weights needs to be the same");
		}
		
		System.out.println("Weights: "+Arrays.toString(weights));
		System.out.println("Values: "+Arrays.toString(values));
		System.out.println("Weight Limit: "+weightLimit+"\n");
		
		final long startTime = System.currentTimeMillis();
		knapsack();
		final long endTime = System.currentTimeMillis();

		System.out.println("\nTotal execution time: " + (endTime - startTime) + "ms");
	}

	/**
	 * Creates dynamic programming table
	 */
	private static void knapsack() {
		
		int[][] V = new int[values.length][weightLimit+1];
		
		for(int row = 0; row < values.length; row++){
			for(int col = 0; col < weightLimit+1; col++){
				if(row == 0){
					if(weights[row] <= col && weights[row] <= weightLimit){
						V[row][col] = values[row];
					}
				}
				else{
					int above = V[row-1][col];
					if(col-weights[row] < 0){
						V[row][col] = above;
					}else{
						V[row][col] = Math.max(V[row-1][col], (V[row-1][col-weights[row]]+values[row]));
					}
				}
			}
		}
		
		//prints table (commented out for consistency of execution time)
		System.out.println("Table:");
		for (int[] row : V){
			System.out.println(Arrays.toString(row));
		}
		
		recovery(V);
	}

	/**
	 * recovers the chosen values from the table
	 * @param V
	 */
	private static void recovery(int[][] V) {
		//recovery
		choices = new int[values.length];
		int row = values.length-1;
		int col = weightLimit;
		int totValue = 0;
		int totWeight = 0;
		while(row > 0){
			int score = V[row][col];
			int above = V[row-1][col];
			if(score == above){
				choices[row] = 0;
				row--;
			}
			else{
				choices[row] = 1;
				col = col - weights[row];
				totValue += values[row];
				totWeight += weights[row];
				row--;	
			}
		}
		
		//check if we can take first row object
		if(totWeight+weights[row] <= weightLimit){
			choices[row] = 1;
			totValue += values[row];
			totWeight += weights[row];
		}
		
		System.out.println("\nChoices: "+Arrays.toString(choices));
		System.out.println("Total Weight of chosen objects: "+totWeight);
		System.out.println("Total Value of chosen objects: "+totValue);
	}
	
}
