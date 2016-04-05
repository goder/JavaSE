/*
 * ���ܣ�̹����Ϸ
 * 1������̹��
 * 2��̹�����������ƶ�
 * 3�������ҷ�̹��
 */
package com.bj.game3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class TankOfGame3 extends JFrame {
	
	//�������
	MyPaneles mp=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankOfGame3 tank=new TankOfGame3();
	}
	
	public TankOfGame3(){
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
	//�������̹����
	Vector<EnemyTank> et=new Vector<EnemyTank>();
	int enSize=3;
	public MyPaneles(){
		hero=new Hero(100,100);

		//��ʼ������̹��
		for(int i=0;i<enSize;i++){
			//����һ��̹��
			EnemyTank etk=new EnemyTank((i+1)*50, 0);
			etk.setColor(1);
			etk.setDirect(2);
			
			//����̹��
			et.add(etk);
			
		}
	}
	//��дpaint
	public void paint(Graphics g){

		super.paint(g);
		
		drawTank(hero.getX(), hero.getY(), g, hero.direct, 0);
		
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
class EnemyTank extends Tank{

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
