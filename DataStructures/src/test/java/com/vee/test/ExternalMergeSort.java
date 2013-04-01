package com.vee.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ExternalMergeSort {
	public int numbers[];
	
	String infile = "";
	String outfile = "";
	
	int size=900;
	int m=100;
	int p=9;
	int slice = m/(p+1);
	
	ExternalMergeSort(){
		numbers = new int[m];
		String suffixarr[] = {"aa","ab","ac","ad","ae","af","ag","ah","ai"};
		try {
			Scanner[] sarr = new Scanner[p];
			for(int i = 0; i<p;i++)
				sortNsave(infile+"_"+suffixarr[i]);
			int arr[] = new int[p];
			for(int i = 0; i<p;i++) {
				sarr[i] = new Scanner(new File(infile+"_"+suffixarr[i]));
				arr[i] = sarr[i].nextInt();
			}
			int min=Integer.MAX_VALUE,minindex=0,cnt=0;
			while(cnt++ < size) {
				for(int i = 0; i<p;i++) {
					min = arr[i] < min ? arr[i] : min;
					minindex = arr[i] < min ? i : minindex;
				}
				System.out.println(min);
				arr[minindex] = sarr[minindex].nextInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void NWayMerge() {
		
	}
	
	void sortNsave(String filename) throws Exception {
		int aux[] = new int[m];
		Scanner s = new Scanner(filename);
		int i=0;
		while(s.hasNextInt())
			aux[i++] = s.nextInt();
		Arrays.sort(aux);
		s.close();
		i=0;
		PrintWriter p = new PrintWriter(filename);
		while(i<m) {
			p.println(aux[i]);
		}
		p.close();
	}
	
	void sort(int low, int high) {
		if(low<high) {
			int middle = low + (high-low)/2;
			sort(low, middle);
			sort(middle+1,high);
			merge(low,middle,high);
		}			
	}
	
	void merge(int low, int middle, int high) {
		int[] helpers = new int[high-low+1];
		for (int i = low; i <= high; i++) {
		      helpers[i-low] = numbers[i];
		}
		int i = low;
		int j = middle+1;
		int k = low;
		while(i <= middle && j <= high) {
			if(helpers[i-low] <= helpers[j-low])
				numbers[k++] = helpers[i++ - low];
			else
				numbers[k++] = helpers[j++ - low];
		}
		while(i<=middle) {
			numbers[k++] = helpers[i++ - low];
		}
	}
	
	private void merge1(int low, int middle, int high) {
		int[] helper = new int[m];
	    for (int i = low; i <= high; i++) 
	      helper[i] = numbers[i];

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	    }
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }
	  }
	
	public static void main(String args[]) {
		int in[] = {5,3,4,2,1,7,11,13,6,9,15,17,13,12,20,10,14,18};
		ExternalMergeSort e = new ExternalMergeSort();
		e.numbers = in;
		e.sort(0, in.length-1);
		for (int i = 0; i < e.numbers.length; i++) {
			System.out.print(e.numbers[i] + " ");
		}
	}
}
