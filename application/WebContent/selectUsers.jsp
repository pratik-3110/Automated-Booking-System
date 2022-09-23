<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="com.hsbc.system.model.Users"%>
    <%@page import="com.hsbc.system.service.AdminService"%>
<!DOCTYPE html>
<html lang="en">
<title>Xceptionals | Manager</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="shortcut icon" href="favicon.png">
<link rel="stylesheet" href="fonts/style.css">
<script src="jquery.js"></script>

<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins Regular", sans-serif}
body {font-size:16px;}
html {
  background: url(img/26.jpg) no-repeat center center fixed;
  background-size: cover;
}
</style>

<%
  		session=request.getSession(false);
   		Users ob = (Users) session.getAttribute("userOb");
     	 if (ob == null || session==null|| !ob.getRole().equalsIgnoreCase("manager")) {
     		 session.invalidate();
    	%>
<script type="text/javascript">
	//alert('Access Denied !!');
	location = "index.jsp";
</script>

<%  
      }
    	  
      else{
       
    %>
<body>
<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container">
    <h3 class="w3-padding-64 w3-large"><b><%= ob.getUserName() %><br>Credits : <%= ob.getCredit() %></b></h3>
  </div>
  <div class="w3-bar-block">
  <a href="organizeMeeting.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Back</a> 
    <a href="managerLogin.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Cancel</a> 

    <a href="logout.jsp" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Logout</a>
  </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
   <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span><%= ob.getUserName() %></span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->

<div class="w3-main" style="margin-left:340px;margin-right:40px">

<!-- Add users to meeting -->


		<div class="w3-container" id="show" style="margin-top: 75px">
			<div class="w3-container w3-row-padding" id="upload" style="margin-top: 75px; max-height:500px;">

				<div class="w3-half" style="max-height:400px;">
					<h1 class="w3-xxlarge w3-text-red">
						<b>Available Members</b>
					</h1>
					<hr style="width: 50px; border: 5px solid red" class="w3-round">
					<%
						AdminService as = new AdminService();
							ArrayList<Users> uList = (ArrayList) as.userList();
					%>

					<select class="SelectUser w3-select" name="UserId" id="optlist"
						multiple="multiple">
						<%
							for (Users u1 : uList) {
									if (!u1.getUserName().equals(ob.getUserName()) && !u1.getRole().equalsIgnoreCase("admin")) {
						%>
						<option name="roomName" value="<%=u1.getUserId()%>"><%=u1.getUserName()%></option>
						<%
							}
								}
						%>

					</select>

				</div>
				<div class="w3-half" style="max-height:400px;">
					<form action="ManagerController" method="post">
						<h1 class="w3-xxlarge w3-text-red">
							<b>Selected Members</b>
						</h1>
						<hr style="width: 50px; border: 5px solid red" class="w3-round">
						<div class="w3-section w3-large">
							<select class="w3-select chosen-user" name="UserId" id="optlist1"
								multiple="multiple">

							</select>
						</div>
						
							</div>

						<button type="submit" name="submit" value="finish"
							class="w3-button w3-block w3-padding-large w3-red w3-margin-bottom">Book
							Meeting</button>
					</form>
			
			</div>

		</div>


		<!-- Footer -->
<footer class="w3-center w3-dark-grey " style="width:110%;margin-left: -40px;margin-top: 170px;margin-bottom:-20px;" >
  <a href="#" class="w3-button w3-black"><i class="w3-margin-right"></i>To the top</a>
  <div class="w3-large w3-section">
    Automated Meeting Room Booking System
    <h4><i class="w3-xlarge w3-margin-right"></i>Need Help? Contact Us : &nbsp&nbsp&nbsp&nbsp
      <i class="w3-xlarge w3-margin-right"></i>Phone:&nbsp1221-637237 &nbsp&nbsp&nbsp&nbsp
      <i class="w3-xlarge w3-margin-right"></i>Email:&nbsphelpdesk@amrbs.com</h4>
  </div>
</footer>
</div>

</body>
<%
}%>
</html>

<script type="text/javascript">
		$(function() {
			opts = $('#optlist option').map(function() {
				return [ [ this.value, $(this).text() ] ];
			});

			$('#someinput').keyup(
					function() {

						var rxp = new RegExp($('#someinput').val(), 'i');
						var optlist = $('#optlist').empty();
						opts.each(function() {
							if (rxp.test(this[1])) {
								optlist.append($('<option/>').attr('value',
										this[0]).text(this[1]));
							} else {
								optlist.append($('<option/>').attr('value',
										this[0]).text(this[1]).addClass(
										"hidden"));
							}
						});
						$(".hidden").toggleOption(false);

					});
			$('.SelectUser').click(
					function() {
						$('.SelectUser option:selected').remove().appendTo(
								'.chosen-user');
						opts = $('#optlist option').map(function() {
							return [ [ this.value, $(this).text() ] ];
						});
					});

			$('.chosen-user').click(
					function() {
						$('.chosen-user option:selected').remove().appendTo(
								'.SelectUser');
						opts = $('#optlist option').map(function() {
							return [ [ this.value, $(this).text() ] ];
						});
					});

		});

		jQuery.fn.toggleOption = function(show) {
			jQuery(this).toggle(show);
			if (show) {
				if (jQuery(this).parent('span.toggleOption').length)
					jQuery(this).unwrap();
			} else {
				if (jQuery(this).parent('span.toggleOption').length == 0)
					jQuery(this)
							.wrap(
									'<span class="toggleOption" style="display: none;" />');
			}
		};
	</script>