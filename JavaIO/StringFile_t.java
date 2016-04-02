/*
 * 功能：文件字符流，读取文件，写出文件，每次读写一个字符
 */
package com.bj.io;

import java.io.*;

public class StringFile_t {

	public static void main(String[] args) {
		// 文件取出字符流对象（输入流）
		FileReader fr=null;
		//写入文件（输出流）
		FileWriter fw=null;
		
		try{
			//创建fr对象
			fr=new FileReader("D:/www/test.txt");
			//创建fw对象
			fw=new FileWriter("D:/www/test_copy.txt");
			//读入到内存
			char c[]=new char[1024];
			//读入字符计数
			int count=0;
			///循环读取，read（）,当读取到最后会返回-1，正常返回读取的字符数
			while(-1!=(count=fr.read(c))){
				String s=new String(c,0,count);
				System.out.println(s);
				fw.write(c,0,count);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
