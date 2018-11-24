/**
  * @filename Profile.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.eclipse;

 /**
 * @type Profile
 * @description 
 * @author qianye.zheng
 */
public class Profile
{
	
	public static void main(String[] args)
	{
		
	}

	/**
	 * 
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main2(String[] args)
	{
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
				{
					System.out.println("thread1");
				}
			}
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
				{
					System.out.println("thread2");
				}
			}
		});
		t2.start();
		
		System.out.println("MultipleThread.multiple()");
		
		try
		{	// 主线程休眠，避免程序提前结束
			Thread.sleep(1000 * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
