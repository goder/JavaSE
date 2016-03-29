/*
 * 功能：测试线程
 */
package com.bj.thread;

public class TestThread {

	public static void main(String[] args) {
				
		//实现Runnable方式
		Cats cat=new Cats();
		Thread t=new Thread(cat);
		t.start();//一个线程对象，start方法只能启动一次
		//t.start();//同一个线程对象，第二次调用start方法运行会报错
		
		//继承Thread方式
		Dogs dog=new Dogs();
		dog.start();//一个线程对象，start方法只能启动一次
		//dog.start();//同一个线程对象，第二次调用start方法运行会报错
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