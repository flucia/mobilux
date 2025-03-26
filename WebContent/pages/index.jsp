<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<%=request.getContextPath()%>/scripts/showSlides.js"></script>
<title>Mobilux</title>
</head>
<body>
	<%@ include file="/partials/header.jsp"%>
	<main>
		<div class="">
			<div class="slideshow-container">
				<div class="mySlides fade">
					<img src="${pageContext.request.contextPath}/images/homepage.jpg"
						style="width: 100%">
				</div>

				<div class="mySlides fade">
					<img src="${pageContext.request.contextPath}/images/homepage1.jpg"
						style="width: 100%">
				</div>

				<div class="mySlides fade">
					<img src="${pageContext.request.contextPath}/images/homepage2.jpg"
						style="width: 100%">
				</div>
				<div style="text-align: center">
					<span class="dot"></span> <span class="dot"></span> <span
						class="dot"></span>
				</div>
			</div>
			<div class="grid grid-cols-2 gap-x-3">
				<div>
					<img src="${pageContext.request.contextPath}/images/divano.jpg"
						style="width: 100%">
				</div>
				<div>
					<img src="${pageContext.request.contextPath}/images/scrivania.jpg"
						style="width: 100%">
				</div>
				<div>
					<img src="${pageContext.request.contextPath}/images/tavolo.jpg"
						style="width: 100%">
				</div>
				<div>
					<img src="${pageContext.request.contextPath}/images/bagno.jpg"
						style="width: 100%">
				</div>
			</div>
		</div>
		<br>

		<script>
		
			showSlides();

		</script>
	</main>

	<%@ include file="/partials/footer.jsp"%>

</body>
</html>