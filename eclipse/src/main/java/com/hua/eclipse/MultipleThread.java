package com.hua.eclipse;

import org.junit.Test;

public class MultipleThread {

	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	@Test
	public void multiple()
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
