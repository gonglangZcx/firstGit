package h_w;

import java.util.Scanner;

public class t01 {
	public static void main(String[] args){
		Scanner sc=new Scanner (System.in);
		System.out.println("请输入月份：");
		int n=sc.nextInt();
		System.out.println("在"+n+"月份有"+fun(n)+"对兔子");
	}
	private static int fun(int n){
		if (n==1 || n==2)
			return 1;
		else 
			return fun(n-1) +fun(n-2);
	}
}
//斐波那契数列