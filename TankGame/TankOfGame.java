/*
 * 功能：
 * 1、画出一个坦克
 */
package com.bj;

import javax.swing.*;
import java.awt.*;

public class TankOfGame extends JFrame {
	
	//定义变量
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
//我的面板
class MyPaneles extends JPanel{
	
	//定义一个我的坦克
	Hero hero=null;
	
	public MyPaneles(){
		hero=new Hero(10,10);
//		System.out.println("Tank");
	}
	//重写paint
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
			g.drawLine(x+10, y+5, x+10, y-2);
			break;
		}
		
		
	}
	
}



//坦克类
class Tank{
	//坦克的横坐标
	int x=0;

	//坦克纵坐标
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

//我的坦克
class Hero extends Tank{
	
	public Hero(int x,int y){
		super(x, y);
	}
	
	
}
