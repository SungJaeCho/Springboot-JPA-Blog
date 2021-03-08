let index = {
	init: function() {
		$("#btn-board-save").on("click", ()=>{ //function(){}, ()=>{}를 쓰는 이유는 this를 바인딩하기 위해서
			this.save();
		});
	},
	
	save: function() {
//		alert("save함수 호출");
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	},
	
}

index.init();