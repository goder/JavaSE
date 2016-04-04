/*
 * ���ܣ�ģ��һ���򵥵ļ��±������û�滭���ַ�������ʵ��
 * 1���˵���JMenuBar->JMenu->JMenuItem
 *          �˵�������㣩->�˵��������ڲ˵����У�->�˵�������ڲ˵��У�
 */

package com.bj.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.event.*;

public class NotePad extends JFrame implements ActionListener{
	
	//�����ı���
	JTextArea jta=null;
	//����˵���
	JMenuBar jmb=null;
	//����˵�
	JMenu jm=null;
	//����˵���,��
	JMenuItem jmi=null;
	//����˵������
	JMenuItem jmi2=null;
	//����˵���˳�
	JMenuItem jmi3=null;
	//JScollPane���
	JScrollPane jsp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NotePad np=new NotePad();
	}
	public NotePad(){
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jmb=new JMenuBar();
		jm=new JMenu("�ļ�");
		//jmi=new JMenuItem("��", new ImageIcon("images/notepad3.jpg"));//��ͼ��˵���
		jmi=new JMenuItem("��");
		jmi2=new JMenuItem("����");
		jmi3=new JMenuItem("�˳�");
		
		//ע����¼�
		jmi.addActionListener(this);
		jmi.setActionCommand("open");
		//ע�ᱣ���¼�
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//ע���˳��¼�
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit");
		
	
		
		//��jm����jmb��
		jmb.add(jm);
		//��jmi����jm��
		jm.add(jmi);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int option=-1;
		if(e.getActionCommand().equals("open")){
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
		if(e.getActionCommand().equals("save")){
			//ʹ��JFileChooser
			JFileChooser jfc=new JFileChooser();
			//jfc����title
			jfc.setDialogTitle("���Ϊ");
			//jfc���洰��
			jfc.showSaveDialog(null);
			//���ô��ڿɼ�
			jfc.setVisible(true);
			if(option==jfc.APPROVE_OPTION){
				//��ȡ�ļ�����·��
				String filepath=jfc.getSelectedFile().getAbsolutePath();			
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
			//�˳����رճ���
			System.exit(0);
		}
			
		
	}

}
