/**
 * 描述: 
 * EclipseParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.eclipse;

import com.hua.util.ReadProperties;

/**
 * 描述: eclipse 配置参数
 * @author  qye.zheng
 * EclipseParam
 */
public class EclipseParam
{

	/* eclipse ide 主目录 */
	private String homeDirectory;
	
	private static final String ECLIPSE_CONFIG_PATH = "/conf/properties/eclipse.properties";
	
	private static final ReadProperties eclipseProps = new ReadProperties(ECLIPSE_CONFIG_PATH);
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public EclipseParam()
	{
		homeDirectory = eclipseProps.getProperty("ide.eclipse.home.directory");
	}

	/**
	 * @return the homeDirectory
	 */
	public final String getHomeDirectory()
	{
		return homeDirectory;
	}

	/**
	 * @param homeDirectory the homeDirectory to set
	 */
	public final void setHomeDirectory(String homeDirectory)
	{
		this.homeDirectory = homeDirectory;
	}

}
