package h_w;

import java.util.Scanner;

public class t14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("��������ݣ�");
		int num1 = sc.nextInt();
		System.out.println("�������·ݣ�");
		int num2 = sc.nextInt();
		System.out.println("������������");
		int num3 = sc.nextInt();
		int sum = 0;
		for (int i = 1; i < num2 ; i++) {
			int day=0;
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: day=31;break;
				
			case 4:
			case 6:
			case 9:
			case 11:day=30;break;

			case 2:{
				if (num1%400==0 || (num1%4==0 && num1%100!=0)) {
					day=29;break;
				}else {
					day=28;break;
				}
			}
			}
			sum+=day;
		}
		sum+=num3;
		System.out.println("����һ���еģ�"+sum+"��");
	}
}
