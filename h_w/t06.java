package h_w;

import java.util.Scanner;

public class t06 {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个正整数：");
		int num1=sc.nextInt();
		System.out.println("请输入第二个整整数：");
		int num2=sc.nextInt();
		int max=0;
		for (int i=2;i<=(num1<num2?num1:num2);i++){
			if(num1%i==0 && num2%i==0){
				max=i;
			}
		}
		int min=0;
		for (int j=num1*num2;j>=(num1>num2?num1:num2);j--){
			if(j%num1==0 &&j%num2==0){
				min=j;
			}
		}
		System.out.println("最大公约数为："+max);
		System.out.println("最小公倍数为："+min);
	}
}
