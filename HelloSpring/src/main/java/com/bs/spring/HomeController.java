package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/* 등록되어있는 springbean은 필드선언 해서 사용 */
	@Autowired 
	@Qualifier(value="alonge")
	private Animal a;
	
	@Autowired
	@Qualifier(value="dog")
	private Animal b;
	
	@Autowired
	@Qualifier(value="getDongmin")
	private Person p;
	
	@Autowired(required = false)
	private Food food;
	
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("/")
	public String index(HttpServletRequest req,HttpServletResponse res,HttpSession session) {
		//쿠키추가하기
		Cookie c=new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		session.setAttribute("sessionId", "admin");
		
		//등록된 springbean출력하기
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");
		
		System.out.println(p);
		System.out.println(food);
//		System.out.println(alonge);		
//		System.out.println("dog : "+dog);
		//메인화면을 출력해주는 mapping 메소드
		// /WEB-INF/views/return값.jsp 
		//	-> request.getRequestDispatcher("/WEB-INF/views/return값.jsp").forward(req,res);
		
		
		
		
		return "index";
	}
}








