package com.vee.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class MedianMaintenance {

	final static String FILENAME = 
			"/home/narayanan/workspace/data/MedianMaintenance.txt";
	
	void treeBased() {
		long sum = 0, cnt =0;
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		try {
			Scanner scan = new Scanner(new File(FILENAME));
			while(scan.hasNextInt()) {
				map.put(scan.nextInt(), 1);
				Integer[] arr = (Integer[]) map.keySet().toArray(new Integer[0]);
				int index = Math.round(arr.length/2f);
				sum = sum + arr[index-1];
				cnt++;
			}
			System.out.println(cnt + " " + sum % cnt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MedianMaintenance().treeBased();
	}

}
