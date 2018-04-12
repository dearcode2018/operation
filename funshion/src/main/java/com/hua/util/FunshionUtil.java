/**
 * FunshionUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.io.File;

import com.hua.util.EmptyUtil;

/**
 * FunshionUtil
 * 描述: 风行电影 - 工具类
 * @author  qye.zheng
 */
public final class FunshionUtil
{

	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private FunshionUtil()
	{
	}

	/**
	 * 
	 * @description 是否下载成功
	 * 判断一部电视剧是否下载成功
	 * 条件:
	 * A. 不包含 *.dat 文件
	 * B. 不包含 *.fc! 文件
	 * @param directory
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean downloadSuccess(final File directory)
	{
		boolean flag = false;
		/** ===== begin 异常拦截段 ===== */
		if (directory.isFile())
		{
			return flag;
		}
		/** ===== end of 异常拦截段 ===== */
		final File[] files = directory.listFiles();
		if (!EmptyUtil.isEmpty(files))
		{
			final String suffixDat = ".dat";
			final String suffixFc = ".fc!";
			// 文件名
			String name = null;
			for (int i = 0; i < files.length; i++)
			{
				name = files[i].getName();
				if (name.endsWith(suffixDat) || name.endsWith(suffixFc))
				{
					return flag;
				}
			}
		}
		//
		flag = true;
		
		return flag;
	}
}
