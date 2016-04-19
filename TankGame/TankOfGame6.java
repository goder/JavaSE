/*
 * 功能：坦克游戏
 * 1、画出坦克
 * 2、hero坦克上下左右移动
 * 3、hero发子弹
 * 4、敌人坦克，炸弹爆炸
 * 5、敌人坦克发子弹
 * 
 */
package com.bj.game6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class TankOfGame6 extends JFrame {
	
	//定义变量
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame6 tank=new TankOfGame6();
	}
	
	public TankOfGame6(){
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
	//定义炸弹组
	Vector<Bomb> vb=new Vector<Bomb>();
	
	//定义三张图片
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	public MyPaneles(){
		hero=new Hero(100,100);

		//初始化敌人坦克
		for(int i=0;i<enSize;i++){
			//创建一辆坦克
			EnemyTank etk=new EnemyTank((i+1)*50, 0);
			etk.setColor(1);
			etk.setDirect(2);
			Thread thread=new Thread(etk);
			thread.start();
			
			Shot shot=new Shot(etk.x, etk.y, etk.direct);
			etk.vs.add(shot);
			Thread t=new Thread(shot);
			t.start();
			
			
			
			
			//加入坦克
			et.add(etk);
			
		}
		//初始化图片
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
	}
	//重写paint
	public void paint(Graphics g){

		super.paint(g);
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
					System.out.println("子弹size()="+e.vs.size());
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
			//不停检测子弹和敌人坦克是否碰撞，
			//以及相应子弹和敌人坦克消失处理
			for(int i=0;i<hero.vs.size();i++){
				//取出每一颗子弹			
				Shot s=hero.vs.get(i);
				//每一颗子弹匹配所有敌人坦克
				for(int j=0;j<et.size();j++){
					if(et.get(j).isLive==true){
						hitTank(s, et.get(j));
					}
				}
				
			}
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
					}
				}
			}
			
			
			
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
	Shot shot=null;
	int time=0;
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			switch(this.direct){
			//向上
			case 0:
			
				for(int i=0;i<30;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(y>0){
						y-=speed;
					}
					
				}
				
				break;
			//向右
			case 1:
				
				for(int i=0;i<30;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(x<400){
						x+=speed;
					}
					
				}
				
				break;
			//向下
			case 2:
				
				for(int i=0;i<30;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(y<400){
						y+=speed;
					}
					
				}
				
				break;
			//向左
			case 3:
				
				for(int i=0;i<30;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
