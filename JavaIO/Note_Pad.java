/*
 * 功能：模拟一个简单的记事本，利用绘绘画、字符流进行实现
 * 1、菜单：JMenuBar->JMenu->JMenuItem
 *          菜单条（最顶层）->菜单（包含在菜单条中）->菜单项（包含在菜单中）
 * 2、简单记事本可以：打开文件，保存文件，另存文件退出
 */

package com.bj.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

import javax.swing.event.*;

public class Note_Pad extends JFrame implements ActionListener,WindowListener,KeyListener{
	//文件路径	
	String dir=null;
	//文本状态
	boolean state=false;
	//定义文本域
	JTextArea jta=null;
	//定义菜单条
	JMenuBar jmb=null;
	//定义菜单
	JMenu jm=null;
	//定义菜单项,打开
	JMenuItem jmi=null;
	//定义菜单项，保存
	JMenuItem jmi1=null;
	//定义菜单项，另存为
	JMenuItem jmi2=null;
	//定义菜单项，退出
	JMenuItem jmi3=null;
	//JScollPane组件
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//判断是否传入文件路径（即是否有双击打开文件的文件关联信息传入）
		if(args.length>0){		
			Note_Pad np=new Note_Pad(args[0]);
		}else{
			Note_Pad np=new Note_Pad(null);
		}
	}
	public Note_Pad(String dir){
		this.dir=dir;
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jmb=new JMenuBar();
		jm=new JMenu("文件");
		//jmi=new JMenuItem("打开", new ImageIcon("images/notepad3.jpg"));//带图标菜单项
		jmi=new JMenuItem("打开");
		jmi1=new JMenuItem("保存");
		jmi2=new JMenuItem("另存为");
		jmi3=new JMenuItem("退出");
		
		//注册打开事件
		jmi.addActionListener(this);
		jmi.setActionCommand("open");
		//注册保存事件
		jmi1.addActionListener(this);
		jmi1.setActionCommand("save");
		//注册另存为事件
		jmi2.addActionListener(this);
		jmi2.setActionCommand("saveAs");
		//注册退出事件
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");
		//注册窗口事件
		this.addWindowListener(this);
		//注册文本域事件，监测是否有文本改变
		jta.addKeyListener(this);;
		
		
	
		
		//把jm加入jmb中
		jmb.add(jm);
		//把jmi加入jm中
		jm.add(jmi);
		jm.add(jmi1);
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
	//打开文件
	public void open(){
		//对话框状态
		int option=-1;
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
			//给文件路径赋值
			this.dir=filepath;
			System.out.println("dir:"+this.dir);
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
					str+=s+"\n";
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
	//保存
	public void save(){		
		if(this.dir==null){			
			saveAs();
		}else{
			//得到文件绝对路径
			String filepath=this.dir;			
			//给文件路径赋值
			this.dir=filepath;
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
					bw.write(s[i]+"\n");
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
		//重置文本状态标识为false
		this.state=false;
		
	}
	
	//另存为
	public void saveAs(){
		//对话框状态
		int option=-1;
		//使用JFileChooser
		JFileChooser jfc=new JFileChooser();
		//jfc设置title
		jfc.setDialogTitle("另存为");
		//jfc保存窗口
		option=jfc.showSaveDialog(null);
		//设置窗口可见
		jfc.setVisible(true);			
		if(option==jfc.APPROVE_OPTION){				
			//获取文件绝对路径
			String filepath=jfc.getSelectedFile().getAbsolutePath();
			//给文件路径赋值
			this.dir=filepath;
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
					bw.write(s[i]+"\n");
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int option=-1;
		//打开文件
		if(e.getActionCommand().equals("open")){			
			open();			
		}
		//文件保存
		if(e.getActionCommand().equals("save")){
			save();
		}
		//文件另存为
		if(e.getActionCommand().equals("saveAs")){
			saveAs();
		}
		//退出
		if(e.getActionCommand().equals("exit")){
			//退出、关闭程序
			System.exit(0);
		}
			
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		if(dir!=null){
			//jta.setText("hello:"+tmp);
			//得到文件绝对路径
			String filepath=dir;
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
	@Override
	public void windowClosing(WindowEvent e) {		
		//文本状态改变，需要提示，是否保存
		if(this.state==true){
			// 对话框的状态
			int option=-1;
			//使用内部信息提示框
			option=JOptionPane.showConfirmDialog(null, "是否保存退出", "提示！", JOptionPane.YES_NO_OPTION); 
			if(option==JOptionPane.YES_OPTION){
				save();
			}
		}
	
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("window closed");
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {		
		
	}
	//监测是否文本变化
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.state=true;
	}

}
