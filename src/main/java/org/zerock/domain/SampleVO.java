package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleVO {
	/*
 		wrapper 클래스를 사용하는 이유
 		1. 제네릭
 		2. 기본 자료형의 값을 Object로 변환 가능
	*/
	private Integer mno;
	private String firstName;
	private String lastName;
}
