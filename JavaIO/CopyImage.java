/*
 * ���ܣ�ͼƬ��һ��λ�ö��룬д������һ��λ��
 */
package com.bj.io;
import java.io.*;
public class CopyImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("D:/www/a.jpg");//a.jpg����Ҫ����
		
		//�ֽ������롢д��
		FileInputStream fis=null;
		FileOutputStream fos=null;
		
		try {
			fis=new FileInputStream(file);
			fos=new FileOutputStream("D:/www/b.jpg");
			byte bytes[] =new byte[1024];
			//���������ֽ���
			int count=0;
			while(-1!=(count=fis.read(bytes))){
				//д����ָ��λ��
				fos.write(bytes);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//�ر��ļ��ֽ���
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}

}
