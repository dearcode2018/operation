/**
  * @filename EmptyFilter.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.filter;

import java.io.File;
import java.io.FileFilter;

import com.hua.constant.Constant;
import com.hua.util.FileUtil;

 /**
 * @type EmptyFilter
 * @description  空文件/目录(大小为空)过滤器
 * @author qye.zheng
 */
public final class EmptyFilter implements FileFilter
{

	/**
	 * @description 
	 * @param file
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public boolean accept(File file)
	{
		// 空文件/目录
		final long empty = 0L;
		/**
		 * 由于根据目录大小为0来进行过滤，效率非常低
		 * 涉及目录过深；因此 检查文件大小为0且目录为空(没有子目录)
		 */
		if ((file.isFile() && empty == file.length()) 
				|| (file.isDirectory() 
						&& (null == file.list() || Constant.ZERO == file.list().length)))
		{
			return true;
		}
		
		return false;
	}

}
