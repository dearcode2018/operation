/**
 * 描述: 清理 - 驱动器
 * CleanDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.driver;

import java.io.File;
import java.io.FileFilter;

import com.hua.bean.CleanerParam;
import com.hua.constant.Constant;
import com.hua.filter.CleanFilter;
import com.hua.filter.EmptyFilter;
import com.hua.util.ArrayUtil;
import com.hua.util.EmptyUtil;
import com.hua.util.FileUtil;
import com.hua.util.ReadProperties;
import com.hua.util.StringUtil;

/**
 * 描述: - 驱动器
 * 
 * @author qye.zheng CleanDriver
 */
public final class CleanDriver
{

	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 */
	private CleanDriver()
	{
	}

	/**
	 * 
	 * 描述: 通用清理
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean clean()
	{
		final CleanerParam param = CleanerParam.getInstance();

		return commonClean(param);
	}

	/**
	 * 
	 * 描述: 空文件/目录清理
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean emptyClean()
	{
		final CleanerParam param = CleanerParam.getInstance();
		boolean flag = false;
		try
		{
			final String[] drivers = param.getPossibleDrivers();
			File rootPath = null;
			File[] files = null;
			// 删除标志
			boolean deleteFlag = false;
			// 文件过滤器
			final FileFilter filter = new EmptyFilter();
			for (int i = 0; i < drivers.length; i++)
			{
				// 根路径 对象
				rootPath = new File(drivers[i] + Constant.COLON
						+ File.separator);
				// 列出符合条件过滤后的文件数组
				files = rootPath.listFiles(filter);
				// 执行清理(删除)
				if (!EmptyUtil.isEmpty(files))
				{
					for (File f : files)
					{
						deleteFlag = FileUtil.delete(f);
						if (deleteFlag)
						{
							System.out.println("删除目标: " + f.getAbsolutePath()
									+ " 成功!");
						} else
						{
							System.out.println("删除目标:  " + f.getAbsolutePath()
									+ " 失败!");
						}
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 风行电影 清理
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean funshionClean()
	{
		final String funshionBase = "C:/Documents and Settings/Administrator/funshion/base";
		FileUtil.deleteDirectory(funshionBase);
		final CleanerParam param = new CleanerParam();
		final ReadProperties props = CleanerParam.getProps();
		// 使用公用的
		param.setPossibleDrivers(CleanerParam.getInstance().getPossibleDrivers());
		// 设置专用参数
		param.setRelativePath(props
				.getProperty("fs.windows.funshion.relative.path"));
		// 文件/目录名称正则
		final String regexs = props
				.getProperty("fs.windows.funshion.name.regexs");
		// 拦截空设置
		if (!StringUtil.isEmpty(regexs))
		{
			final String[] nameRegexs = regexs.split(Constant.COMMA);
			ArrayUtil.trim(nameRegexs);
			param.setNameRegexs(nameRegexs);
		}
		boolean flag = false;
		try
		{
			final String[] drivers = param.getPossibleDrivers();
			File path = null;
			File[] files = null;
			File[] movieFiles = null;
			// 删除标志
			boolean deleteFlag = false;
			long successCount = 0L;
			// 文件过滤器
			final FileFilter filter = new CleanFilter(param);
			for (int i = 0; i < drivers.length; i++)
			{
				// 路径 对象
				path = new File(drivers[i] + Constant.COLON
						+ File.separator + param.getRelativePath());
				files = path.listFiles();
				// 执行清理(删除)
				if (!EmptyUtil.isEmpty(files))
				{
					for (File f : files)
					{
						if (f.isDirectory())
						{
							movieFiles = f.listFiles(filter);
							if (!EmptyUtil.isEmpty(movieFiles))
							{
								for (File movieFile : movieFiles)
								{
									deleteFlag = FileUtil.delete(movieFile);
									if (deleteFlag)
									{
										//System.out.println("删除目标: " + movieFile.getAbsolutePath() + " 成功!");
										successCount++;
									} else
									{
										System.out.println("删除目标:  " + movieFile.getAbsolutePath() + " 失败!");
									}
								}
								System.out.println("成功删除: " + successCount + " 个目标!");
							}
						}
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * 描述: 公共清理逻辑 供 公共 和 专用 调用
	 * 
	 * @author qye.zheng
	 * @return
	 */
	private static final boolean commonClean(final CleanerParam param)
	{
		boolean flag = false;
		try
		{
			final String[] drivers = param.getPossibleDrivers();
			File path = null;
			File[] files = null;
			// 删除标志
			boolean deleteFlag = false;
			// 文件过滤器
			final FileFilter filter = new CleanFilter(param);
			for (int i = 0; i < drivers.length; i++)
			{
				// 路径 对象
				path = new File(drivers[i] + Constant.COLON
						+ File.separator + param.getRelativePath());
				// 列出符合条件过滤后的文件数组
				files = path.listFiles(filter);
				// 执行清理(删除)
				if (!EmptyUtil.isEmpty(files))
				{
					for (File f : files)
					{
						//deleteFlag = FileUtil.delete(f);
						if (deleteFlag)
						{
							System.out.println("删除目标: " + f.getAbsolutePath()
									+ " 成功!");
						} else
						{
							System.out.println("删除目标:  " + f.getAbsolutePath()
									+ " 失败!");
						}
					}
				}
			}
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @description
	 * @return
	 * @author qye.zheng
	 */
	public static final boolean template()
	{
		boolean flag = false;
		try
		{

			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

}
