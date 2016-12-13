/**
 * 
 */
package com.young.shadowsocks.frame.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.young.shadowsocks.controller.Page;
import com.young.shadowsocks.frame.HibernateDao;

/**
 * @author Administrator
 *
 */
public class HibernateDaoImpl implements HibernateDao {

	@Autowired
	private HibernateTemplate hibernateTemplateOracle;

	/**
	 * hirbernate sql 鍒嗛〉鍏叡鏂规硶
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> findObjectListByPage(final Map<Integer, Object> paramMap, final String sql,
			final Page myPage) {
		return (List<Map<String, Object>>) hibernateTemplateOracle.execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				Set<Integer> set = paramMap.keySet();
				for (Integer integer : set) {
					query.setParameter(integer, paramMap.get(integer));
				}
				query.setFirstResult((myPage.getPageNo() - 1) * myPage.getPageSize());
				query.setMaxResults(myPage.getPageSize());
				return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
	}

	/**
	 * sql鏌ヨ(甯﹀弬鏁�)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> findObjectList(final Map<Integer, Object> paramMap, final String sql) {
		return (List<Map<String, Object>>) hibernateTemplateOracle.execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				Set<Integer> set = paramMap.keySet();
				for (Integer integer : set) {
					query.setParameter(integer, paramMap.get(integer));
				}
				return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});

	}
	
	/**
	 * sql 璋冪敤瀛樺偍杩囩▼杩斿洖List
	 */
	@Override
	public List<Map<String, Object>> findObjectProcduresList(Map<String,Object> map, String procdures) {
		try{
			Session session=hibernateTemplateOracle.getSessionFactory().openSession();
			@SuppressWarnings("deprecation")
			Connection connection=session.connection();
			CallableStatement sp=connection.prepareCall(procdures);
			//Map<String,Object> map=JSONUtilNew.jsonToMap(params);
			Set<String> set = map.keySet();
			for (String i : set) {
				sp.setObject(Integer.parseInt(i), map.get(i));
			}
			Integer index=set.size()+1;
			sp.registerOutParameter(index, oracle.jdbc.driver.OracleTypes.CURSOR);
			 sp.execute(); // 鎵ц瀛樺偍杩囩▼  
		     ResultSet rs = (ResultSet) sp.getObject(index);
		     ResultSetMetaData rsm   =rs.getMetaData(); //鑾峰緱鍒楅泦
		     int col=rs.getMetaData().getColumnCount();//寰楀埌缁撴灉闆嗙殑鍒楁暟
		 	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		     while (rs.next()) {  
		          Map<String, Object> map1=new HashMap<String, Object>();
		          for (int i = 1; i < col+1; i++) {
		        	  map1.put(rsm.getColumnName(i), rs.getObject(i));
				}
		          list.add(map1);
		     }
		    session.close();
		    return list;
		}catch(Exception e){
		 e.printStackTrace();
			return null;
		}
	}

	/**
	 * 瀛樺偍杩囩▼鍒嗚   杩斿洖map闆嗗悎
	 */
	@Override
	public Map<String, Object> findObjectProcduresMap(Map<String,Object> map, String procdures) {
		try{
			Session session=hibernateTemplateOracle.getSessionFactory().openSession();
			@SuppressWarnings("deprecation")
			Connection connection=session.connection();
			CallableStatement sp=connection.prepareCall(procdures);
			Set<String> set = map.keySet();
			for (String i : set) {
				Object para=map.get(i);
				sp.setObject(Integer.parseInt(i),para );
			}
			Integer index=set.size()+1;
			sp.registerOutParameter(index, oracle.jdbc.driver.OracleTypes.CURSOR);
			
			 sp.execute(); // 鎵ц瀛樺偍杩囩▼  
		     ResultSet rs = (ResultSet) sp.getObject(index);
		     ResultSetMetaData rsm   =rs.getMetaData(); //鑾峰緱鍒楅泦
		     int col=rs.getMetaData().getColumnCount();//寰楀埌缁撴灉闆嗙殑鍒楁暟
		     Map<String, Object> mapResult=new HashMap<String, Object>();
		     if(rs.next()) {  
		          for (int i = 1; i < col+1; i++) {
		        	  mapResult.put(rsm.getColumnName(i), rs.getObject(i));
				}
		     }
		     session.close();
			return mapResult;
		}catch(Exception e){
		 e.printStackTrace();
			return null;
		}
	}

	/**
	 * sql 涓嶅甫鍙傛暟
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Map<String, Object>> findObjectList(final String sql) {
		return (List<Map<String, Object>>) hibernateTemplateOracle.execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				
				return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
	}

	/**
	 * 瀛樺偍杩囩▼锛堜富瑕佹槸淇敼锛�
	 */
	
	@Override
	public void updateObjectProcduresMap(Map<String, Object> map, String procdures) {
		try{
			Session session=hibernateTemplateOracle.getSessionFactory().openSession();
			@SuppressWarnings("deprecation")
			Connection connection=session.connection();
			CallableStatement sp=connection.prepareCall(procdures);
			Set<String> set = map.keySet();
			for (String i : set) {
				Object para=map.get(i);
				sp.setObject(Integer.parseInt(i),para );
			}
			Integer index=set.size()+1;
			sp.registerOutParameter(index, oracle.jdbc.driver.OracleTypes.CURSOR);
			sp.execute(); // 鎵ц瀛樺偍杩囩▼  
		    
		}catch(Exception e){
		 e.printStackTrace();
		}
	}

	/**
	 * sql鏌ヨ 甯﹀弬鏁颁笖杩斿洖鏄痬ap绫诲瀷
	 */
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> findObject(Map<Integer, Object> paramMap,final  String sql) {
		Session session=hibernateTemplateOracle.getSessionFactory().openSession();	
		Query query = session.createSQLQuery(sql);
		Set<Integer> set = paramMap.keySet();
		for (Integer integer : set) {
			query.setParameter(integer, paramMap.get(integer));
		}
		Map<String, Object> map=new HashMap<String, Object>();
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		map= (Map<String, Object>) query.uniqueResult();
		session.close();
		return map;
	}

}
