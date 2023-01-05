package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.member.vo.Demo;

@Controller
public class DemoController {
	
	private DemoService service;

	@Autowired
	public DemoController(DemoService service) {
		this.service=service;
	}
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한 서비스 주소(URL)에 맞는 메소드를 구현
	//메소드구현 할때 서비스주소와 연결해주는 어노테이션을 선언
	//@RequestMapping(연결주소[,추가 옵션설정]), 
	// - Rest방식의 서비스 구현시 사용 @GetMapping, @PostMapping.. 어노테이션사용
	
	//demo페이지는 연결하는 메소드 구현
	//반환형은 String -> SpringBean으로 등록된 ViewResolver통해 페이지를 지정된 위치에서 찾아 응답
	//매개변수는 없음
	//메소드명은 알아서해!!!
	@RequestMapping("/demo/demo.do")
	public String demoPage() {
		//InternalResourceViewResolver가 처리함.
		//InternalResourceViewResolver에 저장된 prefix, suffix의 내용을 반환값이랑 연결해서 view화면을 찾음
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		// /WEB-INF/views/demo/demo.jsp ->  RequestDispatcher.forward()를 실행함.
		//request.getRequestDispatcher("/WEB-INF/views/demo/demo.jsp").forward(request,response);
		return"demo/demo";
	}
	 
	//요청매핑메소드의 매개변수와 반환형
	//1. 반환형
	//	- String : ViewResolver에 의해서 view화면을 반환할때 사용
	//	- void : HttpResponse로 직접 응답메세지를 작성할때 사용(upload, download..)
	//	- ModelAndView : ModelAndView객체를 반환(화면정보, view에 전송할 데이터를 가지고 있음)
	//	- 클래스타입(RefferenceType) : 생성된 객체를 반환 -> json으로 반환.
	
	//2. 매개변수 -> Spring이 알아서 넣어줌!
	//	- HttpServletRequest : 서블릿의 그 녀석!! 
	//	- HttpServletResponse : 서블릿의 그 녀석!!
	//	- HttpSession : 서블릿의 그녀석!
	//	- java.util.Locale : 운영되고있는 서버의 지역정보
	//	- InputStream/Reader : 파일 IO할때 사용하는 stream객체
	//	- OutputStream/Writer : 파일 IO할때 사용하는 stream객체
	//	- 기본자료형 변수 : 클라이언트가 보낸 name명칭과 변수명이 일치하면 대입해줌!
	//    - name과 일치하지않은 변명을 사용했을때는 @RequestParam어노테이션을 이용해서 mapping할 수 있음
	//	  - 추가적인 설정이 필요할때도 @RequestParam어노테이션을 사용할 수 있음.
	//	  - * 주의 기본자료형 변수를 선언했을때는 반드시 모든 변수에 연결되는 값을 전달해야함.
	//	- 클래스(RefferenceType) 변수 : command라고 하고 클라이언트가보낸 
	//								  데이터를 지정한 타입의 클래스를 생성해서 대입해줌
	//	   - 필드명과 클라이언트가 보낸 name명이 일치하는 값
	//	- java.util.Map : 클라이언트가 보낸 데이터를 map으로 대입해줌.
	//  - Model : 서버에서 데이터를 저장하는 용도의 객체 / 1회용 데이터 저장
	//	- ModelAndView : 저장할 데이터, 화면 정보를 한번에 저장하는 객체 1회용
	
	
	// 	- 기본자료형변수선언시 @RequestHeader, @CookieValue 어노테이션을 이용하면
	//	header나 cookie의 값을 바로 저장할 수 있음
	
	//추가 어노테이션 
	//@ResponseBody -> json형태로 반환할때 사용
	//@RequestBody -> json형태로 전달된 데이터를 vo객체와 mapping해주는 것
	
	
	//서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
	//public String demo1(HttpServletRequest req, HttpServletResponse res) {
	public void demo1(HttpServletRequest req, HttpServletResponse res) 
			throws IOException,ServletException {
		System.out.println(req);
		System.out.println(res);
		
		String name=req.getParameter("devName");
		int age=Integer.parseInt(req.getParameter("devAge"));
		String gender=req.getParameter("devGender");
		String email=req.getParameter("devEmail");
		String[] devlang=req.getParameterValues("devLang");
		System.out.println(name+age+gender+email);
		for(String d : devlang) {
			System.out.println(d);
		}
		
		Demo d=Demo.builder().devName(name).devAge(age).devEmail(email)
				.devGender(gender).devLang(devlang).build();
		
//		req.setAttribute("demo",d);
//		req.getRequestDispatcher("/WEB-INF/views/demo/demoresult.jsp")
//		.forward(req,res);
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		out.print("<h1>데이터</h1>");
		//return "demo/demoresult";
	}
	
	
	//기본자료형으로 데이터 받아오기
	@RequestMapping("/demo/demo2.do")
	public String basicType(String devName, int devAge, String devEmail,
			String devGender,String[] devLang, Model model) {
		System.out.println(devName+" "+devAge+" "+devEmail+" "+devGender);
		for(String l : devLang) {
			System.out.println(l);
		}
		Demo d=Demo.builder().devName(devName).devAge(devAge).devGender(devGender)
				.devEmail(devEmail).devLang(devLang).build();
		//model객체에 데이터 저장하기 
		//key,value형식으로 데이터를 저장함
		//addAttribute()메소드를 이용
		model.addAttribute("demo",d);
		
		return "demo/demoresult";
	}
	
	
	@RequestMapping("/demo/demo3.do")
	public String requestParamTest(
			@RequestParam(value="devName") String name,
			@RequestParam(value="devAge", defaultValue = "1") int age,
			@RequestParam(value="devEmail") String email,
			@RequestParam(value="devGender") String gender,
			@RequestParam(value="devLang", required = false) String[] lang
			,Model model) {
		
		System.out.println(name+age+gender+email);
		if(lang!=null) {
			for(String d : lang) {
				System.out.println(d);
			}
		}
		
		Demo d=Demo.builder().devName(name).devAge(age).devEmail(email)
				.devGender(gender).devLang(lang).build();
	
		model.addAttribute("demo",d);
		
		return "demo/demoresult";
	}
	
	
	@RequestMapping("/demo/demo4.do")
	public String commandMappingTest(Demo demo,Model model) {
		System.out.println(demo);
		model.addAttribute("demo",demo);
		return "demo/demoresult";
	}
	
	@RequestMapping("/demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param,String[] devLang, Model model) {
		System.out.println(param);
		param.put("devLang", devLang);
		model.addAttribute("demo",param);
		return "demo/demoresult";
	}
	
	
	@RequestMapping("/demo/demo6.do")
	public String extraTest(@CookieValue(value="testData", required = false) String testData,
			@RequestHeader(value="User-agent") String userAgent,
			@SessionAttribute(value="sessionId") String id,
			@RequestHeader(value="Referer") String referer
			) {
		
		System.out.println(testData);
		
		System.out.println(userAgent);
		
		System.out.println(id);
		
		System.out.println(referer);
		
		return "demo/demoresult";
	}
	
	
	
	//ModelAndView로 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewTest(ModelAndView mv, Demo demo) {
		//ModelAndView객체는 view설정과, 데이터 저장을 같이 할 수 있는 객체
		//data저장 : addObject("key",value)메소드이용
		//view설정 : setViewName("view이름")메소드이용
		mv.addObject("demo",demo);
		mv.setViewName("demo/demoresult");
		
		return mv;
	}
	
	//restful하게 서비스를 구현할때 사용 -> @RestController를 사용하게 됨. 
//	@GetMapping
//	@PostMapping
//	@PutMapping
//	@DeleteMapping
//  @PathVariable
	
	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	public List<String> responseTest() {
		return List.of("1","2","3","4");
		
	}
	
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		int result=service.insertDemo(demo);
		//spring에서 redirect처리하기
		return "redirect:/demo/demo.do";		
	}
	
	@RequestMapping("/demo/demolist.do")
	public ModelAndView demoList(ModelAndView mv) {
		List<Demo> list=service.selectDemoList();
		System.out.println(list);
		mv.addObject("demos",list);
		mv.setViewName("demo/demolist");
		return mv;
	}
	
}



