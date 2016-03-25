package com.bj.thread;

public class Runnable_implement{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个线程
		Dog d=new Dog();
		Thread t=new Thread(d);		
		//运行线程
		t.start();
	}

	

}

class Dog implements Runnable {
	//重写run
		public void run(){
			int times=0;
			while(true){
				
				try {
					//休眠，单位毫秒,使线程进入到Blocked状态
					Thread.sleep(1000);
					System.out.println("Dog :"+times);
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