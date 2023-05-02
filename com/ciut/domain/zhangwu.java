package com.ciut.domain;

import java.io.Serializable;

public  class zhangwu implements Serializable {
	private Integer zwid;
	private String flname;
	private Double money;
	private String zhanghu;
	private String createtime;
	private String description;
	private String username;

	public zhangwu(Integer zwid, String flname, Double money, String zhanghu, String createtime, String description,
			String username) {
		super();
		this.zwid = zwid;
		this.flname = flname;
		this.money = money;
		this.zhanghu = zhanghu;
		this.createtime = createtime;
		this.description = description;
		this.username = username;
	}

	public zhangwu() {
	}

	public Integer getZwid() {
		return zwid;
	}

	public  void setZwid(Integer zwid) {
		this.zwid = zwid;
	}

	public String getFlname() {
		return flname;
	}

	public  void setFlname(String flname) {
		this.flname = flname;
	}

	public Double getMoney() {
		return money;
	}

	public  void setMoney(Double money) {
		this.money = money;
	}

	public String getZhanghu() {
		return zhanghu;
	}

	public  void setZhanghu(String zhanghu) {
		this.zhanghu = zhanghu;
	}

	public String getCreatetime() {
		return createtime;
	}

	public  void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ZhangWu [zwid=" + zwid + ", flname=" + flname + ", money=" + money + ", zhanghu=" + zhanghu
				+ ", createtime=" + createtime + ", description=" + description + ", username=" + username + "]";
	}
}
