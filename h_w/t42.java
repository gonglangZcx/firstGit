package h_w;

public class t42 {
	public static void main(String[] args) {
		int search = search();
		System.out.println("��λ��Ϊ��" + search);
		int sum = search * 809;
		System.out.println("809*??����ǣ�" + sum);

	}

	public static int search() {
		int num1 = 10;
		while (true) {
			int num2 = num1;
			// boolean shuZi=false;
			if (809 * num2 == ((800 * num2) + (9 * num2)) && 8*num2>=10 && 9*num2>=100) {
				return num1;
			}
			if (num2>=100) {
				return -1;
			}

			num1++;

		}
	}
}
