/*
 * 线程实现继承THread 并重写run()
 */
package com.bj.thread;

public class Thread_extends {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个线程
		Cat t=new Cat();
		//运行线程
		t.start();
		
	}

}

class Cat extends Thread{
	
	//重写run
	public void run(){
		int times=0;
		while(true){
			
			try {
				//休眠，单位毫秒,使线程进入到Blocked状态
				Thread.sleep(1000);
				System.out.println("Cat :"+times);
				times++;
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(times==10){
				break;
			}
			
		}
	}
}