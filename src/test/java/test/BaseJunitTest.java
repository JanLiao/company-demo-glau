package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
* @author jan
* @data 2018年8月15日 下午3:47:51
*/
@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"classpath*:/spring-mvc.xml","classpath*:/spring-hibernate.xml","classpath*:/spring.xml"})
public class BaseJunitTest {
	
}
