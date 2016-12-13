/**
 * 
 */
package com.young.shadowsocks.frame;

import java.util.List;
import java.util.Map;

import com.young.shadowsocks.controller.Page;

/**
 * @author Administrator
 *
 */
public interface HibernateDao {
	public List<Map<String, Object>> findObjectListByPage(final Map<Integer, Object> paramMap, final String sql,
			final Page myPage);//sql分页
	
	public List<Map<String, Object>> findObjectList(final Map<Integer, Object> paramMap, final String sql);//sql查询
	
	public List<Map<String, Object>> findObjectProcduresList(Map<String ,Object> map,String procdures);
	
	public Map<String, Object> findObjectProcduresMap(Map<String,Object> map,String procdures);
	
	public List<Map<String, Object>> findObjectList( final String sql);//sql查询
	
	public void updateObjectProcduresMap(Map<String,Object> map,String procdures);
	
	public Map<String, Object> findObject(Map<Integer, Object> paramMap,String sql);
}
