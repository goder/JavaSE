/*
 * ���ܣ������߳�
 */
package com.bj.thread;

public class TestThread {

	public static void main(String[] args) {
				
		//ʵ��Runnable��ʽ
		Cats cat=new Cats();
		Thread t=new Thread(cat);
		t.start();//һ���̶߳���start����ֻ������һ��
		//t.start();//ͬһ���̶߳��󣬵ڶ��ε���start�������лᱨ��
		
		//�̳�Thread��ʽ
		Dogs dog=new Dogs();
		dog.start();//һ���̶߳���start����ֻ������һ��
		//dog.start();//ͬһ���̶߳��󣬵ڶ��ε���start�������лᱨ��
	}

}

class Cats implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Cats");
	}
	
}

class Dogs extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Dogs");
	}
	
}