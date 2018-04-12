@ rem ----- 信息 -----
@ rem @filename SetClassPath.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description 设置 classpath 环境变量

@ rem @warning 为了防止中文环境乱码，保存文件的时候，应该保存为ANSI编码格式.
@ rem ################################################################################


@ rem 标题
@ title 
@ rem ########## begin  ##########

@ rem 关闭显示命令，使所有命令执行前不显示
@ rem @ echo off
@ echo off
@ rem 打开命令显示 @ echo on

@ rem ----- 变量声明区

set classpath=%classpath%;..\lib\ant-1.9.4.jar
set classpath=%classpath%;..\lib\ant-launcher-1.9.4.jar
set classpath=%classpath%;..\lib\aopalliance-1.0.jar
set classpath=%classpath%;..\lib\asm-3.3.1.jar
set classpath=%classpath%;..\lib\aspectjrt-1.8.5.jar
set classpath=%classpath%;..\lib\aspectjtools-1.8.5.jar
set classpath=%classpath%;..\lib\aspectjweaver-1.8.5.jar
set classpath=%classpath%;..\lib\build-0.0.1.jar
set classpath=%classpath%;..\lib\c3p0-0.9.1.1.jar
set classpath=%classpath%;..\lib\c3p0-0.9.5.jar
set classpath=%classpath%;..\lib\cglib-nodep-3.1.jar
set classpath=%classpath%;..\lib\common-0.0.1.jar
set classpath=%classpath%;..\lib\commons-beanutils-1.9.2.jar
set classpath=%classpath%;..\lib\commons-cli-1.2.jar
set classpath=%classpath%;..\lib\commons-codec-1.10.jar
set classpath=%classpath%;..\lib\commons-collections-3.2.1.jar
set classpath=%classpath%;..\lib\commons-compress-1.9.jar
set classpath=%classpath%;..\lib\commons-configuration-1.10.jar
set classpath=%classpath%;..\lib\commons-dbcp2-2.1.jar
set classpath=%classpath%;..\lib\commons-fileupload-1.3.1.jar
set classpath=%classpath%;..\lib\commons-io-2.4.jar
set classpath=%classpath%;..\lib\commons-lang-2.6.jar
set classpath=%classpath%;..\lib\commons-lang3-3.3.2.jar
set classpath=%classpath%;..\lib\commons-logging-1.2.jar
set classpath=%classpath%;..\lib\commons-net-3.3.jar
set classpath=%classpath%;..\lib\commons-pool2-2.3.jar
set classpath=%classpath%;..\lib\dom4j-1.6.1.jar
set classpath=%classpath%;..\lib\gson-2.3.1.jar
set classpath=%classpath%;..\lib\hamcrest-core-1.3.jar
set classpath=%classpath%;..\lib\jackson-annotations-2.5.1.jar
set classpath=%classpath%;..\lib\jackson-core-2.5.1.jar
set classpath=%classpath%;..\lib\jackson-databind-2.5.1.jar
set classpath=%classpath%;..\lib\javax.servlet-api-3.1.0.jar
set classpath=%classpath%;..\lib\jdom2-2.0.5.jar
set classpath=%classpath%;..\lib\jta-1.1.jar
set classpath=%classpath%;..\lib\junit-4.11.jar
set classpath=%classpath%;..\lib\log4j-1.2.17.jar
set classpath=%classpath%;..\lib\maven-0.0.1.jar
set classpath=%classpath%;..\lib\mchange-commons-java-0.2.9.jar
set classpath=%classpath%;..\lib\mysql-connector-java-5.1.34.jar
set classpath=%classpath%;..\lib\ojdbc14-10.2.0.4.0.jar
set classpath=%classpath%;..\lib\quartz-2.2.1.jar
set classpath=%classpath%;..\lib\slf4j-api-1.7.10.jar
set classpath=%classpath%;..\lib\spring-aop-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-beans-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-context-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-core-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-expression-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-test-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-web-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\spring-webmvc-4.1.4.RELEASE.jar
set classpath=%classpath%;..\lib\xml-apis-1.0.b2.jar



@ rem ----- 程序设计区

@ rem pause

@ rem

@ rem
@ rem 输出提示信息

::
:: 1) 
:: 2)
:: 3)
:: 4)
:: 5)
:: 6)
:: 7)
:: 8)
:: 9)
:: 10)

:: 在程序的末尾，可以根据执行的结果(成功或失败) 给出提示信息，成功可以直接执行exit，而失败
:: 可以执行pause，然后可以通过控制台输出信息来调试、定位问题.
:: 可以在程序中设置一个成功或失败的标志-布尔值，来决定最后程序的执行流程.

@ rem echo
@ rem exit
@ rem ########## end of ##########

@ rem 注释说明: @ rem 注释内容  或者 :: 注释内容
@ rem rem 或两个冒号 后面 都可以写注释
