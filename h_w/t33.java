package h_w;

public class t33 {
	public static void main(String[] args) {
		  int[][] a = new int[10][];
	        // ÿ��Ԫ�ظ���������һ��
	        for (int i = 0; i < a.length; i++) {
	            a[i] = new int[i + 1];
	        }

	        // �߽縳ֵ
	        for (int i = 0; i < a.length; i++) {
	            a[i][0] = 1; // ��߽�
	            a[i][i] = 1; // �ұ߽磨�Խ��ߣ�
	        }
	        // �ڲ�Ԫ�ز��õ��ƹ�ʽ����
	        for (int i = 2; i < a.length; i++) {
	            for (int j = 1; j < i; j++) {
	                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
	            }
	        }

	        // ������������
	        for (int i = 0; i < a.length; i++) {
	            for (int j = 0; j <= i; j++) {
	                System.out.print(a[i][j] + "\t");
	            }
	            System.out.println();
	}
	}
}
