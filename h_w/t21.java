package h_w;

public class t21 {
public static void main(String[] args) {
	int sum=0;
	int a=1;
	for (int i = 1; i < 21; i++) {
		a*=i;
		sum+=a;
	}
	System.out.println(sum);
}
}
