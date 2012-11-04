<%
	boolean logged = request.getSession().getAttribute("loggedSpeaker") != null;
	String contextPath = getServletContext().getContextPath();
%>
<header>
	<nav>
		<ul style="list-style: none;">
			<li id="h2"><a href="<%= contextPath %>/home"> Note ton STA </a></li>
			<% if(logged) { %>
			<li><a href="<%= contextPath %>/interventions/mine">My interventions</a></li>
			<li><a href="<%= contextPath %>/interventions/new">New Intervention</a></li>
			<li><a href="<%= contextPath %>/logout">Logout</a></li>
			<% } else { %>
			<li><a href="<%= contextPath %>/speakers/register">Register</a></li>
			<li><a href="<%= contextPath %>/login">Login</a></li>
			<% } %>
		</ul>
	</nav>
</header>