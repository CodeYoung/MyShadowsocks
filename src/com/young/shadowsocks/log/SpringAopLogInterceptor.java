/**
 * 
 */
package com.young.shadowsocks.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class SpringAopLogInterceptor extends AbstractLogInterceptor{
	private Logger logger = LoggerFactory.getLogger( getClass() );

	@Override
	public void beforeAdvice ( JoinPoint jionpoint ) {
		// ��ȡ�����õ�����
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// ��ȡ�����õķ�����
		String targetMethodName = jionpoint.getSignature().getName();
		// ��־��ʽ�ַ���
		String logInfoText = "Class��" + targetClassName;
		// ����־��Ϣд�����õ��ļ���
		logger.debug( logInfoText );
		String parameters = getParaString( jionpoint.getArgs(), targetMethodName );
		logger.debug("Method��{}",parameters );
		/*SysUser user = SessionContext.getCurrentUserInfo();
		if(user!=null){
			logger.debug( "User : {}:({})",user.getUserName(),user.getAccount() );
		}*/
	}

	@Override
	public void afterAdvice ( JoinPoint jionpoint ) {
		// ��ȡ�����õ�����
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// ��ȡ�����õķ�����
		String targetMethodName = jionpoint.getSignature().getName();
		// ��־��ʽ�ַ���
		String logInfoText = "Class��" + targetClassName + "-" + targetMethodName + " excute complete��";
		// ����־��Ϣд�����õ��ļ���
		logger.debug( logInfoText );
	}

	@Override
	public void exceptionAdvice ( JoinPoint jionpoint , Exception e ) {
		// ��ȡ�����õ�����
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// ��ȡ�����õķ�����
		String targetMethodName = jionpoint.getSignature().getName();
		// ��־��ʽ�ַ���
		String logInfoText = "�쳣֪ͨ��ִ��" + targetClassName + "���" + targetMethodName + "����ʱ�����쳣";
		// ����־��Ϣд�����õ��ļ���
		logger.debug( logInfoText );
	}

	@Override
	public void aroundAdvice ( ProceedingJoinPoint jionpoint ) throws Throwable {
		long beginTime = System.currentTimeMillis();
		jionpoint.proceed();
		long endTime = System.currentTimeMillis();
		// ��ȡ�����õķ�����
		String targetMethodName = jionpoint.getSignature().getName();
		// ��־��ʽ�ַ���
		String logInfoText = "����֪ͨ��" + targetMethodName + "��������ǰʱ��" + beginTime + "����," + "���ú�ʱ��" + endTime + "����";
		// ����־��Ϣд�����õ��ļ���
		logger.debug( logInfoText );
	}
}
