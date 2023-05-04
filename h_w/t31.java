package h_w;

public class t31 {
	public static void main(String[] args) {
		int []a={1,2,3,4,5,6,7,8,9};
		for (int i = 0,j=a.length-1; i < j; i++,j--) {
			swap(a, i, j);
		}
		print(a);
	
	
	
	}

	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	
}
