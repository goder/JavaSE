/*
 * ���ܣ����Զ��̣߳���Ʊ���̰߳�ȫ���⣬ͨ��synchronized (Object)������ʵ���̰߳�ȫͬ��
 */
package com.bj.thread;

/*
 * ���ܣ�ģ��������ȡƱ����֤ȡƱ�Ƿ������̰߳�ȫ����
 */
import javax.swing.plaf.synth.SynthSpinnerUI;

public class TicketThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������������		
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

//��Ʊ����
class TicketWindow implements Runnable{
	//Ʊ��
	private int nums=2000;
	//private DogI di=new DogI();
	public void run(){
		while(true){
			//��Ʊ�ٶ�,1sһ��			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��Ϊif else Ҫ��֤��ԭ����,�Ӷ���������֤����ͬ���������
			synchronized (this) {
				//���ж��Ƿ���Ʊ
				if(nums>0){
					System.out.println(Thread.currentThread().getName()+"���۳���"+(nums)+"Ʊ");
					
					nums--;
				}else{
					//��Ʊ����
					
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





