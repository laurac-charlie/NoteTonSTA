<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.supinfo.notetonsta.entity.Intervention" %>
<%Intervention inter = (Intervention)request.getAttribute("theIntervention");%>
<!DOCTYPE html >
<html>
	<head>
		<title>Subject : <%=inter.getSubject() %></title>
		<link rel="stylesheet" type="text/css" href="../style/style.css" />
		<link type="text/css" href="../script_js/css/smoothness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
		<script type="text/javascript" src="../script_js/js/jquery.js"></script>
		<script type="text/javascript" src="../script_js/js/jquery-ui-1.8.16.custom.min.js"></script>
		<script type="text/javascript">
			function couleur(obj) {
			     obj.style.backgroundColor = "#FFFFFF";
			}
			
			
			function click_subject()
			{
				var content = $("#content h4").text();
				$("#content h4").html("<input type=\"text\" id=\"newsubject\" style=\"text-align :center\" value=\""+ content +"\"/>");
				$("#content h4").unbind( "click" );
				$("#newsubject").focus();
				
				$("#newsubject").blur(function()
				{
					if($("#newsubject").val() == "")
					{
						$("#content h4").html("${theIntervention.subject }");
					}
					else
					{
						var url = "${pageContext.request.contextPath}" + "/interventions/update";
						var subject = $("#newsubject").val();
						$("#content h4").html(subject);
						$.post( url, { "interventionId" : "${theIntervention.id}", "subject" : subject});
						$("#message").html("The intervention's subject was updated.");
						$("#message").animate({height: "toggle"}).delay(2000).slideUp(1000);
					}
					
					$("#content h4").bind( "click",function(){ click_subject();} );
				});
			}
			
			function click_description()
			{
				var content = $("div#description").text();
				$("div#description").html("<textarea id=\"newdescription\" rows=\"4\" cols=\"50\" >"+ content +"</textarea>");
				$("div#description").unbind( "click" );
				$("#newdescription").focus();
				
				$("#newdescription").blur(function()
				{
					if($("#newdescription").val() == "")
					{
						$("div#description").html("${theIntervention.id }");
					}
					else
					{
						var url = "${pageContext.request.contextPath}" + "/interventions/update";
						var description = $("#newdescription").val();
						$("div#description").html(description);
						$.post( url, { "interventionId" : "${theIntervention.id}", "description" : description});
						$("#message").html("The intervention's description was updated.");
						$("#message").animate({height: "toggle"}).delay(2000).slideUp(1000);
					}
					
					$("div#description").bind( "click",function(){ click_description();} );
				});
			}
			
			$(document).ready( function() 
			{
				//Display the popup when clicking on the link
				$("#popLink").click(function()
				{
					$("#popup").animate(
					{
				    	height: "toggle"
				    },1000);
				});
				
				//Change tag to input type on click for concerned speaker
				$("#content h4").click(function(){
					<% if(request.getSession().getAttribute("loggedSpeaker") != null) { %>
						click_subject();
					<% } %>
				});
				
				$("div#description").click(function(){
					<% if(request.getSession().getAttribute("loggedSpeaker") != null) { %>
						click_description();
					<% } %>
				});
				
				//Delete Link click
				$("#deleteLink").click(function()
				{
					var url = "delete";
					url = "<%= request.getContextPath() %>" + "/interventions/delete";
					$.post( url, { "interventionId" : "${theIntervention.id}"},function( data ){
								alert("The intervention has been deleted");
								$(location).attr("href","<%= request.getContextPath() %>" + "/interventions/mine");
	    				});
				});
				
				//Post popup form to the servlet
			   $("#popupForm").submit(function(event) {
				    var $form = $(this);
				    	$idBooster = $form.find("#idBooster");
				    	content = "";
				    
				    event.preventDefault(); 
				    $("font").remove();
				    $("#saut").remove();
				    if($idBooster.val() != "")
				    { 
					    if(!isNaN($idBooster.val()) && $idBooster.val().indexOf(".") == -1)
					    {
					    	var idBooster = $idBooster.val(),
						    	url = $form.attr("action");
		
						    $.post( url, {  "idBooster" : idBooster, 
						    				"knowledge" : $form.find("input[name=knowledge]:checked").val(),
						    				"abilities" : $form.find("input[name=abilities]:checked").val(),
						    				"quality" : $form.find("input[name=quality]:checked").val(),
						    				"richness" : $form.find("input[name=richness]:checked").val(),
						    				"format" : $form.find("input[name=format]:checked").val(),
						    				"examples" : $form.find("input[name=examples]:checked").val(),
						    				"comments" : $form.find("#comments").val(),
						    				"interventionId" : "${theIntervention.id}"},function success( data ){
						    					$("#list_mark").html(data);
						    				});
						    $("#popLink").click();
						    $("#message").html("Your evaluation was considered.<br/>Thanks for your participation.");
						    $("#message").animate({height: "toggle"}).delay(2000).slideUp(1000);
					    }
					    else
					    {
					    	$idBooster.css("background-color","red");
					    	$idBooster.after("<br id=\"saut\"/><font color=\"red\"> You must enter and id booster only composed of numbers.</font>");
					    }
				    }
				    else
				    {
				    	$idBooster.css("background-color","#F3C200");
				    	$idBooster.after("<br id =\"saut\"/><font color=\"red\"> You must enter an "+$idBooster.attr("name")+".</font>");
				    }
			   });
			});
		</script>
	</head>
	<body>
	<%@ include file="/template/header.jsp" %>
		<section id="content">
			<h4><c:out value="${theIntervention.subject}"/></h4> <br/>
			<table class="intervention_table" id="detail">
				<tr>
					<td id="nom">Campus : <c:out value="${theIntervention.campus.name }"/></td>
					<td id="bdate">From : <c:out value="${theIntervention.beginDate }"/></td>
					<td id="edate">To : <c:out value="${theIntervention.endDate}"/></td>
				</tr>
			</table>
			<div id="description"><c:out value="${theIntervention.description}"/></div>
			<br/>
			<ul id = "list_mark">
				<li id="nbMark"> Number of marks : <%= inter.getNumberOfMark() %> </li>
				<li id="spMark"> Speaker mark : <%= inter.getAverageSpeakerNote() %>/5</li>
				<li id="slMark"> Slides mark : <%= inter.getAverageSlideNote() %>/5</li>
				<li id="glMark"> Global event mark : <%= inter.getAverageMark() %>/5</li>
			</ul>
			<br/>
			<c:if test="${empty loggedSpeaker}">
				<a href="#" id="popLink" >Evaluate!</a>
			</c:if>
			<c:if test="${not empty loggedSpeaker and loggedSpeaker.id == theIntervention.speaker.id}">
				<a href="#" id="deleteLink" >Delete!</a>
			</c:if>
		</section>
		<section id="popup">
			<form class="register-form" id="popupForm" action="#" method="post" name="formulaire" >
				<div>ID Booster :<input type="text" name ="idBooster" id="idBooster" maxlength="6" size="15" onfocus="javascript:couleur(this);"/></div><br/>
				<div><h4>About The Speaker</h4></div><br/>
				<table class="radio_table">
					<tr>
						<th></th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th>
					</tr>
					<tr>
						<td id="line_name">His knowledge of the subject : </td>
						<td><input type="radio" name ="knowledge" value="1"/></td>
						<td><input type="radio" name ="knowledge" value="2" checked="checked"/></td>
						<td><input type="radio" name ="knowledge" value="3"/></td>
						<td><input type="radio" name ="knowledge" value="4"/></td>
						<td><input type="radio" name ="knowledge" value="5"/></td>
					</tr>
					<tr>
						<td id="line_name">His teaching abilities : </td>
						<td><input type="radio" name ="abilities" value="1"/></td>
						<td><input type="radio" name ="abilities" value="2" checked="checked"/></td>
						<td><input type="radio" name ="abilities" value="3"/></td>
						<td><input type="radio" name ="abilities" value="4"/></td>
						<td><input type="radio" name ="abilities" value="5"/></td>
					</tr>
					<tr>
						<td id="line_name">The quality of answers : </td>
						<td><input type="radio" name ="quality" value="1"/></td>
						<td><input type="radio" name ="quality" value="2" checked="checked" /></td>
						<td><input type="radio" name ="quality" value="3"/></td>
						<td><input type="radio" name ="quality" value="4"/></td>
						<td><input type="radio" name ="quality" value="5"/></td>
					</tr>
				</table>
				<br/>
				<div><h4>About The Slides</h4></div><br/>
				<table class="radio_table">
					<tr>
						<th></th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th>
					</tr>
					<tr>
						<td id="line_name">The richness of the content : </td>
						<td><input type="radio" name ="richness" value="1"/></td>
						<td><input type="radio" name ="richness" value="2" checked="checked" /></td>
						<td><input type="radio" name ="richness" value="3"/></td>
						<td><input type="radio" name ="richness" value="4"/></td>
						<td><input type="radio" name ="richness" value="5"/></td>
					</tr>
					<tr>
						<td id="line_name">The format/layout : </td>
						<td><input type="radio" name ="format" value="1"/></td>
						<td><input type="radio" name ="format" value="2" checked="checked" /></td>
						<td><input type="radio" name ="format" value="3"/></td>
						<td><input type="radio" name ="format" value="4"/></td>
						<td><input type="radio" name ="format" value="5"/></td>
					</tr>
					<tr>
						<td id="line_name">The examples : </td>
						<td><input type="radio" name ="examples" value="1"/></td>
						<td><input type="radio" name ="examples" value="2" checked="checked" /></td>
						<td><input type="radio" name ="examples" value="3"/></td>
						<td><input type="radio" name ="examples" value="4"/></td>
						<td><input type="radio" name ="examples" value="5"/></td>
					</tr>
				</table>
				<br/>
				<div>Comments : <textarea id="comments" name="comments" rows="4" cols="40"></textarea></div>
				<br/>
				<div><input type="submit" name="submit" value="Submit"  /></div><br/>
			</form>
		</section>
		<section id ="message">
			Your evaluation was considered.<br/>Thanks for your participation.
		</section>
	<%@ include file="/template/footer.jsp" %>
	</body>
</html>