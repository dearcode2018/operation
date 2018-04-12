/**
 * 描述: 
 * GetAuthLogStarter.java
 * @author	qye.zheng
 * 
 *  version 1.0
 */
package com.hua.starter;

import org.junit.Test;

import com.hua.service.GetAuthLogService;

/**
 * 描述: 启动器
 * @author  qye.zheng
 * 
 * GetAuthLogStarter
 */
public final class GetAuthLogStarter
{

	private GetAuthLogService getAuthLogService = new GetAuthLogService();

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
		getAuthLogService.start();
		
	}

}
