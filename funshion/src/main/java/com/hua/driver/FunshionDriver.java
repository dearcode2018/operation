/**
 * 描述: 
 * FunshionDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.driver;

import java.io.File;

import com.hua.bean.CleanerParam;
import com.hua.constant.Constant;
import com.hua.util.EmptyUtil;
import com.hua.util.FileNameUtil;
import com.hua.util.FileUtil;
import com.hua.util.FormatUtil;
import com.hua.util.FunshionUtil;
import com.hua.util.StringUtil;

/**
 * 描述: 风行电影 - 驱动器
 * 
 * @author qye.zheng FunshionDriver
 */
public class FunshionDriver
{

	/* 重命名总数 */
	private static int renameCount = 0;
	
	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 */
	private FunshionDriver()
	{
	}

	/**
	 * 
	 * 描述: 重命名
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean rename()
	{
		boolean flag = false;
		try
		{

			final CleanerParam param = CleanerParam.getInstance();
			final String[] drivers = param.getPossibleDrivers();
			File path = null;
			File[] movieDirs = null;
			for (int i = 0; i < drivers.length; i++)
			{
				// 路径 对象
				path = new File(drivers[i] + Constant.COLON + File.separator
						+ param.getRelativePath());
				// 电影目录 数组
				movieDirs = path.listFiles();
				if (!EmptyUtil.isEmpty(movieDirs))
				{
					// 单个电影目录
					File movieDir = null;
					for (int j = 0; j < movieDirs.length; j++)
					{
						movieDir = movieDirs[j];
						if (movieDir.isFile())
						{
							// 忽略掉文件
							continue;
						} else
						{
							// 判断是否下载成功
							if (FunshionUtil.downloadSuccess(movieDir))
							{
								rename(movieDir.listFiles());
							} else
							{
								System.out.println("电影文件下载没有完成，不执行重命名操作!");
							}
						}
					}
				}
			}
			System.out.println("重命名信息: \n总共对 " + renameCount + " 个对象执行重命名");
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @description 重命名
	 * @author qye.zheng
	 * @param movieFiles
	 */
	public static final void rename(final File[] movieFiles)
	{
		if (!EmptyUtil.isEmpty(movieFiles))
		{
			// 文件名 字符数组
			char[] nameChars = null;
			// 文件名 前缀
			String prefix = null;
			// 文件名 后缀
			String suffix = null;
			StringBuilder builder = null;
			// 数组下标 前移量
			int shiftForward = 0;
			// 位数最大长度 (000 - 999)
			final int maxLength = 3;
			// 是否需要重命名
			boolean needRename = false;
			// 第二个位置
			int second = 0;
			// 第三个位置
			int third = 0;
			String afterAddZero = null;
			for (File movieFile : movieFiles)
			{
				prefix = FileNameUtil.getPrefix(movieFile.getName());
				suffix = FileNameUtil.getSuffix(movieFile.getName());
				// 把文件前缀名转成 字符数组
				nameChars = prefix.toCharArray();
				// 单个文件名 添加器
				builder = StringUtil.getBuilder();
				// 遍历数组
				for (int z = 0; z < nameChars.length; z++)
				{
					if (StringUtil.isNumeric(nameChars[z] + Constant.WHITE_SPACE))
					{
						// 连续数字 首个数字出现
						second = z + 1;
						third = z + 2;
						// 判断第二个是否是数字
						if (second < nameChars.length && StringUtil.isNumeric(nameChars[second] + Constant.WHITE_SPACE))
						{
							// 第二个是数字
							// 判断第三个是否是数字
							if (third < nameChars.length && StringUtil.isNumeric(nameChars[third] + Constant.WHITE_SPACE))
							{
								// 第三个是数字，继续往下检测，直到检测的字符不为数字
								// 添加三个字符
								builder.append(nameChars[z]);
								builder.append(nameChars[second]);
								builder.append(nameChars[third]);
								// 如果是数字则继续往下检测
								third++;
								while (third < nameChars.length && StringUtil.isNumeric(nameChars[third] + Constant.WHITE_SPACE))
								{
									builder.append(nameChars[third]);
									third++;
								}
								// 
								// 前移量
								shiftForward = third - z - 1;
							} else
							{
								// 连续2位数字: 第三个不是数字，补0
								afterAddZero =  FormatUtil.addZero(Constant.WHITE_SPACE + nameChars[z] + nameChars[second], maxLength);
								// 注意 nameChars[z] + nameChars[second]  做了数值运算
								//afterAddZero =  FormatUtil.addZero(nameChars[z] + nameChars[second] + Constant.WHITE_SPACE, maxLength);
								builder.append(afterAddZero);
								// 标识为 需重命名
								needRename = true;
								// 前移1位
								shiftForward = 1;
							}
						} else
						{
							// 仅第一位是数字 直接 补0
							afterAddZero = FormatUtil.addZero(nameChars[z] + Constant.WHITE_SPACE, maxLength);
							builder.append(afterAddZero);
							// 标识为 需重命名
							needRename = true;
						}
					} else
					{
						// 不是数字，直接append
						builder.append(nameChars[z]);
					}
					// 移位
					z += shiftForward;
					// 清零
					shiftForward = 0;
				}
				if (needRename)
				{
					// 清除标志
					needRename = false;
					// 执行重命名
					FileUtil.rename(movieFile, builder.toString() + Constant.DOT_MARK + suffix);
					renameCount++;
					System.out.println("重命名: " + movieFile.getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 
	 * 描述:
	 * 
	 * @author qye.zheng
	 * @return
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
