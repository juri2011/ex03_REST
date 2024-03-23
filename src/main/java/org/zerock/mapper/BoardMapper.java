package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
  
  //@Select("select * from tbl_board where bno > 0")
  public List<BoardVO> getList();
  
  //페이징 목록 출력
  public List<BoardVO> getListWithPaging(Criteria cri);
  
  //데이터 삽입만
  public void insert(BoardVO board);
  
  //데이터 삽입하고 key까지 가져옴
  public void insertSelectKey(BoardVO board);
  
  //게시글 읽기(상세조회)
  public BoardVO read(Long bno);
  
  //게시글 삭제
  public int delete(Long bno);
  
  //게시글 수정
  public int update(BoardVO board);

  public int getTotalCount(Criteria cri);
}
