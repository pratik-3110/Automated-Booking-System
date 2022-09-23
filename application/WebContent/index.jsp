<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.hsbc.system.service.RoomDetails,java.util.HashMap" %>
    <%@page import="com.hsbc.system.model.Users"%>
<!DOCTYPE html>
<html lang="en">
<title>Xceptionals Home</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="shortcut icon" href="favicon.png">
<link rel="stylesheet" href="fonts/style.css">
<style>
body,h1,h2,h3,h4,h5 {font-family: "Poppins Regular", sans-serif}
body {font-size:16px;}
#p1 h6{
    visibility: hidden;
  }
  #p1:hover h6  {
      visibility: visible;
  }
  #team img{
    height: 90px;
    width: 90px;
    margin: auto;
    border-radius: 50%;
    cursor: default;
  }
  #team #p1{
    transform: scale(1.0);
    transition: .3s;
  }
  #team #p1:hover{
    transform: scale(1.2);
    transition: .3s;
  }
  * {
  margin: auto;
  padding: 0;
}
html {
  background: url(img/26.jpg) no-repeat center center fixed;
  background-size: cover;
}
</style>

    <%
   	  session=request.getSession(false);
      if (session.getAttribute("userOb")!=null && session!=null)
      {
    	  Users u=(Users)session.getAttribute("userOb");
    		 
			    if(u!=null && u.getRole().equalsIgnoreCase("admin")){
			    	System.out.println("admin");
			    	%>
			    	<jsp:forward page="adminLogin.jsp"/>
			    	
			    	<%  
			      }
    	 	if(u!=null&&u.getRole().equalsIgnoreCase("manager")){
    		 %>
    	    	<jsp:forward page="managerLogin.jsp"/>
    	    	
    	    	<%
    	 	}
    		 if(u!=null&&u.getRole().equalsIgnoreCase("member")){
    		 %>
    	    	<jsp:forward page="memberLogin.jsp"/>
    	    	
    	    	<%
    	 	}
    	 
    	 }
      else{
       
    %>
<body>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-light-blue w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
  <div class="w3-container">
    <h2 class="w3-padding-64"><b>XSpark</b></h2>
  </div>
  <div class="w3-bar-block">
    <a href="#" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Home</a> 
    <a href="#showcase" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Showcase</a> 
    <a href="#login" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Login</a>
  </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-blue w3-xlarge w3-padding">
  <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
  <span>The Xceptionals</span>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:340px;margin-right:0px">

  <!-- Header -->
    <h1 class="w3-jumbo w3-text-light-blue"><b>Automated Meeting Room Booking System</b></h1>
    <h3 class="w3-left" style="color: grey"><i>Providing Meeting, Training and Conference Rooms<br><b>Reliable-Adaptable-Scalable</b></i></h3>
    <div class="w3-container" style="margin-top:80px" id="showcase">
      <h1 class="w3-xxxlarge w3-text-light-blue"><b>Showcase.</b></h1>
      <hr style="width:50px;border:5px solid red" class="w3-round">
      <div class="w3-container" style="padding:0px 0px;" id="team"><br>
        <div class="w3-row-padding " style="margin-top:10px; margin-left: 0px;">
            <div class="w3-col l3 m6 w3-card-4 w3-red" style="padding: 10px; width:210px;margin-right: 50px" id="p1">
            <%RoomDetails rm=new RoomDetails();
            	HashMap<String, Integer> rc=rm.getRoomCount();
            
            %>
               <div class="w3-center"><img src="img/conCall1.jpg" class=" w3-hover-opacity" alt="Conference Room"></div>
               <h4 class="w3-center w3-light-grey">Conference Room</h4>
               <h4 class="w3-center w3-pale-red">Rooms: <%=rc.get("Conference Call Facility").intValue()%></h4> 
               <h6 class="w3-center w3-light-grey w3-text-red">Conference Call</h6> 
            </div>
            <div class="w3-col l3 m6 w3-card-4 w3-red" style="padding: 10px; width:195px;margin-right: 50px" id="p1">
              <div class="w3-center"><img src="img/onl-tr1.jpg" class="w3-sepia-min w3-hover-opacity"  alt="Online Training"></div>
              <h4 class="w3-center w3-light-grey">Online Training</h4>
              <h4 class="w3-center w3-pale-red">Rooms: <%=rc.get("Online Training").intValue() %></h4>
              <h6 class="w3-center w3-light-grey w3-text-red">Projector,Wifi</h6>
            </div>
            <div class="w3-col l3 m6 w3-card-4 w3-red" style="padding: 10px; width:195px;margin-right: 50px" id="p1">
              <div class="w3-center"><img src="img/class2.png" class="w3-sepia w3-hover-opacity" alt="Class Training"></div>
              <h4 class="w3-center w3-light-grey">Class Training</h4>
              <h4 class="w3-center w3-pale-red">Rooms: <%=rc.get("Classroom Training").intValue() %></h4>
              <h6 class="w3-center w3-light-grey w3-text-red">WhiteBoard,Projector</h6>
            </div>
            <div class="w3-col l3 m6 w3-card-4 w3-red" style="padding: 10px; width:210px;" id="p1">
              <div class="w3-center"><img src="img/business.jpg" class=" w3-hover-opacity" alt="Business"></div>
              <h4 class="w3-center w3-light-grey">Business</h4>
              <h4 class="w3-center w3-pale-red">Rooms: <%=rc.get("Business").intValue() %></h4>
              <h6 class="w3-center w3-light-grey w3-text-red">Projector</h6>
            </div>
        </div>
    </div>

  </div>
  
  
  <!-- Login -->
  <div class="w3-container" id="login" style="margin-top:105px">
    <h1 class="w3-xxxlarge w3-text-red"><b>Login</b></h1>
    <hr style="width:50px;border:5px solid red" class="w3-round">
    <div class="form">
      <div class="thumbnail"><img src="img/login-img.jpg" style="height: 143px;width: 143px;border-radius: 50%"></div>  
      <form class="login-form" method="post" action="login" onsubmit="return validateData()">
        <input type="text" class="w3-input" id="userid" name="userid" onKeyUp="checkid()" autocomplete="off" placeholder="User ID" required>
        <div id="checkid" align="center"></div>
        <input type="email" class="w3-input" id="email" name="email" onKeyUp="checkemail()" autocomplete="off" placeholder="Email ID" required>
        <div id="checkemail" align="center"></div>
        <!-- <button class="hvr-bounce-to-right">
          <input type="submit" id="sbtn" name="sbtn" value="Sign In">
        </button> -->
        <button type="submit" id="sbtn" name="sbtn" value="Login" class="w3-button w3-block w3-padding-large w3-red">Sign In</button>
      </form>
    </div>  
  </div>

<!-- Footer -->
<footer class="w3-center w3-dark-grey " style="width:110%;margin-left: -40px;margin-top: 40px;" >
  <a href="#" class="w3-button w3-black"><i class="w3-margin-right"></i>To the top</a>
  <div class="w3-large w3-section">
    Automated Meeting Room Booking System
    <h4><i class="w3-xlarge w3-margin-right"></i>Need Help? Contact Us : &nbsp&nbsp&nbsp&nbsp
      <i class="w3-xlarge w3-margin-right"></i>Phone:&nbsp1221-63723 &nbsp&nbsp&nbsp&nbsp
      <i class="w3-xlarge w3-margin-right"></i>Email:&nbsphelpdesk@amrbs.com</h4>
  </div>

</div>
</body>

<%} %>
<style type="text/css">
    .form {
    position: relative;
    z-index: 1;
    background: #FFFFFF;
    max-width: 300px;
    margin: 20px auto;
    padding: 30px;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    text-align: center;
  }

  .form .thumbnail {
    background: #17D1D4;
    width: 150px;
    height: 150px;
    margin: 0 auto 30px;
    padding: 50px 30px;
    border-top-left-radius: 100%;
    border-top-right-radius: 100%;
    border-bottom-left-radius: 100%;
    border-bottom-right-radius: 100%;
    box-sizing: border-box;
  }

  .form .thumbnail img {
    position: relative;
    top: -47px;
    left: -27px;
    display: block;
    width: 180%;
  }

  .form input {
    outline: 0;
    background: #f2f2f2;
    width: 100%;
    border: 0;
    margin: 0 0 15px;
    padding: 15px;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    box-sizing: border-box;
    font-size: 14px;
  }

  .form button {
    background: #17D1D4;
    width: 100%;
    border: 0;
    padding: 0px;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    color: #FFFFFF;
    transition: all 0.3 ease;
    overflow: hidden;
    cursor: pointer;
  }

  .error {
    position: relative;
    bottom: 10px;
    font-size: 13px;
    color: red;
  }

  #sbtn {
    background-color: #17D1D4;
    cursor: pointer;
  }

  button {
    background: #17D1D4;
    width: 300px;
    border: 0;
    padding: 20px;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    color: #FFFFFF;
    transition: all 0.3 ease;
    overflow: hidden;
    cursor: pointer;
  }
</style>
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
  function checkid()
  {
    var userid = document.getElementById("userid").value; 
    
    if(isNaN(userid))
    {
      document.getElementById('checkid').innerHTML="<font style=\"color: red\">*Format Invalid</font>";
      document.getElementById('userid').style.border='red 1px solid';
      return false;
    }
    else if(userid.length != 7 || userid.length == 0)
    {
      document.getElementById('checkid').innerHTML="<font style=\"color: red\">*Format Invalid (7-digits).</font>";
      document.getElementById('userid').style.border='red 1px solid';
      return false;
    }
    else 
    {
      document.getElementById('checkid').innerHTML="<font style=\"color: green\"></font>";
      document.getElementById('userid').style.border='grey 1px solid'; 
      return true;
    }
  }

function checkemail() {
  var email = document.getElementById("email").value;
  var atpos = email.indexOf("@");
  var dotpos = email.lastIndexOf(".");
      if (email == null || email == "") 
    {
          document.getElementById('checkemail').innerHTML="<font style=\"color: red\">*Empty Field</font>";
      document.getElementById('email').style.border='red 1px solid';
      return false;
    }
    else if(atpos< 1 || dotpos<atpos+2 || dotpos+2>=email.length)
    {
      document.getElementById('checkemail').innerHTML="<font style=\"color: red\">*Invalid Format</font>";
      document.getElementById('email').style.border='red 1px solid';
      return false;
    }
    else
    {
    document.getElementById('checkemail').innerHTML="<font style=\"color: green\"></font>";
    document.getElementById('email').style.border='grey 1px solid';
    return true;
    }
  }
  
  
  function clearfields()
  {
    document.getElementById('checkid').innerHTML="";
    document.getElementById('userid').style.border='grey 1px solid';
    
    document.getElementById('checkemail').innerHTML="";
    document.getElementById('email').style.border='grey 1px solid';
  }
  
  function validateData()
  {
    var submit = document.getElementById("sbtn").value;
      var b = checkid();
      var a = checkemail();
    if(a&&b)
    return true;
    else 
    return false;
    
  }
</script>




</html>


