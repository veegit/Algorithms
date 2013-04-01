public class MatrixRotate {
	int a[][];
	int size;
    
	MatrixRotate(int X[][]){
		a = X;
		size = X.length;
		display("before");
		rotate(0,size-1,size);
		display("after");
	}
	
	/** in the loop below, if you add i<n, then it overwrite the values **/
	void rotate(int s, int e, int n) {
		if(n<=1)
			return;
		rotate(s+1,e-1,n-2);
		for(int i = 0; i< n-1; i++) {
			int t = a[s+i][s];
			a[s+i][s] = a[e][s+i];
			a[e][s+i] = a[e-i][e];
			a[e-i][e] = a[s][e-i];
			a[s][e-i] = t;
		}
	}
	
	void display(String str) {
		System.out.println(str);
		for(int i = 0;i<size;i++) {
			for(int j = 0;j<size;j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String args[]) {
		int Y[][] =
		   {
		    {1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{13,14,15,16},
		   };
		int X[][] =
		   {
		    {1,2,3,},
			{4,5,6},
			{7,8,9},
		   };
		MatrixRotate e = new MatrixRotate(X);
		MatrixRotate e1 = new MatrixRotate(Y);
	}
}