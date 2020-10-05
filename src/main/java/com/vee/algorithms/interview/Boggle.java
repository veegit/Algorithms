package com.vee.algorithms.interview;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

//TODO - Solve Problem
//interview = microsoft
public class Boggle {
	char[][] board1 = { { 'a', 'b', 'c' }, {'d', 'e', 'f'}, {'g', 'h', 'i'} };
	char[][] board = {{'i','r','d','m' },{'o','s','r','i' },{'e','a','k','a' },{'e','t','s','c' } };
	
	public static void main (String[] args) {
		
	}
	
	public void solveBoard(char[][] board, int minSize) {
		try(Scanner scan = new Scanner(new File("/usr/share/dict/words"))) {
			while (scan.hasNextLine()) {
				String word = scan.nextLine();
				if (word.length() >= minSize && checkBoardForValidWord(board, word)) {
					System.out.println(word);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean checkBoardRepeated(char[][] board, String word) {
		boolean dp[][][] = new boolean[25][board.length][board[0].length];
		for (int c = 0; c < word.length(); c++) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[0].length; j++) {
					if (c == 0) {
						dp[c][i][j] = true;
					} else {
						List<Point> list = getNeighbours(i, j, board.length, board[0].length);
						for (Point p : list) {
							if (dp[c - 1][p.x][p.y] && board[i][j] == word.charAt(c)) {
								dp[c][i][j] = true;
								if (c == word.length() - 1) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkBoardForValidWord(char[][] board, String word) {
		int dp_X = board.length;
		int dp_Y = board[0].length;
		boolean dp[][] = new boolean[dp_X][dp_Y];
		boolean found = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(dfs(board, word, 0, dp, new Point(i, j))) {
					return true;
				}
			}
		}
		return found;
	}
	
	private boolean dfs(char[][] board, String word, int index, boolean[][] dp, Point curr) {
		if (word.length()-1 == index) {
			return true;
		} else {
			boolean found = false;
			if (board[curr.x][curr.y] == word.charAt(index)) {
				dp[curr.x][curr.y] = true;
				List<Point> points = getNeighbours(curr.x, curr.y, dp.length, dp[0].length);
				for (Point p : points) {
					if (!dp[p.x][p.y]) {
						found = found || dfs(board, word, index+1, dp, p);
					}
				}
			}
			dp[curr.x][curr.y] = false;
			return found;
		}
	}

	private List<Point> getNeighbours(int i, int j, int dp_X, int dp_Y) {
		List<Point> list = new ArrayList<>();
		int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
		for (int k = 0; k < dx.length; k++) {
			if (i + dx[k] >= 0 && i + dx[k] < dp_X && j + dy[k] >= 0 && j + dy[k] < dp_Y) {
				list.add(new Point(i + dx[k], j + dy[k]));
			}
		}
		return list;
	}
	
	//@Test
	public void testCheckBoard() {
		Boggle b  = new Boggle();
		char[][] board = { { 'a', 'b', 'c' }, {'d', 'e', 'f'}, {'g', 'h', 'i'} };
		System.out.println(b.checkBoardForValidWord(board,"bad") + " " + b.checkBoardRepeated(board,"bad"));
		System.out.println(b.checkBoardForValidWord(board,"bade") + " " + b.checkBoardRepeated(board,"bade"));
		System.out.println(b.checkBoardForValidWord(board,"badi") + " " + b.checkBoardRepeated(board,"badi"));
		System.out.println(b.checkBoardForValidWord(board,"ceg") + " " + b.checkBoardRepeated(board,"ceg"));
		System.out.println(b.checkBoardForValidWord(board,"ceghe") + " " + b.checkBoardRepeated(board,"ceghe"));
		System.out.println(b.checkBoardForValidWord(board,"subdue") + " " + b.checkBoardRepeated(board,"subdue"));
		char[][] board1 = {{'i','r','d','m' },{'o','s','r','i' },{'e','a','k','a' },{'e','t','s','c' } };
		System.out.println(b.checkBoardForValidWord(board1,"steaks") + " " + b.checkBoardRepeated(board1,"steaksk"));
	}

	//@Test
	public void testSolveBoard() {
		Boggle b  = new Boggle();
		char[][] board = {{'i','r','d','m' },{'o','s','r','i' },{'e','a','k','a' },{'e','t','s','c' } };
		b.solveBoard(board, 6);
	}

	public void checkBoardForMaxSeqeuence(int[][] board) {
		boolean dp[][] = new boolean[board.length][board[0].length];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				max = Math.max(max, dfs(board, Integer.MIN_VALUE, 0, dp, new Point(i,j)));
			}
		}
		System.out.println(max);
	}

	int dfs(int[][] board, int prev, int count, boolean [][]dp, Point curr) {
		int currValue = board[curr.x][curr.y];
		if (prev > currValue) {
			return count;
		} else {
			int max = Integer.MIN_VALUE;
			dp[curr.x][curr.y] = true;
			List<Point> points = getNeighbours(curr.x, curr.y, board.length, board[0].length);
			for (Point p : points) {
				if (!dp[p.x][p.y]) {
					max = Math.max(max, dfs(board, currValue, count+1, dp, p));
				}
			}
			dp[curr.x][curr.y] = false;
			return max;
		}
	}
	
	@Test
	public void testCheckBoardForMaxSeqeuence() {
		int[][] board = {{20,400,60},{10,11,10},{1,200,100}};//5
		checkBoardForMaxSeqeuence(board);
	}
}
