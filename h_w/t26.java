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
		System.out.println("��������һ");
		break;

	case 'T':
		switch (b) {
		case 'u':
			System.out.println("�������ڶ�");
			break;
		case 'h':
			System.out.println("����������");
		default:
			break;
		}
		
		break;
	case 'W':
		System.out.println("����������");
		break;
	case 'S':
		switch (b) {
		case 't':
			System.out.println("����������");
			break;
		case 'u':
			System.out.println("����������");
			break;
		default:
			break;
		}
	}
	
}
}
