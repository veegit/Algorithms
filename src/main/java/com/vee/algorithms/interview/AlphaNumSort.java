package com.vee.algorithms.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Test;


/**
 * 
 * vee
 * Problem Statement # 1:
(Version: JumbleSort $Revision: 1.9 $)
You are to write a program that takes a list of strings containing integers
and words and returns a sorted version of the list.

The goal is to sort this list in such a way that all words are in
alphabetical order and all integers are in numerical order.  Furthermore,
if the nth element in the list is an integer it must remain an integer,
and if it is a word it must remain a word.

Input:
------
The input will contain a single, possibly empty, line containing a
space-separated list of strings to be sorted. Words will not contain spaces,
will contain only the lower-case letters a-z. Integers will be in the range
-999999 to 999999, inclusive. The line will be at most 1000 characters long.

Output:
-------
The program must output the list of strings, sorted per the requirements
above. Strings must be separated by a single space, with no leading space
at the beginning of the line or trailing space at the end of the line.

Constraints:
------------
The code you submit must take input from stdin and produce output to stdout
as specified above. No other output is permitted. You can assume the input
will be valid. Feel free to use standard libraries to assist in sorting.

In the examples below, the text "Input:" and "Output:" are not part of the
output, and neither are the blank lines.

Example 1:
----------
Input:
1

Output:
1

Example 2:
----------
Input:
car truck bus

Output:
bus car truck

Example 3:
----------
Input:
8 4 6 1 -2 9 5

Output:
-2 1 4 5 6 8 9

Example 4:
----------
Input:
car truck 8 4 bus 6 1

Output:
bus car 1 4 truck 6 8
 *
 */

//interview = two sigma
public class AlphaNumSort {

	/*public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String text = scan.nextLine();
		String splits[] = text.split("\\s");
		ArrayList<Alpha> alpha = new ArrayList<Alpha>();
		ArrayList<Num> num = new ArrayList<Num>();
		int i =0;
		for (i = 0; i < splits.length; i++) {
			if (splits[i].matches("[0-9]+") && text.length() > 2) {
				num.add(new Num(Integer.parseInt(splits[i]),i));
			} else {
				alpha.add(new Alpha(splits[i],i));
			}
		}
	}*/
	
	@Test
	public void sort() {
		String input = "car truck 8 4 bus 6 1";
		String []in = input.split("\\s+");
		List<String> alpha = new ArrayList<>();
		List<Integer> num = new ArrayList<>();
		List<String> alphanum = new ArrayList<>();
		for (String i : in) {
			try {
				Integer n = Integer.parseInt(i);
				num.add(n);
			} catch (NumberFormatException e) {
				alpha.add(i);
			}
		}
		Collections.sort(alpha);
		Collections.sort(num);
		for (String i : in) {
			try {
				Integer.parseInt(i);
				alphanum.add(num.remove(0).toString());
			} catch (NumberFormatException e) {
				alphanum.add(alpha.remove(0));
			}
		}
		String s = alphanum.stream().collect(Collectors.joining(" "));
		System.out.println(s);
	}
}

