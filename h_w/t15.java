package h_w;

import java.util.Scanner;

public class t15 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int [] a=new int[3];
		System.out.println("�������һ��������");
		a[0]=sc.nextInt();
		System.out.println("������ڶ���������");
		a[1]=sc.nextInt();
		System.out.println("�����������������");
		a[2]=sc.nextInt();
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if (a[j]<a[i]) {
					swap(a,i,j);
				}
			}
		}
		System.out.println("��С����˳��Ϊ��"+a[0]+"  "+a[1]+"  "+a[2]);
	}

	private static void swap(int[] a, int i, int j) {
		int num=a[i];
		a[i]=a[j];
		a[j]=num;
	}

}
