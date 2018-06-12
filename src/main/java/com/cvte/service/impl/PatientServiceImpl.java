package com.cvte.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvte.cons.Constant;
import com.cvte.dao.impl.ImageDao;
import com.cvte.dto.PersonDto;
import com.cvte.dto.QulityDto;
import com.cvte.entity.EyeInfo;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Person;
import com.cvte.entity.RiskDto;
import com.cvte.msg.Result;
import com.cvte.util.HqlString;
import com.cvte.util.PersonDtoUtil;

/** 
* @author: jan 
* @date: 2018年4月12日 下午8:17:48 
*/
@Service("patientService")
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private ImageDao imgDao;
	
	private Logger logger = Logger.getLogger(PatientServiceImpl.class);

	@Override
	public List<String> getAllTerminal() {
		List<Person> list = imgDao.listAllPerson();
		List<String> listTerminal = getAllTerminalId(list);
		return listTerminal;
	}

	private List<String> getAllTerminalId(List<Person> list) {
		List<String> tid = new ArrayList<String>();
		for(Person p : list) {
			if(!checkTid(p.getTid(), tid)) {
				tid.add(p.getTid());
			}
		}
		return tid;
	}

	private boolean checkTid(String tid, List<String> list) {
		boolean flag = false;
		for(String str : list) {
			if(tid.equals(str)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public Result<PersonDto> saveToMap(String param, String dateTime, int page, int limit, String className) {
		//Map<String, String> map = new HashMap<String, String>();
		String[] str = param.split(",");
		System.out.println("str length= " + str.length);
//		if("".equals(param) || str.length % 3 != 0) {
//			
//		}else {
//			Result<PersonDto> result = new Result<PersonDto>();
//			result.setFail(1);
//			result.setMsg("查询条件有误");
//			return result;
//		}

		if(str.length % 3 != 0) {
			Result<PersonDto> result = new Result<PersonDto>();
			result.setFail(1);
			result.setMsg("查询条件有误");
			return result;
		}else {
			if("".equals(dateTime)) {
				String hql = HqlString.generateHql(param, className);
				System.out.println("generate hql string=" + hql);
				List<ImageResult> list = imgDao.getAllImageResult(hql, page, limit);
				List<String> set = new ArrayList<String>();
//				for(int i = 0; i < list.size(); i++) {
//					String uid = list.get(i).getImgName().split("_")[1];
//					if(!checkSet(list.get(i).getTid() + "," + uid + "," + list.get(i).getImgId() + "," + i, set)) {
//						set.add(list.get(i).getTid() + "," + uid + "," + list.get(i).getImgId() + "," + i);
//					}
//				}
				Set<String> set1 = new HashSet<String>();
				for(ImageResult img : list) {
					String uid = img.getImgName().split("_")[1];
//					if(!checkSet(img.getTid().trim() + "," + uid.trim(), set)) {
//						set.add(img.getTid().trim() + "," + uid.trim());
//					}
					//System.out.println("tiduid=" + img.getTid().trim() + "," + uid.trim());
					set1.add(img.getTid().trim() + "," + uid.trim());
				}
				for(String s : set1) {
					set.add(s);
				}
				//System.out.println("flag=" + set.get(0).equals(set.get(1)));
				//for(String s : set) {
				//	System.out.println("set=" + s);
				//}
				//System.out.println("recored size=" + set.size() + "=" + list.size());
				List<PersonDto> personList = new ArrayList<PersonDto>();
				List<String> pageRecord = new ArrayList<String>();
				int totalRecord = set.size();
				if(page * limit < totalRecord) {
					int start = (page - 1)*limit;
					int end = page*limit;
					for(int i = start; i < end; i++) {
						pageRecord.add(set.get(i));
						Person p = imgDao.queryByTidUid(set.get(i));
						ImageResult lResult = null;
						ImageResult rResult = null;
						if(p.getLid() == null || "".equals(p.getLid())) {
							//lResult = imgDao.queryById("");
						}else {
							lResult = imgDao.queryById(p.getLid());
						}
						
						if(p.getRid() == null || "".equals(p.getRid())) {
							//rResult = imgDao.queryById(p.getRid());
						}else {
							rResult = imgDao.queryById(p.getRid());
						}
						PersonDto dto = PersonDtoUtil.processPerson(lResult, rResult);
						dto.setSex(p.getSex());
						dto.setPid(p.getPid());
						dto.setPdfUrl(p.getLrpdf());
						personList.add(dto);
					}
				}else {
					int start = (page - 1)*limit;
					int end = set.size();
					for(int i = start; i < end; i++) {
						pageRecord.add(set.get(i));
						Person p = imgDao.queryByTidUid(set.get(i));
						ImageResult lResult = null;
						ImageResult rResult = null;
						if(p.getLid() == null || "".equals(p.getLid())) {
							//lResult = imgDao.queryById("");
						}else {
							lResult = imgDao.queryById(p.getLid());
						}
						
						if(p.getRid() == null || "".equals(p.getRid())) {
							//rResult = imgDao.queryById(p.getRid());
						}else {
							rResult = imgDao.queryById(p.getRid());
						}
						PersonDto dto = PersonDtoUtil.processPerson(lResult, rResult);
						dto.setSex(p.getSex());
						dto.setPid(p.getPid());
						dto.setPdfUrl(p.getLrpdf());
						personList.add(dto);
					}
				}
				Result<PersonDto> result = new Result<PersonDto>();
				result.setCount(set.size());
				result.setFail(0);
				result.setList(personList);
				System.out.println("查询结果=" + personList);
				//System.out.println("查询结果=" + list);
				return result;
			}else {
				String hql = HqlString.generateHql(param, className);
				List<ImageResult> list = imgDao.getAllImageResult(hql, dateTime);
				List<String> set = new ArrayList<String>();
				Set<String> set1 = new HashSet<String>();
				for(ImageResult img : list) {
					String uid = img.getImgName().split("_")[1];
					set1.add(img.getTid().trim() + "," + uid.trim());
					//if(!checkSet(img.getTid().trim() + "," + uid.trim(), set)) {
						//set.add(img.getTid().trim() + "," + uid.trim());
					//}
				}
				for(String s : set1) {
					set.add(s);
				}
				
				List<PersonDto> personList = new ArrayList<PersonDto>();
				List<String> pageRecord = new ArrayList<String>();
				int totalRecord = set.size();
				if(page * limit < totalRecord) {
					int start = (page - 1)*limit;
					int end = page*limit;
					for(int i = start; i < end; i++) {
						pageRecord.add(set.get(i));
						Person p = imgDao.queryByTidUid(set.get(i));
						ImageResult lResult = null;
						ImageResult rResult = null;
						if(p.getLid() == null || "".equals(p.getLid())) {
							//lResult = imgDao.queryById("");
						}else {
							lResult = imgDao.queryById(p.getLid());
						}
						
						if(p.getRid() == null || "".equals(p.getRid())) {
							//rResult = imgDao.queryById(p.getRid());
						}else {
							rResult = imgDao.queryById(p.getRid());
						}
						PersonDto dto = PersonDtoUtil.processPerson(lResult, rResult);
						dto.setSex(p.getSex());
						dto.setPid(p.getPid());
						dto.setPdfUrl(p.getLrpdf());
						personList.add(dto);
					}
				}else {
					int start = (page - 1)*limit;
					int end = set.size();
					for(int i = start; i < end; i++) {
						pageRecord.add(set.get(i));
						Person p = imgDao.queryByTidUid(set.get(i));
						ImageResult lResult = null;
						ImageResult rResult = null;
						if(p.getLid() == null || "".equals(p.getLid())) {
							//lResult = imgDao.queryById("");
						}else {
							lResult = imgDao.queryById(p.getLid());
						}
						
						if(p.getRid() == null || "".equals(p.getRid())) {
							//rResult = imgDao.queryById(p.getRid());
						}else {
							rResult = imgDao.queryById(p.getRid());
						}
						PersonDto dto = PersonDtoUtil.processPerson(lResult, rResult);
						dto.setSex(p.getSex());
						dto.setPid(p.getPid());
						dto.setPdfUrl(p.getLrpdf());
						personList.add(dto);
					}
				}
				Result<PersonDto> result = new Result<PersonDto>();
				result.setCount(set.size());
				result.setFail(0);
				result.setList(personList);
				System.out.println("dto reuslt=" + result);
				return result;
			}
		}
	}

	@SuppressWarnings("unused")
	private boolean checkSet(String person, List<String> set) {
		boolean flag = false;
		if(set.size() == 0) {
			set.add(person);
		}else {
			for(String s : set) {
				if(s.equals(person)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public Person queryById(String pid) {
		Person p = imgDao.queryByPid(pid);
		return p;
	}

	@Override
	public List<EyeInfo> queryByPid(String pid) {
		List<EyeInfo> list = new ArrayList<EyeInfo>();
		Person p = imgDao.queryByPid(pid);
		if(p.getLid() == null || "".equals(p.getLid())) {
			logger.info("该UID没有拍左眼");
			EyeInfo info = new EyeInfo();
			info.setCdr("无");
			info.setCupconf("无");
			info.setE_result1("../img/404.jpg");
			info.setE_result2("../img/404.jpg");
			info.setE_url("../img/404.jpg");
			info.setEid("");
			info.setFullconf("无");
			info.setPercent1("0%");
			info.setPercent2("0%");
			info.setPercent3("0%");
			info.setPercent4("0%");
			info.setQulity(-1);
			info.setFlag(0);
			list.add(info);
		}else {
			ImageResult img = imgDao.queryById(p.getLid());
			logger.info("获取到左眼===数据");
			EyeInfo info = transferToEyeInfo(img, 0);
			list.add(info);
		}
		
		if(p.getRid() == null || "".equals(p.getRid())) {
			logger.info("该UID没有拍右眼");
			EyeInfo info = new EyeInfo();
			info.setCdr("无");
			info.setCupconf("无");
			info.setE_result1("../img/404.jpg");
			info.setE_result2("../img/404.jpg");
			info.setE_url("../img/404.jpg");
			info.setEid("");
			info.setFullconf("无");
			info.setPercent1("0%");
			info.setPercent2("0%");
			info.setPercent3("0%");
			info.setPercent4("0%");
			info.setQulity(-1);
			info.setFlag(1);
			list.add(info);
		}else {
			ImageResult img = imgDao.queryById(p.getRid());
			logger.info("获取到右眼===数据");
			EyeInfo info = transferToEyeInfo(img, 1);
			list.add(info);
		}
		return list;
	}

	private EyeInfo transferToEyeInfo(ImageResult img, int flag) {
		EyeInfo info = new EyeInfo();
		info.setFlag(flag);
		info.setCdr("" + img.getCdr());
		info.setCupconf(img.getOc());
		info.setE_result1("../" + img.getFullPath());
		info.setE_result2("../" + img.getCupPath());
		info.setE_url("../" + img.getImgUrl());
		info.setEid(img.getImgName().split("_")[1]);
		info.setFullconf(img.getOd());
		info.setPercent1("" + img.getGlaucomaRisk() + "%");
		info.setPercent2("" + img.getAmdRisk() + "%");
		info.setPercent3("" + img.getDrRisk() + "%");
		info.setPercent4("" + img.getPmRisk() + "%");
		info.setQulity(Integer.parseInt(img.getQulity()));
		return info;
	}

	@Override
	public Result<ImageResult> getResultByParam(String param, String dateTime, int page, int limit, String className) {		
		String hql = generateHql(param, className);
		if("".equals(hql)) {
			Result<ImageResult> msg = new Result<ImageResult>();
			msg.setFail(1);
			msg.setMsg("传入参数有误");
			return msg;
		}
		Result<ImageResult> result = imgDao.getPageResult(hql, dateTime, page, limit);
		return result;
	}

	private String generateHql(String param, String className) {
		String[] str = param.split(",");
		String hql = "";
		int flag = 0;  //判断是否有tid
		int riskflag = 0; //判断是否有risk
		int normalFlag = 0;  //normal=0不正常
		if("".equals(param)) {
			hql = "from " + className + " ";
		}else {
			if(str.length % 3 != 0) {
				return "";
			}else {
				int len = str.length / 3;
				for(int i = 0; i < len; i++) {
					if("tid".equals(str[3 * i])) {
						flag = 1;
					}else if("risk".equals(str[3 * i])) {
						riskflag = 1;
					}else if("normal".equals(str[3 * i])) {
						normalFlag = Integer.parseInt(str[3 * i + 2]);
					}
				}
				
				//param是否有终端ID
				if(flag == 1) {
					for(int i = 0; i < len; i++) {
						if("tid".equals(str[3 * i])) {
							hql = hql + "from " + className + " where tid = " + str[3 * i + 2] + "  and ";
						}
					}
				}else if(flag == 0) {
					hql = hql + "from " + className + " where ";
				}
				
				//param是否含有风险risk
				if(riskflag == 1) {
					for(int i = 0; i < len; i++) {
						if("risk".equals(str[3 * i])) {
							if("1".equals(str[3 * i + 2])) {
								if(normalFlag == 0) {
									hql = hql + "  glaucomaRisk >= " + Constant.Threshold + " ";  
								}else if(normalFlag == 1) {
									hql = hql + "  glaucomaRisk <= " + Constant.Threshold + " ";
								}
							}else if("2".equals(str[3 * i + 2])) {
								if(normalFlag == 0) {
									hql = hql + "  amdRisk >=  " + Constant.Threshold + " ";  
								}else if(normalFlag == 1) {
									hql = hql + "  amdRisk <= " + Constant.Threshold + " ";
								}
							}else if("3".equals(str[3 * i + 2])) {
								if(normalFlag == 0) {
									hql = hql + "  drRisk >= " + Constant.Threshold + " ";  
								}else if(normalFlag == 1) {
									hql = hql + "  drRisk <= " + Constant.Threshold + " ";
								}
							}else if("4".equals(str[3 * i + 2])) {
								if(normalFlag == 0) {
									hql = hql + "  pmRisk >= " + Constant.Threshold + " ";  
								}else if(normalFlag == 1) {
									hql = hql + "  pmRisk <= " + Constant.Threshold + " ";
								}
							}
						}
					}
				}else if(riskflag == 0) {
					if(normalFlag == 0) {
						hql = hql + " glaucomaRisk >= " + Constant.Threshold  + " or "
								+ " amdRisk >= " + Constant.Threshold + " or "
								+ " drRisk >= " + Constant.Threshold + " or "
								+ " pmRisk >= " + Constant.Threshold + " ";
					}else if(normalFlag == 1) {
						hql = hql + " glaucomaRisk <= " + Constant.Threshold + " and "
								+ " amdRisk <= " + Constant.Threshold + " and "
								+ " drRisk <= " + Constant.Threshold + " and "
								+ " pmRisk <= " + Constant.Threshold + " ";
					}
				}
			}
		}
		return hql;
	}

	@Override
	public String queryByTidAndUid(String tid, String imgName) {
		if("".equals(tid) || "".equals(imgName)) {
			return "";
		}else {
			String[] str = imgName.split("_");
			String tiduid = tid.trim() + "," + str[1].trim();
			Person p = imgDao.queryByTidUid(tiduid);
			return p.getPid();
		}
	}

	@Override
	public List<RiskDto> riskAnalysis(String param, String sex) {
		String[] str = param.split(",");
		List<RiskDto> list = new ArrayList<RiskDto>();
		if(str.length != 4 || "".equals(param)) {
			return list;
		}else {
			for(int i = 0; i < str.length; i++) {
				if("0".equals(str[i])) {
					logger.info("当前条件不通过");
				}else if("1".equals(str[i])) {
					RiskDto dto = imgDao.queryRisk(i, sex);
					list.add(dto);
				}
			}
		}
		return list;
	}

	@Override
	public List<QulityDto> getQulityBySex(String sex) {
		List<QulityDto> list = new ArrayList<QulityDto>();
		for(int i = 0; i < 3; i++) {
			QulityDto dto = imgDao.getQulityDto(i, sex);
			list.add(dto);
		}
		return list;
	}
	
	

}
