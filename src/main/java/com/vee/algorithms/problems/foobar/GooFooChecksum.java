package com.vee.algorithms.problems.foobar;

import org.junit.Test;

public class GooFooChecksum {

	/*
	 * You're almost ready to make your move to destroy the LAMBCHOP doomsday
	 * device, but the security checkpoints that guard the underlying systems of
	 * the LAMBCHOP are going to be a problem. You were able to take one down
	 * without tripping any alarms, which is great! Except that as Commander
	 * Lambda's assistant, you've learned that the checkpoints are about to come
	 * under automated review, which means that your sabotage will be discovered
	 * and your cover blown - unless you can trick the automated review system.
	 * 
	 * To trick the system, you'll need to write a program to return the same
	 * security checksum that the guards would have after they would have
	 * checked all the workers through. Fortunately, Commander Lambda's desire
	 * for efficiency won't allow for hours-long lines, so the checkpoint guards
	 * have found ways to quicken the pass-through rate. Instead of checking
	 * each and every worker coming through, the guards instead go over everyone
	 * in line while noting their security IDs, then allow the line to fill back
	 * up. Once they've done that they go over the line again, this time leaving
	 * off the last worker. They continue doing this, leaving off one more
	 * worker from the line each time but recording the security IDs of those
	 * they do check, until they skip the entire line, at which point they XOR
	 * the IDs of all the workers they noted into a checksum and then take off
	 * for lunch. Fortunately, the workers' orderly nature causes them to always
	 * line up in numerical order without any gaps.
	 * 
	 * For example, if the first worker in line has ID 0 and the security
	 * checkpoint line holds three workers, the process would look like this:
	 * 
	 * 	0 1 2 /
		3 4 / 5
		6 / 7 8 
		where the guards' XOR (^) checksum is 0^1^2^3^4^6 == 2.
	 * 
	 * Likewise, if the first worker has ID 17 and the checkpoint holds four
	 * workers, the process would look like:
	 * 
	 * 	17 18 19 20 /
		21 22 23 / 24
		25 26 / 27 28
		29 / 30 31 32 
	 * which produces the checksum 17^18^19^20^21^22^23^25^26^29 == 14.
	 * 
	 * All worker IDs (including the first worker) are between 0 and 2000000000
	 * inclusive, and the checkpoint line will always be at least 1 worker long.
	 * 
	 * With this information, write a function answer(start, length) that will
	 * cover for the missing security checkpoint by outputting the same checksum
	 * the guards would normally submit before lunch. You have just enough time
	 * to find out the ID of the first worker to be checked (start) and the
	 * length of the line (length) before the automatic review occurs, so your
	 * program must generate the proper checksum with just those two values.
	 * 
	 * Test cases
	 * 
	 * Inputs: (int) start = 0 (int) length = 3 Output: (int) 2
	 * 
	 * Inputs: (int) start = 17 (int) length = 4 Output: (int) 14
	 */
	@Test
	public void test() {
		System.out.println(checksum(17, 4)); //returns 14
		System.out.println(checksum(0, 3)); //returns 2
	}

	public int checksum(int start, int length) {
		int ans = 0;
		int j = length;	
		for (int i = 0; i < length; i++) {
			int end = start + --j;
			ans = ans^xor(start-1);
			ans = ans^xor(end);
			start = start+length;
		}
		return ans;
	}
	
	private int xor(int a) {
		int rem = a%4;
		switch (rem) {
			case 0: return a;
			case 1: return 1;
			case 2: return a+1;
			case 3: return 0;
		}
		return 0;
	}
	
	public void test5() {
		int length = 100000;//1651559072
		//2,147,483,647
		long stime = System.currentTimeMillis();
		int start = 17;
		int ans = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length+1; j++) {
				if (i+j < length) {
					ans = ans ^ start++;
				}
				if (i+j == length) {
					start = start + length-j;
					j = length+1;
					continue;
				}
				
			}
			/*if (i%1000 == 0) System.out.println(i);*/
		}
		System.out.println(System.currentTimeMillis()-stime);
		System.out.println(ans);
	}
}
