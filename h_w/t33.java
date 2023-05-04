package h_w;

public class t33 {
	public static void main(String[] args) {
		  int[][] a = new int[10][];
	        // 每行元素个数跟行数一致
	        for (int i = 0; i < a.length; i++) {
	            a[i] = new int[i + 1];
	        }

	        // 边界赋值
	        for (int i = 0; i < a.length; i++) {
	            a[i][0] = 1; // 左边界
	            a[i][i] = 1; // 右边界（对角线）
	        }
	        // 内部元素采用递推公式计算
	        for (int i = 2; i < a.length; i++) {
	            for (int j = 1; j < i; j++) {
	                a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
	            }
	        }

	        // 输出杨辉三角形
	        for (int i = 0; i < a.length; i++) {
	            for (int j = 0; j <= i; j++) {
	                System.out.print(a[i][j] + "\t");
	            }
	            System.out.println();
	}
	}
}
