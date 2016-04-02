/*
 * 功能：图片从一个位置读入，写出到另一个位置
 */
package com.bj.io;
import java.io.*;
public class CopyImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("D:/www/a.jpg");//a.jpg必须要存在
		
		//字节流读入、写出
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		try {
			fis=new FileInputStream(file);
			fos=new FileOutputStream("D:/www/b.jpg");
			byte bytes[] =new byte[1024];
			//计数读入字节数
			int count=0;
			while(-1!=(count=fis.read(bytes))){
				//写出到指定位置
				fos.write(bytes);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//关闭文件字节流
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
