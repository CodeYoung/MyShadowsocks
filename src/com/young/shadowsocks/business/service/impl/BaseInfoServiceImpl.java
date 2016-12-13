/**
 * 
 */
package com.young.shadowsocks.business.service.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
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
	public UserInfo getUserInfo(String userCode) {
		String sql = "from table_users where usercode=? ";
		Query query = getSession().createQuery(sql);
		query.setParameter(0, userCode);
		return (UserInfo)query.uniqueResult();
	}
}
