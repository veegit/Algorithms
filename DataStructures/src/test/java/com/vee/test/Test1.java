package com.vee.test;

public class Test1 {

	public static void main(String[] args) {

		//runTime();
		//print(300);
		int n = 4;
		int mask[] = new int [n]; /* Guess what this is */
		int i;
		for (i = 0; i < n; ++i) {
			mask[i] = 0;
		}
		/* Print the first set */
		printv(mask, n);
		/* Print all the others */
		while (next(mask, n)) {
			printv(mask, n);
		}
	}

	static void printv(int mask[], int n) {
		int i;
		System.out.print("{ ");
		for (i = 0; i < n; ++i) {
			if (mask[i] == 1) {
				System.out.print(i + 1 + " "); /*i+1 is part of the subset*/
			}
		}
		System.out.println(" }");
	}

	static boolean next(int mask[], int n) {
		int i;
		for (i = 0; (i < n) && mask[i] == 1; ++i) {
			mask[i] = 0;
		}
		if (i < n) {
			mask[i] = 1;
			printmask(mask);
			return true;
		}
		return false;
	}

	static void printmask(int[] mask) {
		for (int i = 0; i < mask.length; i++) {
			System.out.print(mask[i] +" ");
		}
		System.out.println();
	}

	static void test2() {
		int n=35,r,cnt=1,i=0,tmp=0;
		while(n>0) {
			r = n%10;
			if(r >3) {
				cnt = (int) (Math.pow(10,i));
			}
			i++;
			n=n/10;
			tmp += cnt+r*(i-1);
		}
		System.out.println(tmp +" " + cnt);
	}

	public static void print(int n) {
		int i = 0;
		while(++i<n) {
			Integer it = i;
			String str = it.toString();
			if(str.contains("4")) {
				continue;
			}
			System.out.println(str);
		}
	}

	static void runTime() {
		Runtime rt = Runtime.getRuntime();

		System.out.println("Available Free Memory: " + rt.freeMemory());

		for(int i=0; i<10000; i++ ) {
			GC1 x = new GC1(i);
		}

		System.out.println("Free Memory before call to gc():  " + rt.freeMemory());
		System.runFinalization();
		System.gc();
		System.out.println(" Free Memory after call to gc():  " + rt.freeMemory());
	}
}

class GC1 {

	String str;
	int id;

	GC1(int i) {
		this.str = new String("abcdefghijklmnopqrstuvwxyz");
		this.id = i;
	}

	@Override
	protected void finalize() {
		System.out.println("GC1 object " + id + " has been finalized.");
	}

}