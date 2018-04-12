/**
 * PluginDriver.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.driver;

import java.io.File;
import java.text.MessageFormat;

import com.hua.bean.eclipse.PluginInstall;
import com.hua.bean.eclipse.PluginParam;
import com.hua.constant.Constant;
import com.hua.util.EmptyUtil;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;

/**
 * PluginDriver
 * 描述: 插件 - 驱动器
 * @author  qye.zheng
 */
public final class PluginDriver
{
	private static final PluginParam param = PluginParam.getInstance();

	private static final String myPluginsPath = ProjectUtil.getAbsolutePath("/doc/" + 
			param.getMyPluginsName(), true);
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private PluginDriver()
	{
	}
	
	/**
	 * 
	 * 描述: 插件安装 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean pluginInstall()
	{
		System.out.println("开始安装插件...load");
		boolean flag = false;
		try
		{
			// 扫描 doc下指定的目录
			final File myPluginsFile = new File(myPluginsPath);
			// 列出所有的插件目录名(同插件名)
			final String[] pluginNames = myPluginsFile.list();
			if (EmptyUtil.isEmpty(pluginNames))
			{
				System.out.println("目录: " + myPluginsPath + " 为空，无法执行安装!");
				
				return flag;
			} else
			{
				// 将 myPluginsPath 目录拷贝到 ide eclipse 主目录
				FileUtil.copy(myPluginsPath, param.getHomeDirectory());
				// 替换属性值
				final Object[] args = new Object[4];
				int index = 0;
				args[index] = param.getHomeDirectory();
				
				index++;
				args[index] =param.getDropinsDirectoryName();
				
				index = 3;
				args[index] = param.getMyPluginsName();
				
				index = 2;
				PluginInstall pluginInstall = null;
				String dropinFilePath = null;
				String dropinFileContent = null;
				for (String name : pluginNames)
				{
					pluginInstall = new PluginInstall();
					pluginInstall.setPluginName(name);
					args[index] = pluginInstall.getPluginName();
					// dropin 文件 路径拼接
					dropinFilePath = MessageFormat.format(param.getDropinsFilePath(), args);
					pluginInstall.setDropinFilePath(dropinFilePath);
					
					// dropin 文件内容
					dropinFileContent = MessageFormat.format(param.getDropinsFileContent(), args);
					pluginInstall.setDropinFileContent(dropinFileContent);
					
					// 输出到指定的文件
					FileUtil.writeString(pluginInstall.getDropinFilePath(), pluginInstall.getDropinFileContent());
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("插件安装完毕!");
		
		return flag;
	}

}
