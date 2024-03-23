package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

/*
 	@Controller의 문자열 return -> jsp파일의 이름
 	@RestController의 문자열 return -> 순수 데이터 반환
*/
@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	//produces -> 지금 메소드가 만들어내는 MIME 타입
	@GetMapping(value="/getText", produces="text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	//APPLICATION_JSON_UTF8_VALUE : 스프링 5.2부터 Deprecated
	//xml과 json 방식의 데이터 생성
	@GetMapping(value="/getSample",
				produces= {MediaType.APPLICATION_JSON_VALUE,
						   MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		
		return new SampleVO(112, "스타","로드");
	}
}
