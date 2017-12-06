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

	/*
	 * 
	 */
	public int minNumOfCombinations(int[] coins, int amount) {
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
	public List<String> numOfCombinations() {
		return null;
	}
}
