/*
 * 功能：FileInputStream类基本用法，读取文件，写出文件，每次读写一个字节
 */
package com.bj.io;
import java.io.*;

public class FileInputStream_t {

	public static void main(String[] args) {
		// 定义File对象
		File file=new File("D:/www/nb.txt");
		
		
		FileInputStream fis=null;
		try {
			//Filem不具备读写方法，需要InputSteam帮助
			fis=new FileInputStream(file);
			//定义一个字节数组
			byte [] bytes=new byte[1024];
			//读入计数
			int count=0;
			//循环读取，read（）,当读取到最后会返回-1，正常返回读取的字节数
			
				while(-1!=(count=fis.read(bytes))){
					String s=new String(bytes,0,count);
					System.out.println(" "+s+" count:"+count);
				}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
