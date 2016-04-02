/*
 * 功能：缓存字符流基本用法,缓存字符流可以读取更多的字符，提高读取写入效率
 */
package com.bj.io;

import java.io.*;

public class BufferStream_t {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			//先创建FileReader
			FileReader fr=new FileReader("D:/www/test.txt");
			br=new BufferedReader(fr);
			//创建FileWriter对象
			FileWriter fw=new FileWriter("D:/www/test_back_buff.txt");			
			bw=new BufferedWriter(fw);
			String s="";
			//循环读取文件
			while(null!=(s=br.readLine())){
				fw.write(s+"\r\n");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

}
