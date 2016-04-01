/*
 * 功能：FileOutputStream类基本功能，读取文件，写出文件，每次读写一个字节
 * 1、
 */
package com.bj.io;
import java.io.*;
public class FileOutputStream_t {

	public static void main(String[] args) {
		//定义File对象
		File file=new File("D:/www/test.txt");
		//字节输出流
		FileOutputStream fos=null;
		
		try {
			fos=new FileOutputStream(file);
			//字符串
			String s="I have a dream.\r\n";
			String s1="you have a dream.";
			//定义字节数组
			//byte bytes[]=new byte[1024];
			
			fos.write(s.getBytes());
			fos.write(s1.getBytes());
			
			
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
