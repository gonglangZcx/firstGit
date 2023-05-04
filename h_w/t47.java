package h_w;

import java.util.Scanner;

public class t47 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		for (int i = 1; i <=7; i++) {
			System.out.println("请输入第"+i+"个数");
			int num=sc.nextInt();
			for (int j = 0; j < num; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
