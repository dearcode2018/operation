@ rem ----- 信息 -----
@ rem @filename UpdateRunner.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description 更新运行器

@ rem @warning 为了防止中文环境乱码，保存文件的时候，应该保存为ANSI编码格式.
@ rem ################################################################################


@ rem 标题
@ title TortoiseSVN Client 更新运行器
@ rem ########## begin  ##########

@ rem 关闭显示命令，使所有命令执行前不显示
@ rem @ echo off
@ echo off
@ rem 打开命令显示 @ echo on


@ rem ----- 变量声明区

:: 设置10个svn更新目录，一般10个已经能满足绝大多数的需求，需要取消哪些路径的操作，注释掉即可.
set work_dir_01=E:\xtionkx\2.需求分析\V6.02\V6.02.1
set work_dir_02=
set work_dir_03=
set work_dir_04=
set work_dir_05=
set work_dir_06=
set work_dir_07=
set work_dir_08=
set work_dir_09=
set work_dir_10=


@ rem ----- 程序设计区

if exist %work_dir_01% TortoiseProc /path:"%work_dir_01%"  /command:update /closeonend:2
if exist %work_dir_02% TortoiseProc /path:"%work_dir_02%"  /command:update /closeonend:2
if exist %work_dir_03% TortoiseProc /path:"%work_dir_03%"  /command:update /closeonend:2
if exist %work_dir_04% TortoiseProc /path:"%work_dir_04%"  /command:update /closeonend:2
if exist %work_dir_05% TortoiseProc /path:"%work_dir_05%"  /command:update /closeonend:2
if exist %work_dir_06% TortoiseProc /path:"%work_dir_06%"  /command:update /closeonend:2
if exist %work_dir_07% TortoiseProc /path:"%work_dir_07%"  /command:update /closeonend:2
if exist %work_dir_08% TortoiseProc /path:"%work_dir_08%"  /command:update /closeonend:2
if exist %work_dir_09% TortoiseProc /path:"%work_dir_09%"  /command:update /closeonend:2
if exist %work_dir_10% TortoiseProc /path:"%work_dir_10%"  /command:update /closeonend:2

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
