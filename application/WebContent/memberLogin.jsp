<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.hsbc.system.model.Users,com.hsbc.system.model.MeetingDataForUser"%>
<%@page import="com.hsbc.system.service.MemberService"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<title>Xceptionals | Member</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="shortcut icon" href="favicon.png">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins">
<style>
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins", sans-serif
}

body {
	font-size: 16px;
}

html {
	background: url(img/26.jpg) no-repeat center center fixed;
	background-size: cover;
}
</style>
<%
	Users u = (Users) session.getAttribute("userOb");
	if (u == null || session == null || !u.getRole().equalsIgnoreCase("member")) {
		session.invalidate();
%>
<script type="text/javascript">
	//alert('Access Denied !!');
	location = "index.jsp";
</script>

<%
	}

	else {
%>
<body>


	<!-- Sidebar/menu -->
	<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding"
		style="z-index: 3; width: 300px; font-weight: bold;" id="mySidebar">
		<br>  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
		<div class="w3-container">
			<h3 class="w3-padding-64">
				<b>The<br>Xceptionals
				</b>
			</h3>
		</div>
		<div class="w3-bar-block">
			<a href="#" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Home</a> <a
				href="#catalogue" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Schedule</a> <a
				href="logout.jsp" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Logout</a>
		</div>
	</nav>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a> <span>The Xceptionals</span>
	</header>

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px">

		<!-- Header -->
		<h1 class="w3-jumbo w3-text-red">
			<b>Welcome <%=u.getUserName()%></b>
		</h1>
		<h3 class="w3-left" style="color: grey"><%=u.getEmail()%><br><%=u.getLastLoggedIn()%><br>
		</h3>

		<!-- Meetings Schedule list -->
		<div class="w3-container" id="catalogue" style="margin-top: 75px">
			<h1 class="w3-xxxlarge w3-text-red">
				<b>Meetings Schedule</b>
			</h1>
			<hr style="width: 50px; border: 5px solid red" class="w3-round">
		</div>

		<div class="w3-row-padding">
			<div class="w3-center">
				<ul class="w3-ul w3-light-grey w3-center">
				<%
						MemberService service = new MemberService();
							ArrayList<MeetingDataForUser> data = (ArrayList) service.meetings(u.getUserId());
							if(data.size()==0){
								%>
								<li class="w3-red w3-xlarge w3-padding-32">No Meetings Available!!</li>
								<%
							}
							else{
							for (MeetingDataForUser m : data) {
					%>
					<li class="w3-red w3-xlarge w3-padding-32">List of Meetings</li>

					<li class="w3-padding-16">
						<table class="w3-table  ">

							<tr>
								<td>Organiser : <%=m.getOrganiser()%></td>
								<td>Title : <%=m.getMeetingTitle()%></td>
								<td>Venue : <%=m.getRoomName()%></td>
							</tr>
							
							<tr>
							<td>Date : <%=m.getDate()%></td>
								<td>From :<%=m.getStarttime()%></td>
								<td>To : <%=m.getEndtime()%></td>
							</tr>
						</table>
					</li>
					
					
					<%
							}
						}
					%>


				</ul>
			</div>
		</div>

		<!-- Footer -->
		<footer class="w3-center w3-dark-grey "
			style="width: 110%; margin-left: -40px; margin-top: 80px;">
			<a href="#" class="w3-button w3-black"><i class="w3-margin-right"></i>To
				the top</a>
			<div class="w3-large w3-section">
				Automated Meeting Room Booking System
				<h4>
					<i class="w3-xlarge w3-margin-right"></i>Need Help? Contact Us :
					&nbsp&nbsp&nbsp&nbsp <i class="w3-xlarge w3-margin-right"></i>Phone:&nbsp1221-637237
					&nbsp&nbsp&nbsp&nbsp <i class="w3-xlarge w3-margin-right"></i>Email:&nbsphelpdesk@amrbs.com
				</h4>
			</div>
		</footer>
	</div>

	<%
		}
	%>

	<script>
		// Script to open and close sidebar
		function w3_open() {
			document.getElementById("mySidebar").style.display = "block";
			document.getElementById("myOverlay").style.display = "block";
		}

		function w3_close() {
			document.getElementById("mySidebar").style.display = "none";
			document.getElementById("myOverlay").style.display = "none";
		}
	</script>

</body>
</html>
