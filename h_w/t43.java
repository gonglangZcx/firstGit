package h_w;

public class t43 {
	public static void main(String[] args) {
		
		int numSum=0;
		for (int i = 1; i <= 8; i++) {
			int sum=count(i);
			numSum+=sum;
			System.out.println(i+"λ������"+sum);
		}
		System.out.println("���������У�"+numSum);
	}

	public static int count(int i) {

		int sum = 4;
		int flag = 6;// ����λ�͸�λ

		for (int j = 1; j <= i - 1; j++) {
			if (j == i - 1) {
				sum = 6 * sum;
			}

			else {
				sum=flag*sum;
				flag--;
			}
		}
		return sum;
	}

}
