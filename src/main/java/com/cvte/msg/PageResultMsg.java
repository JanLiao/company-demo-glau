package com.cvte.msg;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月12日 下午5:00:40 
*/
public class PageResultMsg<T> {

	private int code;
	
	private int fail;
	
	private String msg;
	
	private int count;
	
	private List<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public PageResultMsg() {
		super();
	}

	public PageResultMsg(int code, int fail, String msg, int count, List<T> data) {
		super();
		this.code = code;
		this.fail = fail;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultMsg [code=" + code + ", fail=" + fail + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}

}
