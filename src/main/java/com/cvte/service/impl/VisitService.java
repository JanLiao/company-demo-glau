package com.cvte.service.impl;

import java.util.List;

import com.cvte.dto.CDRDto;
import com.cvte.dto.VisitDto;

/** 
* @author: jan 
* @date: 2018年4月20日 下午4:51:51 
*/
public interface VisitService {

	List<VisitDto> visitAnalysis(List<String> terminal, String date, String sex);

	List<CDRDto> getAllCdr();

}
