<%@page import="com.hsbc.system.model.Users"%>
<%@page import="com.hsbc.system.dao.ManagerDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hsbc.system.model.BookingInfo"%>
<%@page import="com.hsbc.system.model.MeetingDataForUser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hsbc.system.service.ManagerService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<title>Xceptionals | Manager</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="shortcut icon" href="favicon.png">
<link rel="stylesheet" href="fonts/style.css">
<style>
body, h1, h2, h3, h4, h5 {
	font-family: "Poppins Regular", sans-serif
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
	session = request.getSession(false);
	Users ob = (Users) session.getAttribute("userOb");
	if (ob == null || session == null || !ob.getRole().equalsIgnoreCase("manager")) {
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
			<h3 class="w3-padding-64 w3-large">
				<b><%=ob.getUserName()%><br>Credits : <%=ob.getCredit()%></b>
			</h3>
		</div>
		<div class="w3-bar-block">
			<a href="#" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Home</a> <a
				href="#catalogue" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Scheduled Meetings</a>
			<a href="#myMeet" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Meeting Invitations</a>
			<a href="#contact" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Organise Meeting</a> <a
				href="logout.jsp" onclick="w3_close()"
				class="w3-bar-item w3-button w3-hover-white">Logout</a>
		</div>
	</nav>

	<!-- Top menu on small screens -->
	<header
		class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
		<a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a> <span><%=ob.getUserName()%></span>
	</header>
	y

	<!-- Overlay effect when opening sidebar on small screens -->
	<div class="w3-overlay w3-hide-large" onclick="w3_close()"
		style="cursor: pointer" title="close side menu" id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 340px; margin-right: 40px">

		<!-- Header -->
		<h1 class="w3-xxxlarge w3-text-red">
			<b>Welcome <%=ob.getUserName()%></b>
		</h1>
		<h3 class="w3-left" style="color: grey"><%=ob.getEmail()%><br>Credits
			:
			<%=ob.getCredit()%><br>Last Login :
			<%=ob.getLastLoggedIn()%></h3>


		<!-- Scheduled Meetings List -->

		<div class="w3-container" id="catalogue" style="margin-top: 75px">
			<h1 class="w3-xxxlarge w3-text-red">
				<b>Scheduled Meetings</b>
			</h1>
			<hr style="width: 50px; border: 5px solid red" class="w3-round">
		</div>


		<div class="w3-row-padding">
			<div class="w3-center">
				<ul class="w3-ul w3-light-grey w3-center">
					<%
						Date d = new Date();
							ArrayList<BookingInfo> as = ManagerDao.getScheduledMeetingById(ob.getUserId());
							if (as.size() == 0) {
					%>
					<li class="w3-red w3-xlarge w3-padding-32">You haven't
						scheduled any meetings so far!</li>
					<%
						} else {
					%>
					<li class="w3-red w3-xlarge w3-padding-32">List of Meetings</li>

					<div class="w3-center" style="overflow-x: auto; max-width: 1500px; overflow-y: auto; max-height: 350px">
						<table
							class="w3-table w3-striped w3-bordered w3-border w3-hoverable">
							<tr>
								<th>Meeting Name</th>
								<th>Venue</th>
								<th>Date</th>
								<th>From</th>
								<th>Duration</th>
								<th>Rated</th>
								<th colspan="2">Action</th>
								<th>Ratings</th>
							</tr>
							<%
								for (BookingInfo b : as) {
							%>
							<tr>
								<td><%=b.getTitle()%></td>
								<td><%=b.getRoomName()%></td>
								<td><%=b.getDate()%></td>
								<td><%=b.getStartTime()%></td>
								<td><%=b.getDuration()%> mins</td>
								<td><%=b.getIsrated()%></td>
								<td colspan="2">
									<form action="ManagerController" method="post">
										<input type="hidden" value="<%=b.getBookingId()%>"
											name="bookingId"> <input type="hidden"
											value="<%=b.getRoomName()%>" name="roomName"> <input
											type="hidden" value="<%=b.getDuration()%>" name="duration">
										<input type="submit" name="submit" value="Delete Meeting">
									</form>
								</td>
								<td>
									<%
										if (b.getIsrated().equalsIgnoreCase("yes")) {
														out.println("Rating is Done");

													} else {

														SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
														Date d1 = sdf.parse(b.getDate());

														if (d.compareTo(d1) < 0) {
															out.println("Meeting is Not Finished");
														} else if (d.compareTo(d1) > 0) {
									%>

									<form action="ManagerController" method="post">
										<input type="text" name="ratings" placeholder="Enter Rating">
										<input type="hidden" value="<%=b.getBookingId()%>"
											name="bookingId"> <input type="hidden"
											value="<%=b.getRoomName()%>" name="roomName"> <input
											type="submit" name="submit" value="Give Rating">
									</form> <%
 	} else {
 						if (d.getTime() > b.getEndTime().getTime()) {
 %>

									<form action="ManagerController" method="post">
										<input type="text" name="ratings" placeholder="Enter Rating">
										<input type="hidden" value="<%=b.getBookingId()%>"
											name="bookingId"> <input type="hidden"
											value="<%=b.getRoomName()%>" name="roomName"> <input
											type="submit" name="submit" value="Give Rating">
									</form> <%
 	} else {
 							out.println("Meeting is Not Finished");
 						}

 					}

 				}
 %>

								</td>
							</tr>
							<%
								}
								
							%>
						</table>

					</div>
					
					<%} %>

				</ul>
			</div>
		</div>

		<!-- Meetings for Manager -->
		<div class="w3-container" id="myMeet" style="margin-top: 75px">
			<h1 class="w3-xxxlarge w3-text-red">
				<b>Meetings Invitations</b>
			</h1>
			<hr style="width: 50px; border: 5px solid red" class="w3-round">
	

		<div class="w3-row-padding">
			<div class="w3-center">
				<ul class="w3-ul w3-light-grey w3-center">
					<%
						ManagerService mservice = new ManagerService();
							ArrayList<MeetingDataForUser> data = (ArrayList) mservice.meetings(ob.getUserId());
							if (data.size() == 0) {
					%>
					<li class="w3-red w3-xlarge w3-padding-32">There are no
						meeting invitations for you!!</li>
					<%
						} else {
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
		</div>
		
		



	<!-- Organise Meeting-->
	<div class="w3-container" id="contact" style="margin-top: 75px">
		<h1 class="w3-xxxlarge w3-text-red">
			<b>Organise a Meeting</b>
		</h1>
		<hr style="width: 50px; border: 5px solid red" class="w3-round">
		<form action="ManagerController" method="post">
			<div class="w3-section">
				<label>Meeting Title</label> <input class="w3-input w3-border"
					type="text" id="meetingTitle" name="meetingTitle" autocomplete="on"
					required> <label>Meeting Info</label> <input
					class="w3-input w3-border" type="text" name="meetingInfo"
					id="meetingInfo" required autocomplete="off">
			</div>
			<label>Meeting Type</label>
			<div class="w3-section w3-large">
				<select class="w3-select" name="roomType" required="">
					<option hidden disabled selected value>Choose Meeting Type
					</option>
					<option name="roomType" value="Classroom Training">Classroom
						Training</option>
					<option name="roomType" value="Online Training">Online
						Training</option>
					<option name="roomType" value="Conference Call">Conference
						Call</option>
					<option name="roomType" value="Business">Business</option>
				</select><br>
			</div>
			<div class="w3-section w3-large">
				<label>Meeting Date : </label> <input type="date" id="date"
					name="date" required min="2010-10-05" onclick="checkdate()"><br>
				<br>
			</div>
			<button id="submit" name="submit" type="submit"
				class="w3-button w3-block w3-padding-large w3-red w3-margin-bottom"
				value="findRooms">Proceed Next</button>
		</form>
	</div>

		

	<!-- Footer -->
	<footer class="w3-center w3-dark-grey "
		style="width: 110%; margin-left: -40px; margin-top: 40px;">
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
</body>

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

	function checkdate() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd
		}
		if (mm < 10) {
			mm = '0' + mm
		}

		today = yyyy + '-' + mm + '-' + dd;
		document.getElementById("date").setAttribute("min", today);
	}
</script>


</html>
