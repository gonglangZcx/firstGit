package h_w;

import java.util.Scanner;

public class t01 {
	public static void main(String[] args){
		Scanner sc=new Scanner (System.in);
		System.out.println("�������·ݣ�");
		int n=sc.nextInt();
		System.out.println("��"+n+"�·���"+fun(n)+"������");
	}
	private static int fun(int n){
		if (n==1 || n==2)
			return 1;
		else 
			return fun(n-1) +fun(n-2);
	}
}
//쳲���������