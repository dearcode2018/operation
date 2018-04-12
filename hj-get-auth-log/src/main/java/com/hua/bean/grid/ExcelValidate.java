/**
  * @filename ExcelValidate.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.bean.grid;

 /**
 * @type ExcelValidate
 * @description 
 * @author qye.zheng
 */
public enum ExcelValidate
{
	/* 非空 必填 */
	MUST("(*)"),
	
	/* 邮箱地址 */
	EMAIL("(email)", "\\w+@\\.\\w+"),
	
	/* 手机 */
	PHONE("(phone)", ""),
	
	/* 可继续扩展 */
	;
	
	private String name;
	
	private String value;
	
	/**
	 * 
	 * @description 构造方法
	 * @param name
	 * @author qye.zheng
	 */
	private ExcelValidate(final String name)
	{
		this.name = name;
	}
	
	/**
	 * 
	 * @description 构造方法
	 * @param name
	 * @param value
	 * @author qye.zheng
	 */
	private ExcelValidate(final String name, final String value)
	{
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * @return the value
	 */
	public final String getValue()
	{
		return value;
	}

}
