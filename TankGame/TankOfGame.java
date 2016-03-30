/*
 * ���ܣ�
 * 1������һ��̹��
 */
package com.bj;

import javax.swing.*;
import java.awt.*;

public class TankOfGame extends JFrame {
	
	//�������
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame tank=new TankOfGame();
	}
	
	public TankOfGame(){
		mp=new MyPaneles();
		this.add(mp);
		this.setSize(400,400);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
//�ҵ����
class MyPaneles extends JPanel{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	
	public MyPaneles(){
		hero=new Hero(10,10);
//		System.out.println("Tank");
	}
	//��дpaint
	public void paint(Graphics g){

		super.paint(g);
		
		drawTank(hero.getX(), hero.getY(), g, 0, 1);
		
		
		
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
		}
		
		
	}
	
}



//̹����
class Tank{
	//̹�˵ĺ�����
	int x=0;

	//̹��������
	int y=0;
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
	
	
}
