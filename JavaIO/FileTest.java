/*
 * ���ܣ�File������÷�
 * 1������
 * 2��ʹ��
 */
package com.bj.io;
import java.io.*;

public class FileTest {

	public static void main(String[] args) {
		
//		//�����ļ�����
//		File file=new File("D:/aa.txt");
//		//�õ��ļ�·��
//		System.out.println("�ļ�·����"+file.getAbsolutePath());
//		//�õ��ļ���
//		System.out.println("�ļ�����"+file.getName());
//		
//		if(file.exists()==false){
//			System.out.println("File Not Exist");
//		}
		
		
		
//		//�����ļ����ļ���
//		File file=new File("D:/www/nb.txt");
//		if(file.exists()==false){
//				try {
//					file.createNewFile();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}else{
//			System.out.println("�ļ����ڣ�������");
//		}
		
		
		//�����ļ���
		File files=new File("D:/www");
		if(files.isDirectory()){
			
			System.out.println("�ļ�Ŀ¼����");
			File list[]=files.listFiles();
			for(int i=0;i<list.length;i++){
				System.out.println("�ļ�����"+list[i].getName());
			}
		}else{
			files.mkdir();
		}
		
		
		
		
		
		
	}

}
