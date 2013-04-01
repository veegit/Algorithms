package com.vee.intw.twosigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Narayanan
Problem Statement #2:
(Version: TwoSigmaCodeTestCopyFile 293:32e332c64eac)
Oh no! Disaster has struck some of ACME's redundant data centers. The
administrators have managed to restore backups, but some data sets are
still missing from some data centers. Fortunately, every data set can be
found at least once in one or more of the data centers. However, before
ACME can resume normal operations, it needs to ensure that each data
center has a copy of every data set.

Your goal is to help ACME resume normal operations by writing a program
to synchronize data sets between data centers using as few copies as
possible.

Input:
------
The first line of input will contain an integer between 0 and 999999
inclusive, representing the number of data centers.
Following that will be one line of input for each data center. Each
line will contain an integer representing the number of data sets at
that data center, followed by a space and space-separated list of data
set ids currently present at that data center. Data set ids are each
an integer between 1 and 999999, inclusive. Each line will be at most
999 characters long.

Data set ids are not necessarily consecutive. The list of data sets
will not be in any particular order.

Output:
-------
The program must output an optimal data set copy strategy to ensure that
every data center has a copy of every data set. Output one line for every
copy instruction.

A copy instruction is of the form <data-set-id> <from> <to>, where
<data-set-id> is the data set id, <from> is the index of the data center
the data set will be copied from (1 = the first data center), and <to>
is the index of the data center to copy the data set to.
When there are no more copy instructions, the program must output the
word "done" on a line by itself.

There is often more than one correct output with minimal number of
operations for a given input, and any output that satisfies the
requirements is valid.

Constraints:
------------
The code you submit must take input from stdin and produce output to
stdout as specified above. No other output is permitted. You can
assume the input will be valid. In the examples below, the text
"Input:" and "Output:" (or "One Possible Correct Output:") are not
part of the output, and neither are the blank lines.

Example 1:
----------
Input:
4
3 1 3 4
3 1 2 3
2 1 3
3 1 4 2

One Possible Correct Output:
2 2 1
4 1 2
2 2 3
4 4 3
3 1 4
done

Example 2:
----------
Input:
2
2 1 2
2 2 1

Output:
done

Example 3:
----------
Input:
3
5 1 3 4 5 7
2 1 3
1 2

One Possible Correct Output:
2 3 2
2 3 1
1 1 3
4 1 2
5 1 3
5 3 2
4 2 3
3 1 3
7 1 2
7 1 3
done
 *
**/
public class SyncDC {
	
	public static void main(String[] args) {
		new SyncDC().init();
	}

	public void init() {
		Scanner scan = new Scanner(System.in);
		int count = Integer.parseInt(scan.nextLine());
		int i = 0;
		datacenter[] dcs = new datacenter[count];
		Set<Integer> power = new HashSet<Integer>();
		Map<Integer,List<datacenter>> map = 
			new HashMap<Integer, List<datacenter>>();
		
		while(i<count) {
			String text = scan.nextLine();
			String splits[] = text.split("\\s");
			System.out.println(text);
			datacenter dc = new datacenter(); 
			for (int j = 0; j < splits.length; j++) {
				Integer data = Integer.parseInt(splits[j]);
				dc.add(i,data);
			}
			dcs[i] = dc;
			power.addAll(dc.data);
			i++;
		}
		
		for (Iterator<Integer> iterator = power.iterator(); iterator.hasNext();) {
			int integer = iterator.next();
			datacenter dc = null;
			for(i =0; i<dcs.length;i++) {
				if(dcs[i].contains(integer)) {
					dc = dcs[i];
				} else
					dcs[i].missing(integer);
			}
			List<datacenter> list = map.get(integer);
			if(list == null) {
				list = new ArrayList<datacenter>();
			}
			list.add(dc);
			Set<Entry<Integer, List<datacenter>>> set = map.entrySet();
		}
		
		for(i = 0;i< dcs.length;i++){
			for (Iterator<Integer> iterator = dcs[i].missing.iterator(); iterator.hasNext();){
				int item = iterator.next();
				System.out.print(dcs[i].id+ " " + item);
			}
			System.out.println();
		}
		
		for(i = 0;i< dcs.length;i++){
			for (Iterator<Integer> iterator = dcs[i].missing.iterator(); iterator.hasNext();){
				int item = iterator.next();
				datacenter dc = map.get(item).get(0);
				System.out.println(item + " " + dc.id + " " + dcs[i].id);
			}
		}

	}
	
	class datacenter {
		int id;
		Set<Integer> data = new HashSet<Integer>();
		Set<Integer> missing = new HashSet<Integer>();
		
		void add(int i,int data){
			id=i+1;
			this.data.add(data);
		}
		
		boolean contains(int data){
			return this.data.contains(data);
		}
		
		void missing(int data){
			this.missing.add(data);
		}
	}
	
	class dataset {
		int item;
		int occurrence;
		
		dataset(int item,int occurrence){
			this.item = item;
			this.occurrence = occurrence;
		}
		
		public boolean equals(Object o) {
			return 
			(item == ((dataset)o).item && 
					occurrence == ((dataset)o).occurrence);
		}
	}

}
