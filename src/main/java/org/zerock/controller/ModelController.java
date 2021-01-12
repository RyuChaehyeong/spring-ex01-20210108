package org.zerock.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {
	
	
	//요청 경로가 곧 jsp파일이 됨
	@RequestMapping("/ex1")
	public void method1(Model model) {
		log.info("method1");
		
		model.addAttribute("name", "유귤이팡"); 
		
		//model에 붙여놓은 것을 spring이 request Attr로 옮겨놓아줌
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute("name") String name) {
		log.info("method2");
		
		//model.addAttribute("name", name);
	}
	
	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name, 
			@ModelAttribute("age") int age) {
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute("member") Member member) {
		log.info("method4");
		log.info(member);
		
		//결과: INFO : org.zerock.controller.ModelController - Member(name=pang, age=5)
	}
	
	@RequestMapping("/ex5")
	public void method5(@ModelAttribute Member member) {
		log.info("method5");
		log.info(member);
		//model attr name은 type명을 소문자로 바꾼 것과 동일
	}
	
	@RequestMapping("/ex6")
	public String method6(Member member) {
		log.info("method6");
		log.info(member);
		return "model/ex4";
		//결과: INFO : org.zerock.controller.ModelController - Member(name=pang, age=5)
	}
	
	@RequestMapping("/ex7")
	public String method7(Model model, HttpSession session, RedirectAttributes rattr) {
		log.info("method07");
		model.addAttribute("myattr1", "myvalue1");
		session.setAttribute("myAttr2", "myValue2");
		rattr.addFlashAttribute("myAttr3", "myValue3");
		
		//redirect한 곳으로 전달은 되나 session에 남아있지 않음
		return "redirect:ex8";
	}
	@RequestMapping("/ex8")
	public String method8(Model model) {
		log.info("method08");
		Map<String, Object> map = model.asMap();
		log.info(map.get("myattr1"));
		log.info(map.get("myAttr2"));
		log.info(map.get("myAttr3"));

		
		//redirect한 곳으로 전달은 되나 session에 남아있지 않음
		return "redirectex1";
	}
}
