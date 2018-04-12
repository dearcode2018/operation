/**
 * @filename BuildParam.java
 * @author  qye.zheng
 * 	@version 1.0
 */
package com.hua.bean;

import com.hua.constant.Constant;
import com.hua.util.ArrayUtil;
import com.hua.util.ReadProperties;
import com.hua.util.StringUtil;

/**
 * @type BuildParam
 * @description  
 * @author qye.zheng
 */
public final class BuildParam
{

	/* 项目名称 */
	private String projectName;
	
	private String[] targets;
	
	private static BuildParam instance;
	
	private static final String CONFIG_PATH = "/conf/properties/batch-build.properties";
	
	private static final ReadProperties props = new ReadProperties(CONFIG_PATH);
	
	static
	{
		instance = new BuildParam();
		// 项目名称
		instance.setProjectName(props.getProperty("batch.build.project.name"));
		final String targetsValue = props.getProperty("batch.build.copy.targets");
		// 拦截空设置
		if (!StringUtil.isEmpty(targetsValue))
		{
			final String[] targets = targetsValue.split(Constant.COMMA);
			ArrayUtil.trim(targets);
			instance.setTargets(targets);
		}
	}

	/**
	 * @return the projectName
	 */
	public final String getProjectName()
	{
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public final void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	/**
	 * @return the targets
	 */
	public final String[] getTargets()
	{
		return targets;
	}

	/**
	 * @param targets the targets to set
	 */
	public final void setTargets(String[] targets)
	{
		this.targets = targets;
	}

	/**
	 * @return the instance
	 */
	public static final BuildParam getInstance()
	{
		return instance;
	}
}
