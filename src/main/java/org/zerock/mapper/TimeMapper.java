package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

/*
    interface에서 함수를 선언 할 수 있다고!?
      -> java8부터 default 함수를 선언하면 클래스에서 상속을 받아 호출이 가능하다.
 */
public interface TimeMapper {
  
  //dual은 가상테이블이다.
  //아래는 annotation으로 select 문을 작성하는 예시이다.
  //annotation 쿼리는 기능이 한정되어있다는 단점이 있다. (복잡한 쿼리문은 작성할 수 없다)
  @Select("SELECT CURRENT_DATE FROM dual")
  public String getTime();

  public String getTime2();
}
