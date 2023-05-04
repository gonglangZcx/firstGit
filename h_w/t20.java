package h_w;

public class t20 {
public static void main(String[] args) {
	int a=1;
	int b=2;
	int c=0;
	double sum = 0;
	for (int i = 0; i < 20; i++) {
		sum+=b/a;
		c=a+b;
		a=b;
		b=c;
	}
	System.out.println(sum);
}
}
