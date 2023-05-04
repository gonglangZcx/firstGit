package h_w;

public class t02 {
	public static void main(String[] args){
		int flag=0;
		int num1;
		int i;
		for (num1=101;num1<=200;num1++){
			for (i=2;i<num1;i++){
				if (num1%i==0){
					break;
				}
			}
				if(i==num1){
				System.out.println(num1);
					flag+=1;
			}
		}
		System.out.println("ËØÊýÓÐ£º"+flag);
	}
}
