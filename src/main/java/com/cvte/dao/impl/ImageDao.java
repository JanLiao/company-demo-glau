package com.cvte.dao.impl;

import java.util.List;

import com.cvte.dto.QulityDto;
import com.cvte.entity.ImageResult;
import com.cvte.entity.Person;
import com.cvte.entity.RiskDto;
import com.cvte.msg.Result;
import com.cvte.netty.msg.ImgInfo;

/** 
* @author: jan 
* @date: 2018年4月9日 下午6:32:49 
*/
public interface ImageDao {

	String saveImage(String fileName, String path);

	void saveResult(ImgInfo img, String imgId, String imgName);

	void savePerson(Person person, String lr, String pdfPath, int flag);

	void testDao();

	List<Person> listAllPerson();

	List<ImageResult> getAllImageResult(String hql, int page, int limit);

	Person queryByTidUid(String string);

	ImageResult queryById(String rid);

	List<ImageResult> getAllImageResult(String hql, String dateTime);

	Person queryByPid(String pid);

	Result<ImageResult> getPageResult(String hql, String dateTime, int page, int limit);

	RiskDto queryRisk(int i, String sex);

	QulityDto getQulityDto(int qulity, String sex);

	String getUidByImageId(String imgid);

	String queryByTidName(String tid, String imgName);

}
