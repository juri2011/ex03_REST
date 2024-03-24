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
						<script src="/resources/js/reply.js"></script>
						<script>
						
							console.log("===================");
							console.log("JS TEST");
							
							//게시물 번호 그대로 들어온다.
							var bnoValue = '<c:out value="${board.bno}"/>'
							
							//for replyService add test
							replyService.add(
								//첫번째 파라미터에 들어갈 reply 객체
								{reply:"JS Test", replyer:"tester", bno:bnoValue},
								//두번째 파라미터에 들어갈 콜백 함수(Ajax 전송 결과 처리)
								function (result){
									alert("RESULT: "+result);
								}
							);
							
							$(document).ready(function(){
								console.log(replyService);
							});
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
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-6 -->
	</div>
	<!-- /.row -->
</div>
<%@include file="../includes/footer.jsp"%>
