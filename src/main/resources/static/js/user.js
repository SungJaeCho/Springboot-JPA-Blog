let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{ //function(){}, ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
			this.save();
		});
	},
	
	save: function() {
//		alert("save함수 호출");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		console.log(data);
		
		// ajax호출시 기본은 비동기 호출
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청에 대한 응답이 왔을때 응답된 결과 타입 기본적으로 모든것이 문자열(생긴게 json이라면) =>javascript오브젝트로 변경
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
//			console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	},
	
}

index.init();