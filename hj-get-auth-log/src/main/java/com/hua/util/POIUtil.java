/**
* POIUtil.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hua.bean.grid.ExcelExport;
import com.hua.bean.grid.ExcelImport;
import com.hua.bean.grid.ExcelValidate;
import com.hua.constant.Constant;
import com.hua.constant.GridConstant;
import com.hua.util.EmptyUtil;
import com.hua.util.IOUtil;
import com.hua.util.StringUtil;

/**
 * 描述: 
 * @author qye.zheng
 * POIUtil
 */
public final class POIUtil {
	
	/**
	 jxl 目前只能读取 office 2003及相对低版本的 excel表格
	 
	 poi 可以读取 office 2003 或 2007 以及更高版本的 excel表格
	 创建的时候需要根据文件后缀名来选择构建方式 (xls / xlsx)
	 
	 */
	
	/** 无参构造方法 */
	private POIUtil() {}
	
	/**
	 * 
	 * 描述: 列处理 是否为空
	 * @author qye.zheng
	 * @param cell
	 * @return
	 */
	public static boolean isNull(final Cell cell) {
		// 为null 或 空白单元格
		if (null == cell || Cell.CELL_TYPE_BLANK == cell.getCellType()) {
			
			return true;
		} else if (Cell.CELL_TYPE_STRING == cell.getCellType())
		{
			/*
			 如果是一些其他格式填充单元格，则可能会解析为空格字符串
			 */
			return StringUtil.isEmpty(cell.getStringCellValue());
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 根据不同版本的excel构建工作簿对象
	 * @author  qye.zheng
	 * 
	 * @param suffix
	 * @param inputStream
	 * @return
	 */
	public static Workbook buildWorkbook(final String suffix, final InputStream input)
	{
		// org.apache.poi.ss.usermodel.Workbook
		Workbook workbook = null;
		
		try
		{
			if (GridConstant.EXCEL_XLS.equals(suffix))
			{
				// excel 2003格式文档
				workbook = new HSSFWorkbook(input);
			} else if (GridConstant.EXCEL_XLSX.equals(suffix))
			{
				// excel 2007及以后的格式文档
				workbook = new XSSFWorkbook(input);
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	/**
	 * 
	 * 描述: 根据excel文件后缀名创建工作簿对象
	 * @author  qye.zheng
	 * 
	 * @param suffix
	 * @return
	 */
	public static Workbook createWorkbook(final String suffix)
	{
		// org.apache.poi.ss.usermodel.Workbook
		Workbook workbook = null;
		if (GridConstant.EXCEL_XLS.equals(suffix))
		{
			// excel 2003格式文档
			workbook = new HSSFWorkbook();
		} else if (GridConstant.EXCEL_XLSX.equals(suffix))
		{
			// excel 2007及以后的格式文档
			workbook = new XSSFWorkbook();
		}
		
		return workbook;
	}
	
	/**
	 * 
	 * 描述: 去除数字单元格的小数部分
	 * 转化为整型数字 字符串
	 * @author  qye.zheng
	 * 
	 * @param cell
	 * @return
	 */
	public static String trimDecimalFraction(final Cell cell)
	{
		String value = null;
		final DecimalFormat decimalFormat = new DecimalFormat("#");
		value = decimalFormat.format(cell.getNumericCellValue());
		
		return value;
	}
	
	/**
	 * 
	 * 描述: 获取红色(警告)样式
	 * @author  qye.zheng
	 * @param workbook
	 * @return
	 */
	public static CellStyle createRedStyle(final Workbook workbook)
	{
		final CellStyle cellStyle = workbook.createCellStyle();
		// 设置填充模式
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置前景色 (单元格的填充色)
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		
		return cellStyle;
	}
	
	/**
	 
	 说明: 输出xlsx格式的批注，在wps中无法看到，而在ms中可以正常看到.
	 xls格式的批注，在wps中可以看到.
	 
	 */
	
	/**
	 * 
	 * 描述: 生成单元格批注
	 * @author  qye.zheng
	 * @param suffix 后缀 xls / xlsx
	 * @param sheet 表格
	 * @param comment 批注内容
	 * @return
	 */
	public static Comment createCellComment(final String suffix, final Sheet sheet, final String comment)
	{
		// 注意 author参数不能传null
		return createCellComment(suffix, sheet, "", comment);
	}
	
	/**
	 * 
	 * 描述: 生成单元格批注
	 * @author  qye.zheng
	 * @param suffix xls / xlsx
	 * @param sheet 表格
	 * @param author 作者
	 * @param comment 批注内容
	 * @return
	 */
	public static Comment createCellComment(final String suffix, final Sheet sheet, final String author, final String comment)
	{
		Comment cmt = null;
		final Drawing drawing = sheet.createDrawingPatriarch();
		
		if (GridConstant.EXCEL_XLS.equals(suffix))
		{
			// excel 2003格式文档 ClientAnchor必须使用参数，否则在ms excel 中无法查看批注
			cmt = drawing.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3 ,(short) 5, 6));
			cmt.setString(new HSSFRichTextString(comment));
		} else if (GridConstant.EXCEL_XLSX.equals(suffix))
		{
			// excel 2007及以后的格式文档 ClientAnchor必须使用参数，否则在ms excel 中无法查看批注
			cmt = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) 3, 3 ,(short) 5, 6));
			cmt.setString(new XSSFRichTextString(comment));
		}
		// 公共部分
		cmt.setVisible(true);
		// 设置作者
		cmt.setAuthor(author);  
		
		return cmt;
	}
	
	/**
	 * 
	 * 描述: 统计表格中实际记录条数 (排除标题行)
	 * 规定: 第0行为标题行，中间不能有空行隔开
	 * 这样可以在有格式的情况下，统计出正确的记录总数
	 * @author  qye.zheng
	 * @param sheet
	 * @param columnIndex 以哪个列为参照标准，默认是第0列不能为空
	 * @return
	 */
	public static int countActualRecord(final Sheet sheet, final int columnIndex)
	{
		Row row = null;
		Cell cell = null;
		int result = 0;
		final int startIndex = sheet.getFirstRowNum() + 1;
		final int physicialNumber = sheet.getPhysicalNumberOfRows();
		for (int i =  startIndex; i < physicialNumber; i++)
		{
			row = sheet.getRow(i);
			cell = row.getCell(columnIndex);
			if (isNull(cell))
			{
				continue;
			} else
			{
				result++;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 删除指定的行
	 * 通过让下方单元格上移来实现删除行的效果
	 * @author  qye.zheng
	 * @param sheet
	 * @param rowIndex
	 */
	public static void deleteRow(final Sheet sheet, final int rowIndex)
	{
		 /**
		  
		  注意: 第0行只能向下移动，不能再往上移动
		  [0..65535] 最后一行也不能再往下移动，只能往上移动.
		  
		  1) 删除单行
		  删除掉第n行的实现: 
		  若第n行是最后一行，则无需上移 否则，将 n+1 行到最后一行，全部上移一行，
		  这样就可以实现将第n行删除掉.
		  
		  2) 删除多行
		  range可以设置为大于1，最后一个被删除的行的下标+1作为移动的beginIndex
		  
		  移动发生之后，会对读取当前行产生影响，读取到的值是填充之后的行的值.
		  @warning
		  若是在逐行读取数据，为了避免影响读取，不应该在这个过程中执行这个方法.
		  
		  解决方法:
		  删除一行之后，游标下标应该减1，结束下标也应该减1
		  代码示例:
			POIUtil.deleteRow(sheet, i);
			i--;
			endIndex--;
		  */
		final int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= Constant.ZERO && rowIndex < lastRowNum)
		{
			// 将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
			sheet.shiftRows(rowIndex + Constant.ONE, lastRowNum, Constant.NEGATIVE_ONE);
		} else if (lastRowNum == rowIndex)
		{
			// 最后一行，将其清空掉即可
			final Row removingRow = sheet.getRow(rowIndex);
			if (null != removingRow)
			{
				sheet.removeRow(removingRow);
			}
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param excelImport
	 * @author qye.zheng
	 */
	public static final void doImport(final ExcelImport<?> excelImport)
	{
		try
		{
			final Workbook workbook = buildWorkbook(excelImport.getType(), excelImport.getInputStream());
			if (null == workbook)
			{
				excelImport.setMessage("请选择一个excel文件!");
				
				return;
			}
			// 读取第一个 [工作表]
			final Sheet sheet = workbook.getSheetAt(0);
			Object obj = null;
			Row row = null;
			Cell cell = null;
			String result = null;
			final int columnIndex = 0;
			excelImport.setTotal(countActualRecord(sheet, columnIndex));
			int startIndex = sheet.getFirstRowNum();
			
			// 存放失败导入结果
			final  Workbook workbookF = createWorkbook(excelImport.getType());
			final Sheet sheetF = workbookF.createSheet();
			Row rowF = null;
			Cell cellF = null;
			// 生成标题行，可以给导入错 提示信息准备
			row = sheet.getRow(startIndex);
			// row.getPhysicalNumberOfCells() 不可靠
			final String[] headers = new String[excelImport.getColumns().length];
			for (int i = 0; i < excelImport.getColumns().length; i++)
			{
				cell = row.getCell(i);
				if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
				{
					// 数字的情景
					headers[i] = cell.getNumericCellValue() + "";
				} else
				{
					headers[i] = cell.getStringCellValue();
				}
			}
			excelImport.setHeaders(headers);
			
			// 生成标题
			rowF = sheetF.createRow(0);
			int headerIndex = 0;
			if (0 == excelImport.getErrorIndex())
			{
				cellF = rowF.createCell(headerIndex++);
				cellF.setCellValue(ExcelImport.FAILURE_COLUMN);
				for (String header : headers)
				{
					cellF = rowF.createCell(headerIndex++);
					cellF.setCellValue(header);
				}
			} else 
			{
				cellF = rowF.createCell(headers.length, 0);
				cellF.setCellValue(ExcelImport.FAILURE_COLUMN);
				for (String header : headers)
				{
					cellF = rowF.createCell(headerIndex++);
					cellF.setCellValue(header);
				}
			}
			
			// 读取每一样的记录
			startIndex++;
			int endIndex = excelImport.getTotal() + 1;
			int errorRowIndex = 0;
			for (int i = startIndex; i < endIndex; i++)
			{
				row = sheet.getRow(i);
				obj = excelImport.getClazz().newInstance();
				result = fill(excelImport, obj, row);
				if (StringUtil.isEmpty(result))
				{
					// 可以导入
					excelImport.addData(obj);
					// 成功导入数自增
					excelImport.success();
					/*
					 * 删除一行之后，游标下标应该减1，结束下标也应该减1
					 */
					deleteRow(sheet, i);
					i--;
					endIndex--;
				} else
				{
					int errorColumnIndex = 0;
					// 失败导入数自增
					excelImport.failure();
					errorRowIndex = excelImport.getFailure();
					
					rowF = sheetF.createRow(errorRowIndex);
					cellF = rowF.createCell(errorColumnIndex++);
					cellF.setCellValue(result);
					// 应该用 单元标号，而不是长度，长度小于等于单元标号
					//System.out.println(row.getLastCellNum());
					for (int z = 0; z < row.getLastCellNum(); z++)
					{
						cell = row.getCell(z);
						cellF = rowF.createCell(errorColumnIndex++);
						// 应该清空内容，防止被之前的覆盖
						cellF.setCellValue("");
						if (isNull(cell))
						{
							continue;
						}
						if (Cell.CELL_TYPE_STRING == cell.getCellType())
						{
							cellF.setCellValue(cell.getStringCellValue());
						} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
						{
							cellF.setCellValue(cell.getDateCellValue());
						}
					}
				}
			}
			// 结果处理
			if (excelImport.getFailure() > 0)
			{
				// 输出导入 失败结果表
				workbookF.write(excelImport.getOutputStream());
				excelImport.getOutputStream().flush();
				excelImport.getOutputStream().close();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param excelImport
	 * @param t
	 * @param row
	 * @return
	 * @author qye.zheng
	 */
	private static final <T> String fill(final ExcelImport<?> excelImport, final T t, final Row row)
	{
		final StringBuilder builder = StringUtil.getBuilder();
		final String[] columns = excelImport.getColumns();
		final Class<?> clazz = t.getClass();
		Cell cell = null;
		String methodName = null;
		for (int i = 0; i < columns.length; i++)
		{
			cell = row.getCell(i);
			if (-1 != columns[i].lastIndexOf(ExcelValidate.MUST.getName()) )
			{
				// 必填项
				if (isNull(cell))
				{
					builder.append(excelImport.getHeaders()[i] + " 不能为空;");
					builder.append(Constant.LINE_BREAK);
					
					continue;
				}
			} else if (-1 != columns[i].lastIndexOf(ExcelValidate.EMAIL.getName()))
			{
				// 正则
				Pattern pattern = Pattern.compile(ExcelValidate.EMAIL.getValue());
				Matcher matcher = pattern.matcher(cell.getStringCellValue());
				if (!matcher.find())
				{
					builder.append(excelImport.getHeaders()[i] + " 格式不正确;");
					builder.append(Constant.LINE_BREAK);
					
					continue;
				}
			} // 后续可以增加验证分支
			try
			{
				methodName = columns[i].replaceFirst("\\(.+\\)", "");
				methodName = "set" + methodName.substring(0, 1).toUpperCase() 
						+ methodName.substring(1);
				if (Cell.CELL_TYPE_STRING == cell.getCellType())
				{
					clazz.getMethod(methodName, String.class).invoke(t, cell.getStringCellValue());
				} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType())
				{
					try 
					{
						clazz.getMethod(methodName, Date.class).invoke(t, cell.getDateCellValue());
					} catch (IllegalArgumentException ne)
					{
						// 设置为日期发生异常，则设置为数字
						clazz.getMethod(methodName, Number.class).invoke(t, cell.getNumericCellValue());
					}
				} else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType())
				{
					clazz.getMethod(methodName, boolean.class).invoke(t, cell.getBooleanCellValue());
				}
			} catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			} catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			} catch (InvocationTargetException e)
			{
				e.printStackTrace();
			} catch (SecurityException e)
			{
				e.printStackTrace();
			}
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * @description 导出 (生成excel文件然后输出)
	 * @param excelExport
	 * @author qye.zheng
	 */
	public static final void doExport(final ExcelExport<?> excelExport)
	{
		final Workbook workbook = createWorkbook(excelExport.getType());
		final Sheet sheet = workbook.createSheet(excelExport.getTitle());
		final Collection<String[]> data = convert(excelExport);
		Row row = null;
		Cell cell = null;
		int startRowIndex = 0;
		
		// 生成标题行
		String[] headers = excelExport.getHeaders();
		if (!EmptyUtil.isEmpty(headers))
		{
			row = sheet.createRow(startRowIndex++);
			for (int i = 0; i < headers.length; i++)
			{
				cell = row.createCell(i);
				cell.setCellValue( (null == headers[i]) ? "" : headers[i]);
			}
		}
		String[] cells = null;
		final Iterator<String[]> it = data.iterator();
		// 生成各行记录
		while (it.hasNext())
		{
			row = sheet.createRow(startRowIndex++);
			cells = it.next();
			for (int j = 0; j < cells.length; j++)
			{
				cell = row.createCell(j);
				cell.setCellValue( (null == cells[j]) ? "" : cells[j] );
			}
		}
		try
		{
			workbook.write(excelExport.getOutputStream());
			excelExport.getOutputStream().flush();
			excelExport.getOutputStream().close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			IOUtil.close(excelExport.getOutputStream());
		}
	}
	
	/**
	 * 
	 * @description 
	 * @param excelExport
	 * @return
	 * @author qye.zheng
	 */
	private static final Collection<String[]> convert(final ExcelExport<?> excelExport)
	{
		final Collection<String[]> result = new ArrayList<String[]>();
		Iterator<?> it = excelExport.getDataset().iterator();
		Object obj = null;
		Class<?> clazz = null;
		Object value = null;
		String methodName = null;
		final String[] columns = excelExport.getColumns();
		String[] values = null;
		DateFormat df = null;
		if (!StringUtil.isEmpty(excelExport.getDateTimeFormat()))
		{
			df = new SimpleDateFormat(excelExport.getDateTimeFormat());
		}
		//Field field = null;
		while (it.hasNext())
		{
			obj = it.next();
			values = new String[columns.length];
			for (int i = 0; i < columns.length; i++)
			{
				try
				{
					clazz = obj.getClass();
			/* try 
		            {
		            	field = clazz.getDeclaredField(columns[i]);
		            } catch (NoSuchFieldException e2)
		            {
		            	// 捕获到异常，该属性在父类中
		            	field = clazz.getSuperclass().getDeclaredField(columns[i]);	   
		            }*/
				// 设置为可访问
				//field.setAccessible(true);
/*				if (field.getType() == boolean.class)
					{
						// 布尔类型
						methodName =  "is";
					} else
					{
						// 对象类型
						methodName =  "get";
					}*/
					methodName =  columns[i].substring(0, 1).toUpperCase() 
							+ columns[i].substring(1);
					// getDeclaredMethod 会导致继承的方法无法获取
					//value = clazz.getDeclaredMethod(methodName).invoke(obj);
		            try 
		            {
		            	// 1. 尝试有 getXx方法
		            	value = clazz.getMethod("get" + methodName).invoke(obj);
		            } catch (NoSuchMethodException e1)
		            {
		            	// 2. 尝试isXx方法
		            	value = clazz.getMethod("is" + methodName).invoke(obj);
		            	try 
		            	{
			            	// 3. 尝试hasXx方法
			            	value = clazz.getMethod("has" + methodName).invoke(obj);
		            	} catch (NoSuchMethodException e2)
		            	{
		            		// 多次尝试都没有找到该属性
		            		e2.printStackTrace();
		            	}
		            }
					if (null == value)
					{
						values[i] = "";
						
						continue;
					}
					if (value instanceof Date)
					{
						// 日期时间类型
						values[i] = df.format(value);
					}
					else
					{
						// 转成字符串类型
						values[i] = value + "";
					}
				} catch (IllegalAccessException e)
				{
					e.printStackTrace();
				} catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				} catch (InvocationTargetException e)
				{
					e.printStackTrace();
				} catch (NoSuchMethodException e)
				{
					e.printStackTrace();
				} catch (SecurityException e)
				{
					e.printStackTrace();
				}
			}
			result.add(values);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @description 将数字式字符串转成String格式
	 * 在excel 中，像电话号码、邮编等数字读取为Double类型，
	 * 因此需要进行格式的调整，而不是直接调用Double类型的toString
	 * 方法.
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final String getStringOfDouble(final Double target)
	{
		String value = null;
		final DecimalFormat decimalFormat = new DecimalFormat("#");
		value = decimalFormat.format(target);
		
		return value;
	}
	
	/**
	 * 
	 * @description 获取字节对象
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Byte getByte(final Double target)
	{
		if (null == target)
		{
			return null;
		}
		
		return target.byteValue();
	}
	
	/**
	 * 
	 * @description 
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Boolean getBoolean(final Double target)
	{
		return null;
	}
	
	/**
	 * 
	 * @description 
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Short getShort(final Double target)
	{
		if (null == target)
		{
			return null;
		}
		
		return target.shortValue();
	}
	
	/**
	 * 
	 * @description 
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Integer getInteger(final Double target)
	{
		if (null == target)
		{
			return null;
		}
		
		return target.intValue();
	}
	
	/**
	 * 
	 * @description 
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Float getFloat(final Double target)
	{
		if (null == target)
		{
			return null;
		}
		
		return target.floatValue();
	}
	
	/**
	 * 
	 * @description 
	 * @param target
	 * @return
	 * @author qye.zheng
	 */
	public static final Long getLong(final Double target)
	{
		if (null == target)
		{
			return null;
		}
		
		return target.longValue();
	}
	
	/**
	 * 
	 * @description 获取 xls 日期时间、日期、时间
	 * @param target
	 * @param isDate1904
	 * @return
	 * @author qye.zheng
	 */
	public static final Date getDateOfXLS(final Double target, final boolean isDate1904)
	{
		if (null == target)
		{
			return null;
		}
		
		return HSSFDateUtil.getJavaDate(target, isDate1904);
	}
	
	/**
	 * 
	 * @description 获取 xls 日期时间、日期、时间
	 * @param target
	 * @param isDate1904
	 * @return
	 * @author qye.zheng
	 */
	public static final Date getDateOfXLSX(final Double target, final boolean isDate1904)
	{
		if (null == target)
		{
			return null;
		}
		
		return DateUtil.getJavaDate(target, isDate1904);
	}
	
	/**
	 * 
	 * @description 根据字符长度，智能调整 列的宽度
	 * 例如 出现 1或2个字符这种情况，需要智能加长
	 * @param value
	 * @return
	 * @author qye.zheng
	 */
	public static final int getColumnWith(final String value)
	{
		// 设置一个最小宽度
		int width = -1;
		if (StringUtil.isEmpty(value))
		{
			return width;
		}
		width = value.length() * 1000;
		if (width < 4000)
		{
			width = 4000;
		}
		
		
		return width;
	}		
	
	/**
	 * 
	 * @description 给指定的列创建下拉列表
	 * @param sheet
	 * @param values
	 * @param column 列标，从0开始
	 * @author qye.zheng
	 */
	public static final void createColumnPullDown(final Sheet sheet, final String[] values, final int column)
	{
		final DataValidationHelper dvHelper = sheet.getDataValidationHelper();
		
		final DataValidationConstraint constraint = dvHelper.createExplicitListConstraint(values);
		
		/*
		 * 除了标题行之外，默认10万行 
		 * (excel 2003 最大 6万多行，2007是100多万行)
		 */
		final int firstRow = 1;
		final int lastRow = 10000;
		// 同一列
		final int firstCol = column;
		final int lastCol = column;
		
		final CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
		final DataValidation dataValidation =  dvHelper.createValidation(constraint, cellRangeAddressList);
		
		sheet.addValidationData(dataValidation);
	}
}
