/**
  * @filename ExcelImport.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.bean.grid;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import com.hua.constant.GridConstant;
import com.hua.util.StringUtil;

 /**
 * @type ExcelImport
 * @description Excel导入
 * @author qye.zheng
 */
public final class ExcelImport<T>
{
	private Class<T> clazz;
	
	/* 导入类型: xls(默认) / xlsx */
	private String type = GridConstant.EXCEL_XLS;
	
	/* 输入流 */
	private InputStream inputStream;
	
	/* 标题行 */
	private String[] headers;
	
	/* 动态设置: 导入的列 (反射方式获取值 setXx) */
	/* 必填项以(*)结尾，邮箱地址字段以(email)结尾... */
	private String[] columns;
	
	/* 输出流 (部分失败 输出结果文件) */
	private OutputStream outputStream;
	
	/* 可以执行导入的数据集 */
	private Collection<T> dataset = new ArrayList<T>();
	
	/* 全部导入_记录数 */
	private int total;
	
	/* 成功导入_记录数 */
	private int success;
	
	/* 失败导入_记录数 */
	private int failure;
	
	/* 导入结果消息 */
	private String message;
	
	/* 错误消息放置的列，默认是第一列 */
	private int errorIndex = 0;

	public static final String FAILURE_COLUMN = "失败原因";
	
	public ExcelImport()
	{
	}
	
	/**
	 * 
	 * @description 构造方法
	 * @param inputStream
	 * @param columns
	 * @param outputStream
	 * @author qye.zheng
	 */
	public ExcelImport(Class<T> clazz, InputStream inputStream, String[] columns,
			OutputStream outputStream)
	{
		super();
		this.clazz = clazz;
		this.inputStream = inputStream;
		this.columns = columns;
		this.outputStream = outputStream;
	}

	/**
	 * @return the type
	 */
	public final String getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the inputStream
	 */
	public final InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public final void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	/**
	 * @return the headers
	 */
	public final String[] getHeaders()
	{
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public final void setHeaders(String[] headers)
	{
		this.headers = headers;
	}

	/**
	 * @return the columns
	 */
	public final String[] getColumns()
	{
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public final void setColumns(String[] columns)
	{
		this.columns = columns;
	}

	/**
	 * @return the total
	 */
	public final int getTotal()
	{
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public final void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * @return the success
	 */
	public final int getSuccess()
	{
		return success;
	}

	/**
	 * 自增 ++1
	 */
	public final void success()
	{
		success++;
	}

	/**
	 * @return the failure
	 */
	public final int getFailure()
	{
		return failure;
	}

	/**
	 * 自增 ++1
	 */
	public final void failure()
	{
		failure++;
	}

	/**
	 * @return the message
	 */
	public final String getMessage()
	{
		if (StringUtil.isEmpty(message))
		{
			message = "总共 " + total + " 条，" + "成功导入 " + success 
					+ " 条，失败导入 " + failure + " 条!";
		}
		
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * @return the outputStream
	 */
	public final OutputStream getOutputStream()
	{
		return outputStream;
	}

	/**
	 * @param outputStream the outputStream to set
	 */
	public final void setOutputStream(OutputStream outputStream)
	{
		this.outputStream = outputStream;
	}

	/**
	 * @return the dataset
	 */
	public final Collection<T> getDataset()
	{
		return dataset;
	}

	/**
	 * 添加一条记录
	 */
	@SuppressWarnings({"unchecked"})
	public final void addData(Object obj)
	{
		dataset.add( (T) obj);
	}

	/**
	 * @return the clazz
	 */
	public final Class<T> getClazz()
	{
		return clazz;
	}

	/**
	 * @param clazz the clazz to set
	 */
	public final void setClazz(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	/**
	 * @return the errorIndex
	 */
	public final int getErrorIndex()
	{
		return errorIndex;
	}

	/**
	 * @param errorIndex the errorIndex to set
	 */
	public final void setErrorIndex(int errorIndex)
	{
		this.errorIndex = errorIndex;
	}
	
}
