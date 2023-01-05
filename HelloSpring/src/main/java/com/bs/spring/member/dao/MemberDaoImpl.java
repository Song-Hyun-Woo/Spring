package com.bs.spring.member.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Override
	public void test() {
		System.out.println("dao - test() 실행");
	}
}
