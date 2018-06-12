package com.cvte.service.impl;

import java.util.List;

import com.cvte.dto.PersonDto;
import com.cvte.dto.QulityDto;
import com.cvte.dto.VisitDto;
import com.cvte.entity.EyeInfo;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Person;
import com.cvte.entity.RiskDto;
import com.cvte.msg.Result;

/** 
* @author: jan 
* @date: 2018年4月12日 下午8:17:22 
*/
public interface PatientService {

	List<String> getAllTerminal();

	Result<PersonDto> saveToMap(String param, String dateTime, int page, int limit, String className);

	Person queryById(String pid);

	List<EyeInfo> queryByPid(String pid);

	Result<ImageResult> getResultByParam(String param, String dateTime, int page, int limit, String string);

	String queryByTidAndUid(String tid, String imgName);

	List<RiskDto> riskAnalysis(String param, String sex);

	List<QulityDto> getQulityBySex(String sex);

}
