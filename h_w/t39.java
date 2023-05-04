package h_w;

import java.util.Scanner;

public class t39 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		double num1=sc.nextDouble();
		if (num1%2==0) {
			double diGuiOuShu = diGuiOuShu(num1);
			System.out.println(diGuiOuShu);
		}
		else {
			double diGuiJiShu = diGuiJiShu(num1);
			System.out.println(diGuiJiShu);
		}
	}

	private static double diGuiJiShu(double num1) {
		if (num1==1) {
			return 1;
		}
		else {
			double i=(1/num1)+diGuiJiShu(num1-2);
			return i;
		}
	}

	private static double diGuiOuShu(double num1) {
		if (num1==2) {
			return 1/2.0;
		}
		else {
			double i=(1/num1)+diGuiOuShu(num1-2);
			return i;
		}
	}
}
