/*
 * �߳�ʵ�ּ̳�THread ����дrun()
 */
package com.bj.thread;

public class Thread_extends {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ���߳�
		Cat t=new Cat();
		//�����߳�
		t.start();
		
	}

}

class Cat extends Thread{
	
	//��дrun
	public void run(){
		int times=0;
		while(true){
			
			try {
				//���ߣ���λ����,ʹ�߳̽��뵽Blocked״̬
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