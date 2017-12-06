package com.vee.algorithms.interview;

//interview = icims
public class RiceMatrix {
	public static void main(String[] args) {
		int X[][] =
		  {
				  {2,2,4,2},
				  {0,3,0,1},
				  {1,2,2,1},
				  {4,1,2,2}
				  
		  };
		int Y[][] =
		  {
				  {2,2,4,2},
				  {0,3,0,1},	  
		  };
		System.out.println(new RiceMatrix().rice_chessboard(Y));
	}
	
	public int rice_chessboard ( int[][] A ) {
		int[] state = {A.length-1, A[0].length-1};
		recurse(A,A[state[0]][state[1]],state);
		for (int i = 0; i < state.length; i++) {
			System.out.println(state[i]);
		}
		return 0;
    }
	
	int recurse(int[][] A, int cost, int[] state) {
		System.out.println(state[0]+","+state[1]+" " + cost);
		if(state[0] <=0 || state[1] <=0)
			return cost;
		if(A[state[0]-1][state[1]] > A[state[0]][state[1]-1]) 
			state[0]--;
		else 
			state[1]--;
		cost += A[state[0]][state[1]];
		return recurse(A,cost,state);
	}
}
