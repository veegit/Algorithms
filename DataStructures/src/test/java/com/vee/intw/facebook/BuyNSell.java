package com.vee.intw.facebook;

import java.util.Scanner;

public class BuyNSell
{
    	int a[];
    	int p[] = new int[2];
    	int max;
    	
    	BuyNSell() {
    		
    	}
    	
    	
    	public static void main(String[] args) {
    		BuyNSell bs = new BuyNSell();
    		//only if you want to test;
    		bs.test(true);
    		bs.display();
        }
    	
    	void init() {
    		Scanner s = new Scanner(System.in);
    		int n = s.nextInt();
    		int[] a = new int[n];
    		int i = 0;
    		while(s.hasNextInt()) {
    			a[i++] = s.nextInt();
    		}
    	}
    	
    	int findMaxBrute(int[] a, int[] p) {
			int n = a.length;
			int diff = -1;
			for(int i = 0; i < n; i++) 
				for(int j = i+1; j < n; j++) {
					int t = a[j] - a[i];
					if(i < j)
						if(diff < t) {
							p[0] = i;
							p[1] = j;
							diff = t;
						}
				}
			return diff;
		}
		
		int findMaxFast(int[] a, int[] p) {
			int n = a.length;
			int maxi = 0 , mini = 0;
			int maxd = 0 , mind = 0;
			int maxii = 0 , minii = 0;
			int max = a[maxi],min = a[mini];
			for(int i = 0; i < n; i++) {
				max = (max > a[i])?max:a[i];
				maxi = (max > a[i])?maxi:i;
				min = (min < a[i])?min:a[i];
				mini = (mini < a[i])?mini:i;
			}
			
			for(int i = 0; i < n; i++) {
				if(maxi >= i && maxd > max - a[i])
					maxii = i;
				if(mini < i && mind < min - a[i])
					minii = i;
			}
			if(a[maxi] - a[maxii] > a[minii] - a[mini]) {
				p[0] = maxii;
				p[1] = maxi;
				return a[maxi] - a[maxii];
			}
			else 
			{
				p[0] = mini;
				p[1] = minii;
				return a[minii] - a[mini];
			}
		}
		
		void display() {
	    	{
				long start = System.currentTimeMillis();
				int[] p = new int[2];
				
				int max = findMaxBrute(a,p);
				String out = "";
				if(max > 0) {
					out += String.format("Maximum is %d between %d and %d",max,p[0],p[1]);
					out += String.format("\n                     %d and %d",a[p[0]],a[p[1]]);
				}
				System.out.println(out);
				System.out.println(System.currentTimeMillis()-start);
			}
			
			{
				long start = System.currentTimeMillis();
				int[] p = new int[2];
				int max = findMaxFast(a,p);
				String out = "";
				if(max > 0) {
					out += String.format("Maximum is %d between %d and %d",max,p[0],p[1]);
					out += String.format("\n                     %d and %d",a[p[0]],a[p[1]]);
				}
				System.out.println(out);
				System.out.println(System.currentTimeMillis()-start);
			}
    	}
		
		void test(boolean test) {
			if(!test) {
				init();
				return;
			}
			//Works
			a = new int[]{3,1,5,2,7};
			//Doesn't work
			a = new int[]{100,90,80,70,60,70,80,70,60,50,40};
		}

}
