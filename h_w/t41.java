package h_w;

public class t41 {
	public static void main(String[] args) {
		int taoZi = taoZi(5);
		System.out.println("原来海滩上最少有"+taoZi+"个桃子");
		
	}
	public static int taoZi(int num){
		int num1=1;
		while(true){
			int peachLeft=num1;
			boolean findTaoZi=true;
			for (int i = 0; i < num; i++) {
				if ((peachLeft-1) %num==0) {
					peachLeft=(peachLeft-1)/num *(num-1);
				}
				else{
					findTaoZi=false;
					break;
				}
			}
			if (findTaoZi) {
				return num1;
			}
			
			num1++;
		}
	}
}
