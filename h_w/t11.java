package h_w;

public class t11 {
	public static void main(String[] args){
		int [] arr={1,2,3,4};
		int num=0;
		int num1=0;
		int num2;
		int num3;
		int num4;
		System.out.println("组成的数字有:");
		for (int i=0;i<arr.length;i++){
			num2=arr[i];
			for (int j=0;j<arr.length;j++){
				num3=arr[j];
				for (int k=0;k<arr.length;k++){
					num4=num2*100+num3*10+arr[k];
					if (i!=j && i!=k && j!=k){
						num++;
					System.out.print(num4+"  ");
					}
				}
			}
		}
		
		System.out.println("共有："+num+"个");
	}
}
