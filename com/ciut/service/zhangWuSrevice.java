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
	// ��¼
	public static boolean login() throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true) {
			boolean name = false;
			boolean password = false;
			boolean pass = false;
			System.out.println("��¼������A,ע���û�������B");
			String str1 = sc.next();
			if (str1.equals("A")) {
				System.out.println("�������û�����");
				String str2 = sc.next();
				System.out.println("���������룺");
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
					System.out.println("��¼�ɹ�");
					return pass;
				} else if (name && !password) {
					System.out.println("�������");
				} else {
					System.out.println("�û�������");
				}
			} else if (str1.equals("B")) {
				register();
			} else {
				System.out.println("û������A��B�����������������");
				System.out.println();
			}
		}

	}

	// ע��
	public static void register() throws Exception {
		boolean reg = true;
		String st1 = null;
		String st2 = null;
		Scanner sc = new Scanner(System.in);
		while (true) {
			reg = true;
			System.out.println("�����������û�����");
			String str1 = sc.next();
			st1 = str1;
			List<user> findUser = zwd.findUser();
			for (user user : findUser) {
				if (user.getUsernmae().equals(str1)) {
					System.out.println("�û����Ѵ��ڣ�����������");
					reg = false;
					break;
				}
			}
			while (reg) {
				System.out.println("�������������룺");
				String str2 = sc.next();
				st2 = str2;
				System.out.println("���ٴ�����ȷ���������룺");
				String str3 = sc.next();
				if (!str2.equals(str3)) {
					System.out.println("�������벻ͬ������������");
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

	// 1.��ѯ����
	public void selectZhangWu() throws Exception {
		String sql = "select *from cuit_zhangwu where username='" + userName + "'";
		List<zhangwu> query = q.query(sql, new BeanListHandler<zhangwu>(zhangwu.class));
		printZhangWu.print(query);
	}

	// 2.��������ѯ
	public void multiSelectZhangwu() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("��ѯһ������������C����ѯ��������������D");
		String str1 = sc.next();
		if (str1.equals("C")) {
			System.out.println("����������(���磺2023-01-01)��");
			String str2 = sc.next();
			List<zhangwu> multiSelectZhangwu1 = zwd.multiSelectZhangwu1(str2);
			printZhangWu.print(multiSelectZhangwu1);
		} else if (str1.equals("D")) {
			System.out.println("��������ʼ����(���磺2023-01-01)��");
			String str4 = sc.next();
			System.out.println("�������ֹ����(���磺2023-01-01)��");
			String str5 = sc.next();
			List<zhangwu> multiSelectZhangwu2 = zwd.multiSelectZhangwu2(str4, str5);
			printZhangWu.print(multiSelectZhangwu2);

		}
	}

	// 3.�������
	public void insertZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("��������ӵ���Ŀ����(���磺�Է�֧��)��");
		String str1 = sc.next();
		System.out.println("��������ӵ���Ŀ��֧(���磺333)��");
		String str2 = sc.next();
		System.out.println("��������ӵ���Ŀ֧����ʽ(���磺�ֽ�)��");
		String str3 = sc.next();
		System.out.println("��������ӵ���Ŀ֧��ʱ��(���磺2023-01-01)��");
		String str4 = sc.next();
		System.out.println("��������ӵ���Ŀ����(���磺��ͳԷ�)��");
		String str5 = sc.next();
		zwd.insertZhangWu(str1, str2, str3, str4, str5, userName);
		String[] split = str4.split("-");
		String m=split[0]+"-"+split[1];
		updateInsert(m);


	}

	// 3.����
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
			System.out.println("����˷��أ�" + s1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 4.�༭����
	public void editZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		boolean flag = false;
		int num = 0;
		while (true) {
			flag = false;
			System.out.println("������id:");
			num = sc.nextInt();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getZwid() == num) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("��ǰ�˻�û�и�����¼,����������");
				break;
			} else {
				break;
			}
		}

		while (flag) {
			System.out.println("�޸ĵ����ֶ�������E���޸�ȫ���ֶ�������F");
			String str1 = sc.next();
			if (str1.equals("E")) {
				System.out.println("������Ҫ�޸ĵ��ֶ���(�ֶ��У�  flname  money  zhanghu createtime description)");
				String str2 = sc.next();
				System.out.println("�������޸����ݣ�");
				String str3 = sc.next();
				zwd.editZhangWu1(num, str2, str3);
				t4updateInsert(num);
				break;
			} else if (str1.equals("F")) {
				System.out.println("�����뽫flname�޸ĳɣ�");
				String s1 = sc.next();
				System.out.println("�����뽫money�޸ĳɣ�");
				String s2 = sc.next();
				System.out.println("�����뽫zhanghu�޸ĳɣ�");
				String s3 = sc.next();
				System.out.println("�����뽫createtime�޸ĳɣ�");
				String s4 = sc.next();
				System.out.println("�����뽫description�޸ĳɣ�");
				String s5 = sc.next();
				zwd.editZhangWu2(num, "flname", s1, "money", s2, "zhanghu", s3, "createtime", s4, "description", s5);
				t4updateInsert(num);
				break;
			} else {
				System.out.println("û������E��F�����������룺");
			}
		}

	}
	//4.���±༭
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
			System.out.println("����˷��أ�" + s2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	// 5.ɾ������
	public static void deleteZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		boolean flag = false;
		int num = 0;
		while (true) {
			flag = false;
			System.out.println("������id:");
			num = sc.nextInt();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getZwid() == num) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				System.out.println("��ǰ�˻�û�и�����¼");
				break;
			} else {
				break;
			}
		}
		if (flag){
			t5updateInsert(num);
		}




	}
	//5.����ɾ��
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
			System.out.println("����˷��أ�" + s2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 6.��������
	public static void searchZhangWu() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("���ṩһ��ģ���ֶΣ�");
		String str = sc.next();
		zwd.searchZhangWu(str, userName);
	}

	// 7.��������
	public static void exportZhangWu() throws Exception {
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("����һ�����ڵ�����������G�������������ڵ�����������H");
			String str = sc.next();
			if (str.equals("G")) {
				String date1 = null;
				boolean flag = false;
				while (true) {
					System.out.println("������һ������(���磺2023-01-01)��");
					date1 = sc.next();
					for (zhangwu zhangwu : selectZhangWu) {
						if (zhangwu.getCreatetime().equals(date1)) {
							flag = true;
							break;
						}

					}
					if (!flag) {
						System.out.println("��ǰ�˻�û�и����ڵļ�¼������������");
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
						System.out.println("Ŀ¼�����ɹ�");
					} else {
						System.out.println("Ŀ¼����ʧ��");
					}

				} else {
					System.out.println("Ŀ¼�Ѵ���");
				}
				File file = new File(filePath);
				if (!file.exists()) {
					boolean createNewFile = file.createNewFile();
					if (createNewFile) {
						System.out.println("�ļ������ɹ�");
					} else {
						System.out.println("�ļ�����ʧ��");
					}
				} else {
					System.out.println("�ļ��Ѿ�����");
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
					System.out.println("��������ʼ����(���磺2023-01-01)��");
					str1 = sc.next();
					System.out.println("�������������(���磺2023-01-01)��");
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
						System.out.println("��ǰ�˻�û�и���ʼ���ڣ�����������");
					}
					if (!flag2) {
						System.out.println("��ǰ�˻�û�иĽ������ڣ�����������");
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
						System.out.println("Ŀ¼�����ɹ�");
					} else {
						System.out.println("Ŀ¼����ʧ��");
					}

				} else {
					System.out.println("Ŀ¼�Ѵ���");
				}
				File file = new File(filePath);
				if (!file.exists()) {
					boolean createNewFile = file.createNewFile();
					if (createNewFile) {
						System.out.println("�ļ������ɹ�");
					} else {
						System.out.println("�ļ�����ʧ��");
					}
				} else {
					System.out.println("�ļ��Ѿ�����");
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
				System.out.println("û������G��H����������");
			}
		}

	}

	// 8.ͳ������
	public static void sumZhangWu() throws Exception {
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);

		Scanner sc = new Scanner(System.in);
		String str1 = null;
		boolean flag = false;
		while (true) {
			System.out.println("�������·�(����3��-03)��");
			str1 = sc.next();
			for (zhangwu zhangwu : selectZhangWu) {
				if (zhangwu.getCreatetime().contains("-" + str1 + "-")) {
					flag = true;
				}
			}
			if (!flag) {
				System.out.println("��ǰ�˻�û�и��·ݣ�����������");
			} else {
				break;
			}
		}

		double num = zwd.sumZhangWu(str1, userName);
		System.out.println(str1 + "�������ܺ�Ϊ��" + num);

	}

	// 9.�ϴ�����
	// Client
	public static void uploadZhangwu() throws Exception {

		Socket socket = new Socket(ip, location);
		System.out.println("��������"+socket.getInetAddress());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		List<zhangwu> selectZhangWu = zwd.selectZhangWu(userName);
		while (true){
			String date = null;
			Scanner sc = new Scanner(System.in);
			boolean flag = false;
			while (true) {
				System.out.println("���������·�(����2023-04),�˳�������Q��");
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
					System.out.println("û�и����·ݣ����������룺   �˳�������Q");

				} else {
					break;
				}
			}
			if (date.equals("Q")){
				break;
			}
			String[] split = date.split("-");
			String fileName = split[0]+"-"+split[1];
			List<zhangwu> list = zwd.uploadZhangwu(date, userName);//�õ��˶�Ӧ��list
			serDemo upload=new serDemo(3,userName,fileName,list);
			objectOutputStream.writeObject(upload);
			socket.shutdownOutput();
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s = br.readLine();
			System.out.println("����˷���:"+s);
			break;


		}

	}
	// 10.��������
	public static void downloadZhangWu() throws Exception {
		Socket socket=new Socket(ip,location);//��ȡ����
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		serDemo t1UpRequest=new serDemo(1,userName);//�����Ǵ����ļ��������û���
		oos.writeObject(t1UpRequest);
		socket.shutdownOutput();
		ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());

		Object o1 = ois.readObject();

		serDemo t1list=(serDemo) o1;
		List<String> fileNames = t1list.getFileNames();
		if (fileNames.isEmpty()){
			System.out.println("û�п����ص������ļ���Ϊ��");
		}else {
			int num=1;
			Map<Integer,String> t1map=new HashMap<>();
			System.out.println("�����ص��ļ��У�");
			for (String fN:fileNames
			) {System.out.println(num+"."+fN);
				t1map.put(num,fN);
				num++;

			}
			if (t1map.size()==0){
				System.out.println("��ǰ�û�û������,�˳�����");
				return;
			}
			System.out.println("������Ҫ���ص��ļ����");
			Scanner sc=new Scanner(System.in);
			int num1=sc.nextInt();
			if (num1>t1map.size()){
				System.out.println("��ǰ�û�û�и�����");
				return;
			}
			String s1 = t1map.get(num1);
			//�ϴ�Ҫ���ص��ļ���
			t10downloadZhangWu(s1);
		}

//
	}
	public static void t10downloadZhangWu(String s1) throws IOException, ClassNotFoundException {
		Socket socket=new Socket(ip,location);//��ȡ����
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
		System.out.println("�������");
	}
	//11.�ϴ��ļ�
	public static void uploadDir() throws IOException {
		Socket socket=new Socket(ip,location);
		ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		while (true){
			File file1=new File(t10Path+"sql1.log");
			File file2=new File(t10Path+"֤��.png");
			File file3=new File(t10Path+"TortoiseGit.rar");
			File file4=new File(t10Path+"SSH.zip");
			File file5=new File(t10Path+"��5����ҵ .ipynb");
			File file6=new File(t10Path+"ͼ����ྭ����������.tex");
			Map<Integer,File> upMap=new HashMap<>();
			upMap.put(1,file1);upMap.put(2,file2);upMap.put(3,file3);upMap.put(4,file4);upMap.put(5,file5);
			upMap.put(6,file6);
			upMap.forEach((num,file)->{
				System.out.println("���ϴ����ļ��У�"+num+"."+file.getName());
			});
			System.out.println("�������Ӧ��ţ�");
			Scanner sc=new Scanner(System.in);
			int num1=sc.nextInt();
			if (num1>6){
				System.out.println("�������û�и��ļ�");
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
			System.out.println("��ʼ����");
			oos.writeObject(t10upload);
			System.out.println("�������");
//			return;
			socket.shutdownOutput();
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s = br.readLine();
			System.out.println("����˷�����"+s);
			break;

		}

	}
//12.�����ļ�
public  static void downloadDir() throws Exception {
	Socket socket=new Socket(ip,location);//��ȡ����
	ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
	serDemo t1UpRequest=new serDemo(10,userName);//�����Ǵ����ļ��������û���
	oos.writeObject(t1UpRequest);
	ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
	Object o1 = objectInputStream.readObject();
	serDemo t12o=(serDemo) o1;
	List<String> fileNames = t12o.getFileNames();
	if (fileNames.isEmpty()){
		System.out.println("û�п����ص��ļ����ļ���Ϊ��");
	}
	else {
		Map<Integer,String> t12map=new HashMap<>();
		int num=1;
		System.out.println("�����ص��У�");
		for (String name: fileNames
		) {System.out.println(num+"."+name);
			t12map.put(num,name);
			num++;

		}
		if (t12map.size()==0){
			System.out.println("�ļ���Ϊ�㣬û�п����ص��ļ�");
			return;
		}
		System.out.println("������Ҫ���ص��ļ���ţ�");
		Scanner sc=new Scanner(System.in);
		int num1=sc.nextInt();
		if (num1>t12map.size()){
			System.out.println("��ǰ�û�û�и��ļ�");
			return;
		}
		String s1 = t12map.get(num1);
		t11downloadDir(s1);
	}



}
	public  static void t11downloadDir(String s1) throws Exception {
		Socket socket=new Socket(ip,location);//��ȡ����
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
		System.out.println("�������");
	}

}
