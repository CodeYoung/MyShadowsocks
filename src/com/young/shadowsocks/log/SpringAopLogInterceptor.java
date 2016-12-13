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
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "Class：" + targetClassName;
		// 将日志信息写入配置的文件中
		logger.debug( logInfoText );
		String parameters = getParaString( jionpoint.getArgs(), targetMethodName );
		logger.debug("Method：{}",parameters );
		/*SysUser user = SessionContext.getCurrentUserInfo();
		if(user!=null){
			logger.debug( "User : {}:({})",user.getUserName(),user.getAccount() );
		}*/
	}

	@Override
	public void afterAdvice ( JoinPoint jionpoint ) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "Class：" + targetClassName + "-" + targetMethodName + " excute complete！";
		// 将日志信息写入配置的文件中
		logger.debug( logInfoText );
	}

	@Override
	public void exceptionAdvice ( JoinPoint jionpoint , Exception e ) {
		// 获取被调用的类名
		String targetClassName = jionpoint.getTarget().getClass().getName();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "异常通知：执行" + targetClassName + "类的" + targetMethodName + "方法时发生异常";
		// 将日志信息写入配置的文件中
		logger.debug( logInfoText );
	}

	@Override
	public void aroundAdvice ( ProceedingJoinPoint jionpoint ) throws Throwable {
		long beginTime = System.currentTimeMillis();
		jionpoint.proceed();
		long endTime = System.currentTimeMillis();
		// 获取被调用的方法名
		String targetMethodName = jionpoint.getSignature().getName();
		// 日志格式字符串
		String logInfoText = "环绕通知：" + targetMethodName + "方法调用前时间" + beginTime + "毫秒," + "调用后时间" + endTime + "毫秒";
		// 将日志信息写入配置的文件中
		logger.debug( logInfoText );
	}
}
