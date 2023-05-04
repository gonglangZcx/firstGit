package h_w;

public class t28 {
public static void main(String[] args) {
	int []a={4,5,3,7,2,8,1,9,6,10};
	quick(a, 0, a.length-1);
	print(a);
}
private static void print(int[] a) {
	for (int i = 0; i < a.length; i++) {
		System.out.print(a[i]+" ");
	}
}
public static void quick(int []a,int l,int h){
	if (l>=h) {
		return;
	}
	int num=kuaipai(a, l, h);
	quick(a, l, num-1);
	quick(a, num+1, h);
	}

public static int kuaipai(int []a, int l,int h){
	int pv=a[l];
	int i=l;
	int j=h;
	while(i<j){
		while(i<j&&a[j]>=pv){
		j--;
	}
	while(i<j&&a[i]<=pv){
		i++;
	}
	swap(a,i,j);
	}
	swap(a,l,i);
	return i;
	
}
private static void swap(int[] a, int i, int j) {
	int temp=a[i];
	a[i]=a[j];
	a[j]=temp;
}
}
