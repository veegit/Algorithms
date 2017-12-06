package com.vee.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		
		//testStringEquals();
		//testFib();
		System.out.println(strStr("mississippi","issippi"));
	}
	
	public static void episodeList() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(
						"D:\\TEMP\\episodes.txt"))));
		String ep;
		int i = 1;
		String start = "{| class=\"wikitable plainrowheaders\" style=\"width: 100%%; margin-right: 0;\"";
		String s = "";
		String h = "|-\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Episode\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Title\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Summary\n";
		s = s + "|-\n";
		s = s + "|%d\n";
		s = s + "|%s\n";
		s = s + "|%s";
		String end = "|}\n";
		System.out.println(start);
		System.out.print(h);
		while ((ep = br.readLine()) != null) {
			System.out.println(String.format(s, i++, ep, ep));
		}
		System.out.println(end);
	}
	
	public static int testTryCatch(){
		try{
			System.out.println("try");
			return 1;
		}
		catch(Exception e) {
			return 2;
		}
		finally {
			return 0;
		}
	}
	
	public static void testStringEquals(){
		String a = "abc";
		String b = "abc";
		String c = b;
		String d;
		d="abc";
		String e="";
		e="abc";
		String f = "abce";
		String g = f.substring(0,3);
		System.out.println(a==b);
		System.out.println(c==a);
		System.out.println(d==b);
		System.out.println(e==b);
		System.out.println(e==g);
		System.out.println(a.equals(c));
		System.out.println(e.equals(g));
		System.out.println(g);
		
	}
	
	public static void testFib() {
		long start = System.currentTimeMillis();
		int n =40;
		int a[] = new int[n+1];
		for (int j=0;j< n+1;j++)
			a[j] = 0;
		for(int i = 1; i <=n; i++ ) {
			System.out.print(fib(i,a));
			System.out.println();
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	
	public static int fib(int n,int[] a) {
		if(n<=1) {
			a[n] = n;
			return n;
		}
		else{
			int b = (a[n-2] == 0)?fib(n-2,a):a[n-2];
			int c = (a[n-1] == 0)?fib(n-1,a):a[n-1];
			a[n] = b+c;
			return b + c;
		}
	}
	public static int fib(int n) {
		if(n<=1) {
			return n;
		}
		else{
			return fib(n-1)+fib(n-2);
		}
	}
	
	public int pow(int a, int b) {
		return 0;
	}
	
	@Test
	public void testRange() {
		int[] array = new int[] { 1, 3, 4, 5, 8, 9, 10, 15,16,17 };//[1, 3-5, 8-15]
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
	
	public static int strStr(String haystack, String needle) {
		char[] hArr = haystack.toCharArray();
		char[] nArr = needle.toCharArray();
		if (hArr.length == 0 && nArr.length == 0) {
			return 0;
		} else if (hArr.length == 0 || nArr.length == 0) {
			return -1;
		}
		for (int i = 0; i < hArr.length; i++) {
			int k = i;
			if (hArr[k] == nArr[0]) {
				int j = 1;k++;
				while (j < nArr.length && nArr.length- j < hArr.length - k) {
					System.out.println(String.format("i=%d, j=%d, k=%d", i,j,k));
					if (hArr[k] == nArr[j]) {
						k++;
						j++;
					} else {
						break;
					}
				}
				System.out.println(String.format(" i=%d, j=%d, k=%d", i,j,k));
				if (j == nArr.length) {
					return i;
				}
			}
		}
		return -1;
	}
}
