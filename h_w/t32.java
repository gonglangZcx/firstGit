package h_w;

import java.util.Scanner;

public class t32 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一个整数：");
		
		int a= scanner.nextInt();
		int b=(int) Math.floor((a%Math.pow(10, 7)/1000));
		System.out.println(b);
	}
}
