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
			return "�����޲���";
		}
		StringBuffer rs = new StringBuffer();
		rs.append( mName );
		String className = null;
		int index = 1;
		// ������������
		for ( Object info : args ) {
			// ��ȡ��������
			if(info==null){
				continue;
			}
			className = info.getClass().getSimpleName();
			//className = className.substring( className.lastIndexOf( "." ) + 1 );
			rs.append( "[para " + index + "��type:" + className + ",value��" );
				if(className.equalsIgnoreCase( "String" ) || className.equalsIgnoreCase( "Date" )){
					rs.append( "(" + info.toString() + ")" );
					continue;
				}
				if(className.equalsIgnoreCase( "boolean" ) || className.equalsIgnoreCase( "int" ) || className.equalsIgnoreCase( "Integer" )){
					rs.append( "(" + index + ")" );
					continue;
				}
			
			// ��ȡ��������з���
			Method [] methods = info.getClass().getDeclaredMethods();
			// �����������ж�get����
			for ( Method method : methods ) {
				String methodName = method.getName();
				// �ж��ǲ���get����
				if ( methodName.indexOf( "get" ) == -1 ) {// ����get����
					continue;// ������
				}
				Object rsValue = null;
				try {
					// ����get��������ȡ����ֵ
					rsValue = method.invoke( info );
					if ( rsValue == null ) {// û�з���ֵ
						continue;
					}
				} catch ( Exception e ) {
					continue;
				}
				// ��ֵ����������
				rs.append( "(" + methodName + " : " + rsValue + ")" );
			}
			rs.append( "]" );
			index++;
		}
		return rs.toString();
	}
}
