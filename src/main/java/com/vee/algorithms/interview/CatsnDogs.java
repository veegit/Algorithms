package com.vee.algorithms.interview;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;
//interview = spotify
public class CatsnDogs {

	/*
	 * The latest reality show has hit the TV: 'Cat vs. Dog'. In this show, a
	 * bunch of cats and dogs compete for the very prestigious Best Pet Ever
	 * title. In each episode, the cats and dogs get to show themselves off,
	 * after which the viewers vote on which pets should stay and which should
	 * be forced to leave the show.
	 * 
	 * Each viewer gets to cast a vote on two things: one pet which should be
	 * kept on the show, and one pet which should be thrown out. Also, based on
	 * the universal fact that everyone is either a cat lover (i.e. a dog hater)
	 * or a dog lover (i.e. a cat hater), it has been decided that each vote
	 * must name exactly one cat and exactly one dog.
	 * 
	 * Ingenious as they are, the producers have decided to use an advancement
	 * procedure which guarantees that as many viewers as possible will continue
	 * watching the show: the pets that get to stay will be chosen so as to
	 * maximize the number of viewers who get both their opinions satisfied.
	 * Write a program to calculate this maximum number of viewers. Input
	 * 
	 * On the first line one positive number: the number of testcases, at most
	 * 100. After that per testcase:
	 * 
	 * One line with three integers c, d, v (1 ≤ c, d ≤ 100 and 0 ≤ v ≤ 500):
	 * the number of cats, dogs, and voters. v lines with two pet identifiers
	 * each. The first is the pet that this voter wants to keep, the second is
	 * the pet that this voter wants to throw out. A pet identifier starts with
	 * one of the characters C or D, indicating whether the pet is a cat or
	 * dog, respectively. The remaining part of the identifier is an integer
	 * giving the number of the pet (between 1 and c for cats, and between 1 and
	 * d for dogs). So for instance, D42 indicates dog number 42.
	 * 
	 * Output
	 * 
	 * Per testcase:
	 * 
	 * One line with the maximum possible number of satisfied voters for the
	 * show.
	 * 
	 * Sample input
	 *  2
		1 1 2
		C1 D1
		D1 C1
		1 2 4
		C1 D1
		C1 D1
		C1 D2
		D2 C1 
      Sample output 
      	1
      	3
	 */
	private static final String REGEX = "(C|D)(\\d{1,})";
	private static final Pattern p = Pattern.compile(REGEX);
	int c, d, v;
	Scanner scan = null;
	Map<String,Contestant> map;
	Contestant winner = null;
	
	CatsnDogs() {
		scan = new Scanner(System.in);
		int t = scan.nextInt();
		while(t>0) {
			winner = null;
			map = new TreeMap<String,Contestant>();
			read();
			System.out.println(winner.onvote);
			t--;
		}
	}
	
	void read() {
		c = scan.nextInt();
		d = scan.nextInt();
		v = scan.nextInt();
		int i = 0;
		while(i < v) {
			String s1 = scan.next(p);
			String s2 = scan.next(p);
			
			Contestant con = map.get(s1);
			if(con == null) {
				con = new Contestant(s1,1,0);
				map.put(s1, con);
			}
			else
				con.thumbsUp();
			winner = getWinner(con, winner);
			con = map.get(s2);
			
			if(!map.containsKey(s2)) {
				con = new Contestant(s2,0,1);
				map.put(s2, con);
			}
			else
				con.thumbsDown();
			winner = getWinner(con, winner);
			i++;
		}
	}
	
	Contestant getWinner(Contestant con, Contestant winner) {
		if(winner == null)
			return con;
		if(con.compareTo(winner) > 0)
			return con;
		return winner;
	}
	
	void display() {
		for(Map.Entry<String, Contestant> entry : map.entrySet())
			System.out.println(entry.getValue().id + " " + entry.getValue().onvote
					+ " " + entry.getValue().offvote + " " + entry.getValue().score);
	}
	
	void display(List<Contestant> list) {
		for (Iterator<Contestant> iterator = list.iterator(); iterator.hasNext();) {
			Contestant contestant = iterator.next();
			System.out.println(contestant.id);
		}
		
	}
	
	public static void main(String[] args) {
		new CatsnDogs();
	}

}

class Contestant implements Comparable<Contestant>{
	String id;
	int onvote=0;
	int offvote=0;
	int score = 0;
	
	Contestant(String id,int onvote, int offvote) {
		this.id = id;
		this.onvote = onvote;
		this.offvote = offvote;
		score = onvote - offvote;
	}
	
	void thumbsUp() {
		onvote++;
		score++;
	}
	
	void thumbsDown() {
		offvote++;
		score--;
	}

	public int compareTo(Contestant o) {
		if(id == o.id)
			return 0;
		if(onvote != o.onvote)
			return onvote - o.onvote;
		else
			return score - o.score;
	}
	
	public boolean equals(Object o) {
		return id.equals(((Contestant) o).id);
	}
	
	public int hashCode() {
		return id.hashCode();
	}
}
