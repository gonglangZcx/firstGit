package com.ciut.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	public static  String drivername;
	public static  String url;
	public static  String username;
	public static  String password;
	
	
	
	//�ṩ���ӵķ���
	public static Connection getConn() throws Exception{
		Properties p=new Properties();
		p.load(new FileInputStream("druid.properties"));
		//��ȡ���ӳض���
		DataSource createDataSource = DruidDataSourceFactory.createDataSource(p);
		//��ȡ����
		Connection connection = createDataSource.getConnection();
		return connection;
	}
	
	public static DataSource getDataSorce() {
		Properties p=new Properties();
		try {
			p.load(new FileInputStream("druid.properties"));
			//��ȡ���ӳض���
			DataSource createDataSource = DruidDataSourceFactory.createDataSource(p);
			return createDataSource;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
