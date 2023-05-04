package h_w;

public class t13 {
	public static void main(String[] args) {
		double num1;double num2;
		for (int i = 0; i <100000 ; i++) {
			num1=Math.sqrt(i+100);
			num2=Math.sqrt(i+268);
			if (num1==(int) num1 && num2 == (int) num2) {
				System.out.println(i);
				
			}
			
		}
	}
}
