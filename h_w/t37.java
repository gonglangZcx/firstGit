package h_w;

import java.util.Scanner;

public class t37 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("��������������");
		int n=sc.nextInt();
		int find = find(n);
		System.out.println("�������������ԭ����"+(find+1)+"��");
	}
	
	public static int find(int n){
		if (n==1) {
			return 0;
			
		}
		else {
			int m=(find(n-1) +3) %n;
			return m;
		}
	}
}
