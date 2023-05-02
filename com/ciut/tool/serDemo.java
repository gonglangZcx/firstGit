package com.ciut.tool;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.ciut.domain.zhangwu;

public class serDemo implements Serializable{
	private int num;
	public serDemo(int num, String fileName, List<zhangwu> list, byte[] data) {
		super();
		this.num = num;
		this.fileName = fileName;
		this.list = list;
		this.data = data;
	}
	public serDemo(int num, String fileName, List<zhangwu> list) {
		super();
		this.num = num;
		this.fileName = fileName;
		this.list = list;
	}
	public serDemo(int num,String dirName ,String fileName, List<zhangwu> list) {
		super();
		this.num = num;
		this.fileName = fileName;
		this.list = list;
		this.dirName=dirName;
	}
	public serDemo(int num,String dirName ,String fileName, byte[]data) {
		super();
		this.num = num;
		this.fileName = fileName;
		this.dirName = dirName;
		this.data=data;
	}
	public serDemo(int num,String fileName, byte[]data) {
		super();
		this.num = num;
		this.fileName = fileName;
		this.data=data;
	}
	public serDemo(int num, String fileName) {
		super();
		this.num = num;
		this.fileName=fileName;
	}
	public serDemo(int num, List<String> fileNames) {
		super();
		this.num = num;
		this.fileNames=fileNames;
	}
	public serDemo(int num) {
		super();
		this.num = num;
	}
	public serDemo(int num, String fileName,String jpgPath) {
		super();
		this.num = num;
		this.data = data;
		File jpgFile=new File(jpgPath);
		try (FileInputStream fis=new FileInputStream(jpgFile)){
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			byte[] buffer =new byte[8192];
			int len;
			while ((len= fis.read(buffer))>0){
				bos.write(buffer,0,len);

			}
			data = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "serDemo [num=" + num + ", fileName=" + fileName + ", list=" + list + ", data=" + Arrays.toString(data)
				+ "]";
	}
	private String fileName;
	public int getNum() {
		return num;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<zhangwu> getList() {
		return list;
	}
	public void setList(List<zhangwu> list) {
		this.list = list;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	private List<zhangwu> list;
	private byte[] data;

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	private String dirName;
	private List<String> fileNames;

}
