<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
	<head>
		<title>Register</title>
		<link rel="stylesheet" type="text/css" href="../style/style.css" />
		<script type="text/javascript" src="../script_js/js/jquery.js"></script>
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
				
				if (document.formulaire.email.value != "")	
					{
						if (!validmail(document.formulaire.email.value))	
						{
							document.formulaire.email.style.backgroundColor = "#F3C200";
							msg += "The email address is not correct. \n";
						}
					}
				else	
					{
						document.formulaire.email.style.backgroundColor = "#F3C200";
						msg += "You may enter your email address.\n";
					}
				 
				
				if (document.formulaire.lastname.value == "")	
					{
						msg += "You may enter your lastname. \n";
						document.formulaire.lastname.style.backgroundColor = "#F3C200";
					}
				 
				if (document.formulaire.firstname.value == "")	
					{
						msg += "You may enter your firstname. \n";
						document.formulaire.firstname.style.backgroundColor = "#F3C200";
					}
				
				if (document.formulaire.password.value == "" || document.formulaire.password.value.size < 5)
				{
					msg += "You may enter your password . \n";
					document.formulaire.password.style.backgroundColor = "#F3C200";
				}
				
				if (document.formulaire.cpassword.value == "" ||  document.formulaire.cpassword.value.size < 5)
				{
					msg += "You may confirm your password . \n";
					document.formulaire.cpassword.style.backgroundColor = "#F3C200";
				}
				
				if (document.formulaire.password.value != "" && document.formulaire.cpassword.value != "" && document.formulaire.password.value != document.formulaire.cpassword.value)
					{
						msg += "The 2 password you have entered are different. \n";
						document.formulaire.password.style.backgroundColor = "#F3C200";
						document.formulaire.cpassword.style.backgroundColor = "#F3C200";
					}
				

				if(msg == "")
				{
					alert("You have been registered, you can now connect.");
					return true;
				}
				else
				{
					alert(msg);
					return false;
				}
			}
			
			window.onload = function () {document.forms['formulaire'].onsubmit = check;}
			<% boolean fail = false; %>
			<% if(request.getAttribute("failreg") != null) fail = Boolean.valueOf(request.getAttribute("failreg").toString()); %>
			<% if(fail) {%> alert("The registration failed.");<%}%>
		</script>
	</head>
	<body>
	<%@ include file="/template/header.jsp" %>
		<section id="content">
			<form class="register-form"  action="register" method="post" name="formulaire" >
				<div>
					<label for="firstname">First Name :</label><input type="text" id="firstname" name="firstname" maxlength="32" autofocus onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="lastname">Last Name :</label><input type="text" id="lastname" name="lastname" maxlength="32" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="email">E-mail :</label><input type="text" id="email" name="email" maxlength="64" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="password">Password(min. 5 caracs) :</label><input type="password" id="password" name="password" maxlength="32" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<label for="cpassword">Confirmation Password :</label><input type="password" id="cpassword" name="cpassword" maxlength="32" onfocus="javascript:couleur(this);" />
				</div>
				<div>
					<input type="submit" name="submit" value="Submit"  />
				</div>
			</form>
		</section>
	<%@ include file="/template/footer.jsp" %>
	</body>
</html>