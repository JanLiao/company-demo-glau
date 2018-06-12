package com.cvte.msg;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月23日 下午3:51:09 
*/
public class UserResultMsg<T> {

	private int code;
	
	private String msg;
	
	private String updateApp;
	
	private List<T> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUpdateApp() {
		return updateApp;
	}

	public void setUpdateApp(String updateApp) {
		this.updateApp = updateApp;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public UserResultMsg(int code, String msg, String updateApp, List<T> data) {
		super();
		this.code = code;
		this.msg = msg;
		this.updateApp = updateApp;
		this.data = data;
	}

	public UserResultMsg() {
		super();
	}

	@Override
	public String toString() {
		return "UserResultMsg [code=" + code + ", msg=" + msg + ", list=" + data + "]";
	}

}
