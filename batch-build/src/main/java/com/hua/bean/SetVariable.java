/**
 * @filename SetVariable.java
 * @author  qye.zheng
 * 	@version 1.0
 */
package com.hua.bean;

/**
 * @type SetVariable
 * @description  设置变量
 * @author qye.zheng
 */
public class SetVariable
{
	/**
	 * 
	 *windows OS set variableName=value
	 */
	
	/* 设置表达式 set variableName=value */
	private  String setExpression;
	
	
	/* 变量名 */
	private String variableName;
	
	
	/* 变量值 */
	private String value;

	/**
	 * @constructor
	 * @description  
	 * @author qye.zheng
	 * @param variableName
	 */
	public SetVariable(String variableName)
	{
		super();
		this.variableName = variableName;
	}

	/**
	 * @return the setExpression
	 */
	public final String getSetExpression()
	{
		final String osName = System.getenv("os");
		if (osName.toLowerCase().contains("win"))
		{
			// windows os
			setExpression =   "set " + variableName + "=" + "%" + variableName + "%;" + value;
		} else
		{
			setExpression =  "set " + variableName + "=" + "$" + variableName +":" + value;
		}
		
		return setExpression;
	}

	/**
	 * @return the variableName
	 */
	public final String getVariableName()
	{
		return variableName;
	}


	/**
	 * @param variableName the variableName to set
	 */
	public final void setVariableName(String variableName)
	{
		this.variableName = variableName;
	}


	/**
	 * @return the value
	 */
	public final String getValue()
	{
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public final void setValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * 
	 * @description  
	 * @author qye.zheng
	 * @return
	 */
	public final String getInitExpression()
	{
		final String osName = System.getenv("os");
		if (osName.contains("win"))
		{
			// windows os
			return getWindowsInitExpression();
		} else
		{
			return getLinuxInitExpression();
		}
	}
	
	/**
	 * 
	 * @description  
	 * @author qye.zheng
	 * @return
	 */
	public final String getWindowsInitExpression()
	{
		// 一般不直接覆盖之前的，而是在原来的基础上追加
		// windows OS set variableName=%variableName%
		return "set " + variableName + "=" + "%" + variableName + "%";
	}

	/**
	 * 
	 * @description  
	 * @author qye.zheng
	 * @return
	 */
	public final String getLinuxInitExpression()
	{
		// 一般不直接覆盖之前的，而是在原来的基础上追加
		// windows OS set variableName=%variableName%
		return "set " + variableName + "=" + "$" + variableName;
	}
}
