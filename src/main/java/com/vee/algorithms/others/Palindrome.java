package com.vee.algorithms.others;

/*

 1992 -> 1991
 1881 -> 1881
 1999 -> 2002
 1800 -> 1771
 18 -> {17,18,19} -> {1771,1881,1991}

 123456 -> 123321


 */

public class Palindrome {

	public static void main(String[] args) {
		int n = 1999999;
		int result = new Palindrome().closestPalindrome(n);
		System.out.println("Reversed number1 :" + result);
		result = new Palindrome().closestPalindrome1(n);
		System.out.println("Reversed number2 :" + result);
	}

	int closestPalindrome(int number) {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		int left = number;
		int right = number;
		while (left > 0) {
			if (isPalindrome(left)) {
				return left;
			} else if (isPalindrome(right)) {
				return right;
			}
			left--;
			right++; // can cause integer overflow if number = INT_MAX
		}
		return 0;
	}

	private boolean isPalindrome(int number) {
		int num = number;
		int reversedNumber = reverse(number);
		return num == reversedNumber;
	}

	int reverse(int number) {
		int reversedNumber = 0;
		while (number > 0) {
			int rem = number % 10;
			number = number / 10;
			reversedNumber = reversedNumber * 10 + rem;
		}
		return reversedNumber;
	}

	int closestPalindrome1(int number) {

		int[] left = leftSplit(number);
		int less = constructRight(left[0] - 1, left[1]);
		int same = constructRight(left[0], left[1]);
		int more = constructRight(left[0] + 1, left[1]);

		int min = minDiff(number, less, same, more);
		return min;
	}

	int[] leftSplit(int number) {
		String num = new Integer(number).toString();
		int endIndex = num.length() / 2;
		int left = Integer.parseInt(num.substring(0, endIndex));
		int middle = num.length() % 2 == 0 ? -1 : Character.getNumericValue(num.charAt(endIndex));
		System.out.println("leftSplit: "+ left);
		return new int[] {left, middle};
	}

	int constructRight(int left, int middle) {
		int right = reverse(left);
		String leftString = String.valueOf(left);
		String rightString = String.format("%0" + leftString.length() + "d", right);
		String temp = leftString + (middle == -1 ? "" : String.valueOf(middle)) + rightString;
		System.out.println("constructRight: " + temp);
		int number = Integer.parseInt(temp);
		return number;
	}

	int minDiff(int number, int less, int same, int more) {
		if (Math.abs(number - less) < Math.abs(number - more)) {
			if (Math.abs(number - less) < Math.abs(number - same)) {
				return less;
			} else {
				return same;
			}
		} else {
			if (Math.abs(number - more) < Math.abs(number - same)) {
				return more;
			} else {
				return same;
			}
		}
	}
}