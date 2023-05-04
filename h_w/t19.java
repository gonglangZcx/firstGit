package h_w;

import java.util.Scanner;

public class t19 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("请输入一个奇数：");
		double num=sc.nextInt();
		double a=num+1;
		double b=num-1;
		for (int i = 1; i <= num/2+0.5; i++) {
			for (int j = 1; j <= (num+1-i); j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= 2*i-1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (int i = (int) (num/2-0.5); i >= 1; i--) {
			for (int j = 1; j <=(num+1-i); j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= 2*i-1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
