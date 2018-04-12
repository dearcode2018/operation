/**
  * @filename JettyHandler.java
  * @description  
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

 /**
 * @type JettyHandler
 * @description  
 * @author qianye.zheng
 */
public final class JettyHandler extends AbstractHandler
{

	/**
	 * @description 
	 * @param arg0
	 * @param baseRequest
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @author qianye.zheng
	 */
	@Override
	public void handle(String arg0, Request baseRequest, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException
	{
	       response.setContentType("text/html;charset=utf-8");  
	        response.setStatus(HttpServletResponse.SC_OK);  
	        baseRequest.setHandled(true);  
	        response.getWriter().println("<h1>Hello World</h1>");  
	}
}
