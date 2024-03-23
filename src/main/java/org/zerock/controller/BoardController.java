package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//Controller에서 tomcat을 연결
@Controller
@Log4j
// "/board"로 시작하는 모든 처리를 BoardController가 담당
@RequestMapping("/board/*")
@AllArgsConstructor //생성자 주입
public class BoardController {
  
  //스프링 4.3부터는 단일 파라미터를 받는 생성자는 자동으로 주입 가능
  @Autowired
  private BoardService service;
  
  //게시물 목록 출력
  /*
   * @GetMapping("/list") public void list(Model model) {
   * 
   * log.info("list"); model.addAttribute("list", service.getList());
   * 
   * }
   */
  
  //게시물 목록 출력
  //pageNum과 amount를 파라미터로 전달하면 Criteria 디폴트 생성자가 호출되고
  //파라미터는 setter로 받는다.
  //(pageNum이나 amount 둘 중 하나가 없어도 디폴트 값이 있어서 런타임 에러는 나지 않는다.)
  @GetMapping("/list")
  public void list(Criteria cri, Model model) {
    
    log.info("list" + cri);
    model.addAttribute("list", service.getListWithPaging(cri));
    //데이터 123개
    model.addAttribute("pageMaker", new PageDTO(cri,service.getTotal(cri)));
  }
  
  //return값이 void일 경우 주소활용
  @GetMapping("/register")
  public void register() {

  }
  
  //등록
  //PRG패턴
  @PostMapping("/register")
  /*
     여기서는 ModelAttribute를 쓰지 않는다
     RedirectAttributes: ctrl + 클릭으로 확인해보면 Model을 상속받은 인터페이스라는 것을 알 수 있다.
     
     RedirectAttributes의
       addAttribute와 addFlashAttribute의 차이점?
     addAttribute: 리다이렉트에 추가한 attribute가 쿼리 파라미터로 넘어감
     addFlashAttribute: 객체를 넘기고 싶을 때 사용(session에 잠시 보관됨) 
                        일회성으로 사용 가능하고, 새로고침하면 휘발됨(Flash)
                        Model을 상속받은 인터페이스이므로 자동으로 Model에 등록됨
  */
  public String register(BoardVO board, RedirectAttributes rttr) {
    log.info("register: " + board);
    
    service.register(board);
    
    rttr.addFlashAttribute("result",board.getBno());
    
    return "redirect:/board/list";
    
  }
  
  //상세조회 + 수정 -> 같은 화면 띄우기
  @GetMapping({"/get","/modify"})
  public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
    
    log.info("/get or /modify");
    model.addAttribute("board",service.get(bno));
    model.addAttribute("cri",cri);
  }
  
  //수정
  @PostMapping("/modify")
  public String modify(BoardVO board, @ModelAttribute Criteria cri,
                        RedirectAttributes rttr) {
    log.info("modify: " + board);
    
    if(service.modify(board)) {
      rttr.addFlashAttribute("result","success");
    }
    return "redirect:/board/list"+cri.getListLink();
    
  }
  
  //삭제
  @PostMapping("/remove")
  public String remove(@RequestParam("bno") Long bno, @ModelAttribute Criteria cri,
                        RedirectAttributes rttr) {
    log.info("remove: " + bno);
    
    if(service.remove(bno)) {
      rttr.addFlashAttribute("result","success");
    }
    return "redirect:/board/list" + cri.getListLink();
  }
  
  
}
