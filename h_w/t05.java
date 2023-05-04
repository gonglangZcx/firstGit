package h_w;

import java.util.Scanner;

public class t05 {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入你的分数:");
		int num1=sc.nextInt();
		char c1= (num1>=90)?'A':(num1>=60?'B':'C');
		System.out.println("你的成绩是："+c1);
	}
}
