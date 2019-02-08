package com.vee.algorithms.others;
/*

Toeplitz matrix is one in which the elements on any given diagonal from top left to bottom right are identical.

Here is an example:

1 2 3 4 8
5 1 2 3 4
4 5 1 2 3
7 4 5 1 2

[R3,C0],[R3,C1][R2,C0],[R3,C2]..[R1,C0]
*/
public class ToeplitzMatrix {
    public static void main(String args[]) {
        int[][] arr = new int[][]{
            {1,2,3,4,8},
            {5,1,2,3,4},
            {4,5,1,2,3},
            {7,4,5,1,2}
        };
        int[][] arr1 = new int[][]{
            {1,2},
            {5,1},
            {4,5},
            {7,5}
        };
        boolean ans = new ToeplitzMatrix().Toeplitz(arr1);
        System.out.println(ans);

    }

    public boolean Toeplitz(int[][] mat) {
        for (int i=0;i<mat[0].length-1;i++) {
            if (!check(0,i,mat)) return false;
        }
        for (int i=1;i<mat.length-1;i++) {
            if (!check(i,1,mat)) return false;
        }
        return true;
    }

    private boolean check(int row, int col, int[][]mat) {
        int prevNum = mat[row++][col++];
        int num;
        while (row<mat.length && col <mat[0].length) {
            num = mat[row][col];
            if (num != prevNum)  return false;
            prevNum = num;
            row++;col++;
        }
        return true;
    }
}