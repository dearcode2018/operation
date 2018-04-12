/**
 * 描述: 
 * PluginInstall.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.bean.eclipse;

/**
 * 描述: 插件安装
 * @author  qye.zheng
 * PluginInstall
 */
public final class PluginInstall
{
	/* 插件名称 */
	private String pluginName;
	
	/* dropin 文件路径 */
	private String dropinFilePath;
	
	/* dropin 文件内容 */
	private String dropinFileContent;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	public PluginInstall()
	{
	}

	/**
	 * @return the pluginName
	 */
	public final String getPluginName()
	{
		return pluginName;
	}

	/**
	 * @param pluginName the pluginName to set
	 */
	public final void setPluginName(String pluginName)
	{
		this.pluginName = pluginName;
	}

	/**
	 * @return the dropinFilePath
	 */
	public final String getDropinFilePath()
	{
		return dropinFilePath;
	}

	/**
	 * @param dropinFilePath the dropinFilePath to set
	 */
	public final void setDropinFilePath(String dropinFilePath)
	{
		this.dropinFilePath = dropinFilePath;
	}

	/**
	 * @return the dropinFileContent
	 */
	public final String getDropinFileContent()
	{
		return dropinFileContent;
	}

	/**
	 * @param dropinFileContent the dropinFileContent to set
	 */
	public final void setDropinFileContent(String dropinFileContent)
	{
		this.dropinFileContent = dropinFileContent;
	}
}
