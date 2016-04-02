/*
 * ���ܣ��ļ��ַ�������ȡ�ļ���д���ļ���ÿ�ζ�дһ���ַ�
 */
package com.bj.io;

import java.io.*;

public class StringFile_t {

	public static void main(String[] args) {
		// �ļ�ȡ���ַ���������������
		FileReader fr=null;
		//д���ļ����������
		FileWriter fw=null;
		
		try{
			//����fr����
			fr=new FileReader("D:/www/test.txt");
			//����fw����
			fw=new FileWriter("D:/www/test_copy.txt");
			//���뵽�ڴ�
			char c[]=new char[1024];
			//�����ַ�����
			int count=0;
			///ѭ����ȡ��read����,����ȡ�����᷵��-1���������ض�ȡ���ַ���
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
