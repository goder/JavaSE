/*
 * ���ܣ�̹����Ϸ
 * 1������̹��
 * 2��hero̹�����������ƶ�
 * 3��hero���ӵ�
 * 4������̹�ˣ�ը����ը
 * 5������̹�˷��ӵ�
 * 6����ֹ����̹���ص��˶�������ײ�ж�д�뵽EnemyTank ��
 * 7�����Էֹأ���һ��Panel��������˸
 * 8����������ͣ�ͼ���#�����û������ͣʱ���ӵ����ٶȺ�̹���ٶ���Ϊ0������̹�˷��򱣳ֲ��䣩
 * 9�����Լ�¼�ļ��ĳɼ�#���ļ�������дһ�����¼��Ҽ�¼��
 * 10��Java��β��������ļ�#
 * 
 */
package com.bj.game8;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class TankOfGame8 extends JFrame implements ActionListener{
	
	//�������
	MyPaneles mp=null;
	MyStartPanel mp1=null;
	JMenuBar jmb=null;
	JMenu jm=null;
	JMenuItem jmi=null;
	JMenuItem jmi2=null;
	JMenuItem jmi3=null;
	JMenuItem jmi4=null;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame8 tank=new TankOfGame8();
	}
	
	public TankOfGame8(){
		//�����˵��Լ��˵�ѡ��
		jmb=new JMenuBar();
		jm=new JMenu("��Ϸ(G)");
		//���ÿ�ݷ�ʽ
		jm.setMnemonic('G');
		jmi=new JMenuItem("��ʼ����Ϸ(N)");
		jmi2=new JMenuItem("�˳���Ϸ��E��");
		jmi3=new JMenuItem("�����˳���Q��");
		jmi4=new JMenuItem("������һ����Ϸ��C��");
		
		jmi.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('Q');
		jmi4.setMnemonic('C');
		
		
		//ע���¼�
		jmi.addActionListener(this);
		jmi.setActionCommand("Start");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("quit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("continue");
		
		jm.add(jmi);
		jm.add(jmi2);
		jm.add(jmi3);
		jm.add(jmi4);
		jmb.add(jm);
		
		
		mp1=new MyStartPanel();
		Thread tt=new Thread(mp1);
		tt.start();
		this.setJMenuBar(jmb);
		this.add(mp1);		
		this.setSize(600,550);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Start")){
			mp=new MyPaneles("newGame");
			
			//ɾ���ɵ����
			this.remove(mp1);
			
			this.add(mp);
			
			this.addKeyListener(mp);
			//�����ӵ��߳��ػ�
			Thread t=new Thread(mp);
			t.start();
			this.add(mp);		
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//�û�����˳�ϵͳ�˵�
			//������ٵ�������
			Recoder.keepRecording();
			System.exit(0);
			
		}else if(e.getActionCommand().equals("quit")){
			//�û�����˳�ϵͳ�˵�
			//�����˳�����
			//������ٵ�������������
			Recoder.keepTankInfo();
			
			System.exit(0);
			
		}else if(e.getActionCommand().equals("continue")){
			//�û�����˳�ϵͳ�˵�
			//������һ����Ϸ����
			//�����������������
			mp=new MyPaneles("continue");
			
			//ɾ���ɵ����
			this.remove(mp1);
			
			this.add(mp);
			
			this.addKeyListener(mp);
			//�����ӵ��߳��ػ�
			Thread t=new Thread(mp);
			t.start();
			this.add(mp);		
			this.setVisible(true);
			
			
			
		}
		
	}

}
//��ʾ����
class MyStartPanel extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics g){
		super.paint(g);
		
		g.fillRect(0, 0, 400, 400);
		//��ʾ��Ϣ
		if(times%2==0){
			g.setColor(Color.yellow);
			//������Ϣ����
			Font myFont=new Font("����",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("Stage:1", 150, 150);
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(500);
				times++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
		
	}
	
}
class AudioPlay implements Runnable{
	//�ļ���
	String filename=null;
	
	public  AudioPlay(String filename){
		this.filename=filename;	
		
		
	}

	@Override
	public void run() {
		if(filename!=null){
			File soundFile=new File(filename);
			
			AudioInputStream ais=null;
			try {
				ais=AudioSystem.getAudioInputStream(soundFile);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			AudioFormat format=ais.getFormat();
			SourceDataLine auline=null;
			DataLine.Info info=new DataLine.Info(SourceDataLine.class, format);
			
			try {
				auline=(SourceDataLine)AudioSystem.getLine(info);
				auline.open(format);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			auline.start();
			int nByteRead=0;
			byte[] abData=new byte[1024];		
			
				try {
					while(nByteRead!=-1){
						nByteRead=ais.read(abData, 0, abData.length);
						if(nByteRead>=0){
							auline.write(abData, 0, nByteRead);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					auline.drain();
					auline.close();
				}
			}
		
		}		
	}
//�ڵ�
class Node{
	int x;
	int y;
	int direct;
	public Node(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}

//��¼������Ϣ
class Recoder{
	//��¼ÿ�ض��ٵ���
	private static int enNum=20;
	//�������Լ���������
	private static int myLife=3;
	//�������Լ���������
	private static int score=0;
	//���õ���̹������
	private static Vector<EnemyTank> et=new Vector<EnemyTank>();
	//���ýڵ�Node����
	private static Vector<Node> nodeV=new Vector<Node>();
	
	//�ļ�������
	private static  FileWriter fw=null;
	private static  BufferedWriter bw=null;
	private static  FileReader fr=null;
	private static  BufferedReader br=null;
	
	//��ɶ�ȡ
	public static Vector<Node> getNodesAndEnNums(){
		try {
			fr=new FileReader("D:/myRecord.txt");
			br=new BufferedReader(fr);
			//�ȶ�ȡ��һ��
			String n="";
			n=br.readLine();
			score=Integer.parseInt(n);
			while((n=br.readLine())!=null){
				String [] tB=n.split(" ");
				//��Node����ֵ
				Node node=new Node(Integer.parseInt(tB[0]), Integer.parseInt(tB[1]), Integer.parseInt(tB[2]));
				nodeV.addElement(node);
				System.out.println("x:"+Integer.parseInt(tB[0]));
			}		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nodeV;
	}
	
	//������ٵ��������͵���̹�����꣬����
	public static void keepTankInfo(){
		try {
			fw=new FileWriter("D:/myRecord.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(score+"\r\n");
			
			//���浱ǰ���ߵ�̹�˵ķ��������
			for(int i=0;i<et.size();i++){
				//ȡ����һ��̹��
				EnemyTank e=et.get(i);
				
				if(e.isLive){
					//���ŵľͱ���
					String info=e.x+" "+e.y+" "+e.direct;
					//д��
					bw.write(info+"\r\n");
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	//����һ��ٵ���̹���������ļ��ж�������¼�ļ�������
		public static void getRecording(){
			try {
				fr=new FileReader("D:/myRecord.txt");
				br=new BufferedReader(fr);
				String n=br.readLine();
				score=Integer.parseInt(n);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	
	
	//����һ��ٵ���̹���������浽�ļ��У���¼�ļ�������
	public static void keepRecording(){
		
		//�����ļ�
		try {
			fw=new FileWriter("D:/myRecord.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(score+"\r\n");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر��ļ�
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		Recoder.score = score;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recoder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recoder.myLife = myLife;
	}
	public static void reduceMyLife() {
		myLife--;
	}
	public static void reduceEnNum() {
		enNum--;
	}
	public static void increaseScore() {
		score++;
	}
	public static Vector<EnemyTank> getEt() {
		return et;
	}


	public static void setEt(Vector<EnemyTank> et) {
		Recoder.et = et;
	}
	public static Vector<Node> getNodeV() {
		return nodeV;
	}

	public static void setNodeV(Vector<Node> nodeV) {
		Recoder.nodeV =nodeV ;
	}


	
}


//�ҵ����
class MyPaneles extends JPanel implements KeyListener,Runnable{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	//�������̹����
	Vector<EnemyTank> et=new Vector<EnemyTank>();
	int enSize=4;
	//����ڵ�Node��
	Vector<Node> nodeV=null;
	//����ը����
	Vector<Bomb> vb=new Vector<Bomb>();
	
	
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	//����һ��flag��־����־�Ƿ����¿���Ϸ
	public MyPaneles(String flag){
		//���뿪ʼ��������
		AudioPlay ta=new AudioPlay("D:/111.wav");
		Thread threads=new Thread(ta);
		threads.start();
		
		//�ָ���¼
		Recoder.getRecording();
		Recoder.setEt(et);	
		
		
		
		hero=new Hero(100,100);
		if(flag.equals("newGame")){
			//��ʼ������̹��
			for(int i=0;i<enSize;i++){
				//����һ��̹��
				EnemyTank etk=new EnemyTank((i+1)*65, 0);
				etk.setColor(1);
				etk.setDirect(2);
				//���û������̹��
				etk.setEts(et);
				Thread thread=new Thread(etk);
				thread.start();
				//����ӵ�
				Shot shot=new Shot(etk.x, etk.y, etk.direct);
				etk.vs.add(shot);
				Thread t=new Thread(shot);
				t.start();
				
				//����̹��
				et.add(etk);				
			}
		}else{
			nodeV=Recoder.getNodesAndEnNums();
			System.out.println("continue play"+nodeV.size());
			//��ʼ������̹��
			for(int i=0;i<nodeV.size();i++){
				//����һ���ڵ�
				Node node=nodeV.get(i);
				EnemyTank etk=new EnemyTank(node.x, node.y);
				etk.setColor(1);
				etk.setDirect(node.direct);
				//���û������̹��
				etk.setEts(et);
				Thread thread=new Thread(etk);
				thread.start();
				//����ӵ�
				Shot shot=new Shot(etk.x, etk.y, etk.direct);
				etk.vs.add(shot);
				Thread t=new Thread(shot);
				t.start();
				
				//����̹��
				et.add(etk);				
			}
		}
		
		//��ʼ��ͼƬ
		try {
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		//image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
	}
	//showInfo,��ʾ̹����Ϣ
	public void showInfo(Graphics g){
		g.fillRect(0, 0, 450, 450);
		//����̹����Ϣ����ʾ̹����Ϣ��Ŀ
		this.drawTank(80, 455, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recoder.getEnNum()+"", 110, 480);		
		this.drawTank(150, 455, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getMyLife()+"", 180, 480);
		//�ҵĳɼ�
		g.setColor(Color.black);
		Font myFont=new Font("����",Font.BOLD,20);
		g.setFont(myFont);
		g.drawString("�ҵ��ܳɼ�", 460, 80);
		this.drawTank(470, 100, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getScore()+"", 510, 120);
		
		
	}
	
	//��дpaint
	public void paint(Graphics g){

		super.paint(g);
		//��ʾ��Ϣ
		showInfo(g);
		if( hero.isLive){
			drawTank(hero.getX(), hero.getY(), g, hero.direct, 0);
			//��vs��ȡ��ÿ���ӵ�
			for(int i=0;i<hero.vs.size();i++){
				Shot myShot=hero.vs.get(i);
				//����ÿһ���ӵ�
				if(myShot!=null&&myShot.isLive==true){
					g.draw3DRect(myShot.x, myShot.y, 2, 2,false);
				}
				if(myShot.isLive==false){
					//��vs��ɾ�����ӵ�
					hero.vs.remove(myShot);
				}
			}
		}
		
		
		//ը��Ч���滭
				for(int i=0;i<vb.size();i++){
					System.out.println("Bomb size"+vb.size());
					Bomb b=vb.get(i);
					if(b.life>6){				
						//g.drawImage(image1, b.x, b.y,30,30,this);
						g.drawImage(image1, b.x, b.y,30,30,this);
						
					}else if(b.life>3){
						
						g.drawImage(image2, b.x, b.y,30,30,this);
					}else{
						
						g.drawImage(image3, b.x, b.y,30,30,this);
					}
					//��������ֵ
					b.BombDown();
					//������Vector<Bomb>��ȥ����������ը��
					if(b.isLive==false){
						vb.remove(b);
					}
				}
		
		for(int i=0;i<et.size();i++){
			EnemyTank e=et.get(i);
			//���ÿһ������̹���Ƿ����
			if(e.isLive==true){
				this.drawTank(e.getX(), e.getY(), g, e.getDirect(), e.getColor());
				for(int j=0;j<e.vs.size();j++){
					Shot s=e.vs.get(j);
					//System.out.println("�ӵ�size()="+e.vs.size());
					if(s.isLive){
						g.draw3DRect(s.x, s.y, 2, 2, false);
											
					}else{
						e.vs.remove(s);
					}
					
					
					
				}
				
			}			
		}
		
		
	}
	
	//�ж�̹���Ƿ��ӵ�����
	public void hitTank(Shot s,Tank et){
		
		//�ж�̹�˵ķ���
		switch(et.direct){
		//����̹�����·���
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30){
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				//����ը������
				Bomb b=new Bomb(et.x,et.y);
				//���뵽������
				vb.add(b);				
			
				
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20){
				//����
				//����
				//�ӵ�����
				s.isLive=false;
				//����̹������
				et.isLive=false;
				//����ը������
				Bomb b=new Bomb(et.x,et.y);
				//���뵽������
				vb.add(b);
				
			}
			break;
		}
		
	}
	
	public void drawTank(int x,int y,Graphics g,int direction,int type){
		//
		switch(type){
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
			default:
				
				break;
		}
		//�жϷ���
		switch(direction){
		//up
		case 0:
			//g.setColor(Color.CYAN);
			//�����ҵ�̹�ˣ�����ʱ���ٷ�װ��һ��������
			//1��������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//2�������������
			g.fill3DRect(x+15, y, 5, 30,false);
			//3���м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4������Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5��������
			g.drawLine(x+10, y-5, x+10, y+10);
			break;
		case 1:
			//System.out.println("->");
			//�����ҵ�̹�ˣ�����ʱ���ٷ�װ��һ��������
			//1��������߾���
			g.fill3DRect(x, y, 30, 5,false);
			//2�������������
			g.fill3DRect(x, y+15, 30, 5,false);
			//3���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4������Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5��������
			g.drawLine(x+20, y+10, x+35, y+10);
			break;
		case 2:
			//g.setColor(Color.CYAN);
			//�����ҵ�̹�ˣ�����ʱ���ٷ�װ��һ��������
			//1��������߾���
			g.fill3DRect(x, y, 5, 30,false);
			//2�������������
			g.fill3DRect(x+15, y, 5, 30,false);
			//3���м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4������Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5��������
			g.drawLine(x+10, y+20, x+10, y+35);
			break;
		case 3:
			//�����ҵ�̹�ˣ�����ʱ���ٷ�װ��һ��������
			//1��������߾���
			g.fill3DRect(x, y, 30, 5,false);
			//2�������������
			g.fill3DRect(x, y+15, 30, 5,false);
			//3���м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4������Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5��������
			g.drawLine(x+10, y+10, x-5, y+10);
			break;
		}
		
		
	}
	//
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//��������W(��)��S���£���A���ϣ���D���ң�
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W){
			//�����ҵ�̹�˷���
			//����
			this.hero.setDirect(0);System.out.println("dddd");
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			//����
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			//����
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			//����
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_J){
			//�жϷ����ӵ�����С��5
			if(this.hero.vs.size()<=4){
				this.hero.shotEnemy();
			}
			
		}
		//���»���
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//���ٵ���̹��
	public void hitEnemyTank(){
		//��ͣ����ӵ��͵���̹���Ƿ���ײ��
		//�Լ���Ӧ�ӵ��͵���̹����ʧ����
		for(int i=0;i<hero.vs.size();i++){
			//ȡ��ÿһ���ӵ�			
			Shot s=hero.vs.get(i);
			//ÿһ���ӵ�ƥ�����е���̹��
			for(int j=0;j<et.size();j++){
				if(et.get(j).isLive==true){
					hitTank(s, et.get(j));
					
					if(et.get(j).isLive==false){
						//������������һ��
						Recoder.reduceEnNum();
						Recoder.increaseScore();
					}
					
				}
			}
			
		}
	}
	//�����ҵ�̹��
	public void hitMe(){
		//��ͣ��⣬�ӵ���Hero̹���Ƿ���ײ
		//�Լ���Ӧ�ӵ���̹����ʧ����
		for(int i=0;i<et.size();i++){
			EnemyTank etk=et.get(i);
			//ȡ��ÿһ������̹�˵��ӵ�,����ƥ��Hero̹��
			for(int j=0;j<etk.vs.size();j++){
				//ȡ���ӵ�			
				Shot s=etk.vs.get(j);
				if(hero.isLive==true){
					hitTank(s, hero);
					if(hero.isLive==false){
						//�Լ�����ֵ��������һ��
						Recoder.reduceMyLife();
					}
				}
			}
		}
	}
	
	@Override
	public void run() {
		// �ػ��ӵ�
		//ÿ��200����
		while(true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���ٵ���̹��
			hitEnemyTank();
			//�����ҷ�̹��
			hitMe();
			
			
			
			
			
			//�ػ�
			this.repaint();
		}
		
	}
	
}
//ը����
class Bomb{
	int x=0;
	int y=0;
	//����ֵ
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	//����ֵ����
	public void BombDown(){
		if(this.life>0){
			this.life--;
		}else{
			this.isLive=false;
		}
		
	}
	
	
}

//�ӵ���
class Shot implements Runnable{
	int x=0;
	int y=0;
	int direct;
	int speed=10;
	//�Ƿ����
	boolean isLive=true;
	public Shot(int x,int y,int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct){
			//����
			case 0:
				y-=speed;
				break;
			//����
			case 1:
				x+=speed;
				break;
			//����
			case 2:
				y+=speed;
				break;
			//����
			case 3:
				x-=speed;
				break;
			}
			//�ӵ�����,�ж��ӵ��Ƿ������߽�
			if(x<0||x>400||y<0||y>400){
				this.isLive=false;
				break;
			}
			//System.out.println("�ӵ����� ���� x="+x+"  Y="+y);
		}
	}
}




//̹����
class Tank{
	//����״̬
	boolean isLive=true;
	//̹�˵ĺ�����
	int x=0;

	//̹��������
	int y=0;
	//̹�˷���
	//0��ʾ�ϣ�1��ʾ�ң�2��ʾ�£�3��ʾ��
	int direct=0;
	//̹���ٶ�
	int speed=1;
	//��ɫ
	int color=0;
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	
}
//����̹��
class EnemyTank extends Tank implements Runnable{
	
	//boolean isLive=true;
	Vector<Shot> vs=new Vector<Shot>();
	Vector<EnemyTank> ets=null;
	Shot shot=null;
	int time=0;
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void  setEts(Vector<EnemyTank> et){
		ets=et;
	}
	public boolean crash(){
		
		switch(this.direct){
		//����
		case 0:
			//��ײ���
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//����̹�����ϻ���
					if(et1.direct==0||et1.direct==2){
						if((this.x>et1.x&&this.x<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								((this.x+20)>et1.x&&(this.x+20)<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//����̹�����һ���
					if(et1.direct==1||et1.direct==3){
						if((this.x>et1.x&&this.x<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								((this.x+20)>et1.x&&(this.x+20)<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
				}
			}
			
			break;
		//����
		case 1:
			//��ײ���
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//����̹�����ϻ���
					if(et1.direct==0||et1.direct==2){
						if(((this.x+30)>et1.x&&(this.x+30)<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								((this.x+30)>et1.x&&(this.x+30)<(et1.x+20)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+30))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//����̹�����һ���
					if(et1.direct==1||et1.direct==3){
						if(((this.x+30)>et1.x&&(this.x+30)<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								((this.x+30)>et1.x&&(this.x+30)<(et1.x+30)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+20))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
				  }
				}
			break;
		//����
		case 2:
			//��ײ���
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
						//����̹�����ϻ���
						if(et1.direct==0||et1.direct==2){
							if((this.x>et1.x&&this.x<(et1.x+20)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+30))||
									((this.x+20)>et1.x&&(this.x+20)<(et1.x+20)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+30))){
								//����ı䷽��
								//this.direct=(int)(Math.random()*4);
								return true;
							}
						}
						//����̹�����һ���
						if(et1.direct==1||et1.direct==3){
							if((this.x>et1.x&&this.x<(et1.x+30)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+20))||
									((this.x+20)>et1.x&&(this.x+20)<(et1.x+30)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+20))){
								//����ı䷽��
								//this.direct=(int)(Math.random()*4);
								return true;
							}
						}
					}
				}
			break;
		//����
		case 3:
			//��ײ���
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//����̹�����ϻ���
					if(et1.direct==0||et1.direct==2){
						if((this.x>et1.x&&this.x<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								(this.x>et1.x&&this.x<(et1.x+20)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+30))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//����̹�����һ���
					if(et1.direct==1||et1.direct==3){
						if((this.x>et1.x&&this.x<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								(this.x>et1.x&&this.x<(et1.x+30)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+20))){
							//����ı䷽��
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
				}
			}
			break;
		
		

}
return false;
}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			switch(this.direct){
			//����
			case 0:
			
				for(int i=0;i<60;i++){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(crash()){
						break;
					}
					if(y>0){
						y-=speed;
					}
					
					
				}
				
				break;
			//����
			case 1:
				
				for(int i=0;i<60;i++){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(crash()){
						break;
					}
					if(x<400){
						x+=speed;
					}
					
				}
				
				break;
			//����
			case 2:
				
				for(int i=0;i<60;i++){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(crash()){
						break;
					}
					if(y<400){
						y+=speed;
					}
					
				}
				
				break;
			//����
			case 3:
				
				for(int i=0;i<60;i++){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(crash()){
						break;
					}
					if(x>0){
						x-=speed;
					}
					
				}
				
				break;
				
				
			}
			//����ı䷽��
			this.direct=(int)(Math.random()*4);
			
			//time++
			time++;
			
			if(time%1==0){
				if(vs.size()<4){
					switch(this.direct){
					case 0:
						shot=new Shot(x+10, y,this.direct);
						vs.add(shot);
						break;
					case 1:
						shot=new Shot(x+30, y+10,this.direct);
						vs.add(shot);
						break;
					case 2:
						shot=new Shot(x+10, y+30,this.direct);
						vs.add(shot);
						break;
					case 3:
						shot=new Shot(x, y+10,this.direct);
						vs.add(shot);
						break;
					}
					//�����̣߳�Ϊÿһ���ӵ�
					Thread t=new Thread(shot);
					t.start();
				}
				
			}
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	
}




//�ҵ�̹��
class Hero extends Tank{
	
	//Shot shot=null;
	//boolean isLive=true;
	Vector<Shot> vs=new Vector<Shot>();
	Shot shot=null;
	public Hero(int x,int y){
		super(x, y);
		
	}
	
	public void shotEnemy(){
		
		switch(this.direct){
		case 0:
			shot=new Shot(x+10, y,this.direct);
			vs.add(shot);
			break;
		case 1:
			shot=new Shot(x+30, y+10,this.direct);
			vs.add(shot);
			break;
		case 2:
			shot=new Shot(x+10, y+30,this.direct);
			vs.add(shot);
			break;
		case 3:
			shot=new Shot(x, y+10,this.direct);
			vs.add(shot);
			break;
		}
		//�����߳�
		Thread thread=new Thread(shot);
		thread.start();
		
		
	}
	
	//̹�������ƶ�
	public void moveUp(){
		this.y-=speed;
	}
	//̹�������ƶ�
	public void moveRight(){
		this.x+=speed;
	}
	//̹�������ƶ�
	public void moveDown(){
		this.y+=speed;
	}
	//̹�������ƶ�
	public void moveLeft(){
		this.x-=speed;
	}
	
}
