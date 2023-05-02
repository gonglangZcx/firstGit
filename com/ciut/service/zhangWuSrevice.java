package com.ciut.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ObjIntConsumer;

import com.ciut.tool.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ciut.dao.zhangWuDao;
import com.ciut.domain.user;
import com.ciut.domain.zhangwu;

public class zhangWuSrevice {
	static QueryRunner q = new QueryRunner(JDBCUtils.getDataSorce());
	static zhangWuDao zwd = new zhangWuDao();
	static String userName;
	static String ip = "192.168.252.207";
	static int location = 12356;
	static String filePath = "D:\\JavaStudy\\java_class\\accountingProject\\";
	static String uploadFilePath="D:\\Pictures\\";
	static String downloadPath ="D:\\t9downloadTest\\";
	static String t10Path="D:\\";
	static String t11downloadPath ="D:\\t11downloadTest\\";
	static String t7downloadPath ="D:\\t7Test\\";
	static String clientDownloadPath ="D:\\clientDownload\\";
	// 登录
	public static boolean login() throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true) {
			boolean name = false;
			boolean password = false;
			boolean pass = false;
			System.out.println("登录请输入A,注册用户请输入B");
			String str1 = sc.next();
			if (str1.equals("A")) {
				System.out.println("请输入用户名：");
				String str2 = sc.next();
				System.out.println("请输入密码：");
				String str3 = sc.next();
				List<user> findUser = zwd.findUser();
				for (user user : findUser) {
					if (user.getUsernmae().equals(str2) && user.getPassword().equals(str3)) {
						userName = user.getUsernmae();
						name = true;
						password = true;
					}
					if (user.getUsernmae().equals(str2) && !user.getPassword().equals(str3)) {
						name = true;
					}
				}
				if (name && password) {
					pass = true;
					System.out.println("登录成功");
					return pass;
				} else if (name && !password) {
					System.out.println("密码错误");
				} else {
					System.out.println("用户名错误");
				}
			} else if (str1.equals("B")) {
				register();
			} else {
				System.out.println("没有输入A或B输入错误，请重新输入");
				System.out.println();
			}
		}

	}

	// 注册
	public static void register() throws Exception {
		boolean reg = true;
		String st1 = null;
		String st2 = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			reg = true;
			System.out.println("请输入您的用户名：");
			String str1 = sc.next();
			st1 = str1;
			List<user> findUser = zwd.findUser();
			for (user user : findUser) {
				if (user.getUsernmae().equals(str1)) {
					System.out.println("用户名已存在，请重新输入");
					reg = false;
					break;
				}
			}
			while (reg) {
				System.out.println("请输入您的密码：");
				String str2 = sc.next();
				st2 = str2;
				System.out.println("请再次输入确认您的密码：");
				String str3 = sc.next();
				if (!str2.equals(str3)) {
					System.out.println("两次密码不同，请重新输入");
				} else {
					break;
				}
			}
			if (reg) {
				break;
			}

		}
		zwd.insertUser(st1, st2);

	}

	// 1.查询账务
	public void selectZhangWu() throws Exception {
		String sql = "select *from cuit_zhangwu where username='" + userName + "'";
		List<zhangwu> query = q.query(sql, new BeanListHandler<zhangwu>(zhangwu.class));
		printZhangWu.print(query);
	}

	// 2.多条件查询
	public void multiSelectZhangwu() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("查询一个日期请输入C，查询两个日期请输入D");
		String str1 = sc.next();
		if (str1.equals("C")) {
			System.out.println("请输入日期(例如：2023-01-01)：");
			String str2 = sc.next();
			List<zhangwu> multiSelectZhangwu1 = zwd.multiSelectZhangwu1(str2);
			printZhangWu.print(multiSelectZhangwu1);
		} else if (str1.equals("D")) {
			System.out.println("请输入起始日期(例如：2023-01-01)：");
			String str4 = sc.next();
			System.out.println("请输入截止日期(例如：2023-01-01)：");
			String str5 = sc.next();
			List<zhangwu> multiSelectZhangwu2 = zwd.multiSelectZhangwu2(str4, str5);
			printZhangWu.print(multiSelectZhangwu2);

		}
	}

	// 3.添加账务
	public void insertZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入添加的项目名称(例如：吃饭支出)：");
		String str1 = sc.next();
		System.out.println("请输入添加的项目开支(例如：333)：");
		String str2 = sc.next();
		System.out.println("请输入添加的项目支付方式(例如：现金)：");
		String str3 = sc.next();
		System.out.println("请输入添加的项目支付时间(例如：2023-01-01)：");
		String str4 = sc.next();
		System.out.println("请输入添加的项目详情(例如：请客吃饭)：");
		String str5 = sc.next();
		zwd.insertZhangWu(str1, str2, str3, str4, str5, userName);
		String[] split = str4.split("-");
		String m=split[0]+"-"+split[1];
		updateInsert(m);


	}

	// 3.更新
	public static void updateInsert(String m) throws Exception, IOException {
		try (Socket socket = new Socket(ip, location);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {

			List<zhangwu> z = zwd.takeLatestZhangWu(userName,m);
			String createtime = z.get(0).getCreatetime();
			String[] split = createtime.split("-");
			String s=split[0]+"-"+split[1]+".txt";
			serDemo t3up=new serDemo(7,userName,s,z);
			oos.writeObject(t3up);
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s1 = br.readLine();
			System.out.println("服务端返回：" + s1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 4.编辑账务
	public void editZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		boolean flag = false;
		int num = 0;
		while (true) {
			flag = false;
			System.out.println("请输入id:");
			num = sc.nextInt();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getZwid() == num) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("当前账户没有该条记录,请重新输入");
				break;
			} else {
				break;
			}
		}

		while (flag) {
			System.out.println("修改单个字段请输入E，修改全部字段请输入F");
			String str1 = sc.next();
			if (str1.equals("E")) {
				System.out.println("请输入要修改的字段名(字段有：  flname  money  zhanghu createtime description)");
				String str2 = sc.next();
				System.out.println("请输入修改内容：");
				String str3 = sc.next();
				zwd.editZhangWu1(num, str2, str3);
				t4updateInsert(num);
				break;
			} else if (str1.equals("F")) {
				System.out.println("请输入将flname修改成：");
				String s1 = sc.next();
				System.out.println("请输入将money修改成：");
				String s2 = sc.next();
				System.out.println("请输入将zhanghu修改成：");
				String s3 = sc.next();
				System.out.println("请输入将createtime修改成：");
				String s4 = sc.next();
				System.out.println("请输入将description修改成：");
				String s5 = sc.next();
				zwd.editZhangWu2(num, "flname", s1, "money", s2, "zhanghu", s3, "createtime", s4, "description", s5);
				t4updateInsert(num);
				break;
			} else {
				System.out.println("没有输入E或F，请重新输入：");
			}
		}

	}
	//4.更新编辑
	public static void t4updateInsert(int num) throws Exception, IOException {
		try (Socket socket = new Socket(ip, location);
			 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {

			List<zhangwu> z = zwd.t4takeLatestZhangWu(userName,num);
			String createtime = z.get(0).getCreatetime();
			String[] split = createtime.split("-");
			String s=split[0]+"-"+split[1];
			String s1=split[0]+"-"+split[1]+".txt";
			List<zhangwu> list = zwd.takeLatestZhangWu(userName, s);
			serDemo t3up=new serDemo(8,userName,s1,list);
			oos.writeObject(t3up);
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s2 = br.readLine();
			System.out.println("服务端返回：" + s2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// 5.删除账务
	public static void deleteZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		boolean flag = false;
		int num = 0;
		while (true) {
			flag = false;
			System.out.println("请输入id:");
			num = sc.nextInt();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getZwid() == num) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("当前账户没有该条记录");
				break;
			} else {
				break;
			}
		}
		if (flag){
			t5updateInsert(num);
		}




	}
	//5.更新删除
	public static void t5updateInsert(int num) throws Exception, IOException {
		try (Socket socket = new Socket(ip, location);
			 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {

			List<zhangwu> z = zwd.t4takeLatestZhangWu(userName,num);
			zwd.deleteZhangWu(num);
			String createtime = z.get(0).getCreatetime();
			String[] split = createtime.split("-");
			String s=split[0]+"-"+split[1];
			String s1=split[0]+"-"+split[1]+".txt";
			List<zhangwu> list = zwd.takeLatestZhangWu(userName, s);
			serDemo t3up=new serDemo(9,userName,s1,list);
			oos.writeObject(t3up);
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s2 = br.readLine();
			System.out.println("服务端返回：" + s2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 6.搜索账务
	public static void searchZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("请提供一个模糊字段：");
		String str = sc.next();
		zwd.searchZhangWu(str, userName);
	}

	// 7.导出账务
	public static void exportZhangWu() throws Exception {
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("导出一个日期的账务请输入G，导出两个日期的账务请输入H");
			String str = sc.next();
			if (str.equals("G")) {
				String date1 = null;
				boolean flag = false;
				while (true) {
					System.out.println("请输入一个日期(例如：2023-01-01)：");
					date1 = sc.next();
					for (zhangwu zhangwu : selectZhangWu) {
						if (zhangwu.getCreatetime().equals(date1)) {
							flag = true;
							break;
						}

					}
					if (!flag) {
						System.out.println("当前账户没有该日期的记录，请重新输入");
					} else {
						break;
					}
				}
				List<zhangwu> list = zwd.exportZhangWu1(date1, userName);
				String dirName = t7downloadPath+userName;
				String fileName = date1;
				String filePath = dirName + "\\" + fileName+".txt";
				File dir = new File(dirName);
				if (!dir.exists()) {
					boolean mkdir = dir.mkdir();
					if (mkdir) {
						System.out.println("目录创建成功");
					} else {
						System.out.println("目录创建失败");
					}

				} else {
					System.out.println("目录已存在");
				}
				File file = new File(filePath);
				if (!file.exists()) {
					boolean createNewFile = file.createNewFile();
					if (createNewFile) {
						System.out.println("文件创建成功");
					} else {
						System.out.println("文件创建失败");
					}
				} else {
					System.out.println("文件已经存在");
				}
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				for (zhangwu zhangwu : list) {
					String line = zhangwu.getZwid() + "\t" + zhangwu.getFlname() + "\t" + zhangwu.getMoney() + "\t"
							+ zhangwu.getZhanghu() + "\t" + zhangwu.getCreatetime() + "\t" + zhangwu.getDescription()
							+ "\t" + zhangwu.getUsername();
					bos.write(line.getBytes());
					bos.write('\n');
				}
				bos.close();
				break;
			} else if (str.equals("H")) {
				String str1 = null;
				String str2 = null;
				boolean flag1 = false;
				boolean flag2 = false;
				while (true) {
					System.out.println("请输入起始日期(例如：2023-01-01)：");
					str1 = sc.next();
					System.out.println("请输入截至日期(例如：2023-01-01)：");
					str2 = sc.next();
					for (zhangwu zhangwu : selectZhangWu) {
						if (zhangwu.getCreatetime().equals(str1)) {
							flag1 = true;
						}
						if (zhangwu.getCreatetime().equals(str2)) {
							flag2 = true;
						}
					}
					if (!flag1) {
						System.out.println("当前账户没有该起始日期，请重新输入");
					}
					if (!flag2) {
						System.out.println("当前账户没有改截至日期，请重新输入");
					}
					if (flag1 && flag2) {
						break;
					}
				}

				List<zhangwu> list = zwd.exportZhangWu2(str1, str2, userName);
				String dirName = userName;
				String fileName = str1 + "-" + str2;
				String filePath = dirName + "\\" + fileName;
				File dir = new File(dirName);
				if (!dir.exists()) {
					boolean mkdir = dir.mkdir();
					if (mkdir) {
						System.out.println("目录创建成功");
					} else {
						System.out.println("目录创建失败");
					}

				} else {
					System.out.println("目录已存在");
				}
				File file = new File(filePath);
				if (!file.exists()) {
					boolean createNewFile = file.createNewFile();
					if (createNewFile) {
						System.out.println("文件创建成功");
					} else {
						System.out.println("文件创建失败");
					}
				} else {
					System.out.println("文件已经存在");
				}
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				for (zhangwu zhangwu : list) {
					String line = zhangwu.getZwid() + "\t" + zhangwu.getFlname() + "\t" + zhangwu.getMoney() + "\t"
							+ zhangwu.getZhanghu() + "\t" + zhangwu.getCreatetime() + "\t" + zhangwu.getDescription()
							+ "\t" + zhangwu.getUsername();
					bos.write(line.getBytes());
					bos.write('\n');
				}
				bos.close();
				break;
			} else {
				System.out.println("没有输入G或H请重新输入");
			}
		}

	}

	// 8.统计账务
	public static void sumZhangWu() throws Exception {
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);

		Scanner sc = new Scanner(System.in);
		String str1 = null;
		boolean flag = false;
		while (true) {
			System.out.println("请输入月份(例如3月-03)：");
			str1 = sc.next();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getCreatetime().contains("-" + str1 + "-")) {
					flag = true;
				}
			}
			if (!flag) {
				System.out.println("当前账户没有该月份，请重新输入");
			} else {
				break;
			}
		}

		double num = zwd.sumZhangWu(str1, userName);
		System.out.println(str1 + "月账务总和为：" + num);

	}

	// 9.上传账务
	// Client
	public static void uploadZhangwu() throws Exception {

		Socket socket = new Socket(ip, location);
		System.out.println("正在连接"+socket.getInetAddress());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		while (true){
			String date = null;
			Scanner sc = new Scanner(System.in);
			boolean flag = false;
			while (true) {
				System.out.println("请输入年月份(例如2023-04),退出请输入Q：");
				date = sc.next();
				if (date.equals("Q")){
					break;
				}
				for (zhangwu zhangwu : selectZhangWu) {
					if (zhangwu.getCreatetime().contains(date)) {
						flag = true;
					}
				}
				if (!flag) {
					System.out.println("没有该年月份，请重新输入：   退出请输入Q");

				} else {
					break;
				}
			}
			if (date.equals("Q")){
				break;
			}
			String[] split = date.split("-");
			String fileName = split[0]+"-"+split[1];
			List<zhangwu> list = zwd.uploadZhangwu(date, userName);//得到了对应的list
			serDemo upload=new serDemo(3,userName,fileName,list);
			objectOutputStream.writeObject(upload);
			socket.shutdownOutput();
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s = br.readLine();
			System.out.println("服务端反馈:"+s);
			break;


		}

	}
	// 10.下载账务
	public static void downloadZhangWu() throws Exception {
		Socket socket=new Socket(ip,location);//获取连接
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		serDemo t1UpRequest=new serDemo(1,userName);//这里是传了文件名，即用户名
		oos.writeObject(t1UpRequest);
		socket.shutdownOutput();
		ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

		Object o1 = ois.readObject();

		serDemo t1list=(serDemo) o1;
		List<String> fileNames = t1list.getFileNames();
		if (fileNames.isEmpty()){
			System.out.println("没有可下载的账务，文件夹为空");
		}else {
			int num=1;
			Map<Integer,String> t1map=new HashMap<>();
			System.out.println("可下载的文件有：");
			for (String fN:fileNames
			) {System.out.println(num+"."+fN);
				t1map.put(num,fN);
				num++;

			}
			if (t1map.size()==0){
				System.out.println("当前用户没有账务,退出下载");
				return;
			}
			System.out.println("请输入要下载的文件序号");
			Scanner sc=new Scanner(System.in);
			int num1=sc.nextInt();
			if (num1>t1map.size()){
				System.out.println("当前用户没有该账务");
				return;
			}
			String s1 = t1map.get(num1);
			//上传要下载的文件名
			t10downloadZhangWu(s1);
		}

//
	}
	public static void t10downloadZhangWu(String s1) throws IOException, ClassNotFoundException {
		Socket socket=new Socket(ip,location);//获取连接
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		serDemo t1ser=new serDemo(2,userName+"\\"+s1);
		oos.writeObject(t1ser);
		socket.shutdownOutput();
		ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
		Object o2 = ois.readObject();
		serDemo t1dolo=(serDemo) o2;
		String fileName1 = t1dolo.getFileName();
		File file=new File(clientDownloadPath+fileName1);
		File file1=new File(clientDownloadPath+userName);
		if (!file1.exists()){
			file1.mkdir();
		}
		List<zhangwu> list = t1dolo.getList();
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		for (zhangwu zhangwu:list
		) {bw.write(zhangwu.toString());
			bw.newLine();
		}
		bw.flush();
		System.out.println("下载完成");
	}
	//11.上传文件
	public static void uploadDir() throws IOException {
		Socket socket=new Socket(ip,location);
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		while (true){
			File file1=new File(t10Path+"sql1.log");
			File file2=new File(t10Path+"证书.png");
			File file3=new File(t10Path+"TortoiseGit.rar");
			File file4=new File(t10Path+"SSH.zip");
			File file5=new File(t10Path+"第5章作业 .ipynb");
			File file6=new File(t10Path+"图像分类经典卷积神经网络.tex");
			Map<Integer,File> upMap=new HashMap<>();
			upMap.put(1,file1);upMap.put(2,file2);upMap.put(3,file3);upMap.put(4,file4);upMap.put(5,file5);
			upMap.put(6,file6);
			upMap.forEach((num,file)->{
				System.out.println("可上传的文件有："+num+"."+file.getName());
			});
			System.out.println("请输入对应序号：");
			Scanner sc=new Scanner(System.in);
			int num1=sc.nextInt();
			if (num1>6){
				System.out.println("输入错误，没有该文件");
				break;
			}
			File file = upMap.get(num1);
			byte[] data;
			if (file.isDirectory()){
				data= readBytesFromDir.readBytesFrom(file);

			}else {
				data=readBytesFromFile.readBytesFrom(file);
			}
			serDemo t10upload=new serDemo(4,userName,file.getName(),data);
			System.out.println("开始传输");
			oos.writeObject(t10upload);
			System.out.println("传输完成");
//			return;
			socket.shutdownOutput();
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s = br.readLine();
			System.out.println("服务端反馈："+s);
			break;

		}

	}
//12.下载文件
public  static void downloadDir() throws Exception {
	Socket socket=new Socket(ip,location);//获取连接
	ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
	serDemo t1UpRequest=new serDemo(10,userName);//这里是传了文件名，即用户名
	oos.writeObject(t1UpRequest);
	ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
	Object o1 = objectInputStream.readObject();
	serDemo t12o=(serDemo) o1;
	List<String> fileNames = t12o.getFileNames();
	if (fileNames.isEmpty()){
		System.out.println("没有可下载的文件，文件夹为空");
	}
	else {
		Map<Integer,String> t12map=new HashMap<>();
		int num=1;
		System.out.println("可下载的有：");
		for (String name: fileNames
		) {System.out.println(num+"."+name);
			t12map.put(num,name);
			num++;

		}
		if (t12map.size()==0){
			System.out.println("文件数为零，没有可下载的文件");
			return;
		}
		System.out.println("请输入要下载的文件序号：");
		Scanner sc=new Scanner(System.in);
		int num1=sc.nextInt();
		if (num1>t12map.size()){
			System.out.println("当前用户没有该文件");
			return;
		}
		String s1 = t12map.get(num1);
		t11downloadDir(s1);
	}



}
	public  static void t11downloadDir(String s1) throws Exception {
		Socket socket=new Socket(ip,location);//获取连接
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		if (s1.contains("txt")){
			serDemo t1ser=new serDemo(2,userName+"\\"+s1);
			oos.writeObject(t1ser);

			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			Object o2 = ois.readObject();
			serDemo t1dolo=(serDemo) o2;
			String fileName1 = t1dolo.getFileName();
			File file=new File(clientDownloadPath+fileName1);
			File file1=new File(clientDownloadPath+userName);
			if (!file1.exists()){
				file1.mkdir();
			}
			List<zhangwu> list = t1dolo.getList();
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			for (zhangwu zhangwu:list
			) {bw.write(zhangwu.toString());
				bw.newLine();
			}
			bw.flush();
		}else {
			serDemo t1ser=new serDemo(11,userName+"\\"+s1);
			oos.writeObject(t1ser);

			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			Object o2 = ois.readObject();
			serDemo t1dolo=(serDemo) o2;
			String fileName1 = t1dolo.getFileName();
			File file=new File(clientDownloadPath+fileName1);
			File file1=new File(clientDownloadPath+userName);
			if (!file1.exists()){
				file1.mkdir();
			}
			byte[] data = t1dolo.getData();
			BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
			bufferedOutputStream.write(data);

		}
		System.out.println("下载完成");
	}

}
