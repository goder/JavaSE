/*
 * ���ܣ�FileInputStream������÷�����ȡ�ļ���д���ļ���ÿ�ζ�дһ���ֽ�
 */
package com.bj.io;
import java.io.*;

public class FileInputStream_t {

	public static void main(String[] args) {
		// ����File����
		File file=new File("D:/www/nb.txt");
		
		
		FileInputStream fis=null;
		try {
			//Filem���߱���д��������ҪInputSteam����
			fis=new FileInputStream(file);
			//����һ���ֽ�����
			byte [] bytes=new byte[1024];
			//�������
			int count=0;
			//ѭ����ȡ��read����,����ȡ�����᷵��-1���������ض�ȡ���ֽ���
			
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
