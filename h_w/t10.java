package h_w;

public class t10 {
	public static void main(String[] args){
		double sum=0;
		double high=0;
		double x=100;
		for(int i=1;i<=10;i++){
			sum+=x;
			x/=2;
			if(i==10){
				high=x;
			}
		}
		System.out.println("第十次落地时经过："+sum+"第十次反弹高度:"+high);
	}
}
