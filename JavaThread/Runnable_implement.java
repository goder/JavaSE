package com.bj.thread;

public class Runnable_implement{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ���߳�
		Dog d=new Dog();
		Thread t=new Thread(d);		
		//�����߳�
		t.start();
	}

	

}

class Dog implements Runnable {
	//��дrun
		public void run(){
			int times=0;
			while(true){
				
				try {
					//���ߣ���λ����,ʹ�߳̽��뵽Blocked״̬
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