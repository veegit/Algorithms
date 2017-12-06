package com.vee.algorithms.recursion;

public class Anagram {
	static int count = 0, space = 0;
	public Boolean recursive(String a, String b)  {
    if (a.length() != b.length())
        return false;

    for(int x = 0; x < b.length(); x++) 
        if(a.charAt(0) == b.charAt(x))
            return recursive(utilityFunction(a, 0), utilityFunction(b, x)) ;
     return b.length() == 0;
	}
 
   String utilityFunction(String s, int j) {
     char[] ret = new char[s.length() - 1];
     space = space + s.length();
     int d = 0;
     for (int k =0; k<s.length();k++) {
         if (k == j)
             d = 1;
         else
             ret[k - d] = s.charAt(k);
         count++;
     }
     return new String(ret);
   }
   
   public static void main(String args[]) {
	   System.out.println(new Anagram().recursive("abc", "cba"));
	   System.out.println(space + " " + count);
   }
}
