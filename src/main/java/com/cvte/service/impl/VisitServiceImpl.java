package com.cvte.service.impl;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvte.dao.impl.VisitDao;
import com.cvte.dto.CDRDto;
import com.cvte.dto.VisitDto;

/** 
* @author: jan 
* @date: 2018年4月20日 下午4:52:23 
*/
@Service("visitService")
public class VisitServiceImpl implements VisitService {

	@Autowired
	private VisitDao visitDao;
	
	private Logger logger = Logger.getLogger(VisitServiceImpl.class);
	
	@Override
	public List<VisitDto> visitAnalysis(List<String> terminal, String date, String sex) {
		List<VisitDto> list = new ArrayList<VisitDto>();
		if(terminal == null || terminal.size() == 0) {
			logger.info("终端为空");
			return list;
		}else {
			for(String tid : terminal) {
				VisitDto dto = visitDao.getVisitDto(tid, date, sex);
				list.add(dto);
			}
			logger.info("终端数据返回=" + list.size());
			return list;
		}
	}

	@Override
	public List<CDRDto> getAllCdr() {
		return visitDao.getAllCDR();
	}

	
	
}
