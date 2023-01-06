package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		// TODO Auto-generated constructor stub
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
	
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("controller - test() 실행");
//		service.test();
//		
//		//MemberService s=new MemberServiceImpl();
//	}
	
	@RequestMapping("/loginMember.do")
	/*public String login(String userId, String password) {*/
//	public String login(@RequestParam Map param,) {
	//public String login(Member m,HttpSession session) {
	public String login(Member m, Model model) {
		//Session에 데이터를 저장하고 관리
		Member loginMember=service.selectMemberById(m);
		
		//암호화된 패스워드를 원본 값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비굘르 해야한다.
		//matches("원본값", 암호화값)메소드를 이용
		
		if(loginMember!=null&& 
				//loginMember.getPassword().equals(m.getPassword())) {
				passwordEncoder.matches(m.getPassword(), loginMember.getPassword())){
			//로그인성공
			//session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember", loginMember);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	//public String logout(HttpSession session) {
	public String logout(SessionStatus session) {	
		if(!session.isComplete()) { //session 확인
			session.setComplete(); //session데이터 삭제
		}
		
		//session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/enrollmember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		System.out.println(m);
		
		//password 암호화처리
		String encodePassword=passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
		int result=service.insertMember(m);
		if(result>0) {
			mv.addObject("msg","회원가입 완료");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg","회원가입 실패");
			mv.addObject("loc","/member/enrollmember.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/memberView.do")
	public String memberview(Member m,Model model) {
		Member viewMember=service.selectMemberById(m);
		model.addAttribute("member",viewMember);
		return "member/memberView";
	}
}	