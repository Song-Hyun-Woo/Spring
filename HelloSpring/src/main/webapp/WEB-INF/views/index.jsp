<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set var="path" value="${pageContext.request.contextPath }"/>       
<!-- 
	해더파일불러오기 
	title값을 전달해서 출력해야함 -> MainPage가 출력
-->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>
<section id="content">
	<h2>Hello Spring</h2>
	<img src="${path }/resources/images/logo-spring.png" id="center-image" alt="스프링로고">
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<!-- 푸터파일불러오기 -->