package com.ciut.tool;

import java.util.List;

import com.ciut.domain.zhangwu;

public class printZhangWu {
	public static void print(List<zhangwu> list) {
		System.out.println("Id\t��Ŀ\t��֧\t֧����ʽ\t֧��ʱ��\t\t��Ŀ����\t�û���");
		System.out.println("--------------------------------------------------------------");
		for (zhangwu zhangwu : list) {
			System.out.println(zhangwu.getZwid()+"\t"+zhangwu.getFlname()+"\t"+zhangwu.getMoney()
			+"\t"+zhangwu.getZhanghu()+"\t"+zhangwu.getCreatetime()+"\t"+zhangwu.getDescription()
			+"\t"+zhangwu.getUsername());
		}
	}
}
