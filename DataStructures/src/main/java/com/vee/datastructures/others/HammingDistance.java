package com.vee.datastructures.others;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HammingDistance {
	
	static void AllPairs(List<Integer> list) {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int num : list) {
			int digit = 1;
			while(num >0) {
				Integer val = map.get(digit); 
				if( val == null)
					val = 0;
				val += num & 1;
				num = num >> 1;
				map.put(digit,val);
				digit++;
			}
		}
		int sum = 0;
		for(Map.Entry<Integer,Integer> entry: map.entrySet()) 
			sum += (list.size()-entry.getValue()) * entry.getValue();
		System.out.println(sum);
	}
	
	static void AllPairsXor(List<Integer> list) {
		Integer a[] = list.toArray(new Integer[]{1});
		int sum = 0;
		for (int i = 0; i < a.length-1; i++) {
			for (int j = i+1; j < a.length; j++) {
				sum = sum + popcount(a[i], a[j]);
			}
		}
		System.out.println(sum);
	}
	
	static int popcount(int a, int b) {
		int sum = 0;
		while(a > 0 || b > 0) {
			if(((a & 1) != (b & 1)))
				sum++;
			a = a >> 1;
			b = b >> 1;
		}
		return sum;
	}
	
	public static void main(String args[]) {
		List<Integer> list = Arrays.asList(5,7,4,1,2); 
		AllPairsXor(list);
		AllPairs(list);
	}
}
