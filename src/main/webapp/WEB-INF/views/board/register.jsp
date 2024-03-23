<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<!-- page-wrapper 없으면 nav가 튀어나오는 오류 발생 -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Register</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Register</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					
					<form role="form" action="/board/register" method="post">
						<div class="form-group">
							<label>Title</label> <input class="form-control" name="title">
						</div>
						<div class="form-group">
							<label>Text area</label><textarea class="form-control" rows="3" name="content" style="resize:none;"></textarea> 
						</div>
						<div class="form-group">
							<label>Writer</label> <input class="form-control" name="writer">
						</div>
						<button type="submit" class="btn btn-default">Submit Button</button>
						<button type="reset" class="btn btn-default">Reset Button</button>
					</form>
					
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
<script>

	//onpageshow -> 새롭게 진입하거나 뒤로가기로 진입하거나 항상 발생하는 이벤트
	//event의 persisted 속성: 웹페이지가 캐시로부터 로드되었는지 여부를 저장하는 속성 (boolean)
	window.onpageshow = function(event) {
	//back 이벤트 일 경우
	if (event.persisted) {
		location.reload(true);
	}

	}
</script>