package com.ciut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ciut.tool.JDBCUtils;
import com.ciut.domain.user;
import com.ciut.domain.zhangwu;;

public class zhangWuDao {
	static QueryRunner q = new QueryRunner(JDBCUtils.getDataSorce());

	// 登录
	public List<user> findUser() throws SQLException {
		String str = "select *from user";
		List<user> query = q.query(str, new BeanListHandler<user>(user.class));

		return query;
	}

	// 注册
	public void insertUser(String str1, String str2) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);

		String sql = "insert into user (usernmae,password) values(?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, str1);
		prepareStatement.setString(2, str2);
		int executeUpdate = prepareStatement.executeUpdate();
		conn.commit();
		System.out.println("成功注册" + executeUpdate + "名用户");
		prepareStatement.close();
		conn.close();
	}

	// 1.查询账务
	public static List<zhangwu> selectZhangWu(String str) throws Exception {
		String sql = "select *from cuit_zhangwu where username='" + str + "'";
		List<zhangwu> query = q.query(sql, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

	// 2.多条件查询-单日期
	public static List<zhangwu> multiSelectZhangwu1(String str) throws Exception {
		String sql = "select *from cuit_zhangwu where createtime='" + str + "'";
		List<zhangwu> query = q.query(sql, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

	// 2.多条件查询-双日期
	public static List<zhangwu> multiSelectZhangwu2(String str1, String str2) throws Exception {
		String sql = "select *from cuit_zhangwu where createtime between '" + str1 + "' and '" + str2 + "'";
		List<zhangwu> query = q.query(sql, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

	// 3.添加账务
	public static void insertZhangWu(String str1, String str2, String str3, String str4, String str5, String str6)
			throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "insert into cuit_zhangwu (flname,money,zhanghu,createtime,description,username) "
				+ "values(?,?,?,?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, str1);
		prepareStatement.setString(2, str2);
		prepareStatement.setString(3, str3);
		prepareStatement.setString(4, str4);
		prepareStatement.setString(5, str5);
		prepareStatement.setString(6, str6);
		int executeUpdate = prepareStatement.executeUpdate();
		conn.commit();
		System.out.println("成功添加账务" + executeUpdate + "条");
		prepareStatement.close();
		conn.close();
	}

	// 3.取添加了的账务
	public static List<zhangwu> takeLatestZhangWu(String name,String m) throws SQLException {
		String str = "select *from cuit_zhangwu  where username = '" + name + "' and createtime like '"+m+"-%'";
		List<zhangwu> query = q.query(str, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

	// 4.编辑账务
	public static void editZhangWu1(int id, String str, String st1) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String str1 = "update cuit_zhangwu set " + str + "=? where zwid=?";
		PreparedStatement prepareStatement = conn.prepareStatement(str1);
		prepareStatement.setString(1, st1);
		prepareStatement.setInt(2, id);
		int executeUpdate = prepareStatement.executeUpdate();
		conn.commit();
		System.out.println("成功修改" + executeUpdate + "条id为" + id + "的数据");
		prepareStatement.close();
		conn.close();
	}
	//4.取编辑了的账务
	public static List<zhangwu> t4takeLatestZhangWu(String name,int num) throws SQLException {
		String str = "select *from cuit_zhangwu  where username = '" + name + "' and zwid = "+num+" ";
		List<zhangwu> query = q.query(str, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

	public static void editZhangWu2(int id, String a, String a1, String b, String b1, String c, String c1, String d,
			String d1, String e, String e1) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String str1 = "update cuit_zhangwu set " + a + "=? where zwid=?";
		String str2 = "update cuit_zhangwu set " + b + "=? where zwid=?";
		String str3 = "update cuit_zhangwu set " + c + "=? where zwid=?";
		String str4 = "update cuit_zhangwu set " + d + "=? where zwid=?";
		String str5 = "update cuit_zhangwu set " + e + "=? where zwid=?";

		PreparedStatement prepareStatement = conn.prepareStatement(str1);
		prepareStatement.setString(1, a1);
		prepareStatement.setLong(2, id);
		PreparedStatement prepareStatement1 = conn.prepareStatement(str2);
		prepareStatement1.setString(1, b1);
		prepareStatement1.setLong(2, id);
		PreparedStatement prepareStatement2 = conn.prepareStatement(str3);
		prepareStatement2.setString(1, c1);
		prepareStatement2.setLong(2, id);
		PreparedStatement prepareStatement3 = conn.prepareStatement(str4);
		prepareStatement3.setString(1, d1);
		prepareStatement3.setLong(2, id);
		PreparedStatement prepareStatement4 = conn.prepareStatement(str5);
		prepareStatement4.setString(1, e1);
		prepareStatement4.setLong(2, id);

		int executeUpdate = prepareStatement.executeUpdate();
		int executeUpdate1 = prepareStatement1.executeUpdate();
		int executeUpdate2 = prepareStatement2.executeUpdate();
		int executeUpdate3 = prepareStatement3.executeUpdate();
		int executeUpdate4 = prepareStatement4.executeUpdate();
		conn.commit();
		System.out.println("成功修改" + executeUpdate + "条id为" + id + "的数据");
		prepareStatement.close();
		prepareStatement1.close();
		prepareStatement2.close();
		prepareStatement3.close();
		prepareStatement4.close();
		conn.close();
	}

	// 5.删除账务
	public static void deleteZhangWu(int id) throws Exception {

		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "delete from cuit_zhangwu where zwid=?";
		String sql1 = "ALTER TABLE cuit_zhangwu AUTO_INCREMENT = 1;";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setInt(1, id);
		int executeUpdate = prepareStatement.executeUpdate();
		Statement createStatement = conn.createStatement();
		createStatement.executeUpdate(sql1);
		conn.commit();
		System.out.println("成功删除" + executeUpdate + "条数据");
		prepareStatement.close();
		createStatement.close();
		conn.close();

	}

	// 6.搜索账务
	public static void searchZhangWu(String str, String name) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		System.out.println("Id\t项目\t开支\t支付方式\t支付时间\t\t项目详情");
		System.out.println("--------------------------------------------------------------");
		String sql = "select * from cuit_zhangwu where flname like ? and username =?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, "%" + str + "%");
		prepareStatement.setString(2, name);
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			int zwid = resultSet.getInt("zwid");
			String flname = resultSet.getString("flname");
			double money = resultSet.getDouble("money");
			String zhangHu = resultSet.getString("zhanghu");
			String createtime = resultSet.getString("createtime");
			String description = resultSet.getString("description");

			System.out.println(
					zwid + "\t" + flname + "\t" + money + "\t" + zhangHu + "\t" + createtime + "\t" + description);
		}

		resultSet.close();

		prepareStatement.close();
		conn.close();

	}

	// 7.导出账务
	public static List<zhangwu> exportZhangWu1(String str1, String str2) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "select * from cuit_zhangwu where createtime = ? and username = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, str1);
		prepareStatement.setString(2, str2);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<zhangwu> ZhangWu = new ArrayList<>();
		while (resultSet.next()) {
			int zwid = resultSet.getInt("zwid");
			String flname = resultSet.getString("flname");
			double money = resultSet.getDouble("money");
			String zhangHu = resultSet.getString("zhanghu");
			String createtime = resultSet.getString("createtime");
			String description = resultSet.getString("description");

			zhangwu zhangWu = new zhangwu(zwid, flname, money, zhangHu, createtime, description, str2);
			ZhangWu.add(zhangWu);
		}

		prepareStatement.close();
		conn.close();
		return ZhangWu;

	}

	public static List<zhangwu> exportZhangWu2(String str1, String str2, String str3) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "select * from cuit_zhangwu where createtime between ? and ? and username = ? ";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, str1);
		prepareStatement.setString(2, str2);
		prepareStatement.setString(3, str3);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<zhangwu> ZhangWu = new ArrayList<>();
		while (resultSet.next()) {
			int zwid = resultSet.getInt("zwid");
			String flname = resultSet.getString("flname");
			double money = resultSet.getDouble("money");
			String zhangHu = resultSet.getString("zhanghu");
			String createtime = resultSet.getString("createtime");
			String description = resultSet.getString("description");

			zhangwu zhangWu = new zhangwu(zwid, flname, money, zhangHu, createtime, description, str3);
			ZhangWu.add(zhangWu);
		}

		prepareStatement.close();
		conn.close();
		return ZhangWu;

	}

	// 8.统计账务
	public static double sumZhangWu(String str1, String name) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "select * from cuit_zhangwu where createtime like  ? and username = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, "%-" + str1 + "-%");
		prepareStatement.setString(2, name);
		ResultSet resultSet = prepareStatement.executeQuery();
		double sum = 0;
		while (resultSet.next()) {
			String string = resultSet.getString("flname");
			if (string.contains("支出")) {
				sum -= resultSet.getDouble("money");
			} else {
				sum += resultSet.getDouble("money");
			}

			String createtime = resultSet.getString("createtime");

		}
		conn.close();
		return sum;

	}

	// 9.上传账务
	public static List<zhangwu> uploadZhangwu(String date, String name) throws Exception {
		Connection conn = JDBCUtils.getConn();
		conn.setAutoCommit(false);
		String sql = "select * from cuit_zhangwu where createtime like  ? and username = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, date + "-%");
		prepareStatement.setString(2, name);
		ResultSet resultSet = prepareStatement.executeQuery();
		List<zhangwu> ZhangWu = new ArrayList<>();
		while (resultSet.next()) {
			int zwid = resultSet.getInt("zwid");
			String flname = resultSet.getString("flname");
			double money = resultSet.getDouble("money");
			String zhangHu = resultSet.getString("zhanghu");
			String createtime = resultSet.getString("createtime");
			String description = resultSet.getString("description");

			zhangwu zhangWu = new zhangwu(zwid, flname, money, zhangHu, createtime, description, name);
			ZhangWu.add(zhangWu);
		}

		prepareStatement.close();
		conn.close();
		return ZhangWu;

	}
	//10.下载账务
	public static List<zhangwu> downloadZhangWu(String name) throws Exception{
		String str = "select *from cuit_zhangwu  where username = '" + name + "' ";
		List<zhangwu> query = q.query(str, new BeanListHandler<zhangwu>(zhangwu.class));
		return query;
	}

}
