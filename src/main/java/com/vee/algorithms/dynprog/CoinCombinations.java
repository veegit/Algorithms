package com.vee.algorithms.dynprog;

import java.util.Arrays;
import java.util.List;

/*
 * 2 Problems
 * 
 * Prerequisites - have coins on unlimited quantity of denominations v1, v2, v3 ..
 * i.  Find mininum number of coins to reach value V
 * ii. Fine number of combinations to reach value V
 * 
 * TODO
 */
public class CoinCombinations {
	
	public static void main(String[] args) {
		CoinCombinations c = new CoinCombinations();
		System.out.println(c.minNumOfCoins(new int[]{2, 5, 3, 6}, 10));
		System.out.println(c.numOfCombinations(new int[]{2, 5, 3, 6}, 10));
		System.out.println(c.numOfCombinations2D(new int[]{2, 3, 5}, 5));
	}

	/*
	 * 
	 */
	public int minNumOfCoins(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}
	/*
	 * f(n, k) = 	0, k < 1 or n < 0
					1, n = 0
					f(n, k - 1) + f(n - ak, k). else
	 */
	public int numOfCombinations(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = dp[i] + dp[i - coins[j]] //way to sum upto is count of solutions excluding Jth coin (dp[i]) and including Jth coin (dp[i-coins[j]])
				}
			}
		}
		return dp[amount]
	}
	
	public int numOfCombinations2D(int[] coins, int amount) {
		int[][] dp = new int[amount + 1][coins.length];
		// Fill the enteries for 0 value case (n = 0)
		for (int i = 0; i < coins.length; i++) {
			dp[0][i] = 1;
		}
		for (int i =1; i < amount+1 ; i++) {
			for (int j = 0; j < coins.length; j++) {
				// Count of solutions including coins[j]
				int x = 0;
				if (i - coins[j] >= 0) {
					x = dp[i - coins[j]][j];
				}

				// Count of solutions excluding coins[j]
				int y = (j >= 1) ? dp[i][j - 1] : 0;
				dp[i][j] = x + y;
			}
		}
		return dp[amount][coins.length-1];
	}
}
