package com.vee.intw.icims;

public class PawnArray {

	public static void main(String[] args) {
		int A[] = {0,0,0,0};
		System.out.println(new PawnArray().arrayJmp(A));
	}

	 public int arrayJmp ( int[] A ) {
			int visited[] = new int[A.length];
			for (int i = 0; i < visited.length; i++) {
				visited[i] = 0;
			}
			int start = 0;
			int end = A.length;
			int count = 0;
			int i = start;
			while(true) {
				if(i < start || i >= end) {
					break;
				}
				if(visited[i] == 1) {
					count = -1;
					break;
				}
				visited[i] = 1;
				i = i+A[i];
				count++;
			}
			return count;
	}
}
