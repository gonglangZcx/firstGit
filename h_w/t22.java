package h_w;

public class t22 {
	public static void main(String[] args) {
		System.out.println("5£¡="+jieCheng(5));

	}
	public static int jieCheng(int num){
		if(num==1){
			return 1;
		}else{
			return num*jieCheng(num-1);
		}
		
	}
}
