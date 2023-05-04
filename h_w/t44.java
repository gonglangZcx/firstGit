package h_w;

import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.JIS;

public class t44 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个偶数：");
		int num=sc.nextInt();
		jiSuan(num);
		
	}
	public static void jiSuan(int num){
		for (int i = 2; i <= num>>>1 ; i++) {
			if (isPrime(i) && isPrime(num-i)) {
				System.out.println(num+"="+i+"+"+(num-i));
//				break;
			}
		}
	}
	
	public static boolean isPrime(int num){
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num%i==0) {
				return false;
			}
		}
		return true;
	}
}
