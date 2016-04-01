/*
 * 功能：File类基本用法
 * 1、创建
 * 2、使用
 */
package com.bj.io;
import java.io.*;

public class FileTest {

	public static void main(String[] args) {
		
//		//创建文件对象
//		File file=new File("D:/aa.txt");
//		//得到文件路径
//		System.out.println("文件路径："+file.getAbsolutePath());
//		//得到文件名
//		System.out.println("文件名："+file.getName());
//		
//		if(file.exists()==false){
//			System.out.println("File Not Exist");
//		}
		
		
		
//		//创建文件和文件夹
//		File file=new File("D:/www/nb.txt");
//		if(file.exists()==false){
//				try {
//					file.createNewFile();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}else{
//			System.out.println("文件存在，不创建");
//		}
		
		
		//创建文件夹
		File files=new File("D:/www");
		if(files.isDirectory()){
			
			System.out.println("文件目录存在");
			File list[]=files.listFiles();
			for(int i=0;i<list.length;i++){
				System.out.println("文件名："+list[i].getName());
			}
		}else{
			files.mkdir();
		}
		
		
		
		
		
		
	}

}
