package h_w;

import java.util.Scanner;

public class t27 {
public static void main(String[] args) {
	for (int i = 2; i < 101; i++) {
		boolean num=false;
		for (int j = 2; j < i; j++) {
			if (i%j==0) {
				num=true;
				break;
			}
		}
		if (!num) {
			System.out.println(i+"ÊÇËØÊý");
		}
	}
}
}
