package h_w;

import java.util.Scanner;

public class t06 {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("�������һ����������");
		int num1=sc.nextInt();
		System.out.println("������ڶ�����������");
		int num2=sc.nextInt();
		int max=0;
		for (int i=2;i<=(num1<num2?num1:num2);i++){
			if(num1%i==0 && num2%i==0){
				max=i;
			}
		}
		int min=0;
		for (int j=num1*num2;j>=(num1>num2?num1:num2);j--){
			if(j%num1==0 &&j%num2==0){
				min=j;
			}
		}
		System.out.println("���Լ��Ϊ��"+max);
		System.out.println("��С������Ϊ��"+min);
	}
}
