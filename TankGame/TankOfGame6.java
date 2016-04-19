/*
 * ���ܣ�̹����Ϸ
 * 1������̹��
 * 2��hero̹�����������ƶ�
 * 3��hero���ӵ�
 * 4������̹�ˣ�ը����ը
 * 5������̹�˷��ӵ�
 * 
 */
package com.bj.game6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class TankOfGame6 extends JFrame {
	
	//�������
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame6 tank=new TankOfGame6();
	}
	
	public TankOfGame6(){
		mp=new MyPaneles();
		this.add(mp);
		this.addKeyListener(mp);
		//�����ӵ��߳��ػ�
		Thread t=new Thread(mp);
		t.start();
		
		
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//�ҵ����
class MyPaneles extends JPanel implements KeyListener,Runnable{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	//�������̹����
	Vector<EnemyTank> et=new Vector<EnemyTank>();
	int enSize=3;
	//����ը����
	Vector<Bomb> vb=new Vector<Bomb>();
	
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	public MyPaneles(){
		hero=new Hero(100,100);

		//��ʼ������̹��
		for(int i=0;i<enSize;i++){
			//����һ��̹��
			EnemyTank etk=new EnemyTank((i+1)*50, 0);
			etk.setColor(1);
			etk.setDirect(2);
			Thread thread=new Thread(etk);
			thread.start();
			
			Shot shot=new Shot(etk.x, etk.y, etk.direct);
			etk.vs.add(shot);
			Thread t=new Thread(shot);
			t.start();
			
			
			
			
			//����̹��
			et.add(etk);
			
		}
		//��ʼ��ͼƬ
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		
	}
	//��дpaint
	public void paint(Graphics g){

		super.paint(g);
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
					System.out.println("�ӵ�size()="+e.vs.size());
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
			this.hero.setDirect(0);
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
			//��ͣ����ӵ��͵���̹���Ƿ���ײ��
			//�Լ���Ӧ�ӵ��͵���̹����ʧ����
			for(int i=0;i<hero.vs.size();i++){
				//ȡ��ÿһ���ӵ�			
				Shot s=hero.vs.get(i);
				//ÿһ���ӵ�ƥ�����е���̹��
				for(int j=0;j<et.size();j++){
					if(et.get(j).isLive==true){
						hitTank(s, et.get(j));
					}
				}
				
			}
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
					}
				}
			}
			
			
			
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
			//����
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
			//����
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
			//����
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
			//����
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
