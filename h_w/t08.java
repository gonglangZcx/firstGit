package h_w;

import java.util.Scanner;

public class t08 {
	public static void main(String[] args){
		Scanner sc =new Scanner(System.in);
		System.out.println("������a��ֵ:");
		int num1=sc.nextInt();
		int num3=num1;
		System.out.println("�������м�������ӣ�");
		int num2=sc.nextInt();
		int sum=0;
		for(int i=1;i<=num2;i++){
			System.out.print(num1+"+");
			sum+=num1;
			num1=num1*10+num3;
		}
		System.out.println("="+sum);
	}
}
