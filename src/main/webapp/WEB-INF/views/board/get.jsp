<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<!-- page-wrapper 없으면 nav가 튀어나오는 오류 발생 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Read</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Read Page</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
						<div class="form-group">
							<label>Bno</label> <input class="form-control" name="bno"
								value='<c:out value="${board.bno}"/>' readonly>
						</div>
						<div class="form-group">
							<label>Title</label> <input class="form-control" name="title"
								value='<c:out value="${board.title}"/>' readonly>
						</div>
						<div class="form-group">
							<label>Text area</label><textarea class="form-control" rows="3" name="content" style="resize:none;" readonly><c:out value="${board.content}"/></textarea> 
						</div>
						<div class="form-group">
							<label>Writer</label> <input class="form-control" name="writer"
								value='<c:out value="${board.writer}"/>' readonly>
						</div>
						<form id='actionForm' action="/board/list" method="get">
							<input type="hidden" name='pageNum' value='<c:out value="${cri.pageNum}"/>'/>
							<input type="hidden" name='amount' value='<c:out value="${cri.amount}"/>'/>
							<input type="hidden" name='bno' value='<c:out value="${board.bno}"/>'/>
							<input type="hidden" name='keyword' value='<c:out value="${cri.keyword}"/>'/>
							<input type="hidden" name='type' value='<c:out value="${cri.type}"/>'/>
							
						</form>
						<button type="button" class="btn btn-info listBtn"><a href="/board/modify?bno=<c:out value='${bno}'/>">List</a></button>
						<button type="button" class="btn btn-info modBtn">
							<a href="/board/modify?bno=<c:out value='${board.bno}'/>">Modify</a></button>
						
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<!-- /.panel -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-comments fa-fw"></i> Reply
                        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>
                        	New Reply
                        </button>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul class="chat">
                        	<!-- start reply -->
                            <li class="left clearfix" data-rno='12'>
                                 <div class="header">
                                     <strong class="primary-font">user00</strong>
                                     <small class="pull-right text-muted">
                                         <i class="fa fa-clock-o fa-fw"></i> 2024-01-01 13:13
                                     </small>
                                 </div>
                                 <p>
                                     Good Job!
                                 </p>
                            </li>
                            <!-- end reply -->
                        </ul>
                        <!-- ./ end ul -->
                    </div>
                    <!-- /.panel-body .chat-pannel 추가 -->
                    <div class="panel-footer">
                    
                    </div>
                    <!-- ./ end panel-footer -->
                </div>
                <!-- /.panel .chat-panel -->
		</div>
	</div>
	<!-- ./end row -->
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                	<label>Reply</label>
                	<input class="form-control" name='reply' value='New Reply!!!!!' />
                </div>
                <div class="form-group">
                	<label>Replyer</label>
                	<input class="form-control" name='replyer' value='replyer' />
                </div>
                <div class="form-group">
                	<label>Reply Date</label>
                	<input class="form-control" name='replyDate' value='' />
                </div>
            </div>
            <div class="modal-footer">
            	<!-- 각 작업에 따라 버튼이나 입력창이 보이거나 사라진다 -->
                <button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
                <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
                <button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
                <button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<%@include file="../includes/footer.jsp"%>
<script src="/resources/js/reply.js"></script>
<script>

	$(document).ready(function(){
		var bnoValue = '<c:out value="${board.bno}"/>'
		var replyUL = $('.chat');
		
		showList(1);
		
		let pageNum = 1;
		const replyPageFooter = $(".panel-footer");
		function showReplyPage(replyCnt){
			let endNum = Math.ceil(pageNum/10.0) * 10; // 한 블록 끝페이지
			const startNum = endNum - 9;// 한 블록 시작 페이지
			
			const prev = startNum != 1; //startNum이 1이면 prev없음, 그 외 존재
			let next = false;
			
			if(endNum * 10 >= replyCnt){//한 블록 끝페이지 마지막 게시물 번호가 실제 게시물 번호보다 크다면
				endNum = Math.ceil(replyCnt/10.0);//실제 마지막 게시물 번호로 맞춘다.
			}
			
			if(endNum * 10 < replyCnt){//마지막 블록이 아니라면 next 존재
				next = true;
			}
			
			let str = "<ul class='pagination pull-right'>";
			if(prev){//이전 블록이 있다면
				str += "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>"
			}
			
			for(let i=startNum; i<=endNum; i++){
				var active = pageNum == i? "active":"";
				
				//현재 페이지면 active 해제, 나머지 active
				str+="<li class='page-item"+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
			if(next){//이전 블록이 있다면
				str += "<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>"
			}
			
			str+="</ul></div>"
			
			console.log(str);
			
			replyPageFooter.html(str);
			
		}
		
		function showList(page){
			replyService.getList({bno:bnoValue, page: page||1}, function(replyCnt, list){
				console.log("replyCnt: "+replyCnt);
				console.log("list: "+list);
				
				//페이지 번호가 -1이면 마지막페이지를 호출
				if(page == -1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				var str = "";
				//댓글이 없으면 댓글창 비우기
				if(list == null || list.length == 0){
					replyUL.html("");
					
					return;
				}
				for(let i=0, len=list.length || 0; i<len; i++){
					str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
                    str += "	<div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
                    str += "		<small class='pull-right text-muted'>";
                    str += "		<i class='fa fa-clock-o fa-fw'></i>" +
                    		replyService.displayTime(list[i].replyDate) + "</small></div>";
                    str +="			<p>"+list[i].reply+"</p></li>";
				}
				
				replyUL.html(str);
				showReplyPage(replyCnt);
			});//end function
		}//end showList
		
		const modal = $(".modal");
		const modalInputReply = modal.find("input[name='reply']");
		const modalInputReplyer = modal.find("input[name='replyer']");
		const modalInputReplyDate = modal.find("input[name='replyDate']");
		
		const modalModBtn = $("#modalModBtn");
		const modalRemoveBtn = $("#modalRemoveBtn");
		const modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click",function(e){
			modal.find("input").val(""); // 내용 초기화
			modalInputReplyDate.closest("div").hide(); //날짜창 숨기기
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalRegisterBtn.show();
			$(".modal").modal("show");
		});
		modalRegisterBtn.on("click", function(e){
			const reply = {
				reply: modalInputReply.val(),
				replyer: modalInputReplyer.val(),
				bno:bnoValue
			};
			replyService.add(reply, function(result){
				alert(result);
				
				modal.find("input").val("");//도배 안 되도록 입력 초기화
				modal.modal("hide"); //모달창 닫기
				
				showList(-1);// 목록 갱신
			});
		});
		//chat으로 이벤트를 걸고 실제 이벤트 대상은 li (두번째 파라미터로 자식 요소를 보낼 수 있다)
		$(".chat").on("click", "li", function(e){
			//data-rno의 값을 읽어온다
			const rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate))
				.attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			})
			console.log(rno);
		});
		modalModBtn.on("click",function(e){
			const reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			replyService.update(reply,function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
		modalRemoveBtn.on("click",function(e){
			const rno = modal.data("rno");
			replyService.remove(rno, function(result){
				alert(result);
				modal.modal("hide");
				showList(1);
			});
		});
	});
	
	//게시물 번호 그대로 들어온다.
	
	//for replyService add test
	/* 
	replyService.add(
		//첫번째 파라미터에 들어갈 reply 객체
		{reply:"JS Test", replyer:"tester", bno:bnoValue},
		//두번째 파라미터에 들어갈 콜백 함수(Ajax 전송 결과 처리)
		function (result){
			alert("RESULT: "+result);
		}
	);
	 */
	/*  
	replyService.getList({bno: bnoValue, page:1}, function(list){
		//ajax로 받아온 list에 값이 없으면 길이 0으로 할당
		for(let i=0, len = list.length||0; i<len; i++){
			console.log(list[i]);
		}
	});
	*/
	//23번째 댓글 삭제
	/* 
	replyService.remove(23, function(count){
		console.log(count);
		if(count === "success"){
			alert("REMOVED");
		}
	}, function(err){
		alert('ERROR...');
	});
	 */
	/*  
	replyService.update({
		rno: 24,
		bno: bnoValue,
		reply: "수정된 댓글 ㅎㅎ"
	}, function(result){
		alert("수정 완료...");
	});
	 
	replyService.get(24,function(data){
		console.log(data);
	});
	 
	*/ 
	var actionForm = $('#actionForm');
	$('.listBtn').on('click',function(e){
		e.preventDefault();
		actionForm.attr("action","/board/list");
		actionForm.submit();
	});
	$('.modBtn').on('click',function(e){
		e.preventDefault();
		actionForm.attr("action","/board/modify");
		actionForm.submit();
	});
</script>
