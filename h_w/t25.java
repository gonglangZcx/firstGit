package h_w;

import java.util.Scanner;

public class t25 {
public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	System.out.println("������һ����λ����");
	int num1=scanner.nextInt();
	int n1=num1/10000;//��
	int n2=num1/1000%10;//ǧ
	int n3=num1/10%10;//ʮ
	int n4=num1%10;//��
	if (n1==n4 && n2==n3) {
		System.out.println(num1+"�ǻ�����");
	}else {
		System.out.println(num1+"���ǻ�����");
	}
}
}
