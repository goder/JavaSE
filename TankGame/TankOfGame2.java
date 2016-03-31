/*
 * ���ܣ�̹����Ϸ
 * 1������̹��
 * 2��̹�����������ƶ�
 */
package com.bj.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankOfGame2 extends JFrame {
	
	//�������
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame2 tank=new TankOfGame2();
	}
	
	public TankOfGame2(){
		mp=new MyPaneles();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//�ҵ����
class MyPaneles extends JPanel implements KeyListener{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	
	public MyPaneles(){
		hero=new Hero(100,100);
//		System.out.println("Tank");
	}
	//��дpaint
	public void paint(Graphics g){

		super.paint(g);
		
		drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
		
		
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
			g.drawLine(x+10, y+5, x+10, y-2);
			break;
		case 1:
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
			g.drawLine(x+10, y+5, x+10, y-2);
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
			g.drawLine(x+10, y+5, x+10, y-2);
			break;
		case 3:
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
			g.drawLine(x+10, y+5, x+10, y-2);
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
		//���»���
		this.repaint();
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}



//̹����
class Tank{
	//̹�˵ĺ�����
	int x=0;

	//̹��������
	int y=0;
	//̹�˷���
	//0��ʾ�ϣ�1��ʾ�ң�2��ʾ�£�3��ʾ��
	int direct=0;
	//̹���ٶ�
	int speed=1;
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

//�ҵ�̹��
class Hero extends Tank{
	
	
	
	public Hero(int x,int y){
		super(x, y);
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
