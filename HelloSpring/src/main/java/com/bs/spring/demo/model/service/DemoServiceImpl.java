package com.bs.spring.demo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.member.vo.Demo;

@Service
public class DemoServiceImpl implements DemoService{

	private DemoDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public DemoServiceImpl(DemoDao dao,SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}
	
	@Override
	public int insertDemo(Demo demo) {	
		return dao.insertDemo(session, demo);
	}
	@Override
	public List<Demo> selectDemoList(){
		return dao.selectDemoList(session);
	}
}
