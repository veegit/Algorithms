package com.vee.test;

import java.io.FileInputStream;
import java.text.BreakIterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Concorde {

	String text = "Hello you! How are you.\nBut you are wrong.";
	TreeMap<String,Tuple> concordance = new TreeMap<String,Tuple>();
	
	int sentences = 0;
	int words = 0;
	
	public static void main(String[] args) {
		Concorde c = new Concorde();
		c.Concordance();
		c.display();
	}
	
	public static void usage() {
		System.out.println("Usage: java concordance \"TEXT\"");
		System.out.println("Usage: java concordance /path/to/filename");
		System.exit(0);
	}
	
	void Concordance() {
		try {
			//createConcordance(text);
			System.setIn(new FileInputStream("C://TEMP/gutenberg_dubliner.txt"));
			Scanner scanner = new Scanner(System.in);
			while(scanner.hasNextLine()) {
				text= scanner.nextLine();
				createConcordance(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void createConcordance(String text) {
		BreakIterator bword = BreakIterator.getWordInstance();
		BreakIterator bsentence = BreakIterator.getSentenceInstance();
		bword.setText(text);
		bsentence.setText(text);
		//int s = bsentence.first();
		int f = bsentence.next();
		int b = bword.first();
		int e = bword.next();
		//int w = 1;
		while(e != BreakIterator.DONE)
		{
			String word = text.substring(b, e);
			if (Character.isLetterOrDigit(word.charAt(0))) {
				Tuple value = concordance.get(word);
				if(value != null)
					value.IncrementAndAdd(sentences+1);
				else
					concordance.put(word, new Tuple(sentences+1));
			}
			b = e;
			e = bword.next();
			if(e == f) {
				sentences++;
				f = bsentence.next();
			}
				
		}	
	}
	
	void display() {
		for (Entry<String, Tuple> entry : concordance.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		} 
	}
	
	void addText(String text) {
		createConcordance(text);
	}
}

class Tuple {
	int frequency=1;
	String lineNum = "";
	
	Tuple(int lineNum) {
		this.lineNum += lineNum;
	}
	private void increment() {
		frequency++;
	}
	
	private void addLineNum(int lineNum) {
		this.lineNum += "," + lineNum;
	}
	
	void IncrementAndAdd(int lineNum) {
		increment();
		addLineNum(lineNum);
	}
	
	public String toString() {
		return frequency+":" + lineNum;
	}
}