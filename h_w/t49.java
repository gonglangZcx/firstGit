package h_w;

public class t49 {
	public static void main(String[] args) {
		String s1="java is java  world java ";
		int jiSuan = jiSuan(s1);
		System.out.println("java出现了:"+jiSuan+"次");
		
	}
	public static int jiSuan(String s1){
		int sum=0;
		int index=0;
		while((index=s1.indexOf("java", index))!=-1 ){
			sum++;
			index+=4;
		}
		
		return sum;
		
	} 
}
