package h_w;

import java.util.ArrayList;

public class t30 {
	public static void main(String[] args) {
		int []a={1,2,3,5,6,7,8,9};
		paiXu(a, 4);
	}
	public static void paiXu(int []a,int b){
		ArrayList<Integer> list=new ArrayList<>();
		int index=0;
		for (int i = 0; i < a.length; i++) {
			if (b>=a[i]) {
				index=i;
			}
			list.add(a[i]);
		}
		list.add(index+1, b);
		System.out.println(list);
	}
}
