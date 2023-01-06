package com.bs.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	
	@Autowired
	public MemberServiceImpl(MemberDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public void test() {
		System.out.println("service - test() 실행");
		dao.test();
	}
	
	@Override
	public Member selectMemberById(Member m) {
		return dao.selectMemberById(session,m);
	}
	
	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session,m);
	}
	
	@Override
	public Member login(Member m) {
		return dao.login(session,m);
	}

}