/**
 * BatchBuildUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.util.List;

import com.hua.bean.SetVariable;
import com.hua.constant.BatchBuildConstant;
import com.hua.constant.Constant;
import com.hua.util.FileUtil;
import com.hua.util.StringUtil;

/**
 * BatchBuildUtil
 * 描述: 批处理项目构建 - 工具类
 * @author  qye.zheng
 */
public final class BatchBuildUtil
{

	
	
	/**
	 * 构造方法
	 * 描述: 私有 - 禁止实例化
	 * @author  qye.zheng
	 */
	private BatchBuildUtil()
	{
	}

	/**
	 * 
	 * @description  生成多个 set variableName=value 表达式
	 * @author qye.zheng
	 * @param filePath
	 * @param variables
	 * @return
	 */
	public static final boolean createSetVariable(final String filePath, final List<? extends SetVariable> variables)
	{
		boolean flag = false;
		final StringBuilder builder = StringUtil.getBuilder();
		try
		{
			// 获取文件数据
			String data = FileUtil.getString(filePath, Constant.CHART_SET_GB2312);
			// 遍历组装多个set表达式，一个表达式一行
			for (final SetVariable variable : variables)
			{
				builder.append(variable.getSetExpression() + Constant.LINE_BREAK);
			}
			
			// 执行替换
			data = data.replace(BatchBuildConstant.CLASS_PATH_REPLACE_SYMBOL, builder);
			// 输出到文件
			FileUtil.writeString(filePath, data, Constant.CHART_SET_GB2312);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
