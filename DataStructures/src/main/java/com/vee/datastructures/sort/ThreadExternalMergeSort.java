import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
/**
>split -l 1000000 -a 5 -d input.txt
size = 4000000, m = 40000, p = 100
real    1m31.473s
user    0m0.031s
sys     0m0.046s

size = 4000000, m = 400000, p = 10
real    1m24.032s
user    0m0.015s
sys     0m0.015s
**/
public class ThreadExternalMergeSort {
    public static final String infile = "x";
    public static final String sinfile = "s";
	
	long size=4000000;
	int p=10;
	int m=(int) (size/p);
			
	ThreadExternalMergeSort(){
		String suffixarr[] = new String[p];
		try {
			Scanner[] sarr = new Scanner[p];
			long arr[] = new long[p];
			int finished[]  = new int[p];
			final CountDownLatch countdown = new CountDownLatch(p);
				
			for(int i = 0; i<p;i++) {
				suffixarr[i]=String.format("%05d",i);
				Thread t = new Thread(new Worker(countdown,m,suffixarr[i]));
				t.start();
			}
			countdown.await();
			for(int i = 0; i<p;i++) {
				sarr[i] = new Scanner(new File(sinfile+suffixarr[i]));
				arr[i] = sarr[i].nextLong();
				finished[i] = 0;
			}
			long cnt=0;
			while(cnt < size) {
				long min=Long.MAX_VALUE;
				int minindex=0;
				for(int i = 0; i<p ;i++) {
					if(finished[i]==1)
						continue;
					minindex = arr[i] < min ? i : minindex;
					min = arr[i] < min ? arr[i] : min;
				}
				System.out.println(min);
				if(sarr[minindex].hasNextLong())
					arr[minindex] = sarr[minindex].nextInt();
				else
					finished[minindex]=1;
				cnt++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ThreadExternalMergeSort e = new ThreadExternalMergeSort();
	}
}

class Worker implements Runnable {
	private final CountDownLatch countdown;
	private String suffix;
	private int size;
	
	Worker(CountDownLatch countdown, int size, String suffix) {
		this.countdown = countdown;
		this.suffix = suffix;
		this.size = size;
	}
	
	public void run() {
		try {
			sortNsave(suffix);
			countdown.countDown();
		} catch(Exception e) { }
	}
	
	void sortNsave(String suffix) throws Exception {
		long aux[] = new long[size];
		Scanner s = new Scanner(new File(ThreadExternalMergeSort.infile+suffix));
		int i=0;
		while(s.hasNextInt())
			aux[i++] = s.nextInt();
		Arrays.sort(aux);
		s.close();
		i=0;
		PrintWriter p = new PrintWriter(ThreadExternalMergeSort.sinfile+suffix);
		while(i<size) {
			p.println(aux[i++]);
		}
		p.close();
	}

}