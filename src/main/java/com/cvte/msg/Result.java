package com.cvte.msg;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月13日 下午12:15:00 
*/
public class Result<T> {

	private int count;
	
	private int fail;
	
	private String msg;
	
	private List<T> list;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFail() {
		return fail;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Result(int count, int fail, String msg, List<T> list) {
		super();
		this.count = count;
		this.fail = fail;
		this.msg = msg;
		this.list = list;
	}

	public Result() {
		super();
	}

	@Override
	public String toString() {
		return "Result [count=" + count + ", fail=" + fail + ", msg=" + msg + ", list=" + list + "]";
	}

}
