package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/return/*")
@Log4j
public class ReturnController {
	
	//1. request forward
	@RequestMapping("/ex1") 
	public String method1() {
		log.info("method1");
		return "returnView1";
	}

	//2. request redirect, context root는 안붙여도 됨
	@RequestMapping("/ex2") 
	public String method2() {
		log.info("method2");
		return "redirect:/sample/";
	}
	
	//String 앞에 @ResponseBody가 있으면 jsp이름이 아니라 응답 자체가 된다. 
	@RequestMapping("/ex3")
	public @ResponseBody String method3() {
		log.info("method3");
		
		return "returnValue3 hello world~!!~~!!";
	}
	
	
	//return type없는 경우 요청 경로가 jsp파일명
	@RequestMapping("/ex4")
	public void method4() {
		log.info("method4!!!");
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		log.info("method5....!!!(´◡`❁)");
		Member member = new Member();
		
//		서버와 클라이언트가 주고 받는 정보는 모두 텍스트라는 제한이 있는데
//		JSON형식으로 데이터를 보내기로 하면 브라우저는 이 데이터를 잘 활용할 수 있다.
		
		member.setName("donald");
		member.setAge(33);
		
		return member;
	}
		
		//응답객체가 본문이 되도록 @ResponseBody
	
	
}
