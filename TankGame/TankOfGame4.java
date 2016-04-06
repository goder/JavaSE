/*
 * 功能：坦克游戏
 * 1、画出坦克
 * 2、坦克上下左右移动
 * 3、发子弹
 */
package com.bj.game4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class TankOfGame4 extends JFrame {
	
	//定义变量
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame4 tank=new TankOfGame4();
	}
	
	public TankOfGame4(){
		mp=new MyPaneles();
		this.add(mp);
		this.addKeyListener(mp);
		//创建子弹线程重绘
		Thread t=new Thread(mp);
		t.start();
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//我的面板
class MyPaneles extends JPanel implements KeyListener,Runnable{
	
	//定义一个我的坦克
	Hero hero=null;
	//定义敌人坦克组
	Vector<EnemyTank> et=new Vector<EnemyTank>();
	int enSize=3;
	public MyPaneles(){
		hero=new Hero(100,100);

		//初始化敌人坦克
		for(int i=0;i<enSize;i++){
			//创建一辆坦克
			EnemyTank etk=new EnemyTank((i+1)*50, 0);
			etk.setColor(1);
			etk.setDirect(2);
			
			//加入坦克
			et.add(etk);
			
		}
		
		
		
	}
	//重写paint
	public void paint(Graphics g){

		super.paint(g);
		
		drawTank(hero.getX(), hero.getY(), g, hero.direct, 0);
		
		//画出子弹
		if(hero.shot!=null&&hero.shot.isLive==true){
			g.draw3DRect(hero.shot.x, hero.shot.y, 2, 2,false);
		}
		
		for(int i=0;i<et.size();i++){
			this.drawTank(et.get(i).getX(), et.get(i).getY(), g, et.get(i).getDirect(), et.get(i).getColor());
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
			this.hero.setDirect(0);
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
			this.hero.shotEnemy();
		}
		//重新绘制
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
			//重绘
			this.repaint();
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
			System.out.println("子弹发射 坐标 x="+x+"  Y="+y);
		}
	}
}




//坦克类
class Tank{
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
class EnemyTank extends Tank{

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	
}




//我的坦克
class Hero extends Tank{
	
	Shot shot=null;
	
	public Hero(int x,int y){
		super(x, y);
		
	}
	
	public void shotEnemy(){
		
		switch(this.direct){
		case 0:
			shot=new Shot(x+10, y,this.direct);
			
			break;
		case 1:
			shot=new Shot(x+30, y+10,this.direct);
			break;
		case 2:
			shot=new Shot(x+10, y+30,this.direct);
			break;
		case 3:
			shot=new Shot(x, y+10,this.direct);
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
