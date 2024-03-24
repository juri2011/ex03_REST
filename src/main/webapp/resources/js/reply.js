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
	
	function getList(param, callback, error){
		var bno = param.bno;
		//js에서의 논리연산자 (||) -> 제일 먼저 true가 나온 값을 반환
		//param.page가 없으면(undefined) 1을 반환
		var page = param.page || 1;
		
		//getJSON : JSON 목록 호출(json 형태가 필요하므로 URL확장자 .json)
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json", //파라미터 1: url
			function(data){ // 파라미터2: 성공
				if(callback){//callback 함수가 있으면
					callback(data);
				}
			}).fail(function(xhr, status, err){ //파라미터3 : 실패
				if(error){ // error함수가 있으면
					error();
				}
			});
	}
	
	//key: add, value: add메소드
	return {
		add:add,
		getList: getList
	};
})();