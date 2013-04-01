import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/**
>split -l 1000000 -a 5 -d input.txt
size = 100000000, m = 100000, p = 1000
real	48m58.584s
user	37m51.622s
sys	10m38.188s

size = 100000000, m = 10000000, p = 100
real	31m46.806s
user	21m21.668s
sys	10m26.355s

size = 4000000, m = 40000, p = 100
real    1m20.967s
user    0m0.015s
sys     0m0.031s

size = 4000000, m = 400000, p = 10
real    1m31.015s
user    0m0.015s
sys     0m0.015s
awk '{print $0*100000+1}' tmp.file > 1.txt
awk '{print $0*100000+2}' tmp.file > 2.txt
awk '{print $0*100000+3}' tmp.file > 3.txt
awk '{print $0*100000+4}' tmp.file > 4.txt
awk '{print $0*100000+5}' tmp.file > 5.txt
awk '{print $0*100000+6}' tmp.file > 6.txt
awk '{print $0*100000+7}' tmp.file > 7.txt
awk '{print $0*100000+8}' tmp.file > 8.txt
awk '{print $0*100000+9}' tmp.file > 9.txt
cat *.txt >> tmp.file
rm *.txt

**/
public class ExternalMergeSort {
    String infile = "x";
    String sinfile = "s";
	
	long size=4000000;
	int p=10;
	int m=(int) (size/p);
			
	ExternalMergeSort(){
		String suffixarr[] = new String[p];
		try {
			Scanner[] sarr = new Scanner[p];
			long arr[] = new long[p];
			int finished[]  = new int[p];
			
			for(int i = 0; i<p;i++) {
				suffixarr[i]=String.format("%05d",i);
				sortNsave(suffixarr[i]);
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

	void sortNsave(String filename) throws Exception {
		long aux[] = new long[m];
		Scanner s = new Scanner(new File(infile+filename));
		int i=0;
		while(s.hasNextInt())
			aux[i++] = s.nextInt();
		Arrays.sort(aux);
		s.close();
		i=0;
		PrintWriter p = new PrintWriter(sinfile+filename);
		while(i<m) {
			p.println(aux[i++]);
		}
		p.close();
	}

	public static void main(String args[]) {
		ExternalMergeSort e = new ExternalMergeSort();
	}
}