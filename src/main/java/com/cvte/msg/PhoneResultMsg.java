package com.cvte.msg;

import java.util.List;

import com.cvte.entity.HospitalDescBean;

/**
* @author jan
* @data 2018年8月4日 下午4:54:32
*/
public class PhoneResultMsg<T> {

	private int count;
	private boolean error;
	private List<T> results;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
}
