/**
  * @filename GetAuthLogService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hua.constant.FormatConstant;
import com.hua.constant.GridConstant;
import com.hua.util.ClipboardUtil;
import com.hua.util.IOUtil;
import com.hua.util.POIUtil;
import com.hua.util.ReadProperties;

/**
 * @type GetAuthLogService
 * @description 
 * @author qianye.zheng
 */
public class GetAuthLogService
{
	private static final String CONFIG_PATH = "/conf/properties/hj-get-auth-log.properties";
	
	private static final ReadProperties readProps = new ReadProperties(CONFIG_PATH);
	
	private static final Properties props = readProps.getProps();
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public final void start()
	{
		// 1.获取密码
		final String password = getAuth();
		// 2.写到剪贴板
		ClipboardUtil.writeText(password);
		
		// 3.记录获取密码的日志到excel
		final String excelPath =  props.getProperty("log.excel.path");
		try
		{
			final InputStream inputStream = new FileInputStream(excelPath);
			final Workbook workbook = POIUtil.buildWorkbook(GridConstant.EXCEL_XLSX, inputStream);
			if (null == workbook)
			{
				System.out.println("文件找不到，请先在指定路径下创建文件");
				
				return;
			}
			Sheet sheet = workbook.getSheetAt(0);
			
			sheet.setColumnWidth(0, 100 * 100);
			final int startRowIndex = sheet.getPhysicalNumberOfRows();
			final Row row = sheet.createRow(startRowIndex);
			final Cell cell = row.createCell(0);
			final DateFormat df = new SimpleDateFormat(FormatConstant.DATE_TIME_FORMAT_yyyy_MM_dd_HH_mm_ss);
			cell.setCellValue("上一次获取时间: " + df.format(new Date()));
			
			workbook.write(new FileOutputStream(excelPath));
			
			// 关闭资源
			workbook.close();
			IOUtil.close(inputStream);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @description 
	 * @return
	 * @author qianye.zheng
	 */
	private final String getAuth()
	{
		final String username = props.getProperty("mail.username");
		final String password = props.getProperty("mail.password");
		//final String fromAddress = "dearzqy@163.com";
		Properties props = new Properties();
		//props.put("mail.transport.protocol", "smtp");
		//props.put("mail.smtp.host", "smtp.163.com");
		//props.put("mail.smtp.port", "25");
		// 协议
		props.setProperty("mail.store.protocol", "pop3");
		// 端口
		props.setProperty("mail.pop3.port", "110");
		// pop3服务器
		props.setProperty("mail.pop3.host", "pop3.163.com");
		
		// 根据邮件会话属性和密码验证器构建一个发送邮件的session
		//final Session session = Session.getDefaultInstance(props, authenticator);
		final Session session = Session.getDefaultInstance(props);
		String target = null;
		try
		{
			Store store = session.getStore("pop3");
			 store.connect(username, password);  
			//Store store = session.getStore();
			
			// 获得收件箱
			Folder folder = store.getFolder("INBOX");
			/* Folder.READ_ONLY：只读权限 
	         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
	         */  
			// 打开收件箱
			folder.open(Folder.READ_WRITE);
			// 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数  
			Message message = null;
			// 从1开始，最早的邮件是1
			message = folder.getMessage(79);
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			// 获取邮件内容，0是标题对象，1-是内容对象
			BodyPart bodyPart = mimeMultipart.getBodyPart(1);
			String html = bodyPart.getContent().toString();
			Integer length = 10;
			String str = "密码: ";
			int startIndex = html.indexOf(str) + str.length();
			target = html.substring(startIndex, startIndex + length);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return target;
	}
	
	
	
}
