/*
 * 功能：坦克游戏
 * 1、画出坦克
 * 2、hero坦克上下左右移动
 * 3、hero发子弹
 * 4、敌人坦克，炸弹爆炸
 * 5、敌人坦克发子弹
 * 6、防止敌人坦克重叠运动，把碰撞判断写入到EnemyTank 类
 * 7、可以分关，做一个Panel，并且闪烁
 * 8、可以在暂停和继续#（懂用户电价暂停时，子弹的速度和坦克速度设为0，并让坦克方向保持不变）
 * 9、可以记录文件的成绩#（文件流、单写一个类记录玩家记录）
 * 10、Java如何操作声音文件#
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
	
	//定义变量
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
		//创建菜单以及菜单选项
		jmb=new JMenuBar();
		jm=new JMenu("游戏(G)");
		//设置快捷方式
		jm.setMnemonic('G');
		jmi=new JMenuItem("开始新游戏(N)");
		jmi2=new JMenuItem("退出游戏（E）");
		jmi3=new JMenuItem("存盘退出（Q）");
		jmi4=new JMenuItem("继续上一次游戏（C）");
		
		jmi.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('Q');
		jmi4.setMnemonic('C');
		
		
		//注册事件
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
			
			//删除旧的面板
			this.remove(mp1);
			
			this.add(mp);
			
			this.addKeyListener(mp);
			//创建子弹线程重绘
			Thread t=new Thread(mp);
			t.start();
			this.add(mp);		
			this.setVisible(true);
		}else if(e.getActionCommand().equals("exit")){
			//用户点击退出系统菜单
			//保存击毁敌人数量
			Recoder.keepRecording();
			System.exit(0);
			
		}else if(e.getActionCommand().equals("quit")){
			//用户点击退出系统菜单
			//存盘退出操作
			//保存击毁敌人数量和坐标
			Recoder.keepTankInfo();
			
			System.exit(0);
			
		}else if(e.getActionCommand().equals("continue")){
			//用户点击退出系统菜单
			//继续上一局游戏操作
			//导入敌人数量和坐标
			mp=new MyPaneles("continue");
			
			//删除旧的面板
			this.remove(mp1);
			
			this.add(mp);
			
			this.addKeyListener(mp);
			//创建子弹线程重绘
			Thread t=new Thread(mp);
			t.start();
			this.add(mp);		
			this.setVisible(true);
			
			
			
		}
		
	}

}
//提示作用
class MyStartPanel extends JPanel implements Runnable{
	int times=0;
	public void paint(Graphics g){
		super.paint(g);
		
		g.fillRect(0, 0, 400, 400);
		//提示信息
		if(times%2==0){
			g.setColor(Color.yellow);
			//开关信息字体
			Font myFont=new Font("宋体",Font.BOLD,30);
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
	//文件名
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
//节点
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

//记录保存信息
class Recoder{
	//记录每关多少敌人
	private static int enNum=20;
	//设置我自己的生命数
	private static int myLife=3;
	//设置我自己的生命数
	private static int score=0;
	//设置敌人坦克向量
	private static Vector<EnemyTank> et=new Vector<EnemyTank>();
	//设置节点Node向量
	private static Vector<Node> nodeV=new Vector<Node>();
	
	//文件流变量
	private static  FileWriter fw=null;
	private static  BufferedWriter bw=null;
	private static  FileReader fr=null;
	private static  BufferedReader br=null;
	
	//完成读取
	public static Vector<Node> getNodesAndEnNums(){
		try {
			fr=new FileReader("D:/myRecord.txt");
			br=new BufferedReader(fr);
			//先读取第一行
			String n="";
			n=br.readLine();
			score=Integer.parseInt(n);
			while((n=br.readLine())!=null){
				String [] tB=n.split(" ");
				//给Node对象赋值
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
	
	//保存击毁敌人数量和敌人坦克坐标，方向
	public static void keepTankInfo(){
		try {
			fw=new FileWriter("D:/myRecord.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(score+"\r\n");
			
			//保存当前或者的坦克的方向和坐标
			for(int i=0;i<et.size();i++){
				//取出第一个坦克
				EnemyTank e=et.get(i);
				
				if(e.isLive){
					//活着的就保持
					String info=e.x+" "+e.y+" "+e.direct;
					//写入
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
	
	
	//把玩家击毁敌人坦克数量从文件中读出，记录文件流操作
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
	
	
	//把玩家击毁敌人坦克数量保存到文件中，记录文件流操作
	public static void keepRecording(){
		
		//创建文件
		try {
			fw=new FileWriter("D:/myRecord.txt");
			bw=new BufferedWriter(fw);
			
			bw.write(score+"\r\n");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭文件
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


//我的面板
class MyPaneles extends JPanel implements KeyListener,Runnable{
	
	//定义一个我的坦克
	Hero hero=null;
	//定义敌人坦克组
	Vector<EnemyTank> et=new Vector<EnemyTank>();
	int enSize=4;
	//定义节点Node组
	Vector<Node> nodeV=null;
	//定义炸弹组
	Vector<Bomb> vb=new Vector<Bomb>();
	
	
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	//定义一个flag标志，标志是否是新开游戏
	public MyPaneles(String flag){
		//引入开始背景音乐
		AudioPlay ta=new AudioPlay("D:/111.wav");
		Thread threads=new Thread(ta);
		threads.start();
		
		//恢复记录
		Recoder.getRecording();
		Recoder.setEt(et);	
		
		
		
		hero=new Hero(100,100);
		if(flag.equals("newGame")){
			//初始化敌人坦克
			for(int i=0;i<enSize;i++){
				//创建一辆坦克
				EnemyTank etk=new EnemyTank((i+1)*65, 0);
				etk.setColor(1);
				etk.setDirect(2);
				//设置获得其他坦克
				etk.setEts(et);
				Thread thread=new Thread(etk);
				thread.start();
				//添加子弹
				Shot shot=new Shot(etk.x, etk.y, etk.direct);
				etk.vs.add(shot);
				Thread t=new Thread(shot);
				t.start();
				
				//加入坦克
				et.add(etk);				
			}
		}else{
			nodeV=Recoder.getNodesAndEnNums();
			System.out.println("continue play"+nodeV.size());
			//初始化敌人坦克
			for(int i=0;i<nodeV.size();i++){
				//创建一个节点
				Node node=nodeV.get(i);
				EnemyTank etk=new EnemyTank(node.x, node.y);
				etk.setColor(1);
				etk.setDirect(node.direct);
				//设置获得其他坦克
				etk.setEts(et);
				Thread thread=new Thread(etk);
				thread.start();
				//添加子弹
				Shot shot=new Shot(etk.x, etk.y, etk.direct);
				etk.vs.add(shot);
				Thread t=new Thread(shot);
				t.start();
				
				//加入坦克
				et.add(etk);				
			}
		}
		
		//初始化图片
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
	//showInfo,显示坦克信息
	public void showInfo(Graphics g){
		g.fillRect(0, 0, 450, 450);
		//画出坦克信息，显示坦克信息数目
		this.drawTank(80, 455, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recoder.getEnNum()+"", 110, 480);		
		this.drawTank(150, 455, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getMyLife()+"", 180, 480);
		//我的成绩
		g.setColor(Color.black);
		Font myFont=new Font("宋体",Font.BOLD,20);
		g.setFont(myFont);
		g.drawString("我的总成绩", 460, 80);
		this.drawTank(470, 100, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recoder.getScore()+"", 510, 120);
		
		
	}
	
	//重写paint
	public void paint(Graphics g){

		super.paint(g);
		//提示信息
		showInfo(g);
		if( hero.isLive){
			drawTank(hero.getX(), hero.getY(), g, hero.direct, 0);
			//从vs中取出每颗子弹
			for(int i=0;i<hero.vs.size();i++){
				Shot myShot=hero.vs.get(i);
				//画出每一个子弹
				if(myShot!=null&&myShot.isLive==true){
					g.draw3DRect(myShot.x, myShot.y, 2, 2,false);
				}
				if(myShot.isLive==false){
					//从vs中删除该子弹
					hero.vs.remove(myShot);
				}
			}
		}
		
		
		//炸弹效果绘画
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
					//减少生命值
					b.BombDown();
					//从向量Vector<Bomb>中去除生命死亡炸弹
					if(b.isLive==false){
						vb.remove(b);
					}
				}
		
		for(int i=0;i<et.size();i++){
			EnemyTank e=et.get(i);
			//检查每一辆敌人坦克是否活着
			if(e.isLive==true){
				this.drawTank(e.getX(), e.getY(), g, e.getDirect(), e.getColor());
				for(int j=0;j<e.vs.size();j++){
					Shot s=e.vs.get(j);
					//System.out.println("子弹size()="+e.vs.size());
					if(s.isLive){
						g.draw3DRect(s.x, s.y, 2, 2, false);
											
					}else{
						e.vs.remove(s);
					}
					
					
					
				}
				
			}			
		}
		
		
	}
	
	//判断坦克是否被子弹击中
	public void hitTank(Shot s,Tank et){
		
		//判断坦克的方向
		switch(et.direct){
		//敌人坦克上下方向
		case 0:
		case 2:
			if(s.x>et.x&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30){
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//产生炸弹对象
				Bomb b=new Bomb(et.x,et.y);
				//加入到向量中
				vb.add(b);				
			
				
			}
			break;
		case 1:
		case 3:
			if(s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20){
				//击中
				//击中
				//子弹死亡
				s.isLive=false;
				//敌人坦克死亡
				et.isLive=false;
				//产生炸弹对象
				Bomb b=new Bomb(et.x,et.y);
				//加入到向量中
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
		//判断方向
		switch(direction){
		//up
		case 0:
			//g.setColor(Color.CYAN);
			//画出我的坦克，（到时候再封装成一个函数）
			//1、画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2、画出右面矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3、中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4、画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5、画线条
			g.drawLine(x+10, y-5, x+10, y+10);
			break;
		case 1:
			//System.out.println("->");
			//画出我的坦克，（到时候再封装成一个函数）
			//1、画出左边矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2、画出右面矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3、中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4、画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5、画线条
			g.drawLine(x+20, y+10, x+35, y+10);
			break;
		case 2:
			//g.setColor(Color.CYAN);
			//画出我的坦克，（到时候再封装成一个函数）
			//1、画出左边矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2、画出右面矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3、中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4、画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5、画线条
			g.drawLine(x+10, y+20, x+10, y+35);
			break;
		case 3:
			//画出我的坦克，（到时候再封装成一个函数）
			//1、画出左边矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2、画出右面矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3、中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4、画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5、画线条
			g.drawLine(x+10, y+10, x-5, y+10);
			break;
		}
		
		
	}
	//
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//按键按下W(左)、S（下）、A（上）、D（右）
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_W){
			//设置我的坦克方向
			//向上
			this.hero.setDirect(0);System.out.println("dddd");
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){
			//向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){
			//向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){
			//向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_J){
			//判断发射子弹个数小于5
			if(this.hero.vs.size()<=4){
				this.hero.shotEnemy();
			}
			
		}
		//重新绘制
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//击毁敌人坦克
	public void hitEnemyTank(){
		//不停检测子弹和敌人坦克是否碰撞，
		//以及相应子弹和敌人坦克消失处理
		for(int i=0;i<hero.vs.size();i++){
			//取出每一颗子弹			
			Shot s=hero.vs.get(i);
			//每一颗子弹匹配所有敌人坦克
			for(int j=0;j<et.size();j++){
				if(et.get(j).isLive==true){
					hitTank(s, et.get(j));
					
					if(et.get(j).isLive==false){
						//敌人数量减少一个
						Recoder.reduceEnNum();
						Recoder.increaseScore();
					}
					
				}
			}
			
		}
	}
	//击毁我的坦克
	public void hitMe(){
		//不停检测，子弹和Hero坦克是否碰撞
		//以及相应子弹和坦克消失处理
		for(int i=0;i<et.size();i++){
			EnemyTank etk=et.get(i);
			//取出每一个敌人坦克的子弹,并且匹配Hero坦克
			for(int j=0;j<etk.vs.size();j++){
				//取出子弹			
				Shot s=etk.vs.get(j);
				if(hero.isLive==true){
					hitTank(s, hero);
					if(hero.isLive==false){
						//自己生命值数量减少一个
						Recoder.reduceMyLife();
					}
				}
			}
		}
	}
	
	@Override
	public void run() {
		// 重绘子弹
		//每隔200毫秒
		while(true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//击毁敌人坦克
			hitEnemyTank();
			//击毁我方坦克
			hitMe();
			
			
			
			
			
			//重绘
			this.repaint();
		}
		
	}
	
}
//炸弹类
class Bomb{
	int x=0;
	int y=0;
	//生命值
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
		
	}
	//生命值减少
	public void BombDown(){
		if(this.life>0){
			this.life--;
		}else{
			this.isLive=false;
		}
		
	}
	
	
}

//子弹类
class Shot implements Runnable{
	int x=0;
	int y=0;
	int direct;
	int speed=10;
	//是否活着
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
			//向上
			case 0:
				y-=speed;
				break;
			//向右
			case 1:
				x+=speed;
				break;
			//向下
			case 2:
				y+=speed;
				break;
			//向左
			case 3:
				x-=speed;
				break;
			}
			//子弹死亡,判断子弹是否碰到边界
			if(x<0||x>400||y<0||y>400){
				this.isLive=false;
				break;
			}
			//System.out.println("子弹发射 坐标 x="+x+"  Y="+y);
		}
	}
}




//坦克类
class Tank{
	//生命状态
	boolean isLive=true;
	//坦克的横坐标
	int x=0;

	//坦克纵坐标
	int y=0;
	//坦克方向
	//0表示上，1表示右，2表示下，3表示左
	int direct=0;
	//坦克速度
	int speed=1;
	//颜色
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
//敌人坦克
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
		//向上
		case 0:
			//碰撞检测
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//相遇坦克向上或下
					if(et1.direct==0||et1.direct==2){
						if((this.x>et1.x&&this.x<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								((this.x+20)>et1.x&&(this.x+20)<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))){
							//随机改变方向
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//相遇坦克向右或左
					if(et1.direct==1||et1.direct==3){
						if((this.x>et1.x&&this.x<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								((this.x+20)>et1.x&&(this.x+20)<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))){
							//随机改变方向
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
				}
			}
			
			break;
		//向右
		case 1:
			//碰撞检测
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//相遇坦克向上或下
					if(et1.direct==0||et1.direct==2){
						if(((this.x+30)>et1.x&&(this.x+30)<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								((this.x+30)>et1.x&&(this.x+30)<(et1.x+20)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+30))){
							//随机改变方向
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//相遇坦克向右或左
					if(et1.direct==1||et1.direct==3){
						if(((this.x+30)>et1.x&&(this.x+30)<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								((this.x+30)>et1.x&&(this.x+30)<(et1.x+30)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+20))){
							//随机改变方向
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
				  }
				}
			break;
		//向下
		case 2:
			//碰撞检测
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
						//相遇坦克向上或下
						if(et1.direct==0||et1.direct==2){
							if((this.x>et1.x&&this.x<(et1.x+20)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+30))||
									((this.x+20)>et1.x&&(this.x+20)<(et1.x+20)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+30))){
								//随机改变方向
								//this.direct=(int)(Math.random()*4);
								return true;
							}
						}
						//相遇坦克向右或左
						if(et1.direct==1||et1.direct==3){
							if((this.x>et1.x&&this.x<(et1.x+30)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+20))||
									((this.x+20)>et1.x&&(this.x+20)<(et1.x+30)&&(this.y+30)>et1.y&&(this.y+30)<(et1.y+20))){
								//随机改变方向
								//this.direct=(int)(Math.random()*4);
								return true;
							}
						}
					}
				}
			break;
		//向左
		case 3:
			//碰撞检测
			for(int i=0;i<ets.size();i++){
				EnemyTank et1=ets.get(i);
				if(!et1.equals(this)){		
					//相遇坦克向上或下
					if(et1.direct==0||et1.direct==2){
						if((this.x>et1.x&&this.x<(et1.x+20)&&this.y>et1.y&&this.y<(et1.y+30))||
								(this.x>et1.x&&this.x<(et1.x+20)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+30))){
							//随机改变方向
							//this.direct=(int)(Math.random()*4);
							return true;
						}
					}
					//相遇坦克向右或左
					if(et1.direct==1||et1.direct==3){
						if((this.x>et1.x&&this.x<(et1.x+30)&&this.y>et1.y&&this.y<(et1.y+20))||
								(this.x>et1.x&&this.x<(et1.x+30)&&(this.y+20)>et1.y&&(this.y+20)<(et1.y+20))){
							//随机改变方向
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
			//向上
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
			//向右
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
			//向下
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
			//向左
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
			//随机改变方向
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
					//创建线程，为每一颗子弹
					Thread t=new Thread(shot);
					t.start();
				}
				
			}
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	
}




//我的坦克
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
		//创建线程
		Thread thread=new Thread(shot);
		thread.start();
		
		
	}
	
	//坦克向上移动
	public void moveUp(){
		this.y-=speed;
	}
	//坦克向右移动
	public void moveRight(){
		this.x+=speed;
	}
	//坦克向下移动
	public void moveDown(){
		this.y+=speed;
	}
	//坦克向左移动
	public void moveLeft(){
		this.x-=speed;
	}
	
}
