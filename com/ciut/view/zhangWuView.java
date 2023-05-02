package com.ciut.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.ElseIf;
import com.ciut.domain.user;
import com.ciut.domain.zhangwu;
import com.ciut.service.zhangWuSrevice;
import com.ciut.tool.printZhangWu;

public class zhangWuView {
	static zhangWuSrevice zws = new zhangWuSrevice();

	public static void run() throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean login = login();
		while (login) {
			System.out.println("---------------------------------------------------------���˱�");
			System.out.println("1.��ѯ����2.��������ѯ3.�������4.�༭����"
					+ "5.ɾ������6.��������7.��������8.ͳ������9.�ϴ�����10.��������11.�ϴ��ļ�12.�����ļ�");
			System.out.println("�������Ӧ���:");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				selectZhangWu();
				break;
			case 2:
				multiSelectZhangwu();
				break;
			case 3:
				insertZhangWu();
				break;
			case 4:
				editZhangWu();
				break;
			case 5:
				deleteZhangWu();
				break;
			case 6:
				searchZhangWu();
				break;
			case 7:
				exportZhangWu();
				break;
			case 8:
				sumZhangWu();
				break;
			case 9:
				uploadZhangwu();
				break;
			case 10:
				downloadZhangWu();
				break;
				case 11:
					uploadDir();
					break;
					case 12:
					zws.downloadDir();
					break;
			}
		}
	}

	// ��¼+ע��
	public static boolean login() throws Exception {
		return zws.login();

	}

	// 1.��ѯ����
	public static void selectZhangWu() throws Exception {
		zws.selectZhangWu();
	}

	// 2.��������ѯ
	private static void multiSelectZhangwu() throws Exception {
		zws.multiSelectZhangwu();
	}

	// 3.�������
	private static void insertZhangWu() throws Exception {
		zws.insertZhangWu();
	}

	// 4.�༭����
	private static void editZhangWu() throws Exception {
		zws.editZhangWu();
	}

	// 5.ɾ������
	private static void deleteZhangWu() throws Exception {
		zws.deleteZhangWu();
	}

	// 6.��������
	public static void searchZhangWu() throws Exception {
		zws.searchZhangWu();
	}

	// 7.��������
	public static void exportZhangWu() throws Exception {
		zws.exportZhangWu();
	}

	// 8.ͳ������
	public static void sumZhangWu() throws Exception {
		zws.sumZhangWu();
	}

	// 9.�ϴ�����
	public static void uploadZhangwu() throws Exception {
		zws.uploadZhangwu();
	}

	// 10.��������
	public static void downloadZhangWu() throws Exception {
		zws.downloadZhangWu();
	}
	//11.�ϴ��ļ�
	public static void uploadDir() throws IOException {
		zws.uploadDir();
	}//12.�����ļ�
	public  static void downloadDir() throws Exception {
		zws.downloadDir();
	}
}
