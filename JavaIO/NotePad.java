/*
 * 功能：模拟一个简单的记事本，利用绘绘画、字符流进行实现
 * 1、菜单：JMenuBar->JMenu->JMenuItem
 *          菜单条（最顶层）->菜单（包含在菜单条中）->菜单项（包含在菜单中）
 */

package com.bj.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.event.*;

public class NotePad extends JFrame implements ActionListener{
	
	//定义文本域
	JTextArea jta=null;
	//定义菜单条
	JMenuBar jmb=null;
	//定义菜单
	JMenu jm=null;
	//定义菜单项,打开
	JMenuItem jmi=null;
	//定义菜单项，保存
	JMenuItem jmi2=null;
	//定义菜单项，退出
	JMenuItem jmi3=null;
	//JScollPane组件
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad np=new NotePad();
	}
	public NotePad(){
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jmb=new JMenuBar();
		jm=new JMenu("文件");
		//jmi=new JMenuItem("打开", new ImageIcon("images/notepad3.jpg"));//带图标菜单项
		jmi=new JMenuItem("打开");
		jmi2=new JMenuItem("保存");
		jmi3=new JMenuItem("退出");
		
		//注册打开事件
		jmi.addActionListener(this);
		jmi.setActionCommand("open");
		//注册保存事件
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//注册退出事件
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");
		
	
		
		//把jm加入jmb中
		jmb.add(jm);
		//把jmi加入jm中
		jm.add(jmi);
		jm.add(jmi2);
		jm.add(jmi3);
		//把jsp加入
		this.add(jsp);
		//设置jmb
		
		this.setJMenuBar(jmb);		
		this.setSize(800,800);
		this.setLocation(100,100);
		this.setTitle("记事本");
		this.setIconImage(new ImageIcon("images/notepad3.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int option=-1;
		if(e.getActionCommand().equals("open")){
			//JFileChooser文件选择器
			JFileChooser jfc=new JFileChooser();
			//设置窗口title
			jfc.setDialogTitle("打开文件");
			//打开窗口，同时返回对话框最终状态，确认或取消
			option=jfc.showOpenDialog(null);
			//显示窗口
			jfc.setVisible(true);
			//判断按键是确认，再做下步操作
			if(option==jfc.APPROVE_OPTION){
				//得到文件绝对路径
				String filepath=jfc.getSelectedFile().getAbsolutePath();
				FileReader fr=null;
				BufferedReader br=null;
				try {
					//先创建FileReader				
					fr=new FileReader(filepath);
					//新建BufferedReader
					br=new BufferedReader(fr);
					String s="";
					String str="";
					//循环读取数据,缓存流进行读取,返回每行读取的数据，赋值给s
					while(null!=(s=br.readLine())){
						str+=s+"\r\n";
					}
					//设置JTextArea文本显示
					this.jta.setText(str);
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						br.close();
						fr.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		}
		if(e.getActionCommand().equals("save")){
			//使用JFileChooser
			JFileChooser jfc=new JFileChooser();
			//jfc设置title
			jfc.setDialogTitle("另存为");
			//jfc保存窗口
			jfc.showSaveDialog(null);
			//设置窗口可见
			jfc.setVisible(true);
			if(option==jfc.APPROVE_OPTION){
				//获取文件绝对路径
				String filepath=jfc.getSelectedFile().getAbsolutePath();			
				FileWriter fw=null;
				BufferedWriter bw=null;			
				try {
					fw=new FileWriter(filepath);
					bw=new BufferedWriter(fw);
					String text=this.jta.getText();
					//把JTextArea中文本，按行存入数组
					String s[]=text.split("\n");
					//通过循环把每一行写入文件中
					for(int i=0;i<s.length;i++){
						bw.write(s[i]+"\r\n");
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						bw.close();
						fw.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		}
		if(e.getActionCommand().equals("exit")){
			//退出、关闭程序
			System.exit(0);
		}
			
		
	}

}
