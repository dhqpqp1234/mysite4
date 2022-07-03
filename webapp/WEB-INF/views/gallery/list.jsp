<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/includes/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				
				<div id="list">
				
					<c:if test="${authUser.no != null}">
						<button id="btnImgUpload" >이미지올리기</button>
						<div class="clear"></div>
					</c:if>
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						
						<c:forEach items="${gList }" var ="gList">
               				<li>
								<div class="view" id="img${gList.no}">
									<img class="imgItem"  src="${pageContext.request.contextPath}/upload/${gList.saveName}">
									<div class="imgWriter">작성자: <strong>${gList.name}</strong></div>
									
								</div>
							</li>
                 		</c:forEach>
						
							
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<form method="post" action="${pageContext.request.contextPath }/gallery/upload"  >
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
					<input type="hidden" name ="userNo" value="${authUser.no}">
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
						<input type="hidden" id="no" name = "no" value="">
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
				</div>
				<form method="" action="">
					<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
				</div>
				
				
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">
	
	//이미지올리기 클릭하면 모달창 띄우기
	$("#btnImgUpload").on("click", function(){
		console.log("sibal");
		
		$("#addModal").modal("show");
		
		
		
	});
	
	
	
	//이미지클릭시 사진보이기
	$(".view").on("click",".imgItem", function(){
		console.log("사진보이기");
		
		var $this = $(this);
		console.log($this)
		
	 	var imag = $this.attr("src");
		
		var filePath = imag.split("/");
		console.log(filePath[3]);
		
		var saveName = filePath[3];
		
		
		$.ajax({
			url : "${pageContext.request.contextPath }/gallery/read",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(saveName),
			
			dataType : "json",
			success : function(gVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(gVo.saveName);
			
			//번호가져왓어
			var no = gVo.no;
			
			
			
			$("#viewModelImg").attr("src" ,"${pageContext.request.contextPath}/upload/"+gVo.saveName);
			$("#viewModelContent").text(gVo.content)
			var authUserNo = "${authUser.no}";
			if(authUserNo != gVo.userNo){
				$("#btnDel").hide();
			}else{
				$("#btnDel").show();
			}
			
			$("#no").val(no);
			
			
			$("#viewModal").modal("show");
			
			},
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
		
	});
		
	//삭제
	$("#btnDel").on("click",function(){
		console.log("ㅈㄲ")
		
		//no를 변수에 담앗음
		var no = $("[name='no']").val();
		console.log(no);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/gallery/delete",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(no),
			dataType : "json",
			
			success : function(count){
			/*성공시 처리해야될 코드 작성*/
			
				if(count == 1){
					$("#img" + no).remove();
				}
				
				$("#viewModal").modal("hide");
			
			},
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
		
	});
	
</script>


</html>

