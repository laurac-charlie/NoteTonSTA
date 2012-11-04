<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.supinfo.notetonsta.entity.Intervention" %>
<% Intervention inter = null; %>
<!DOCTYPE html >
<html>
	<head>
		<title>Interventions</title>
		<link rel="stylesheet" type="text/css" href="../style/style.css" />
		<script type="text/javascript" src="../script_js/js/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				$("tr:not(#title)").click(function(){
					$(location).attr("href","<%= request.getContextPath() %>" + "/intervention/" + $(this).attr("id"));
				});
			});
		</script>
	</head>
	<body>
		<%@ include file="/template/header.jsp" %>
		<section id="content">
			<c:choose>
				<c:when test="${empty interventions}">
					<p align="center">No intervention to display.</p>
				</c:when>
				<c:otherwise>
					<table class="intervention_table">
						<tr id="title">
							<th>Subject</th>
							<c:if test="${not empty loggedSpeaker and empty campus}">
								<th>Campus</th>
							</c:if>
							<th>Begin</th>
							<th>End</th>
							<th>Status</th>
							<c:if test="${not empty loggedSpeaker and empty campus}">
								<th>Global Event Mark</th>
							</c:if>
						</tr>
						<c:forEach items="${interventions}" var="intervention">
							<% inter = (Intervention)pageContext.getAttribute("intervention"); %>
							<tr id="${intervention.id}">
								<td><c:out value="${intervention.subject}"/></td>
								<c:if test="${not empty loggedSpeaker and empty campus}">
									<td><c:out value="${intervention.campus.name}" default="no campus"/></td>
								</c:if>
								<td><c:out value="${intervention.beginDate}"/></td>
								<td><c:out value="${intervention.endDate}"/></td>
								<td><%= inter.getStatus() %></td>
								<c:if test="${not empty loggedSpeaker and empty campus}">
									<td><%= inter.getAverageMark() %></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
					<br/>
					<c:if test="${not empty loggedSpeaker}">
						<a href="<%= contextPath %>/interventions/new">Add an intervention</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</section>
		<%@ include file="/template/footer.jsp" %>
	</body>
</html>