package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
/*
   @WebAppConfiguration 어노테이션
   Servlet에서는 ServletContext를, spring에서는 WebApplicationContext를 사용하기 위해서 붙임
 */
@WebAppConfiguration
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {

  //@Setter(onMethod_ = {@Autowired})
  @Autowired
  private WebApplicationContext ctx;
  /*
      개발 단계에서 Tomcat(WAS)를 실행하지 않고 스프링 프로젝트를 테스트 할 수 있는 방법!
      
      MockMvc?: 가짜 MVC
      가짜로 URL과 파라미터를 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행할 수 있다.
      
   */
  private MockMvc mockMvc;
  
  /*
     @Before이 붙은 메소드는 모든 테스트 전에 매번 실행된다.
       모든 테스트를 실행할 때 값을 clear하지 않으면 충돌의 위험이 있으므로 @Before에서 값을 clear하는 작업을 하는 것이다.
     
     @After: 후에 실행
     @Around: 전/후 둘 다
   */
  
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
  }
  
  //목록 조회 테스트
  @Test
  public void testList() throws Exception {
    
    log.info(
        mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
        .andReturn()
        .getModelAndView()
        .getModelMap());
  }
  
  //등록 조회 테스트
  @Test
  public void testRegister() throws Exception {
    /*
       .param(): input 태그와 비슷한 역할
       
     */
    String resultPage
    = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
        .param("title","테스트 새글 제목")
        .param("content","테스트 새글 내용")
        .param("writer","user00")
        ).andReturn().getModelAndView().getViewName();
    
    log.info(resultPage);
    
  }
  
  //상세 조회 테스트
  @Test
  public void testGet() throws Exception {
    
    //3번 데이터가 있는지 확인
    log.info(mockMvc.perform( MockMvcRequestBuilders
                              .get("/board/get")
                              .param("bno", "3")  )
                    .andReturn()
                    .getModelAndView().getModelMap());
  }
  
  //수정 테스트
  @Test
  public void testModify() throws Exception {
    /*
       .param(): input 태그와 비슷한 역할
       
     */
    String resultPage
    = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
        .param("bno","8") //데이터 확인
        .param("title", "수정된 테스트 새글 제목")
        .param("content","수정된 테스트 새글 내용")
        .param("writer","user00")
        ).andReturn().getModelAndView().getViewName();
    
    log.info(resultPage);
    
  }
  
  //삭제 테스트
  @Test
  public void testRemove() throws Exception {
    //삭제전 데이터베이스에 게시물 번호 확인
    String resultPage
    = mockMvc.perform( MockMvcRequestBuilders.post("/board/remove")
            .param("bno", "24")  )
            .andReturn().getModelAndView().getViewName();
    
    log.info(resultPage);
  }
  
  //페이징 목록 조회 테스트
  @Test
  public void testListPaging() throws Exception {
    
    log.info(mockMvc.perform(
        MockMvcRequestBuilders.get("/board/list")
        .param("pageNum", "2")
        .param("amount","10"))
        .andReturn().getModelAndView().getViewName());
  }

}
