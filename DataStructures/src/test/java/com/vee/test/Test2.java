package com.vee.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader
			(new InputStreamReader
					(new DataInputStream
							(new FileInputStream("D:\\TEMP\\episodes.txt"))));
  	    String ep;
		int i = 1;
		String start="{| class=\"wikitable plainrowheaders\" style=\"width: 100%%; margin-right: 0;\"";
		String s = "";
		String h = "|-\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Episode\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Title\n";
		h = h+ "! style=\"background-color: #E9D66B; color: #000000;\"| Summary\n";
		s = s+ "|-\n";
		s = s+ "|%d\n";
		s = s+ "|%s\n";
		s = s+ "|%s";
		String end = "|}\n";
		System.out.println(start);
		System.out.print(h);
		while ((ep = br.readLine()) != null) {
	          System.out.println(String.format(s, i++,ep,ep));
	      }
		System.out.println(end);
	}
}
