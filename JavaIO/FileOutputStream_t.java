/*
 * ���ܣ�FileOutputStream��������ܣ���ȡ�ļ���д���ļ���ÿ�ζ�дһ���ֽ�
 * 1��
 */
package com.bj.io;
import java.io.*;
public class FileOutputStream_t {

	public static void main(String[] args) {
		//����File����
		File file=new File("D:/www/test.txt");
		//�ֽ������
		FileOutputStream fos=null;
		
		try {
			fos=new FileOutputStream(file);
			//�ַ���
			String s="I have a dream.\r\n";
			String s1="you have a dream.";
			//�����ֽ�����
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
