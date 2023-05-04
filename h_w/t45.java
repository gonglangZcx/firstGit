package h_w;

import java.util.Scanner;

public class t45 {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入一个素数：");
		int num=sc.nextInt();
		int panDuan = panDuan(num);
		System.out.println(num+"能被"+panDuan+"个9整除");
		
	}
	public static int panDuan(int num){
		int sum=0;
		while(true){
			if(num/9==0){
				sum+=1;
				num=num/9;
			}
			else {
				return sum;
			}
		}
	}
}
