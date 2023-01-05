package com.bs.spring.demo.model.service;

import com.bs.spring.member.vo.Demo;

public interface DemoUpdateService {
	
	Demo selectDemo(int no);
	
	int updateDemo(Demo d);
}
