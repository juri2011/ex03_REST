package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
  
  //게시물 등록
  public void register(BoardVO board);
  
  //게시물 상세보기
  public BoardVO get(Long bno);
  
  //게시물 수정
  public boolean modify(BoardVO board);
  
  //게시물 삭제
  public boolean remove(Long bno);
  
  //게시물 목록
  //public List<BoardVO> getList();
  
  //게시물 목록
  public List<BoardVO> getListWithPaging(Criteria cri);
  
  //게시글 수 찾기
  public int getTotal(Criteria cri);
  
}
