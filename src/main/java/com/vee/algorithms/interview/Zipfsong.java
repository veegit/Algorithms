package com.vee.algorithms.interview;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 
 * By Zipf's Law, you expect that the first song is listened to twice as often
 * as the second song, and more generally that the first song is listened to i
 * times as often as the ith song. When some songs are better than others,
 * those will be listened to more often than predicted by Zipfs Law, and those
 * are the songs your program should select as the good songs. Specifically,
 * suppose that song i has been played fi times but that Zipfs Law predicts
 * that it would have been played zi times. Then you define the quality of song
 * i to be qi = fi / zi. Your software should select the songs with the highest
 * values of qi.
 * 
 * Input The first line of input contains two integers n and m (1 n 50000, 1
 *  m  n), the number of songs on the album, and the number of songs to
 * select. Then follow n lines. The ith of these lines contains an integer fi
 * and string si, where 0 -1012 is the number of times the ith song was
 * listened to, and si is the name of the song. Each song name is at most 30
 * characters long and consists only of the characters a-z, 0-9, and
 * underscore (_).
 * 
 * Output Output a list of the m songs with the highest quality qi, in
 * decreasing order of quality. If two songs have the same quality, give
 * precedence to the one appearing first on the album (presumably there was a
 * reason for the producers to put that song before the other).
 * 
 * Sample input1 
 * 4 2 
 * 30 one 
 * 30 two 
 * 15 three 
 * 25 four 
 * 
 * Sample output 1 
 * four 
 * two
 */

/**
 * vee
 *
 */
//interview = spotify
public class Zipfsong {

	PriorityQueue<Song> q;
	int n;
	int m;
	Comparator<Song> comparator;
	
	Zipfsong() {
		comparator = new Comparator<Song>() {
			public int compare(Song o1, Song o2) {
				int sgn = Long.signum(o1.getScore()- o2.getScore());
				if(sgn == 0) 
					return o2.id - o1.id;
				else
					return sgn;
			}
		};
		read();
		display();
	}
	
	void read() {
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		q = new PriorityQueue<Song>(m,comparator);
		int i=0;
		while(scan.hasNext()) {
			long views = scan.nextLong();
			String name = scan.next();
			long score = views*++i;
			Song s = new Song(i,name,views,score);
			if(q.size() == m && score <= q.peek().getScore()) 
				continue;
			q.add(s);
			if(q.size() > m) 
				q.poll();
		}
	}
	
	void display() {
		int size = q.size();
		Song[] list = new Song[size];
		for (int i = 0; i < size; i++) {
			list[i] = q.poll();
		}
		for (int i = size-1; i >=0; i--) {
			System.out.println(list[i].getName());
		}
	}
	
	public static void main(String[] args) {
		new Zipfsong();
	}

}

class Song {
	int id;
	private String name;
	private long views;
	private long score;
	
	Song(int id, String name, long views, long score) {
		this.id = id;
		this.name = name;
		this.views = views;
		this.score = score;
	}
	
	String getName() {
		return name;
	}
	
	long getViews() {
		return views;
	}
	
	long getScore() {
		return score;
	}
}
