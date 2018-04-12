/**
  * @filename TestRun.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.test.funshion;

import org.junit.Test;

 /**
 * @type TestRun
 * @description  
 * @author qye.zheng
 */
public final class TestRun
{

	/*
	 * junit 4 采用的方式
	 * java org.junit.runner.JUnitCore a.b.TestRun
	 * junit 3 采用的方式
	 * java junit.textui.TestRunner a.b.TestRun
	 */
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public TestRun()
	{
	}
	
	@Test
	public void start()
	{
		System.out.println("TestRun.start()");
	}
	
	/**
	 * 
	 * @description 
	 * @author qye.zheng
	 */
	public static void staticMethod()
	{
		
	}
	
	/**
	 * 
	 * @description 
	 * @param args
	 * @author qye.zheng
	 */
	public static void main(String[] args)
	{
		System.out.println("TestRun.main()");
	}
}
