package com.vee.algorithms.problems.cake;

import org.junit.Test;

public class CakeMaxStockPrice {
	/**
	 * 
	 * Writing programming interview questions hasn't made me rich. Maybe trading Apple stocks will.
Suppose we could access yesterday's stock prices as a list, where:

The indices are the time in minutes past trade opening time, which was 9:30am local time.
The values are the price in dollars of Apple stock at that time.
So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500.

Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

For example:

  stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

get_max_profit(stock_prices_yesterday)
# returns 6 (buying for $5 and selling for $11)

No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
	 */
	@Test
	public void test() {
		System.out.println(stockMaxProfit(new int[]{10, 7, 5, 8, 11, 9}));
	}
	
	public int stockMaxProfit(int[] a) {
		int min = a[0];
		int max = a[0];
		int maxProfit = Integer.MIN_VALUE;
		int i = 1;
		while (i < a.length) {
			min = Math.min(a[i], min);
			max = min == a[i] ? max : Math.max(a[i], max);
			maxProfit = Math.max(maxProfit, max-min);
			i++;
		}
		return maxProfit;
	}
}
