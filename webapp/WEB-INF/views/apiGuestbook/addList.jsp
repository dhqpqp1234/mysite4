<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css -->
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>

<body>
	<div id="wrap">

		<!-- header 블러오기 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<c:import url="/WEB-INF/views/includes/nav.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2><a href="${pageContext.request.contextPath}/guestbook/addList">방명록</a></h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/guestbook/addList">일반방명록</a></li>
					<li><a href="${pageContext.request.contextPath}/api/guestbook/addList">방명록(ajax)</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								</th>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label>
								</th>
								<td><input id="input-pass" type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->


					<!-- 리스트 영역 -->
					<div id="listArea"></div>
					<!-- 리스트 영역 -->
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

	<!-- 삭제모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">비밀번호를 입력하세요.</h4>
				</div>
				<div class="modal-body">
					비밀번호<input type="text" name="password" value=""><br> <input type="text" name="no" value="">

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button id="bntModalDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 모달 -->



</body>

<script type="text/javascript">
	//준비가 끝났을때
	$(document).ready(function() {
		console.log("jquery 로 요청 data만 받는 요청");
		fetchList()
	});
	
	/* 저장버튼 (Json) */
	$("#btnSubmit").on("click", function() {
		console.log("저장버튼 클릭");

		//데이터수집
		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name='content']").val();

		//객체 만들기
		var guestVo = {
			name : name,
			password : password,
			content : content
		};

		$.ajax({

			url : "${pageContext.request.contextPath}/api/guestbook/add2",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestVo), //개념 다시

			dataType : "json",
			success : function(gVo) {
				/* 성공시 처리해야될 코드 작성 */
				console.log(gVo);
				
				//1개데이터 리스트 추가()
				
				render(gVo, "up");

				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	
	
	/* 저장버튼을 클릭했을때 (파라미터) 
	$("#btnSubmit").on("click", function() {
		console.log("저장버튼 클릭");

		//데이터수집
		var name = $("[name='name']").val();
		var password = $("[name='password']").val();
		var content = $("[name='content']").val();

		//객체 만들기
		var guestVo = {
			name : name,
			password : password,
			content : content
		};

		$.ajax({

			url : "${pageContext.request.contextPath}/api/guestbook/add",
			type : "post",
			//contentType : "application/json",
			data : guestVo, //파라미터로정리

			dataType : "json",
			success : function(gVo) {
				/*성공시 처리해야될 코드 작성
				console.log(gVo);
				
				//1개데이터 리스트 추가()
				
				render(gVo, "up");

				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	*/
	
	/* ------------------------------------------------------------------------------------------------- */
	
	/* 삭제 버튼을 눌럿을때 */ 
	/* $(document).on("click",".delBtn",function(){
		console.log("111");
		
		$("#delModal").modal();
	}); */
	
	
	//추가된 글이라 부모 --> 자식에게
	/* $(".delBtn").on("click",function(){
		console.log("d");
	}); */
	
	
	//삭제 버튼 클릭햇을때
	$("#listArea").on("click",".delBtn",function(){
		
		var $this = $(this);
		var no = $this.data("no"); //클릭한 버튼의 no가져오기
		
		$("#delModal [name='password']").val("");
		$("[name='no']").val(no); //이름이 no이면 value는  클릭한 no 값
		
		//모달창 띄우기
		$("#delModal").modal("show");
	});
	
	/* 모달창 삭제 클릭할때 */
	$("#bntModalDel").on("click",function(){
		console.log("모달버튼클릭");
		
		//데이터 모으기
		var password = $("#delModal [name='password']").val();
		var no = $("[name='no']").val();
		
		var guestbookVo = {
			password : password,
			no : no
		};
		
		console.log(no);
		console.log(guestbookVo);
		
		/* 서버로 데이터 전송 */
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,
			
			
			dataType : "json",
			success : function(result){
			/*성공시 처리해야될 코드 작성*/
			//성공이면 지우고
			if(result == "succeess"){
				$("#t"+no).remove();
				//모달창 닫기
				$("#delModal").modal("hide");	
			}else{
				//실패면 안지우고
				alert("멍충~~ 멍충~~");
				//모달창 닫기
				$("#delModal").modal("hide");
			}
			
			},
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});

		
		//성공이면 리스트에서 글제거
		
		
		//모달창 닫기
		
		
	});
	
	
	
	
	function fetchList() {
		//리스트 요청 + 그리기
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},
			
			dataType : "json",
			success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			
			for(var i=0; i<guestList.length; i++){
				render(guestList[i], "down");
			};
			
			},
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
	}

	//리스트 그리기 1개씩
	function render(GuestBookVo, opt) {
		console.log("render()");
		//var name = GuestBookVo.name;
		var str = "";
		str += '<table id="t'+GuestBookVo.no+'" class="guestRead">';
		str += '	<colgroup>';
		str += '			<col style="width: 10%;">';
		str += '			<col style="width: 40%;">';
		str += '			<col style="width: 40%;">';
		str += '			<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + GuestBookVo.no + '</td>';
		str += '		<td>' + GuestBookVo.name + '</td>';
		str += '		<td>' + GuestBookVo.regdate + '</td>';
		str += '		<td><button type="button" class="delBtn" data-no="'+GuestBookVo.no+'">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + GuestBookVo.content
				+ '</td>';
		str += '	</tr>';
		str += '</table>';

		if (opt == "down") {
			$("#listArea").append(str);
		} else if (opt == "up") {
			$("#listArea").prepend(str);
		} else {
			console.log("opt오류");
		}

	};
</script>

</html>

