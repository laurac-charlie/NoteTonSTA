<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
	<head>
		<title>New Intervention</title>
		<link rel="stylesheet" type="text/css" href="../style/style.css" />
		<link type="text/css" href="../script_js/css/smoothness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="../script_js/js/jquery.js"></script>
		<script type="text/javascript" src="../script_js/js/jquery-ui-1.8.16.custom.min.js"></script>
		<script type="text/javascript">
			function couleur(obj) {
			     obj.style.backgroundColor = "#FFFFFF";
			}
			
			function check() 
			{
				var sucess = true;
				$("font").remove();
				$("input").each(function(){
					if($(this).val() == ""){
						$(this).css("background-color","#F3C200");
						$(this).after("<font color=\"red\"> You must enter a "+$(this).attr("name")+".</font>");
						sucess = false;
					}
					
					if($(this).attr("id") == $("#end_date").attr("id") && $(this).val() != "" && $("#end_date").val() != "")
					{
						if($.datepicker.parseDate("dd/mm/yy",$(this).val()) <= $.datepicker.parseDate("dd/mm/yy",$("#begin_date").val()))
						{
							$(this).css("background-color","red");
							$(this).after("<font color=\"red\"> The end date is not after the begin date.</font>");
							sucess = false;
						}
					}
						
				});
				
				return sucess;
			}
			
			<% boolean fail = false; %>
			<% if(request.getAttribute("failreg") != null) fail = Boolean.valueOf(request.getAttribute("failreg").toString()); %>
			<% if(fail) {%> alert("The registration failed.");<%}%>
			
			window.onload = function () {document.forms['formulaire'].onsubmit = check;}
			$(function()
			{
				$(".date").datepicker();
				$(".date").datepicker("option", "dateFormat", "dd/mm/yy");
			});
		</script>
	</head>
	<body>
	<%@ include file="/template/header.jsp" %>
		<section id="content">
			<form class="register-form"  action="new" method="post" name="formulaire" >
				<div>
					<label for="subject">Subject :</label><input type="text" id="subject" name="subject" maxlength="64" size="32" autofocus onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="list_campus">Campus :</label>
					<select name="list_campus" >
						<c:forEach items="${list_campus}" var="campus">
							<option value="${campus.id}"><c:out value="${campus.name}"/></option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label for="begin_date">From :</label><input type="text" class="date" id="begin_date" name="begin_date" size="10" onfocus="javascript:couleur(this);"  />
				</div>
				<div>
					<label for="end_date">To :</label><input type="text" class="date" id="end_date" name="end_date" size="10" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="description">Description :</label><textarea id="description" name="description" rows="4" cols="50"></textarea>
				</div>
				<div>
					<input type="submit" name="submit" value="Submit"  />
				</div>
			</form>
		</section>
	<%@ include file="/template/footer.jsp" %>
	</body>
</html>