/*
 * ���ܣ�ģ��һ���򵥵ļ��±������û�滭���ַ�������ʵ��
 * 1���˵���JMenuBar->JMenu->JMenuItem
 *          �˵�������㣩->�˵��������ڲ˵����У�->�˵�������ڲ˵��У�
 * 2���򵥼��±����ԣ����ļ��������ļ�������ļ��˳�
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
	//�ļ�·��	
	String dir=null;
	//�ı�״̬
	boolean state=false;
	//�����ı���
	JTextArea jta=null;
	//����˵���
	JMenuBar jmb=null;
	//����˵�
	JMenu jm=null;
	//����˵���,��
	JMenuItem jmi=null;
	//����˵������
	JMenuItem jmi1=null;
	//����˵�����Ϊ
	JMenuItem jmi2=null;
	//����˵���˳�
	JMenuItem jmi3=null;
	//JScollPane���
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//�ж��Ƿ����ļ�·�������Ƿ���˫�����ļ����ļ�������Ϣ���룩
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
		jm=new JMenu("�ļ�");
		//jmi=new JMenuItem("��", new ImageIcon("images/notepad3.jpg"));//��ͼ��˵���
		jmi=new JMenuItem("��");
		jmi1=new JMenuItem("����");
		jmi2=new JMenuItem("���Ϊ");
		jmi3=new JMenuItem("�˳�");
		
		//ע����¼�
		jmi.addActionListener(this);
		jmi.setActionCommand("open");
		//ע�ᱣ���¼�
		jmi1.addActionListener(this);
		jmi1.setActionCommand("save");
		//ע�����Ϊ�¼�
		jmi2.addActionListener(this);
		jmi2.setActionCommand("saveAs");
		//ע���˳��¼�
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");
		//ע�ᴰ���¼�
		this.addWindowListener(this);
		//ע���ı����¼�������Ƿ����ı��ı�
		jta.addKeyListener(this);;
		
		
	
		
		//��jm����jmb��
		jmb.add(jm);
		//��jmi����jm��
		jm.add(jmi);
		jm.add(jmi1);
		jm.add(jmi2);
		jm.add(jmi3);
		//��jsp����
		this.add(jsp);
		//����jmb
		
		this.setJMenuBar(jmb);		
		this.setSize(800,800);
		this.setLocation(100,100);
		this.setTitle("���±�");
		this.setIconImage(new ImageIcon("images/notepad3.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	//���ļ�
	public void open(){
		//�Ի���״̬
		int option=-1;
		//JFileChooser�ļ�ѡ����
		JFileChooser jfc=new JFileChooser();
		//���ô���title
		jfc.setDialogTitle("���ļ�");
		//�򿪴��ڣ�ͬʱ���ضԻ�������״̬��ȷ�ϻ�ȡ��
		option=jfc.showOpenDialog(null);
		//��ʾ����
		jfc.setVisible(true);
		//�жϰ�����ȷ�ϣ������²�����
		if(option==jfc.APPROVE_OPTION){
			//�õ��ļ�����·��
			String filepath=jfc.getSelectedFile().getAbsolutePath();
			//���ļ�·����ֵ
			this.dir=filepath;
			System.out.println("dir:"+this.dir);
			FileReader fr=null;
			BufferedReader br=null;
			try {
				//�ȴ���FileReader				
				fr=new FileReader(filepath);
				//�½�BufferedReader
				br=new BufferedReader(fr);
				String s="";
				String str="";
				//ѭ����ȡ����,���������ж�ȡ,����ÿ�ж�ȡ�����ݣ���ֵ��s
				while(null!=(s=br.readLine())){
					str+=s+"\n";
				}
				//����JTextArea�ı���ʾ
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
	//����
	public void save(){		
		if(this.dir==null){			
			saveAs();
		}else{
			//�õ��ļ�����·��
			String filepath=this.dir;			
			//���ļ�·����ֵ
			this.dir=filepath;
			FileWriter fw=null;
			BufferedWriter bw=null;			
			try {
				fw=new FileWriter(filepath);
				bw=new BufferedWriter(fw);
				String text=this.jta.getText();
				//��JTextArea���ı������д�������
				String s[]=text.split("\n");
				//ͨ��ѭ����ÿһ��д���ļ���
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
		//�����ı�״̬��ʶΪfalse
		this.state=false;
		
	}
	
	//���Ϊ
	public void saveAs(){
		//�Ի���״̬
		int option=-1;
		//ʹ��JFileChooser
		JFileChooser jfc=new JFileChooser();
		//jfc����title
		jfc.setDialogTitle("���Ϊ");
		//jfc���洰��
		option=jfc.showSaveDialog(null);
		//���ô��ڿɼ�
		jfc.setVisible(true);			
		if(option==jfc.APPROVE_OPTION){				
			//��ȡ�ļ�����·��
			String filepath=jfc.getSelectedFile().getAbsolutePath();
			//���ļ�·����ֵ
			this.dir=filepath;
			FileWriter fw=null;
			BufferedWriter bw=null;			
			try {
				fw=new FileWriter(filepath);
				bw=new BufferedWriter(fw);
				String text=this.jta.getText();
				//��JTextArea���ı������д�������
				String s[]=text.split("\n");
				//ͨ��ѭ����ÿһ��д���ļ���
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
		//���ļ�
		if(e.getActionCommand().equals("open")){			
			open();			
		}
		//�ļ�����
		if(e.getActionCommand().equals("save")){
			save();
		}
		//�ļ����Ϊ
		if(e.getActionCommand().equals("saveAs")){
			saveAs();
		}
		//�˳�
		if(e.getActionCommand().equals("exit")){
			//�˳����رճ���
			System.exit(0);
		}
			
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		if(dir!=null){
			//jta.setText("hello:"+tmp);
			//�õ��ļ�����·��
			String filepath=dir;
			FileReader fr=null;
			BufferedReader br=null;
			try {
				//�ȴ���FileReader				
				fr=new FileReader(filepath);
				//�½�BufferedReader
				br=new BufferedReader(fr);
				String s="";
				String str="";
				//ѭ����ȡ����,���������ж�ȡ,����ÿ�ж�ȡ�����ݣ���ֵ��s
				while(null!=(s=br.readLine())){
					str+=s+"\r\n";
				}
				//����JTextArea�ı���ʾ
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
		//�ı�״̬�ı䣬��Ҫ��ʾ���Ƿ񱣴�
		if(this.state==true){
			// �Ի����״̬
			int option=-1;
			//ʹ���ڲ���Ϣ��ʾ��
			option=JOptionPane.showConfirmDialog(null, "�Ƿ񱣴��˳�", "��ʾ��", JOptionPane.YES_NO_OPTION); 
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
	//����Ƿ��ı��仯
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		this.state=true;
	}

}
