/*
 * ���ܣ������ַ��������÷�,�����ַ������Զ�ȡ������ַ�����߶�ȡд��Ч��
 */
package com.bj.io;

import java.io.*;

public class BufferStream_t {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		BufferedWriter bw=null;
		try {
			//�ȴ���FileReader
			FileReader fr=new FileReader("D:/www/test.txt");
			br=new BufferedReader(fr);
			//����FileWriter����
			FileWriter fw=new FileWriter("D:/www/test_back_buff.txt");			
			bw=new BufferedWriter(fw);
			String s="";
			//ѭ����ȡ�ļ�
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
