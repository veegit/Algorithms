package com.vee.datastructures.others;
 
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// 1, 3, 4, 5, 8, 9, 10, 15,16,17 };//[1, 3-5, 8-15]

public class RangeConsolidate {
	public RangeConsolidate(int[] array) {
		List<String> ranges = new ArrayList<>();
		int rstart, rend;
		for (int i = 0; i < array.length-1; i++) {
			rstart = array[i];
			rend = rstart;
			while (i < array.length-1 && array[i + 1] - array[i] == 1) {
				rend = array[i + 1]; // increment the index if the numbers sequential
				i++;
			}
			ranges.add(rstart == rend ? rstart + "" : rstart + "-" + rend);
		}
		System.out.println(ranges);
	}
	
	public static void main(String args[]) {
		int[] array = new int[] { 1, 3, 4, 5, 8, 9, 10, 15,16,17 };//[1, 3-5, 8-15]
		new RangeConsolidate(array);
	}
}