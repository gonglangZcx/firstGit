package h_w;

public class t03 {
	public static void main(String[] args){
		int num1;
		int num2;
		int num3;
		int i;
		int j;
		for (i=100;i<=999;i++){
			num1=i%10;//��λ
			num2=i/10%10;//ʮλ
			num3=i/100;//��λ
			if (i==num1*num1*num1+num2*num2*num2+num3*num3*num3){
				System.out.println(i+"��ˮ�ɻ���");
			}
		}
	}
}
