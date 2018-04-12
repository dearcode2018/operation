/**
 * 描述: 
 * CleanerParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.ReadProperties;
import com.hua.util.StringUtil;

/**
 * 描述: 清理器 - 参数
 * @author  qye.zheng
 * CleanerParam
 */
public final class CleanerParam
{
	/* 可能存放的盘符 */
	private String[] possibleDrivers;
	
	/* 相对路径，不以/开头 */
	private String relativePath;
	
	/* 文件/目录名称正则 */
	private String[] nameRegexs;
	
	private static final String CONFIG_PATH = "/conf/properties/cleaner.properties";
	
	private static final ReadProperties props = new ReadProperties(CONFIG_PATH);

	/* 默认实例 */
	private static final CleanerParam instance;
	
	static
	{
		instance = new CleanerParam();
		// 可能存放的盘符
		final String drivers = props.getProperty("fs.windows.possible.drivers");
		// 拦截空设置
		if (!StringUtil.isEmpty(drivers))
		{
			final String[] possibleDrivers = drivers.split(Constant.COMMA);
			ArrayUtil.trim(possibleDrivers);
			instance.setPossibleDrivers(possibleDrivers);
		}
		instance.setRelativePath(props.getProperty("fs.windows.relative.path"));
		
		// 文件/目录名称正则
		final String regexs = props.getProperty("fs.windows.name.regexs");
		// 拦截空设置
		if (!StringUtil.isEmpty(regexs))
		{
			final String[] nameRegexs = regexs.split(Constant.COMMA);
			ArrayUtil.trim(nameRegexs);
			instance.setNameRegexs(nameRegexs);
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public CleanerParam()
	{
	}

	/**
	 * @return the possibleDrivers
	 */
	public final String[] getPossibleDrivers()
	{
		return possibleDrivers;
	}

	/**
	 * @param possibleDrivers the possibleDrivers to set
	 */
	public final void setPossibleDrivers(String[] possibleDrivers)
	{
		this.possibleDrivers = possibleDrivers;
	}

	/**
	 * @return the relativePath
	 */
	public final String getRelativePath()
	{
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public final void setRelativePath(String relativePath)
	{
		this.relativePath = relativePath;
	}

	/**
	 * @return the nameRegexs
	 */
	public final String[] getNameRegexs()
	{
		return nameRegexs;
	}

	/**
	 * @param nameRegexs the nameRegexs to set
	 */
	public final void setNameRegexs(String[] nameRegexs)
	{
		this.nameRegexs = nameRegexs;
	}

	/**
	 * @return the props
	 */
	public static final ReadProperties getProps()
	{
		return props;
	}

	/**
	 * @return the instance
	 */
	public static final CleanerParam getInstance()
	{
		return instance;
	}

}
