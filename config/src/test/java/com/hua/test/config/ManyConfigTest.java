/**
 * 描述: 
 * ManyConfigTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.config;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.OperationUtil;
import com.hua.util.ReadProperties;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ManyConfigTest
 */
public final class ManyConfigTest extends BaseTest {

	/**
	 * 编译运行时，优先找当前项目包下的类或资源文件，
	 * 找不到，再去lib的jar包中去寻找，jar包的优先级如何?
	 * 如果多个jar包包含多个相同的类或资源，优先加载哪个?
	 */
	
	/**
	 * 当前jar运行，优先加载的也是当前项目的类或资源文件，
	 * 找不到再到其他jar包去找.
	 */
	
	/**
	 * 多个依赖的包，出现同一包下，同名类、配置文件等的时候，
	 * 验证其是否冲突或者当前项目的文件优先，是否影响运行以及
	 * 部署的.
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testManyConfig() {
		try {
			ReadProperties readProps = new ReadProperties("/conf/properties/project.properties");
			
			System.out.println(readProps.getProperty("project.name"));
			
			OperationUtil.t();
			
		} catch (Exception e) {
			log.error("testManyConfig =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 当前项目没有该配置文件
	 * 可以读取到指定包下 其他项目的配置(只有一个项目存在)
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCurrentNoHasConfig01() {
		try {
			ReadProperties readProps = new ReadProperties("/conf/properties/operation.properties");
			
			System.out.println(readProps.getProperty("operation.type"));
			
		} catch (Exception e) {
			log.error("testCurrentNoHasConfig01 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 当前项目没有该配置文件
	 * 可以读取到指定包下 其他项目的配置(多个项目存在)
	 * 验证到底以哪个项目为优先，是第一个找到的为优先?
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCurrentNoHasConfig02() {
		try {
			ReadProperties readProps = new ReadProperties("/conf/properties/maintain.properties");
			
			System.out.println(readProps.getProperty("project.maintain.range.projects"));
			
		} catch (Exception e) {
			log.error("testCurrentNoHasConfig =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
