package com.vee.datastructures.recursion;

public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] a = {{1,2,3},
				     {4,5,6},
				     {7,8,9}};
		spiral(a,0,2,0,2);
	}
	
	static void spiral(int[][] a, int row_st, int row_end, int col_st, int col_end)  {
		if(row_st == row_end) 
			for (int i = col_st; i < col_end; i++)
				System.out.print(a[row_st][i] + " ");
		else if(col_st == col_end) {
			for (int i = row_st; i < row_end; i++)
				System.out.print(a[i][col_end] + " ");
		}
		else {
			for (int i = col_st; i < col_end; i++)
				System.out.print(a[row_st][i] + " ");
			for (int i = row_st; i < row_end; i++)
				System.out.print(a[i][col_end] + " ");
			for (int i = col_end; i > col_st; i--)
				System.out.print(a[row_end][i] + " ");
			for (int i = row_end; i > row_st; i--)
				System.out.print(a[i][col_st] + " ");
			spiral(a,row_st+1, row_end-1, col_st+1,col_end-1);
		}
	}

}
