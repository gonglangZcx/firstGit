package h_w;

import java.util.Scanner;

public class t12 {
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in);
		System.out.println("请输入当月利润：");
		long num1=sc.nextLong();
		double num2 = 0;
		if (num1<=10){
			num2=num1*0.1;
		}
		else if(num1<=20 && num1>10){
			num2=1+(num1=10)*0.075;
		}
		else if (num1<=40 && num1 >20){
			num2=1+1.5+(num1-40)*0.03;
		}
		else if (num1<=60 && num1>40){
			num2=1+1.5+1+(num1-40)*0.03;
		}
		else if (num1<=100 && num1>60){
			num2=1+1.5+1+0.6+(num1-60)*0.015;
		}
		else if (num1>100){
			num2=1+1.5+1+0.6+0.6+(num1-100)*0.01;
		}
		System.out.println("奖金总额为："+num2);
	}
}
