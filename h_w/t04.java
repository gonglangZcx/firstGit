package h_w;

import java.util.Scanner;

public class t04 {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入一个正整数：");
		int num1 =sc.nextInt();
		System.out.print(num1+"=");
		for(int i=2;i<num1;i++){
			while(num1!=i){
				if (num1%i==0){
					System.out.print(i+"*");
					num1=num1/i;
				}
				else{
					break;
				}
			}
		}
		System.out.print(num1);
	}
}
