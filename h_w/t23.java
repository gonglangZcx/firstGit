package h_w;

public class t23 {
	public static void main(String[] args) {
		int sum=jiSuan(10,1);
		System.out.println("第五个人的岁数："+sum);
	}
	public static int jiSuan(int num,int i){
		if (i==5) {
			return num;
		}else{
			i++;
			return jiSuan(num+2,i);
		}
		
	}
}
