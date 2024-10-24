<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			let slideIndex = 0;
			showSlides();

			function showSlides() {
				let i;
				let slides = document.getElementsByClassName("mySlides");
				let dots = document.getElementsByClassName("dot");
				for (i = 0; i < slides.length; i++) {
					slides[i].style.display = "none";
				}
				slideIndex++;
				if (slideIndex > slides.length) {
					slideIndex = 1
				}
				for (i = 0; i < dots.length; i++) {
					dots[i].className = dots[i].className
							.replace(" active", "");
				}
				slides[slideIndex - 1].style.display = "block";
				dots[slideIndex - 1].className += " active";
				setTimeout(showSlides, 3000);
			}
		</script>
	</main>

	<%@ include file="/partials/footer.jsp"%>

</body>
</html>