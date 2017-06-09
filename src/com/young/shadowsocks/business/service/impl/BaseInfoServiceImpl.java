/**
 * 
 */
package com.young.shadowsocks.business.service.impl;


import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.young.shadowsocks.business.service.BaseInfoService;
import com.young.shadowsocks.entity.UserInfo;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
/**
 * @author Administrator
 *
 */
@Repository
public class BaseInfoServiceImpl extends HibernateDaoSupport implements BaseInfoService {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}
	@Override
	public UserInfo getUserInfo(String code) {
//		String sql = "select id,code,name,pwd from table_users where code=?";
//		Query query = getSession().createSQLQuery(sql);
//		query.setParameter(0, code);
//		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list().toString();
		String hql="from UserInfo where code=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, code);
		return (UserInfo)query.uniqueResult();
//		query.setParameter(0, userCode);
//		return query.uniqueResult().toString();
//		return query.setResultTransformer(
//				Transformers.ALIAS_TO_ENTITY_MAP).toString();
	}
	@Override
	public void insertUserInfo(UserInfo info) {
		// TODO 自动生成的方法存根
//		String sql="insert table_users values()"
		
		try {
			getSession().save(info);
		} catch (Exception e) {
			// TODO: handle exception
		
		}
		}
		
		@Override
		public void deleteUserInfo(UserInfo info) {
			// TODO 自动生成的方法存根
//			String sql="insert table_users values()"
//		
//			try {
//				Transaction transaction=getSession().beginTransaction();
//				Session session=getSession();
//				session.delete(info);
//				transaction.commit();
//				
//			} catch (Exception e) {
//				// TODO: handle exception
//			
//			}
			try {
				String hql="delete UserInfo as p where p.code=?";
Session session=getSession();
				Query query=getSession().createQuery(hql);

				query.setParameter(0, info.getCode());

				query.executeUpdate();

				session.beginTransaction().commit();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
	}
}
