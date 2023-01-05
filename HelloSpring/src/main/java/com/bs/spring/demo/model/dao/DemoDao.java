package com.bs.spring.demo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.vo.Demo;

public interface DemoDao {
	
	int insertDemo(SqlSessionTemplate session,Demo demo);
	
	List<Demo> selectDemoList(SqlSessionTemplate session);
	
}
