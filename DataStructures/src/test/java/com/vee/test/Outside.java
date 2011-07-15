package com.vee.test;

public class Outside {

    public static void main(String[] args) {
    
             //runTime();
            //print(300);
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
    		 if(str.contains("4"))
    			 continue;
    		 System.out.println(str);
    	 }
     }
    
     static void runTime() {
    	 Runtime rt = Runtime.getRuntime();
    	    
         System.out.println("Available Free Memory: " + rt.freeMemory());
         
         for(int i=0; i<10000; i++ ) {
             GC1 x = new GC1(i);                        
         }

         System.out.println("Free Memory before call to gc():  " +                 
                 rt.freeMemory());
         System.runFinalization();
         System.gc();
         System.out.println(" Free Memory after call to gc():  " +                 
                 rt.freeMemory());           
    }
}

class GC1 {

    String str;
    int id;

    GC1(int i) {
        this.str = new String("abcdefghijklmnopqrstuvwxyz");        
        this.id = i;
    }
    
    protected void finalize() {
        System.out.println("GC1 object " + id + " has been finalized.");
    }
    
}