package h_w;

import java.util.Scanner;

public class t07 {
	public static void main(String[] args){
		Scanner sc =new Scanner(System.in);
		System.out.println("������һ���ַ���");
		String str =sc.nextLine();
		char []ch =null;
		ch=str.toCharArray();
		int num_number=0;
		int num_letter=0;
		int num_blank=0;
		int num_others=0;
		for (int i=0;i<ch.length;i++){
			if (ch[i] >='0' && ch[i]<='9'){
				num_number+=1;
			}
			else if(ch[i] >='a' && ch[i] <='z'|| ch[i] >='A' && ch[i] <='Z'){
				num_letter+=1;
			}
			else if (ch[i] == ' '){
				num_blank+=1;
			}
			else {
				num_others+=1;
			}
		}
		System.out.println("��ĸ����Ϊ��"+num_letter);
		System.out.println("���ָ���Ϊ��"+num_number);
		System.out.println("�ո����Ϊ��"+num_blank);
		System.out.println("�����ַ�����Ϊ��"+num_blank);
	}
}
