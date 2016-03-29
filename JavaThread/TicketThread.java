/*
 * 功能：测试多线程，售票，线程安全问题，通过synchronized (Object)对象锁实现线程安全同步
 */
package com.bj.thread;

/*
 * 功能：模拟多个窗口取票，验证取票是否会出现线程安全问题
 */
import javax.swing.plaf.synth.SynthSpinnerUI;

public class TicketThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//定义三个窗口		
		TicketWindow tw1=new TicketWindow();
		//TicketWindow tw2=new TicketWindow();
		//TicketWindow tw3=new TicketWindow();
		
		Thread t1=new Thread(tw1);
		Thread t2=new Thread(tw1);
		Thread t3=new Thread(tw1);
		
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
	}

}

//售票窗口
class TicketWindow implements Runnable{
	//票数
	private int nums=2000;
	//private DogI di=new DogI();
	public void run(){
		while(true){
			//出票速度,1s一张			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//认为if else 要保证其原子性,加对象锁，保证锁内同步代码进行
			synchronized (this) {
				//先判断是否还有票
				if(nums>0){
					System.out.println(Thread.currentThread().getName()+"在售出第"+(nums)+"票");
					
					nums--;
				}else{
					//售票结束
					
					break;
				}
			}
			
			
		}
	}
	
}


class DogI{
	public DogI(){
		System.out.println("Dog");
	}
}





