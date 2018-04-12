/**
 * 描述: 
 * PluginParam.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.eclipse;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.hua.util.ReadProperties;

/**
 * 描述: 插件 - 配置参数
 * @author  qye.zheng
 * PluginParam
 */
public final class PluginParam extends EclipseParam
{

	/* 放置插件的目录名 */
	private String myPluginsName;
	
	/* dropins 目录 */
	private String dropinsDirectoryName;
	
	/* dropins 文件路径 */
	private String dropinsFilePath;
	
	/* dropins 文件内容 */
	private String dropinsFileContent;
	
	/* 公用单例 */
	private static PluginParam instance;
	
	private static final String PLUGIN_CONFIG_PATH = "/conf/properties/plugin.properties";
	
	private static final ReadProperties pluginProps = new ReadProperties(PLUGIN_CONFIG_PATH);
	
	static
	{
		instance = new PluginParam();
		
		instance.setDropinsDirectoryName(pluginProps.getProperty("ide.eclipse.plugin.dropins.directory.name"));
		instance.setMyPluginsName(pluginProps.getProperty("ide.eclipse.plugin.myPlugins.name"));
		instance.setDropinsFilePath(pluginProps.getProperty("ide.eclipse.plugin.dropins.file.path"));
		instance.setDropinsFileContent(pluginProps.getProperty("ide.eclipse.plugin.dropins.file.content"));
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private PluginParam()
	{
	}

	/**
	 * @return the myPluginsName
	 */
	public final String getMyPluginsName()
	{
		return myPluginsName;
	}

	/**
	 * @param myPluginsName the myPluginsName to set
	 */
	private final void setMyPluginsName(String myPluginsName)
	{
		this.myPluginsName = myPluginsName;
	}

	/**
	 * @return the dropinsDirectory
	 */
	public final String getDropinsDirectoryName()
	{
		return dropinsDirectoryName;
	}

	/**
	 * @param dropinsDirectory the dropinsDirectory to set
	 */
	private final void setDropinsDirectoryName(String dropinsDirectoryName)
	{
		this.dropinsDirectoryName = dropinsDirectoryName;
	}

	/**
	 * @return the dropinsFilePath
	 */
	public final String getDropinsFilePath()
	{
		return dropinsFilePath;
	}

	/**
	 * @param dropinsFilePath the dropinsFilePath to set
	 */
	private final void setDropinsFilePath(String dropinsFileName)
	{
		this.dropinsFilePath = dropinsFileName;
	}

	/**
	 * @return the dropinsFileContent
	 */
	public final String getDropinsFileContent()
	{
		return dropinsFileContent;
	}

	/**
	 * @param dropinsFileContent the dropinsFileContent to set
	 */
	private final void setDropinsFileContent(String dropinsFileContent)
	{
		this.dropinsFileContent = dropinsFileContent;
	}

	/**
	 * @return the instance
	 */
	public static final PluginParam getInstance()
	{
		return instance;
	}

}
