package h_w;

import java.util.Scanner;

public class t48 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入数据：");
		int num=sc.nextInt();
		jiaMi(num);
		
	}

	private static void jiaMi(int num) {
		int []a={num/1000,num/100%10,num/10%10,num%10};
		for (int i = 0; i < a.length; i++) {
			a[i]+=5;
			a[i]=a[i]%10;
		}
		swap(a,0,3);swap(a,1,2);
		int num1=a[0]*1000+a[1]*100+a[2]*10+a[3];
		System.out.println("加密后："+num1);
//		int num1=num/1000;
//		int num2=num/100%10;
//		int num3=num/10%10;
//		int num4=num%10;
	}

	private static void swap(int[] a, int i, int j) {
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
		
	}
	
	
}
