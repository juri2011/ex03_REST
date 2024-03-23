package org.zerock.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
  //@Autowired
  @Setter(onMethod_ = {@Autowired})
  private BoardService service;
  
  //BoardService 객체가 제대로 주입되었는지 확인
  //BoardService 안의 BoardMapper가 주입이 잘 되었다면 BoardService 객체도 성공적으로 주입이 될 것이다.
  @Test
  public void testExist() {
    
    log.info(service);
    assertNotNull(service);
  }
  
  //테스트: 데이터 삽입이 잘 되는지 확인
  @Test
  public void testRegister() {
    BoardVO board = new BoardVO();
    board.setTitle("새로 작성하는 글");
    board.setContent("새로 작성하는 내용");
    board.setWriter("newbie");
    
    service.register(board);
    
    log.info("생성된 게시물의 번호: "+board.getBno());
  }
  
  //테스트: 게시물 리스트 전체 출력
  @Test
  public void testGetList() {
    
    service.getListWithPaging(new Criteria(2, 10)).forEach(board -> log.info(board));
  }
  
  //테스트: 게시물 상세 조회
  @Test
  public void testGet() {
    
    log.info(service.get(1L));
  }
  
  //테스트: 게시물 삭제
  @Test
  public void testDelete() {
    //게시물 번호가 있는지 확인하고 테스트할것
    log.info("REMOVE RESULT: " + service.remove(2L));
  }
  
  //테스트: 게시물 수정
  @Test
  public void testUpdate() {
    
    //1번 게시물을 수정
    BoardVO board = service.get(1L);
    
    //1번에 내용이 없다면 종료
    if(board == null) {
      return;
    }
    
    board.setTitle("제목을 수정합니다.");
    log.info("UPDATE RESULT: " + service.modify(board));
  }
  
}
