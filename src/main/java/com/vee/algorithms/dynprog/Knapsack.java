package com.vee.algorithms.dynprog;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.Test;

public class Knapsack {

	int[] weights; // should n+1 with [0] = 0
	int[] values; // should be n+1 with [0] = 0;
	int maxWeight;

	public int knapsack01() {
		int n = values.length;
		int[][] dp = new int[n][maxWeight+1];
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= maxWeight; j++) {
				if (weights[i] <= j) {
					dp[i][j] = Math.max(
							dp[i-1][j],// If we exclude ith item to knapsack for maxweight =weight[j]
							values[i] + dp[i-1][j-weights[i]]// if we include ith item in knapsack for maxweight =weight[j]
							);
				} else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[n-1][maxWeight];
	}

	public int knapsack01light() {
		int n = values.length;
		int[] dp = new int[maxWeight+1];
		int[] prev_dp = new int[maxWeight+1];
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= maxWeight; j++) {
				if (weights[i] <= j) {
					dp[j] = Math.max(
							prev_dp[j],// If we exclude ith item to knapsack for maxweight =weight[j]
							values[i] + prev_dp[j-weights[i]]// if we include ith item in knapsack for maxweight =weight[j]
							);
				} else {
					dp[j] = prev_dp[j];
				}
			}
			System.arraycopy(dp, 0, prev_dp, 0, dp.length);
		}
		return dp[maxWeight];
	}

	private void init(String pathName) {
		try {
			Scanner scanner = new Scanner(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(pathName)));
			maxWeight = scanner.nextInt();
			int n = scanner.nextInt()+1;
			weights = new int[n];
			values = new int[n];
			values[0] = 0;
			weights[0] = 0;
			int i = 1;
			while(scanner.hasNextInt()) {
				values[i] = scanner.nextInt();
				weights[i++] = scanner.nextInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Knapsack build(String pathName) {
		Knapsack k = new Knapsack();
		k.init(pathName);
		return k;
	}

	@Test
	public void testKnapsack01() {
		Knapsack k = Knapsack.
				build("/home/vee/workspace/data/coursera/knapsack_small.txt");
		assertEquals(2493893, k.knapsack01());
	}
}
