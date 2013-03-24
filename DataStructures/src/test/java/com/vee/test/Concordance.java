package com.vee.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author narayanan_venkiteswaran
 * @version 1.0
 * <br>
 * <p> Problem : Given an arbitrary text document written in English, 
 * write a program that will generate a concordance, 
 * i.e. an alphabetical list of all word occurrences, 
 * labeled with word frequencies. 
 * Bonus: label each word with the sentence numbers in which each 
 * occurrence appeared.
 * <pre>
 * a    {2:1,1}
 * all    {1:1}
 * alphabetical     {1:1}
 * an    {2:1,1}
 * appeared    {1:2}
 * arbitrary    {1:1}
 * bonus    {1:2}
 * concordance    {1:1}
 * document    {1:1}
 * each    {2:2,2}
 * english    {1:1}
 * frequencies    {1:1}
 * generate    {1:1}
 * given    {1:1}
 * i.e.    {1:1}
 * in    {2:1,2}
 * label    {1:2}
 * labeled    {1:1}
 * list    {1:1}
 * numbers    {1:2}
 * occurrence    {1:2}
 * occurrences    {1:1}
 * of    {1:1}
 * program    {1:1}
 * sentence    {1:2}
 * text    {1:1}
 * that    {1:1}
 * the    {1:2}
 * which    {1:2}
 * will    {1:1}
 * with    {2:1,2}
 * word    {3:1,1,2}
 * write    {1:1}
 * written    {1:1}
 * </pre>
 * 
 * Solution: Uses BreakIterator <br><br>
 * 
 * Assumptions:
 * <ul>A newline character doesn't mean a sentence ending. A sentence ending
 * should be a sentence punctuation. E.g.
 * <pre>You are
 *welcome</pre>
 * is one sentence instead of 2
 * </ul>
 * 
 * <p>Known Issues:</p>
 * <li> Has problems with email addresses</li>
 * <li> Has problems with currencies 4.3$</li>
 * <li>
 * A sentence not beginning with Caps is part of previous sentence. E.g.
 * <pre>Impossible. i can't imagine.</pre>
 * has only 1 sentence
 * </li> 
 * */
public class Concordance {
	
	/*
	 * Thought of using Hash map versus TreeMap. TreeMap enables ordered sorting
	 * with insertion as O(logn) while HashMap is O(1). While using Treemap, 
	 * sorting will not be required during display but HashMap would require
	 * sorting of keys once before display. TreeMap vs HashMap difference will 
	 * not matter for small text or small files. On investigation this proved
	 * true as "Complete works of Shakespeare" yielded following results
	 * Size = 5.4 MB
	 * TreeMap = 3.15 Seconds
	 * HashMap = 2.65 Seconds
	 *  
	 */
	Map<String,Tuple> concordance = new HashMap<String,Tuple>();
	
	int numsentences = 1;
	int words = 0;
	
	/*
	 * Provided helpers/switches to allow command line input vs file input
	 */
	public static void main(String[] args) {
		Concordance c = new Concordance();
		if(args.length == 1)
			c.createConcordance(args[0]);
		else if(args.length == 2 && args[0].equals("-f"))
			c.readFile(args[1]);
		else {
			usage();
			return;
		}
		c.display();
	}
	
	public static void usage() {
		System.out.println("Usage: java concordance " +
				"\"your text in double quotes\"");
		System.out.println("Usage: java concordance -f " +
				"/path/to/filename");
	}
	
	/*
	 * Thought of using Scanner vs Buffered Reader, but scanner can prove more
	 * useful if we want to tokenize the input before completely reading the line
	 * But since we are using the JDK's BreakIterator Utility which can read in
	 * a line completely and tokenize it according to words, it made more sense 
	 * to use bufferedreader rather than Scanner which can be an overkill as I 
	 * saw a mild Read boost of 300ms while reading 
	 * "Complete works of Shakespeare"
	 */
	
	/**
	 * Reading a file based input
	 */
	void readFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename)));
			String text;
			while((text = br.readLine()) != null) {
				createConcordance(text);
			}
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method which performs the tokenization
	 * @param text   Text on which concordance needs to be performed 
	 */
	void createConcordance(String text) {
		/*
		 * BreakIterator[1] is standard utility used to tokenize text. 
		 * Its probably the next best thing over standard NLP tokenizers 
		 * which are either based on a corpus or more extensive rules e.g.
		 * StanfordNLP[2] tokenizer which is actually based PennTreeBank[3]
		 * corpus. 
		 * BreakIterator has an added benefit of locale support as language 
		 * properties like word boundaries vary by region[4]
		 *
		 * Reference
		 * [1] http://docs.oracle.com/javase/6/docs/api/java/text/BreakIterator.html
		 * [2] http://nlp.stanford.edu/nlp/javadoc/javanlp/
		 * [3] http://www.cis.upenn.edu/~treebank/
		 * [4] http://docs.oracle.com/javase/tutorial/i18n/text/about.html
		 * 
		 */
		
		/*
		 * Initialize a word based iterator and sentence based iterator 
		 */
		BreakIterator bword = BreakIterator.getWordInstance();
		BreakIterator bsentence = BreakIterator.getSentenceInstance();
		bword.setText(text);
		bsentence.setText(text);
		int f = bsentence.next();
		int b = bword.first();
		int e = bword.next();
		while(e != BreakIterator.DONE)
		{
			String word = text.substring(b, e);
			//Ignore the spaces or punctuation
			if (Character.isLetterOrDigit(word.charAt(0))) {
				Tuple value = concordance.get(word.toLowerCase());
				if(value != null)
					value.IncrementAndAdd(numsentences);
				else
					concordance.put(word.toLowerCase(), new Tuple(numsentences));
			}
			b = e;
			e = bword.next();
			/* If we reach sentence boundary, then increment the sentence# and 
			 * forward the iterator
			 * Also check for last character if its a word boundary or not
			 * If this check is not made then the following text "ok" would 
			 * give a statement# of 2 instead of 1 since there is not sentence
			 * boundary present 
			 */
			
			if(e == f && f!=-1 && !Character.isLetterOrDigit(text.charAt(f-1))) {
				numsentences++;
				f = bsentence.next();
			}
				
		}	
	}
	
	void display() {
		List<String> sortedKeys=new ArrayList<String>(concordance.keySet());
		Collections.sort(sortedKeys);
		for (String string : sortedKeys) 
			System.out.println(String.format("%s    {%s}", 
					string,concordance.get(string)));
	}
	
}

/*
 * Class to store the Tuple which is a pair of frequency:int and comma separated
 * Sentences, linenum:string 
 */
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