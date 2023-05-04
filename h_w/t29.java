package h_w;

public class t29 {
	public static void main(String[] args) {
		int [][]a={{1,2,3},{4,5,6},{7,8,9}};
		duiJiao(a);
	}
	public static void duiJiao(int [][]a){
		int sum=0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (i==j) {
					sum+=a[i][j];
				}
			}
		}
		System.out.println("对角线元素和为："+sum);
	}
}
