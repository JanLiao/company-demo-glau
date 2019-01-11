package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cvte.dao.impl.PhoneDao;
import com.cvte.entity.Report;

/**
* @author jan
* @data 2018年8月15日 下午3:50:34
*/
public class JunitTest extends BaseJunitTest {

	@Autowired
	private PhoneDao phoneDao;
	
	@Test
	public void test1() {
		Report report = new Report();
		report.setId(1);
		report.setAccountId(1);
		report.setlImgId(1);
		report.setrImgId(2);
		phoneDao.saveReport(report);
	}
}
