@ rem ----- ��Ϣ -----
@ rem @filename UpdateRunner.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description ����������

@ rem @warning Ϊ�˷�ֹ���Ļ������룬�����ļ���ʱ��Ӧ�ñ���ΪANSI�����ʽ.
@ rem ################################################################################


@ rem ����
@ title TortoiseSVN Client ����������
@ rem ########## begin  ##########

@ rem �ر���ʾ���ʹ��������ִ��ǰ����ʾ
@ rem @ echo off
@ echo off
@ rem ��������ʾ @ echo on


@ rem ----- ����������

:: ����10��svn����Ŀ¼��һ��10���Ѿ���������������������Ҫȡ����Щ·���Ĳ�����ע�͵�����.
set work_dir_01=E:\xtionkx\2.�������\V6.02\V6.02.1
set work_dir_02=
set work_dir_03=
set work_dir_04=
set work_dir_05=
set work_dir_06=
set work_dir_07=
set work_dir_08=
set work_dir_09=
set work_dir_10=


@ rem ----- ���������

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
@ rem �����ʾ��Ϣ

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

:: �ڳ����ĩβ�����Ը���ִ�еĽ��(�ɹ���ʧ��) ������ʾ��Ϣ���ɹ�����ֱ��ִ��exit����ʧ��
:: ����ִ��pause��Ȼ�����ͨ������̨�����Ϣ�����ԡ���λ����.
:: �����ڳ���������һ���ɹ���ʧ�ܵı�־-����ֵ���������������ִ������.

@ rem echo
@ rem exit
@ rem ########## end of ##########

@ rem ע��˵��: @ rem ע������  ���� :: ע������
@ rem rem ������ð�� ���� ������дע��
