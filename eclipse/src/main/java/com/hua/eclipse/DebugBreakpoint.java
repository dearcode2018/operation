/**
  * @filename DebugBreakpoint.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.eclipse;

import org.junit.Test;

/**
 * @type DebugBreakpoint
 * @description 
 * @author qianye.zheng
 */
@SuppressWarnings({"all"})
public class DebugBreakpoint
{
	
	/* 初始化时后变成非空 会自动触发断点  */
	//private String watchVar = "watch if access or modify";
	
	/* 初始化时为空 则不会自动触发断点  */
	private String watchVar;
	
	private String ab = ",";
	
	
	/**
	 * 
	 * @description 行断点/单步断点
	 * @author qianye.zheng
	 */
	@Test
	public void line()
	{
		/**	
		 * 在方法任意行双击设置断点，行断点/单步调试.
		 */
		String str = null;
		System.out.println("DebugBreakpoint.line()");
	}
	
	/**
	 * 
	 * @description 异常断点
	 * @author qianye.zheng
	 */
	@Test
	public void exception()
	{
		/*
		 * 首选项 -> Java -> Debug -> Suspend execution on uncaught exceptions 去掉勾选后
		 * 发生异常也不会断点，避免在关闭JVM进程例如关闭tomcat时出错而导致很多断点出现
		 */
		String str = null;
		// 发生空指针异常
		//str.length();
		// 在抛出异常没有捕获时就会触发断点
		throw new IllegalArgumentException("主动抛出异常");
	}

	/**
	 * 
	 * @description 运行次数断点
	 * @author qianye.zheng
	 */
	@Test
	public void runtimes()
	{
		/*
		 *定 hit count = 20，在即将执行20次时就会自动挂起
		 */
		for (int i = 0; i < 1000; i++)
		{
			System.out.println("time " + i);
		}
	}
	
	/**
	 * 
	 * @description 条件断点
	 * @author qianye.zheng
	 */
	@Test
	public void conditional()
	{
		String str = null;
		for (int i = 0; i < 1000; i++)
		{
			/*
			 * 再此处断点，断点条件 "xx200".equals(str)
			 */
			str = "xx" + i;
			System.out.println("time " + str);
		}
	}
	
	/**
	 * 
	 * @description 观察断点
	 * @author qianye.zheng
	 */
	@Test
	public void watch()
	{
		/*
		 * 在全局变量所在行双击设置断点，当该变量被访问或被修改时自动触发断点
		 * 不支持观察局部变量，局部变量可以使用条件断点
		 */
		System.out.println(watchVar);
		watchVar = "value change";
		System.out.println(watchVar);
	}
	
	/**
	 * 
	 * @description 方法断点
	 * @author qianye.zheng
	 */
	@Test
	public void method()
	{
		/**
		 * 在方法行双击设置断点，可以设置进入和离开时触发断点
		 */
		String str = null;
		call();
	}
	
	/**
	 * 
	 * @description 方法断点(被调用)
	 * @author qianye.zheng
	 */
	public void call()
	{
		/**
		 * 在方法行双击设置断点，可以设置进入和离开时触发断点
		 */
		// 进入之前
		System.out.println("DebugBreakpoint.call1()");
		
		// 离开之后
		System.out.println("DebugBreakpoint.call2()");
	}
	
	/**
	 * 
	 * @description 类加载断点
	 * @author qianye.zheng
	 */
	@Test
	public void classload()
	{
		String str = null;
		for (int i = 0; i < 1000; i++)
		{
			/*
			 * 再此处断点，断点条件 "xx200".equals(str)
			 */
			str = "xx" + i;
			System.out.println("time " + str);
		}
	}
	
	/**
	 * 
	 * 
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args) {
		
		System.out.println("DebugBreakpoint.main()");
		String str1 = "x";
		String str2 = "xx";
		String str = null;
		for (int i = 0; i < 1000; i++)
		{
			str = "xx" + i;
			System.out.println("time " + str);
		}
		System.out.println("dddd");
		System.out.println("ddd");
		System.out.println("AAA.main()");
	}
	
}
