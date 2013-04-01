package com.vee.test;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class FileMergeSort {
	String infile = "";
	
	int size=900;
	int m=100;
	int p=9;
		
	FileMergeSort(){
		String suffixarr[] = {"aa","ab","ac","ad","ae","af","ag","ah","ai"};
		try {
			Scanner[] sarr = new Scanner[p];
			for(int i = 0; i<p;i++)
				sortNsave(infile+suffixarr[i]);
			int arr[] = new int[p];
			for(int i = 0; i<p;i++) {
				sarr[i] = new Scanner(new File(infile+suffixarr[i]));
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
			p.println(aux[i++]);
		}
		p.close();
	}

	public static void main(String args[]) {
		FileMergeSort e = new FileMergeSort();
	}
}
