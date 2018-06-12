package com.cvte.util;

import java.text.SimpleDateFormat;

import com.cvte.dto.PersonDto;
import com.cvte.entity.ImageResult;

/** 
* @author: jan 
* @date: 2018年4月13日 下午5:11:36 
*/
public class PersonDtoUtil {

	public static PersonDto processPerson(ImageResult lResult, ImageResult rResult) {
		
		PersonDto dto = new PersonDto();
		int flag = 0;
		if(lResult == null) {
			flag = 1;
		}
		if(rResult == null) {
			flag = 2;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(flag == 1) {
			dto.setTid(rResult.getTid());
			dto.setUid(rResult.getImgName().split("_")[1]);
			dto.setAmdRisk("无 | " + rResult.getAmdRisk() + "%");
			dto.setCdr("无 | " + rResult.getCdr());
			dto.setDrRisk("无 | " + rResult.getDrRisk() + "%");
			dto.setGlaucomaRisk("无 | " + rResult.getGlaucomaRisk() + "%");
			dto.setOc("无 | " + rResult.getOc());
			dto.setOd("无 | " + rResult.getOd());
			String date = format.format(rResult.getSaveDate());
			dto.setPdtTime(date);
			dto.setPmRisk("无 | " + rResult.getPmRisk() + "%");
			dto.setQulity("无 | " + rResult.getQulity());
		}else if(flag == 0) {
			dto.setTid(lResult.getTid());
			dto.setUid(lResult.getImgName().split("_")[1]);
			dto.setAmdRisk(lResult.getAmdRisk() + "%" + " | " + rResult.getAmdRisk() + "%");
			dto.setCdr(lResult.getCdr() + " | " + rResult.getCdr());
			dto.setDrRisk(lResult.getDrRisk() + "%" + " | " + rResult.getDrRisk() + "%");
			dto.setGlaucomaRisk(lResult.getGlaucomaRisk() + "%" + " | " + rResult.getGlaucomaRisk() + "%");
			dto.setOc(lResult.getOc() + " | " + rResult.getOc());
			dto.setOd(lResult.getOd() + " | " + rResult.getOd());
			String date = format.format(lResult.getSaveDate());
			dto.setPdtTime(date);
			dto.setPmRisk(lResult.getPmRisk() + "%" + " | " + rResult.getPmRisk() + "%");
			dto.setQulity(lResult.getQulity() + " | " + rResult.getQulity());
		}else if(flag == 2) {
			dto.setTid(lResult.getTid());
			dto.setUid(lResult.getImgName().split("_")[1]);
			dto.setAmdRisk(lResult.getAmdRisk() + "%" + " | 无");
			dto.setCdr(lResult.getCdr() + " | 无");
			dto.setDrRisk(lResult.getDrRisk() + "%" + " | 无");
			dto.setGlaucomaRisk(lResult.getGlaucomaRisk() + "%" + " | 无");
			dto.setOc(lResult.getOc() + " | 无");
			dto.setOd(lResult.getOd() + " | 无");
			String date = format.format(lResult.getSaveDate());
			dto.setPdtTime(date);
			dto.setPmRisk(lResult.getPmRisk() + "%" + " | 无");
			dto.setQulity(lResult.getQulity() + " | 无");
		}
		
		return dto;
	}

}
