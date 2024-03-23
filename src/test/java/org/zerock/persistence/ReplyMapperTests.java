package org.zerock.persistence;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
  
	//실제로 있는 게시글번호인지 확인할것
	private Long[] bnoArr = {1029L, 1028L, 1027L, 1025L, 1024L};
	
  @Autowired
  private ReplyMapper mapper;
  
  @Test
  public void testList() {
	  Criteria cri = new Criteria();
	  // 1029L
	  List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
	  
	  replies.forEach(reply -> log.info(reply));
  }
  
  @Test
  public void testUpdate() {
	  //update는 read가 선행되어야 한다.
	  Long targetRno = 2L;
	  ReplyVO vo = mapper.read(targetRno);
	  vo.setReply("Update Reply .... ");
	  
	  int count = mapper.update(vo);
	  log.info("UPDATE COUNT: "+count);
  }
  
  @Test
  public void testDelete() {
	  Long targetRno = 5L;
	  
	  mapper.delete(targetRno);
  }
  
  @Test
  public void testRead() {
	  Long targetRno = 5L;
	  ReplyVO vo = mapper.read(targetRno);
	  log.info(vo);
  }
  @Test
  public void testCreate() {
	  IntStream.rangeClosed(1,10).forEach(i->{
		  ReplyVO vo = new ReplyVO();
		  
		  //게시물의 번호
		  vo.setBno(bnoArr[i % 5]);
		  vo.setReply("댓글테스트 " + i);
		  vo.setReplyer("replyer"+i);
		  
		  mapper.insert(vo);
	  });
  }
  
  @Test
  public void testMapper() {
	  log.info(mapper);
  }

}
