# ex03_REST
Spring Framework 기반으로 수업 때 진행된 게시판 CRUD 실습 예제 프로젝트입니다.
REST와 Ajax를 이용해 댓글 기능을 추가했습니다.
(코드로 배우는 Spring 서적 참고)

## :computer: 개발 환경
* `JAVA11`
* `javascript`
* `STS3`
* Postman
* `MySql`
* `Spring` `MyBatis`

## :memo: 요구사항
### ReplyController
|메소드|주요기능(함수)|설명|SQL
|---|---|---|---|
|`Post`|ResponseEntity<String> create(@RequestBydo ReplyVO vo)|ajax로 댓글 객체를 받아 등록|`INSERT`
|`Get`|ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno))|페이지네이션으로 현재 게시물의 댓글 리스트 가져오기|`SELECT`|
|`Get`|ResponseEntity<ReplyVO> get(@PathVariable("page") Long rno))|하나의 댓글 조회|`SELECT`|
|`Delete`|ResponseEntity<String> remove(@PathVariable("rno") Long rno))|댓글 삭제하기|`DELETE`|
|`Put` `Patch`|ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno))|댓글 수정하기|`UPDATE`|

### get.jsp
주요기능(함수)|설명|SQL
|---|---|---|
|function showReplyPage(replyCnt)|페이지네이션 표시|`SELECT`
|function showList(page)|현재 게시물의 댓글 리스트 가져오기|`SELECT`|

### resources/reply.js
주요기능(함수)|설명|SQL
|---|---|---|
|function add(reply, callback, error)|reply 객체를 받아 댓글로 추가|`INSERT`
|function getList(param, callback, error)|현재 게시물의 댓글 리스트 가져오기|`SELECT`|
|function remove(rno, callback, error)|댓글 삭제|`DELETE`|
|function update(reply, callback, error)|댓글 수정|`UPDATE`|
|function get(rno, callback, error)|댓글 조회|`SELECT`|
|function displayTime(timeValue)|현재 시간 객체를 적절한 형식으로 반환|


## :open_file_folder: 구조 (ex03과 중복 제외)
### 클래스
* ReplyController
* ReplyPageDTO
* ReplyVO

### views
* get.jsp
## :wrench: 개선사항

## :bulb: 알게 된 점
* REST방식이 스프링 프로젝트에서 어떻게 사용되는지 알 수 있었다.
* CRUD 작업에 따라 어떤 HTTP메소드를 쓸 수 있는지 정리할 수 있었다.
* Ajax를 사용하면서 javascript의 문법에 더 익숙해졌다.
