package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
  
  private int startPage; //현재 블록 첫페이지
  private int endPage; //현재 블록 마지막 페이지
  private boolean prev, next; // 이전 or 다음 블록 있음
  
  private int total; //전체 글 수
  private Criteria cri; //현재 페이지와 글 최대 표시 갯수
  
  public PageDTO(Criteria cri, int total) {
    this.cri = cri;
    this.total = total;
    
    this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
    this.startPage = this.endPage - 9;
    
    int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
    
    if(realEnd < this.endPage) {
      this.endPage = realEnd;
    }
    
    this.prev = this.startPage > 1;
    this.next = this.endPage < realEnd;
  }
  
  
}
