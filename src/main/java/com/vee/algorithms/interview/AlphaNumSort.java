package com.vee.algorithms.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;


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
public class AlphaNumSort extends BaseSort {

	public static void main(String[] args) {
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
		new AlphaNumSort(i,alpha.toArray(new Alpha[0]),num.toArray(new Num[0]));

	}

	public AlphaNumSort(int count, Alpha[] alpha, Num[] num) {
		String[] alphanums = new String[count];
		Alpha[] aarray = alpha;
		int size = alpha.length;
		runQuickSort_First(aarray, 0,size - 1);
		Num[] narray = num;
		size = num.length;
		runQuickSort_First(narray, 0,size - 1);
		for (int i = 0; i < aarray.length; i++) {
			alphanums[aarray[i].getPosition()] =
					aarray[i].getValue();
		}
		for (int i = 0; i < narray.length; i++) {
			alphanums[narray[i].getPosition()] = narray[i].getValue()+"";
		}
		for (int i = 0; i < alphanums.length; i++) {
			System.out.print(alphanums[i] + " ");
		}
	}

	void runQuickSort_First(AlphaNum[] array, int low, int high){
		if(low >= high) {
			return;
		}
		int index = partition(array, low,high);
		runQuickSort_First(array, low,index-1);
		runQuickSort_First(array, index+1,high);
	}

	int partition(AlphaNum[] array, int low,int high) {
		int i = low+1, j = low+1;
		AlphaNum pivot = array[low];
		while(i<=high) {
			if(compare(array[i],pivot)<0) {
				exchange(array,i,j);
				j++;
			}
			i++;
		}
		exchange(array,low,j-1);// exchange rightmost element less than p
		return j-1;
	}

	public int compare(AlphaNum o1, AlphaNum o2) {
		if(o1 instanceof Alpha) {
			return ((Alpha) o1).getValue().compareTo(((Alpha) o2).getValue());
		} else {
			return ((Num) o1).getValue()- ((Num) o2).getValue();
		}
	}

	public void alphaNumSort(String s) {
		String a[] = s.split("\\W+");
		ArrayList<String> alpha = new ArrayList<String>();
		ArrayList<Integer> num = new ArrayList<Integer>();
		boolean isAlpha[] = new boolean[a.length];
		for (int i = 0; i<a.length; i++) {
			try {
				Integer in = Integer.parseInt(a[i]);
				num.add(in);
				isAlpha[i] = false;
			} catch (NumberFormatException e) {
				alpha.add(a[i]);
				isAlpha[i] = true;
			}
		}
		Collections.sort(alpha, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});
		Collections.sort(num);
		Iterator<String> ia = alpha.iterator();
		Iterator<Integer> in = num.iterator();
		for (boolean b : isAlpha) {
			if (b) {
				System.out.println(ia.next());
			} else {
				System.out.println(in.next());
			}
		}
	}

}

class Alpha extends AlphaNum {
	private String value;
	
	public Alpha(String value, int position) {
		this.value = value;
		this.position = position;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

class Num extends AlphaNum{
	private int value;
	
	public Num(int value, int position) {
		this.value = value;
		this.position = position;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

abstract class AlphaNum {
	protected int position;
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}

abstract class BaseSort {

	protected void exchange(AlphaNum[] array, int i, int j) {
		AlphaNum temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		int position = array[i].getPosition();
		array[i].setPosition(array[j].getPosition());
		array[j].setPosition(position);
	}
}


