@ rem ----- ��Ϣ -----
@ rem @filename ProjectIndex.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description 

@ rem @warning Ϊ�˷�ֹ���Ļ������룬�����ļ���ʱ��Ӧ�ñ���ΪANSI�����ʽ.
@ rem ################################################################################


@ rem ����
@ title 
@ rem ########## begin  ##########

@ rem �ر���ʾ���ʹ��������ִ��ǰ����ʾ
@ rem @ echo off
@ rem ��������ʾ @ echo on

@ rem ----- ����������
:: set starterPath=D:\Program Files\update\UpdateStarter.bat
:: ����������·��
set starterPath=

@ rem ----- ���������

if not exist "%starterPath%" (
	echo "%starterPath% �����ڣ�����·��!"
	goto failure
)

:: ��ֹ·���к��������ַ� ����ո�Ӧ����˫����������
call "%starterPath%"

goto success

@ rem pause

@ rem



:failure
 pause
 
:success


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
