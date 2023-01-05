package com.bs.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.vo.Demo;

public interface DemoUpdateDao {
	Demo selectDemo(SqlSessionTemplate session, int no);
	int updateDemo(SqlSessionTemplate session, Demo d);
}
