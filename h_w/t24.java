package h_w;

import java.util.Scanner;

public class t24 {
public static void main(String[] args) {
	Scanner sc =new Scanner(System.in);
	System.out.println("������һ��5λ���µ�����:");
	int num2=1;
	int num3=0;
	int num4=0;
	int num5=0;
	int num6=0;
	int num1=sc.nextInt();
	if (num1/10000!=0) {
		System.out.println("����̫������������");
	}
	else {
		System.out.print("�����ӡ��");
		num6=num1%10;
		System.out.print(num6);
		if (num1/10!=0) {
			num2++;
			num5=num1/10%10;
			System.out.print(num5);
		}
		if (num1/100!=0) {
			num2++;
			num4=num1/100%10;
			System.out.print(num4);
		}
		
		if (num1/1000!=0) {
			num2++;
			num3=num1/1000;
			System.out.print(num3);
		}
		
		System.out.println("����һ��"+num2+"λ��");
	}
	
}
}
