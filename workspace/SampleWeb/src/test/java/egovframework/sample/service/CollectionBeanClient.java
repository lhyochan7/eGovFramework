package egovframework.sample.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import egovframework.sample.service.impl.CollectionBean;

public class CollectionBeanClient {

	public static void main(String[] args) {
		AbstractApplicationContext contianer=
				new GenericXmlApplicationContext("egovframework/spring/context-common.xml");
		CollectionBean bean=(CollectionBean)contianer.getBean("collectionBean");
		Properties li=bean.getAddressList();
		Set<Object>keys=li.keySet();
		for(Object key:keys) {
			System.out.println(key+"의 주소:"+li.get(key));
		}
		contianer.close();
	}

}
