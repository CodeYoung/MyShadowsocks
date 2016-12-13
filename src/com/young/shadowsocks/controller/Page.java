/**
 * 
 */
package com.young.shadowsocks.controller;

/**
 * @author Administrator
 *分页参数封装�?
 */
public class Page {
	public int pageNo = 1;
	public int pageSize = 10000;

	public Page(){
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	public Page(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * 获得当前页的页号, 序号�?�?��, 默认�?.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号, 序号�?�?��, 低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数�? 默认�?.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数�? 低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 根据pageNo和pageSize计算当前页第�?��记录在�?结果集中的位�? 序号�?�?��.
	 */
	public int getOffset() {
		return ((pageNo - 1) * pageSize);
	}
}
