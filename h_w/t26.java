package h_w;

import java.util.Scanner;

public class t26 {
public static void main(String[] args) {
	Scanner sc =new Scanner(System.in);
	String s=sc.next();
	StringBuilder s1=new StringBuilder(s);
	char a=s1.charAt(0);
	char b=s1.charAt(1);
	switch (a) {
	case 'M':
		System.out.println("这是星期一");
		break;

	case 'T':
		switch (b) {
		case 'u':
			System.out.println("这是星期二");
			break;
		case 'h':
			System.out.println("这是星期四");
		default:
			break;
		}
		
		break;
	case 'W':
		System.out.println("这是星期三");
		break;
	case 'S':
		switch (b) {
		case 't':
			System.out.println("这是星期六");
			break;
		case 'u':
			System.out.println("这是星期天");
			break;
		default:
			break;
		}
	}
	
}
}
