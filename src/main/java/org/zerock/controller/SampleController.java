package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

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
	
	//produces 속성은 생략도 가능
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(113, "로켓","라쿤");
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i+"First", "Last"))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	@GetMapping(value="/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
				@PathVariable("cat") String cat,
				@PathVariable("pid") Integer pid) {
		return new String[] {"category: " + cat, "productid: " + pid};
	}
	
	//@RequestBody가 요청(request)한 내용(body)를 처리하기때문에 @PostMapping 적용
	//JSON 타입을 Ticket 객체로 변환
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert .................... ticket" + ticket);
		
		return ticket;
	}
}
