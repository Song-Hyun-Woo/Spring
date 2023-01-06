package com.bs.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.vo.Member;

public interface MemberService {
	void test();
	Member selectMemberById(Member m);
	
	int insertMember(Member m);
	
	Member login(Member m);
	
}
