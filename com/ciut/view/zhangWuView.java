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
			System.out.println("---------------------------------------------------------记账本");
			System.out.println("1.查询账务2.多条件查询3.添加账务4.编辑账务"
					+ "5.删除账务6.搜索账务7.导出账务8.统计账务9.上传账务10.下载账务11.上传文件12.下载文件");
			System.out.println("请输入对应序号:");
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

	// 登录+注册
	public static boolean login() throws Exception {
		return zws.login();

	}

	// 1.查询账务
	public static void selectZhangWu() throws Exception {
		zws.selectZhangWu();
	}

	// 2.多条件查询
	private static void multiSelectZhangwu() throws Exception {
		zws.multiSelectZhangwu();
	}

	// 3.添加账务
	private static void insertZhangWu() throws Exception {
		zws.insertZhangWu();
	}

	// 4.编辑账务
	private static void editZhangWu() throws Exception {
		zws.editZhangWu();
	}

	// 5.删除账务
	private static void deleteZhangWu() throws Exception {
		zws.deleteZhangWu();
	}

	// 6.搜索账务
	public static void searchZhangWu() throws Exception {
		zws.searchZhangWu();
	}

	// 7.导出账务
	public static void exportZhangWu() throws Exception {
		zws.exportZhangWu();
	}

	// 8.统计账务
	public static void sumZhangWu() throws Exception {
		zws.sumZhangWu();
	}

	// 9.上传账务
	public static void uploadZhangwu() throws Exception {
		zws.uploadZhangwu();
	}

	// 10.下载账务
	public static void downloadZhangWu() throws Exception {
		zws.downloadZhangWu();
	}
	//11.上传文件
	public static void uploadDir() throws IOException {
		zws.uploadDir();
	}//12.下载文件
	public  static void downloadDir() throws Exception {
		zws.downloadDir();
	}
}
