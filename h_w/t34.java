package h_w;

import java.util.Scanner;

public class t34 {
	public static void main(String[] args) {
		int []a=new int[3];
		Scanner sc= new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			System.out.println("请输入第"+i+"个数");
			a[i]=sc.nextInt();
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if(a[i]>a[j]){
					swap(a,i,j);
				}
			}
		}
		for (int i : a) {
			System.out.print(i+"  ");
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}


}
