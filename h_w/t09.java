package h_w;

public class t09 {
	public static void main(String[] args){
		System.out.print("1000以内的完数有：");
		for(int i=2;i<=1000;i++){
			int num1=0;
			for(int j=1;j<=i/2;j++){
				if (i%j==0){
					num1+=j;
				}
			}
			if (num1==i){
				System.out.print(" "+i);
			}
		}
	}
}
