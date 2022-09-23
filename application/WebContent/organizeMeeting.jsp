<%@page import="com.hsbc.system.model.Users"%>
<%@page import="com.hsbc.system.dao.ManagerDao"%>
<%@page import="com.hsbc.system.model.RoomSuggested"%>
<%@page import="com.hsbc.system.model.BookingInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.hsbc.system.model.MeetingDataForUser" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.hsbc.system.service.ManagerService" %>
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

 
   
  
  <!-- Scheduled Meetings List -->

		<div class="w3-container" id="catalogue" style="margin-top: 75px">
			<h1 class="w3-xxxlarge w3-text-red">
				<b>Rooms Available for <%=session.getAttribute("roomType") %></b>
			</h1>
			<hr style="width: 50px; border: 5px solid red" class="w3-round">
		</div>
	
  		<%  HashMap<String, RoomSuggested> hm=(HashMap)session.getAttribute("HashMap");%>
		<div class="w3-row-padding">
		<div class="w3-center">
			<ul class="w3-ul w3-light-grey w3-center">
          	<li class="w3-red w3-xlarge w3-padding-32">Rooms available</li>
		
		<div style="overflow-x: auto; max-width:1500px;overflow-y: auto; max-height:350px">
          <div style="text-align: center;flex-direction: row;display: flex;" class="w3-large " >
  
     <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    RoomName<br>(Ratings)
     </div>
     <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto"  >
    Credits
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    Amenities
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    Capacity
     </div>
     <div class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    9am-10am
     </div>
     <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    10am-11am
     </div>
     <div class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    11am-12pm
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    12pm-1pm
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    1pm-2pm
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
   2pm-3pm
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    3pm-4pm
     </div>
      <div  class="header w3-dark-gray" style="width: 118px; flex: 0 0 auto">
    4pm-5pm
     </div>
    
  </div>     
				  <% 
	   					 for(String roomName:hm.keySet())
					  {
						  
					     int credit=hm.get(roomName).getCredits();
					     float ratings=hm.get(roomName).getRatings();
					     List<String> aminities=hm.get(roomName).getAmenities();
					     List<Integer> ls=hm.get(roomName).getSchedule();
						 int capacity=hm.get(roomName).getSeatingCapacity(); 
						 List<Integer> id = hm.get(roomName).getOrganizedById();
						 List<String> name = hm.get(roomName).getOrganizedByName();
						 int j=0;
					   
  					 %>
				<div style="flex-direction: row; display: flex;text-align: center">
     
    <div  style="border: 0px solid black;width:120px; height:100%;flex: 0 0 auto ">
    <%=roomName %><br>(<%=ratings %>)
     </div>
     <div style="border: 0px solid black; width:120px; height:100% ;flex: 0 0 auto">
    <%=credit %>
     </div>
      <div style="border: 0px solid black; width:120px; height:100%;  flex: 0 0 auto ">
    <%=aminities.toString()%>
     </div>
      <div style="border: 0px solid black; width:120px; height:100%;flex: 0 0 auto ">
    <%=capacity%>
     </div>
     
         <%
            for(int i=0;i<ls.size();i++)
            {
            	int n=ls.get(i);
            	
            	if(n==0)continue;
            	if(i%2==0)
            	{
          %>   		
            	<div style="border: 1px solid silver;width:<%=2*(n)%>px; text-align:center; flex: 0 0 auto"  ></div>
              
          <%   
            	}
            	else
            	{
            		
            	 
           %>
              <div class="zoom" style="background-color:#2C3E50;border: 1px solid grey;color:white;width:<%=2*(n)%>px; flex: 0 0 auto" ><b><%="Manager\n "+name.get(j) %><% j++;%></b></div>
                    
          <%	
            	
            	}
         
            }
         %>	
     
     </div>
     
     <%} %>
			</div>
		</div>
		</div>
		

  <!-- Form Part  2-->
  <div class="w3-container" id="contact" style="margin-top:75px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Fill the Details</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <form action="ManagerController" method="post">
      <div class="w3-section">
        <label>RoomName</label>
        <div class="w3-section w3-large">
              <select class="w3-select"  name="roomName"  required="">
                <option hidden disabled selected value> Choose Room Name  </option>
              <%    for(String rName:hm.keySet()){
            	  %>
            	  <option name="roomName" value="<%=rName%>"><%=rName %></option>
            	  <% 
            	  }%>
             
              </select><br>
      </div>
      </div>
      <div>
      <div class="w3-half" style="max-width:48%">
        <label>Start Time</label>
        <input class="w3-input w3-border" type="text" name="startTime" id="startTime" required autocomplete="off">
        </div>
        <div class="w3-half" style="max-width:48%; margin-left:30px">
        <label>End Time</label>
        <input class="w3-input w3-border" type="text" name="duration" id="duration" required autocomplete="off">
      </div> 
      <br>
      </div>
      <button id="submit" name="submit" type="submit" class="w3-button w3-block w3-padding-large w3-red w3-margin-bottom" value="insert">Add Meeting Members</button>
    </form>  
    </div>


<!-- Footer -->
<footer class="w3-center w3-dark-grey " style="width:110%;margin-left: -40px;margin-top: 40px;" >
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

function checkdate()
{
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth()+1;
  var yyyy = today.getFullYear();
   if(dd<10){
          dd='0'+dd
      } 
      if(mm<10){
          mm='0'+mm
      } 

  today = yyyy+'-'+mm+'-'+dd;
  document.getElementById("date").setAttribute("min", today);
}
</script>


</html>
