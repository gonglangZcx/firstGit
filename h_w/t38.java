package h_w;

import java.util.Scanner;

public class t38 {
	public static void main(String[] args) {

	Scanner sc =new Scanner(System.in);
	System.out.println("请输入一个字符串");
	String s1=sc.next();
	int jiSuan = jiSuan(s1);
	System.out.println("输入的字符串长度为："+jiSuan);
	}

	private static int jiSuan(String s1) {
		int num1=s1.length();
		return num1;
	}
}
