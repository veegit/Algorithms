package com.vee.datastructures.dynprog;

public class LCSubstr {
	
	public String LCS(String a, String b,int i, int j) {
		if(i == -1 || j == -1)
			return "";
		if(a.charAt(i) == b.charAt(j))
			return LCS(a,b,i-1,j-1)+ a.charAt(i);
		else
		{
			String f = LCS(a,b,i-1,j);
			String s = LCS(a,b,i,j-1);
			if(f.length() > s.length()) return f; else return s;
		}
	}
	
	public static void main(String[] args) {
		new LCSubstr().LCS("abcd", "bc",3,1);
	}
}
