package org.zerock.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.mapper.BoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
  
  @Autowired
  private BoardMapper mapper;
  
  //테스트: 전체 게시글 가져오기
  @Test
  public void testGetList() {
    
    mapper.getList().forEach(board -> log.info(board));
    
  }
  
  //테스트: 데이터 삽입
  @Test
  public void testInsert() {
    
    BoardVO board = new BoardVO();
    board.setTitle("새로 작성하는 글");
    board.setContent("새로 작성하는 내용");
    board.setWriter("newbie");
    
    mapper.insert(board);
    
    log.info(board);
  }
  
  //테스트: key값 확인 후 데이터 삽입
  @Test
  public void testInsertSelectKey() {
    
    BoardVO board = new BoardVO();
    board.setTitle("새로 작성하는 글 selectKey");
    board.setContent("새로 작성하는 내용 selectKey");
    board.setWriter("newbie");
    
    mapper.insertSelectKey(board);
    
    log.info(board);
    log.info("after insert selectkey : "+board.getBno());
  }
  
  //테스트 : 게시물 상세읽기
  @Test
  public void testRead() {
    
    // 존재하는 게시물 번호인지 확인
    BoardVO board = mapper.read(5L);
    
    log.info(board);
  }
  
  //테스트: 게시물 삭제
  @Test
  public void testDelete() {
    //7L이 있는지 확인하고 실행
    log.info("DELETE COUNT: " + mapper.delete(7L));
  }
  
  //테스트: 게시물 수정
  @Test
  public void testUpdate() {
    BoardVO board = new BoardVO();
    //실행 전 존재하는 번호인지 확인
    board.setBno(6L);
    board.setTitle("수정된 제목");
    board.setContent("수정된 내용");
    board.setWriter("user00");
    
    int count = mapper.update(board);
    log.info("UPDATE COUNT: " + count);
  }
  
  //테스트: 페이징 리스트
  @Test
  public void testPaging() {
    
    Criteria cri = new Criteria();
    //10개씩 3페이지
    cri.setPageNum(3);
    cri.setAmount(10);
    
    List<BoardVO> list = mapper.getListWithPaging(cri);
    
    list.forEach(board -> log.info(board));
    
  }
  
  @Test
  public void testPageDTO() {
    Criteria cri = new Criteria();
    cri.setPageNum(2);
    PageDTO pageDTO = new PageDTO(cri, 251);
    
    log.info(pageDTO);
  }
  
  @Test
  public void testSearch() {
	  Criteria cri = new Criteria();
	  cri.setKeyword("새");
	  cri.setType("TW");
	  List<BoardVO> list = mapper.getListWithPaging(cri);
	  list.forEach(board -> log.info(board));
  }
}
