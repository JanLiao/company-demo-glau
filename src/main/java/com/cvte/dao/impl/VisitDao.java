package com.cvte.dao.impl;

import java.util.List;

import com.cvte.dto.CDRDto;
import com.cvte.dto.VisitDto;

/** 
* @author: jan 
* @date: 2018年4月20日 下午4:54:18 
*/
public interface VisitDao {

	VisitDto getVisitDto(String tid, String date, String sex);

	List<CDRDto> getAllCDR();

}
