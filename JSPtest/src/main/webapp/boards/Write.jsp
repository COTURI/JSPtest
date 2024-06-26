<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>



<title>글작성</title>

<style>
body {
	font-size: 14px;
}

#container {
	width: 1200px;
	padding: 20px;
	margin: 0 auto;
}

#contents {
	width: 800px;
	margin: 20px auto;
}

#footer {
	overflow: hidden;
	width: 1200px;
	margin: 0 auto;
	padding-bottom: 25px;
	background: url(/img/imfs/main/bg_footer.jpg) repeat-x;
	font-family: "Noto Sans KR";
	font-weight: 400;
	font-size: 12px;
	color: black;
	background-color: whitesmoke;
	z-index: 2;
}
</style>
</head>
<body>

	<!-- Main Container -->
	<div id="container">

		<div id="contents">
			<form name="writeFrm" method="post" enctype="multipart/form-data"
				action="write.do" onsubmit="return validateForm(this);">
				<div class="form-group">
					<label for="category">분류</label> <select class="form-control"
						id="category">
						<option>아기 자랑</option>
						<option>거래 후기</option>
					</select>
				</div>
				<div class="form-group">
					<label for="title">제목</label> <input type="text"
						class="form-control" id="title" placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<label for="title">작성자</label>
				</div>
				<div class="form-group">
					<label for="summernote">내용</label>
					<div id="summernote"></div>
				</div>
				<button type="submit" class="btn btn-primary">확인</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='../boards/list.do';">취소</button>
			</form>
			<script>
				$('#summernote').summernote({
					
					tabsize : 2,
					height : 300,
					minHeight : 300,
					maxHeight : 500,
				});
			</script>
		</div>

	</div>

</body>
</html>
