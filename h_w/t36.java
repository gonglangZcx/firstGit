package h_w;

import java.util.ArrayList;
import java.util.Collections;

public class t36 {
	public static void main(String[] args) {
		ArrayList<Integer> s1= new ArrayList<>();
		Collections.addAll(s1, 1,2,3,4,5,6);
		yiDong(s1, 2);
		System.out.println(s1);
	}
	public static void yiDong(ArrayList a,int m){
		int num=0;
		for (int i = m-1; i >=0; i--) {
			a.add(0,a.get(i+num));
			num++;
		}
	}
}
