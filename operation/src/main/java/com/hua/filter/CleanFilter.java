/**
  * @filename CleanFilter.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.filter;

import java.io.File;
import java.io.FileFilter;

import com.hua.bean.CleanerParam;

 /**
 * @type CleanFilter
 * @description  文件清理 - 过滤器
 * @author qye.zheng
 */
public final class CleanFilter implements FileFilter
{

	private CleanerParam param;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public CleanFilter(final CleanerParam param)
	{
		this.param = param;
	}

	/**
	 * @description 
	 * @param file
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean accept(File file)
	{
		// 文件 或 目录 名称
		final String name = file.getName().toLowerCase();
		final String[] regexs = param.getNameRegexs();
		for (String regex : regexs)
		{
			if (name.endsWith(regex))
			{
				return true;
			}
		}
		
		return false;
	}

}
