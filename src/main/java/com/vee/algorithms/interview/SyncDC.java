package com.vee.algorithms.interview;

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
 * vee
Problem Statement #2:
(Version: CodeTestCopyFile 293:32e332c64eac)
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

//TODO - Solve Problem
//interview = two sigma

public class SyncDC {
	
	public static void main(String[] args) {
		 /* Copy the code from the file you submitted
		 */
	}

}
