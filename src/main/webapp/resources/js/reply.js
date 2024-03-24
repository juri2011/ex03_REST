console.log("Reply Module..............");
//즉시실행함수 : replyService 에 {name: "AAAA"} 할당
var replyService = (function(){
	function add(reply, callback, error){
		console.log("add reply..........");
		console.log(reply);
		$.ajax({
			type: 'post',
			url: '/replies/new',
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			/*
				xhr: ajax 요청을 생성하는 자바스크립트 api
				브라우저와 서버간의 네트워크 요청을 전송할 수 있음
				새로고침 없이도 url에서 데이터를 가져올 수 있다.
			*/
			success: function(result, status, xhr){//성공
				if(callback){ //callback값으로 적절한 함수가 존재한다면
					callback(result);//호출해서 결과 반영
				}
			},
			error: function(xhr, status, er){//실패
				if(error){//예외처리 함수가 있다면
					error(er);//예외처리
				}
			}
		});
	}
	
	//key: add, value: add메소드
	return {
		add:add
	};
})();