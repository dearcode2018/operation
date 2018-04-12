/**
 * 描述: 
 * JettyServerStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.starter;

import org.eclipse.jetty.server.Server;
import org.junit.Test;

import com.hua.handler.JettyHandler;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * 
 * JettyServerStarter
 */
public final class JettyServerStarter
{


	

	// 启动器模板
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void start()
	{
		/** ===== begin 驱动参数设置  ===== */
		// 设置例子
		
		
		/** ===== end of 驱动参数设置 ===== */

		// 启动驱动
		Server server = new Server(8080);  
	    server.setHandler(new JettyHandler());  
        try
		{
			server.start();
			server.join();  
		} catch (Exception e)
		{
			e.printStackTrace();
		}  
		
	}

}
