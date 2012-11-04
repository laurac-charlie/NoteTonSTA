<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<script type="text/javascript" src="script_js/js/jquery.js"></script>
		<script type="text/javascript">
			function couleur(obj) {
			     obj.style.backgroundColor = "#FFFFFF";
			}
			
			function validmail(mail)
			{
				var reg = new RegExp('^[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*@[a-z0-9]+([_|\.|-]{1}[a-z0-9]+)*[\.]{1}[a-z]{2,6}$', 'i');

				if(reg.test(mail))
					return true;
				else
					return false;
			}
			
			function check() 
			{
				var msg = "";
			 	
				$("font").remove();
				if ($("#email").val() != "")	
					{
						if (!validmail($("#email").val()))	
						{
							$("#email").css("background-color","red");
							$("#email").after("<font color=\"red\"> Your email adress is not correct.</font>");
							msg += "fail";
						}
					}
				else	
					{
						$("#email").css("background-color","#F3C200");
						$("#email").after("<font color=\"red\"> You may enter your email adress.</font>");
						msg += "fail";
					}
				
				if ($("#password").val() == "")
				{
					$("#password").css("background-color","#F3C200");
					$("#password").after("<font color=\"red\"> Your password is not correct.</font>");
					msg += "fail";
				}
					

				if(msg == "")
					return true;
				else
				{
					return false;
				}
			}
			
			window.onload = function () {document.forms['formulaire'].onsubmit = check;}
	</script>
	</head>
	<body>
	<%@ include file="/template/header.jsp" %>
		<section id="content">
			<form class="register-form"  action="login" method="post" name="formulaire" >
				<div>
					<label for="email">E-mail:</label><input type="text" id="email" name="email" maxlength="64" autofocus onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="password">Password :</label><input type="password" id="password" name="password" maxlength="32" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<input type="submit" name="submit" value="Submit"/>
				</div>
			</form>
		</section>
	<%@ include file="/template/footer.jsp" %>
	</body>
</html>