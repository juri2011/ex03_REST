package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long targetRno);
	
	public int update(ReplyVO reply);
	
	/*
	 * MyBatis에서 두 개 이상의 데이터를 파라미터로 전달 할 때
	 * 1) 별도의 객체로 구현
	 * 2) Map 이용
	 * 3) @Param 이용
	 * @Param의 속성값은 MyBatis에서 SQL을 이용할 때 #{}로 사용 가능
	 * 
	 */
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	

}
