package com.cvte.entity;

import java.util.List;

/** 
* @author: jan 
* @date: 2018年4月19日 下午9:06:16 
*/
public class RiskDto {

	private String name;
	
	private List<String> xAxis;
	
	private List<String> series;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(List<String> xAxis) {
		this.xAxis = xAxis;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public RiskDto(String name, List<String> xAxis, List<String> series) {
		super();
		this.name = name;
		this.xAxis = xAxis;
		this.series = series;
	}

	public RiskDto() {
		super();
	}

}
