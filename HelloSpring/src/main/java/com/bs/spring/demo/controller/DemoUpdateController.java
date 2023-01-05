package com.bs.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.service.DemoUpdateService;
import com.bs.spring.member.vo.Demo;

@Controller
public class DemoUpdateController {
	
	private DemoUpdateService service;
	
	@Autowired
	public DemoUpdateController(DemoUpdateService service) {
		// TODO Auto-generated constructor stub
		this.service=service;
	}
	
	@RequestMapping("/demo/updatedemo.do")
	public String updateViewpage(int no,Model m) {
		Demo d=service.selectDemo(no);
		m.addAttribute("demo",d);
		return "demo/demoupdate";
	}
	
	@RequestMapping("/demo/updatedemoend.do")
	public String updateDemo(Demo demo) {
		
		int result=service.updateDemo(demo);
		
		return "redirect:/demo/demolist.do";
	}
	
}





