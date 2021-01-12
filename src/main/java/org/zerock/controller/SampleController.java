package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

//controller anno안에 component anno 포함이 되어있음.
//dispatcher servlet이 요청을 받아서 적절한 controller에 전달을 해주는데
//경로는 @RequestMapping에 따라서 정해짐 

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	@RequestMapping("")
	public void basicget() {
		log.info("basic get.........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get.........................");
	}
	
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name
			, @RequestParam("age") int age ) {
		log.info("sample ex02");
		log.info("name: " + name);
		log.info("age: " + age);
		
		return "ex02";
		
	}
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: "+ ids );
		return "ex02List";
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("dto : " + dto);
		
		return "ex01";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	*/
	// /sample/ex03?title=text&dueDate=2018-01-01
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " +todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex4(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06(´▽`ʃ♡ƪ)(´▽`ʃ♡ƪ)");
		SampleDTO dto = new SampleDTO();
		dto.setAge(333);
		dto.setName("홍홍홍");
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("/ex07../ex07../ex07../ex07../ex07../ex07..");
		
		String msg = "{\"name\":\"유귤이팡\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload") 
	public void exUpload() {
		log.info("/exupload!!!!!!!!!!!!!!!!!!");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("-------------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}
}