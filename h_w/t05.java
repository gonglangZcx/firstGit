package h_w;

import java.util.Scanner;

public class t05 {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("��������ķ���:");
		int num1=sc.nextInt();
		char c1= (num1>=90)?'A':(num1>=60?'B':'C');
		System.out.println("��ĳɼ��ǣ�"+c1);
	}
}
