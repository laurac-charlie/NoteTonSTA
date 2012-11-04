<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.supinfo.notetonsta.entity.Speaker" %>
<% 	Speaker theSpeaker = null;
	if((Speaker)request.getSession().getAttribute("loggedSpeaker") != null) theSpeaker = (Speaker)request.getSession().getAttribute("loggedSpeaker"); 
%>
<!DOCTYPE html >
<html>
	<head>
		<title>Home</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<script type="text/javascript" src="script_js/js/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				$("select").change(function(){
					$(location).attr("href","<%= request.getContextPath() %>" + "/intervention/" + $("#list_campus option:selected").html());
				});
				<% if(request.getSession().getAttribute("first") == "first"){ %>
					$("#message").animate({height: "toggle"},1000).delay(2000).slideUp(1000);
				<% request.getSession().setAttribute("first",null);} %>
			});
		</script>
	</head>
	<body>
		<%@ include file="/template/header.jsp" %>
		<section id="content">
			<h3> Welcome to Note ton STA !</h3> <br>
			This website propose you to evaluate interventions of SUPINFO speakers.<br>
			You can also see statistics by speaker or by campus. <br><br>
			Please select your campus &nbsp; :
			<select name="list_campus" id="list_campus" > 
				<option value="default"> --Campus-- &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</option>
				<c:forEach items="${list_campus}" var="campus">
					<option value="${campus.id}"><c:out value="${campus.name}"/></option>
				</c:forEach>
			</select><br><br>
			If you are speaker and have already an account, please <a href="<%= contextPath %>/login">authenticate you</a> to manage your interventions. <br>
			If you doesn't have an account, <a href="<%= contextPath %>/speakers/register">register you</a>!<br>
		</section>
		<section id="message">
			<c:if test="${not empty loggedSpeaker }">
				<div>Welcome <%= theSpeaker.getLastName() %> <%= theSpeaker.getFirstName() %>, You are now logged</div>
			</c:if>
		</section>
		<%@ include file="/template/footer.jsp" %>
	</body>
</html>