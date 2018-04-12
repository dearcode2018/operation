/**
  * @filename ExcelExport.java
  * @description  
  * @version 1.0
  * @author qye.zheng
 */
package com.hua.bean.grid;

import java.io.OutputStream;
import java.util.Collection;

import com.hua.constant.FormatConstant;
import com.hua.constant.GridConstant;

 /**
 * @type ExcelExport
 * @description Excel 表格导出
 * @author qye.zheng
 */
public final class ExcelExport<T>
{
	/* 导出类型: xls(默认) / xlsx */
	private String type = GridConstant.EXCEL_XLS;
	
	/* 电子表格名称 (标题)，没有默认值会抛异常 */
	private String title = "Sheet1";
	
	/* 标题行 */
	private String[] headers;
	
	/**
	 * 注意，属性应该是规范的javabean
	 * boolean 类型 isXx(默认) 或者 getXx (人工修改)
	 * Boolean 类型 getXx
	 * 尝试顺序: getXx isXx hasXx
	 */
	/* 动态设置: 导出的列 (反射方式获取值 getXx isXx hasXx) */
	private String[] columns;
	
	/* 导出的记录 */
	private Collection<T> dataset;
	
	/* 输出流 */
	private OutputStream outputStream;

	/* 日期时间格式 */
	private String dateTimeFormat = FormatConstant.DATE_FORMAT_yyyy_MM_dd;
	
	/**
	 * @description 构造方法
	 * @author qye.zheng
	 */
	public ExcelExport()
	{
	}
	
	/**
	 * 
	 * @description 构造方法
	 * @param columns
	 * @param dataset
	 * @param outputStream
	 * @author qye.zheng
	 */
	public ExcelExport(String[] columns, Collection<T> dataset,
			OutputStream outputStream)
	{
		super();
		this.columns = columns;
		this.dataset = dataset;
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
	 * @return the title
	 */
	public final String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title)
	{
		this.title = title;
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
	 * @return the dataset
	 */
	public final Collection<T> getDataset()
	{
		return dataset;
	}

	/**
	 * @param dataset the dataset to set
	 */
	public final void setDataset(Collection<T> dataset)
	{
		this.dataset = dataset;
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
	 * @return the dateTimeFormat
	 */
	public final String getDateTimeFormat()
	{
		return dateTimeFormat;
	}

	/**
	 * @param dateTimeFormat the dateTimeFormat to set
	 */
	public final void setDateTimeFormat(String dateTimeFormat)
	{
		this.dateTimeFormat = dateTimeFormat;
	}

}
