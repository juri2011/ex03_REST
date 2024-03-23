package org.zerock.domain;

import java.util.Date;

import lombok.Data;
/*
   bno의 타입이 Long인 이유?
   Oracle sql문에서 bno가 number(10,0)로 선언되었다
   자릿수가 10자리이므로 Long형으로 선언된 것
 */
@Data
public class BoardVO {
  private Long bno;
  private String title;
  private String content;
  private String writer;
  private Date regdate;
  private Date updateDate;
}
