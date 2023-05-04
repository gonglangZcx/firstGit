package h_w;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class t40 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("ÇëÊäÈëÒ»¸ö×Ö·û´®£º");
		String s1=sc.next();
		String[] s2 = s1.split("");
//		Arrays.sort(s2,new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				// TODO Auto-generated method stub
//				return o1.charAt(0)-o2.charAt(0);
//			}
//		});
		Arrays.sort(s2, t40::sortStr);
		System.out.println(Arrays.toString(s2));
	}
	
	public static int sortStr(String o1,String o2){
		return o1.charAt(0)-o2.charAt(0);
	}
}
