package h_w;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class t50 {
	public static void main(String[] args) throws Exception {
		BufferedOutputStream sr=new BufferedOutputStream( new FileOutputStream("stud"));
		Scanner sc=new Scanner(System.in);
		List<Student> list=new ArrayList<>();
		double sum=0;
		for (int i = 1; i <= 5; i++) {
			
			System.out.println("�������"+i+"��ѧ����id");
			int num=sc.nextInt();
			System.out.println("�������"+i+"��ѧ����name");
			String s=sc.next();
			System.out.println("�������"+i+"��ѧ����degree");
			int degree=sc.nextInt();
			sum+=degree;
			list.add(new Student(num,s,degree) );
			
		}
		double avg=sum/5;
		for (Student student : list) {
			
			sr.write((student.getId()+" "+student.getName()+" "+student.getGrade()+"\n").getBytes());
		}
		sr.write(("ƽ���� "+avg).getBytes());
		sr.close();
	}
}
