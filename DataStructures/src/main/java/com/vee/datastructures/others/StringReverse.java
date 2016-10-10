package com.vee.datastructures.others;
public class StringReverse {

	static void reverse(String str) {
		char[] s = str.toCharArray();
		int i = s.length-1,end=i;
		while(i>=0) {
			if(s[i] == ' ') {
				print(s, i,end);
				System.out.print(s[i]+"");
				end = i-1;
			}
			i--;
		}
		print(s, i,end);
	}
	
	static void print(char[]s, int start,int end) {
		while(start < end)
			System.out.print(s[++start]);
	}
	
	public static void main(String[] args) {
		reverse("very good is bad");
	}
}
