package com.ciut.tool;

import java.util.List;

import com.ciut.domain.zhangwu;

public class printZhangWu {
	public static void print(List<zhangwu> list) {
		System.out.println("Id\t项目\t开支\t支付方式\t支付时间\t\t项目详情\t用户名");
		System.out.println("--------------------------------------------------------------");
		for (zhangwu zhangwu : list) {
			System.out.println(zhangwu.getZwid()+"\t"+zhangwu.getFlname()+"\t"+zhangwu.getMoney()
			+"\t"+zhangwu.getZhanghu()+"\t"+zhangwu.getCreatetime()+"\t"+zhangwu.getDescription()
			+"\t"+zhangwu.getUsername());
		}
	}
}
