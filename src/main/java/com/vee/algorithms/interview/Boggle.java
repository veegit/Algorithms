package com.vee.algorithms.interview;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//TODO
//interview = microsoft
public class Boggle {
	char[][] board;
	String[] words;
	boolean [][] visited;
	
	Boggle() {
		board = new char[][] { {'w','e','m'},{'d','u','b'},{'a','h','r'}};
		words = new String[] {"wemud","dub"};
		visited = new boolean[board.length][board.length];
	}
	
	void reset() {
		for (int i = 0; i < visited.length; i++)
			for (int j = 0; j < visited.length; j++)
				visited[i][j] = false;
				
	}
	
	boolean check(String word,int i, int r, int c) {
		if(i == word.length()-1)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		
	}

}
