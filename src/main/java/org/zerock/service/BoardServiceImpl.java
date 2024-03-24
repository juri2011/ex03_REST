package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
  
  //@Setter는 해당 필드의 setter를 생성한다. 클래스 레벨은 물론이고 필드레벨에도 사용 가능하다.
  //@Setter(onMethod_ = @Autowired) : setter가 생성되면 @Autowired를 붙인다.
  /*
     @Autowired : BoardServiceImpl을 생성할 때 스프링의 AutowiredAnnotationBeanPostProcessor가
     BoardMapper 타입의 빈을 얻어서 mapper 필드에 자동으로 연결한다.
     (지금은 Spring이 controller 패키지 안의 컴포넌트만 스캔하도록 설정 되어있는데, 과연 이 상태에서 의존주입이 될까?)
     
     -> 결론: 상관 없이 의존주입이 된다.
             이전에 TimeMapperTests에서 확인했던 것처럼 TimeMapper 객체변수에 Autowired를 지정해도 정상적으로 실행이 되었다.
             즉, TimeMapper 타입의 빈이 정상적으로 주입되었다는 뜻이다.
             root-context.xml에서 mybatis-spring:scan으로 mapper 패키지 안의 인터페이스들이 자동으로 스캔되어
             빈으로 등록된다는 것을 확인할 수 있다.
             만약 이 부분을 지운다면 spring은 해당 bean을 찾을 수 없다며 에러를 띄울 것이다.
             mapper 패키지 안의 컴포넌트들도 spring이 관리하므로
             여기서 autowired를 설정해도 정상적으로 주입이 가능한 것이다.
  */
  
  //@Autowired
  @Setter(onMethod_ = @Autowired)
  private BoardMapper mapper;
  
  //Service에서는 사용자들이 쓰는 일반용어를 쓴다.
  
  //게시글 등록
  @Override
  public void register(BoardVO board) {
    
    log.info("register......" + board);
    mapper.insertSelectKey(board);
    
  }

  //게시물 조회(상세 보기)
  @Override
  public BoardVO get(Long bno) {
    log.info("get..........."+bno);
    
    return mapper.read(bno);
  }

  //게시물 수정
  @Override
  public boolean modify(BoardVO board) {
    
    log.info("modify......." + board);
    
    //정상적으로 수정되면 1이 반환됨
    return mapper.update(board) == 1;
  }
  
  //게시물 삭제
  @Override
  public boolean remove(Long bno) {
    
    log.info("remove...." + bno);
    
    //정상적으로 삭제되면 1이 반환됨
    return mapper.delete(bno) == 1;
  }

  //게시글 목록
  @Override
  public List<BoardVO> getListWithPaging(Criteria cri) {
    
    log.info("get List with criteria : " + cri);
    
    return mapper.getListWithPaging(cri);
    
  }
  
  @Override
  public int getTotal(Criteria cri) {
    log.info("get total count");
    return mapper.getTotalCount(cri);
  }
}
