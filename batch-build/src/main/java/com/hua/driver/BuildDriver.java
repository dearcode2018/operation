/**
 * 描述: 
 * BuildDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.driver;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.hua.bean.BuildParam;
import com.hua.bean.SetClassPath;
import com.hua.classpath.ClassPathConstant;
import com.hua.classpath.Kind;
import com.hua.util.BatchBuildUtil;
import com.hua.util.EmptyUtil;
import com.hua.util.FileUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述:  - 驱动器
 * @author  qye.zheng
 * BuildDriver
 */
public class BuildDriver
{
	
	/* 项目根路径 */
	private static final String PROJECT_ROOT = System.getProperty("user.dir");
	
	private static final String classPathFile;
	
	//private static final String SET_CLASS_PATH_PATH = ProjectUtil.getAbsolutePath("/doc/batch/SetVariable.bat", true);
	
	static {
		// .classpath 文件的路径 (不同类型项目 通用)
		classPathFile = PROJECT_ROOT + File.separator + ".classpath";
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 */
	private BuildDriver()
	{
	}

	/**
	 * 
	 * 描述: 根据 lib下的文件去构建 类路径bat文件
	 * @author  qye.zheng
	 * @return
	 */
	public static final boolean batchBuild()
	{
		boolean flag = false;
		try
		{
			// 获取构建参数对象
			final BuildParam param = BuildParam.getInstance();
			
			// 在 doc/build 目录下 根据项目名称创建一个目录
			final File buildProjectDir = new File(ProjectUtil.getAbsolutePath("/doc/build/" + param.getProjectName(), true));
			if (buildProjectDir.exists())
			{
				// 存在先删除
				FileUtil.delete(buildProjectDir);
			} else {
				// 不存在则创建
				buildProjectDir.mkdirs();
			}
			// 将目标项目的指定目录拷贝到 构建目标项目目录下
			final String targetProjectPath = ProjectUtil.getAbsolutePath("/" + param.getProjectName(), false);
			if (!EmptyUtil.isEmpty(param.getTargets()))
			{
				for (String target : param.getTargets())
				{
					// 目标项目的 target目录下
					FileUtil.copy(targetProjectPath + "/target/" +target, buildProjectDir.getAbsolutePath());
				}
			}
			/**
			 * 
			 * set classpath=%classpath%;.
			 * set classpath=%classpath%;lib\commons-beanutils-1.9.1.jar
			 */
			
			final List<SetClassPath> setClassPaths = new ArrayList<SetClassPath>();
			SetClassPath setClassPath = null;
			// 扫描 目标项目下 target/lib目录
			final File targetProjectLibFile = new File(targetProjectPath + "/target/lib");
			final File[] jarFiles = targetProjectLibFile.listFiles();
			if (EmptyUtil.isEmpty(jarFiles))
			{
				
				return flag;
			}
			for (File jarFile : jarFiles)
			{
				// ..\lib\commons-beanutils-1.9.1.jar
				setClassPath = new SetClassPath();
				// 拼接变量值
				setClassPath.setValue("..\\lib\\" + jarFile.getName());
				setClassPaths.add(setClassPath);
			}
			
			// 将 doc/batch 拷贝到 构建的目标项目目录下
			FileUtil.copy(ProjectUtil.getAbsolutePath("/doc/batch/", true), buildProjectDir.getAbsolutePath());
			// 输出 SetClassPath.bat
			final String setClassPathFile = buildProjectDir.getAbsolutePath() + "/batch/SetClassPath.bat";
			BatchBuildUtil.createSetVariable(setClassPathFile, setClassPaths);

			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}	
	
	/**
	 * 
	 * 描述: 根据 .classpath 文件去构建 类路径bat文件
	 * 在 maven 已经不能使用，因此标识为deprecated
	 * @author  qye.zheng
	 * @return
	 */
	@Deprecated
	public static final boolean batchBuildOld()
	{
		boolean flag = false;
		try
		{
			/**
			 * 
			 * set classpath=%classpath%;.
			 * set classpath=%classpath%;lib\commons-beanutils-1.9.1.jar
			 */
			final List<SetClassPath> setClassPaths = new ArrayList<SetClassPath>();
			SetClassPath setClassPath = null;
			// 建立构造器  
			final SAXBuilder saxBuilder = new SAXBuilder();
			// 读入指定文件  
			final Document oldDoc = saxBuilder.build(new FileInputStream(classPathFile));
			
			// 获得根节点  
			final Element oldRoot = oldDoc.getRootElement();
			// 将根节点下的所有子节点放入List中  
			final List<Element> oldElements = oldRoot.getChildren();
			Element classPathEntryElement = null;
			String kindValue = null;
			String pathValue = null;
			for (int i = 0; i < oldElements.size(); i++) {
				classPathEntryElement = oldElements.get(i);
				kindValue = classPathEntryElement.getAttributeValue(ClassPathConstant.ATTRIBUTE_KIND);
				pathValue = classPathEntryElement.getAttributeValue(ClassPathConstant.ATTRIBUTE_PATH);
				
				/*
				 * 只处理 kind=lib  和 kind=output
				 * 取出 path 值，将 / 替换为 \
				 */
				if (Kind.LIB.equals(kindValue) || Kind.OUTPUT.equals(kindValue)) {
					/**
					 * 
					 * set classpath=%classpath%;.
					 * set classpath=%classpath%;lib\commons-beanutils-1.9.1.jar
					 */
					// 将 / 替换为 \
					pathValue = pathValue.replace('/', '\\');
					setClassPath = new SetClassPath();
					setClassPath.setValue(pathValue);
					setClassPaths.add(setClassPath);
				}
			}
			// 获取构建参数对象
			final BuildParam param = BuildParam.getInstance();
			
			// 在 doc/build 目录下 根据项目名称创建一个目录
			final File buildProjectDir = new File(ProjectUtil.getAbsolutePath("/doc/build/" + param.getProjectName(), true));
			if (buildProjectDir.exists())
			{
				// 存在先删除
				FileUtil.delete(buildProjectDir);
			} else {
				// 不存在则创建
				buildProjectDir.mkdirs();
			}
			
			// 将 doc/batch 拷贝到 构建的目标项目目录下
			FileUtil.copy(ProjectUtil.getAbsolutePath("/doc/batch/", true), buildProjectDir.getAbsolutePath());
			final String setClassPathFile = buildProjectDir.getAbsolutePath() + "/batch/SetClassPath.bat";
			BatchBuildUtil.createSetVariable(setClassPathFile, setClassPaths);
			// 将目标项目的指定目录拷贝到 构建目标项目目录下
			final String targetProjectPath = ProjectUtil.getAbsolutePath("/" + param.getProjectName(), false);
			if (!EmptyUtil.isEmpty(param.getTargets()))
			{
				for (String target : param.getTargets())
				{
					FileUtil.copy(targetProjectPath + "/" +target, buildProjectDir.getAbsolutePath());
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
	 * 描述: 
	 * @author  qye.zheng
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
