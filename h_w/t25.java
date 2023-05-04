package h_w;

import java.util.Scanner;

public class t25 {
public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.println("请输入一个五位数：");
	int num1=scanner.nextInt();
	int n1=num1/10000;//万
	int n2=num1/1000%10;//千
	int n3=num1/10%10;//十
	int n4=num1%10;//个
	if (n1==n4 && n2==n3) {
		System.out.println(num1+"是回文数");
	}else {
		System.out.println(num1+"不是回文数");
	}
}
}
