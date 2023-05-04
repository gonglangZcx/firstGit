package h_w;

import java.util.Scanner;

public class t46 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第一个字符串：");
		String s1=sc.next();
		System.out.println("请输入第二个字符串：");
		String s2=sc.next();
		System.out.println("拼接后："+s1.concat(s2));
	}
}
