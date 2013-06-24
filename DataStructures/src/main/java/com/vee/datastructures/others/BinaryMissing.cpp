# include <iostream>

using namespace std;

int binaryduplicate(int a[],int len,int low)
{
	int l = 0;
	int r = len -1;
	int mid = l + (r-l)/2;
	while(l <=r)
	{	
		mid = l + (r-l)/2;
		if(a[mid] != low+mid ) 
			r = mid -1;
		else
			l = mid+1;
		cout<<"l = "<<l<<"mid ="<<mid<<"r ="<<r<<endl;
	}
	if(a[mid] < mid-low)
		return a[mid]+1;
	else
		return a[mid]-1;
}
		

int main()
{
	int a[] = {-3,-2,-1,0,1,2,3,4,5};
	int len = 9;
	int i = binaryduplicate(a,len,a[0]);
	cout<<i<<endl;
	return 0;
}
