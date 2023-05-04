package h_w;

import java.util.Scanner;

public class t35 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("ÇëÊäÈëÊı×é£º");
		int []a=new int[5];
		for (int i = 0; i < a.length; i++) {
			a[i]=sc.nextInt();
		}
		int max=0;
		int min=0;
		for (int i = 1; i < a.length; i++) {
			if (a[i]>a[max]) {
				max=i;
				
			}
		}
		for (int i = 1; i < a.length; i++) {
			if (a[i]<a[min]) {
				min=i;
				
			}
		}
		for (int i : a) {
			System.out.print(i+" ");
		}
		System.out.println();
		swap(a,0,max);
		swap(a,4,max);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
	}

	private static void swap(int[] a, int i, int max) {
		int temp=a[i];
		a[i]=a[max];
		a[max]=temp;
	}
}
