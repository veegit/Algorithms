package com.vee.algorithms.dynprog;

public class EditDistance {
	String source;
	String dest;
	String str = "";
	int cnt = 1;
	int[][] a;
	
	public EditDistance(String source, String dest) {
		this.source = source;
		this.dest = dest;
		//System.out.println(LCS(source.length()-1,dest.length()-1));
		a = new int[source.length()+1][dest.length()+1];
		LCS_it();
	}
	
	public String LCS(int i, int j) {
		System.out.println(cnt++);
		if(i == -1 || j == -1)
			return "";
		if(source.charAt(i) == dest.charAt(j))
			return LCS(i-1,j-1) + source.charAt(i);
		else
		{
			String first = LCS(i,j-1);
			String second = LCS(i-1,j);
			return (first.length() > second.length() ? first : second);
		}		
	}
	
	public void LCS_it() {	
		for (int i = 0; i <= source.length(); i++) {
			a[i][0] = 0; 
		}
		for (int i = 0; i <= dest.length(); i++) {
			a[0][i] = 0; 
		}
		for (int i = 0; i < source.length(); i++)
			for (int j=0; j < dest.length(); j++) {
			System.out.println(cnt++);
			if(source.charAt(i) == dest.charAt(j)) 
				a[i+1][j+1] = a[i][j] + 1; 
			else 
				a[i+1][j+1] = Math.max(a[i+1][j], a[i][j+1]);
		}
		System.out.println(a[source.length()][dest.length()]);
		
		/* Print array */
		for (int i = 0; i <= source.length(); i++) {
			for (int j=0; j <= dest.length(); j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}
	
	public static void main(String args[]) {
		new EditDistance("rappler", "maappse");
	}
}
