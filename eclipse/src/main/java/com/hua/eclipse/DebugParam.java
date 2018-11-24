/**
  * @filename DebugParam.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.eclipse;

 /**
 * @type DebugParam
 * @description 
 * @author qianye.zheng
 */
public final class DebugParam
{

	/**
	 * @description 
	 * @param args
	 * @author qianye.zheng
	 */
	public static void main(String[] args)
	{
		/**
		 * Program arguments: 直接填写main方法的参数值，多个用空格隔开
		 * VM arguments: -Xms128M -Xmx256M -Xmn64M
		 */
		args[0] = "xxx";
		String str1 = "x";
		String str2 = "xx";
		String str = null;
		for (int i = 0; i < 1000; i++)
		{
			str = "xx" + i;
			System.out.println("time " + str);
		}
		System.out.println("dddd");
		System.out.println(args[0]);
		System.out.println("ddd");
		System.out.println("AAA.main()");
	}

}
