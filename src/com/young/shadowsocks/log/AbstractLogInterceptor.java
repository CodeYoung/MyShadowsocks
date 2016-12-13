/**
 * 
 */
package com.young.shadowsocks.log;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Administrator
 *
 */
public abstract class AbstractLogInterceptor {
	public abstract void beforeAdvice ( JoinPoint jionpoint );

	public abstract void afterAdvice ( JoinPoint jionpoint );

	public abstract void exceptionAdvice ( JoinPoint jionpoint , Exception e );

	public abstract void aroundAdvice ( ProceedingJoinPoint jionpoint ) throws Throwable;

	protected String getParaString ( Object [] args , String mName ) {
		if ( args == null || args.length==0 ) {
			return "方法无参数";
		}
		StringBuffer rs = new StringBuffer();
		rs.append( mName );
		String className = null;
		int index = 1;
		// 遍历参数对象
		for ( Object info : args ) {
			// 获取对象类型
			if(info==null){
				continue;
			}
			className = info.getClass().getSimpleName();
			//className = className.substring( className.lastIndexOf( "." ) + 1 );
			rs.append( "[para " + index + "，type:" + className + ",value：" );
				if(className.equalsIgnoreCase( "String" ) || className.equalsIgnoreCase( "Date" )){
					rs.append( "(" + info.toString() + ")" );
					continue;
				}
				if(className.equalsIgnoreCase( "boolean" ) || className.equalsIgnoreCase( "int" ) || className.equalsIgnoreCase( "Integer" )){
					rs.append( "(" + index + ")" );
					continue;
				}
			
			// 获取对象的所有方法
			Method [] methods = info.getClass().getDeclaredMethods();
			// 遍历方法，判断get方法
			for ( Method method : methods ) {
				String methodName = method.getName();
				// 判断是不是get方法
				if ( methodName.indexOf( "get" ) == -1 ) {// 不是get方法
					continue;// 不处理
				}
				Object rsValue = null;
				try {
					// 调用get方法，获取返回值
					rsValue = method.invoke( info );
					if ( rsValue == null ) {// 没有返回值
						continue;
					}
				} catch ( Exception e ) {
					continue;
				}
				// 将值加入内容中
				rs.append( "(" + methodName + " : " + rsValue + ")" );
			}
			rs.append( "]" );
			index++;
		}
		return rs.toString();
	}
}
