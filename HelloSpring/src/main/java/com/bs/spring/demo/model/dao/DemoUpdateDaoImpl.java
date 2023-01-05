package com.bs.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.vo.Demo;

@Repository
public class DemoUpdateDaoImpl implements DemoUpdateDao{

	@Override
	public Demo selectDemo(SqlSessionTemplate session, int no) {
		// TODO Auto-generated method stub
		return session.selectOne("demoUpdate.selectDemo",no);
	}

	@Override
	public int updateDemo(SqlSessionTemplate session, Demo d) {
		// TODO Auto-generated method stub
		return session.update("demoUpdate.updateDemo",d);
	}

	
	
}
