package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.service.MemberServiceImpl;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("/test/")
	public void test() {
		System.out.println("controller - test() 실행");
		service.test();
		
		//MemberService s=new MemberServiceImpl();
		
		
		
	}
}
