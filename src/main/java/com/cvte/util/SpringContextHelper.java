package com.cvte.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
* @author : jan
* @date : 2018年4月9日 下午6:20:48 
*/
public class SpringContextHelper implements ApplicationContextAware{

	 private static ApplicationContext context = null;

	    //提供一个接口，获取容器中的Bean实例，根据名称获取
	    public static Object getBean(String beanName)
	    {
	        return context.getBean(beanName);
	    }
	    @Override
	    public void setApplicationContext(ApplicationContext applicationContext)
	            throws BeansException {
	        context = applicationContext;

	    }
	
}
