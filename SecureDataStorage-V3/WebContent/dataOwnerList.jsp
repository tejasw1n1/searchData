<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
           "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>SDS</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link href="stylegreen.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function deleteFile(userId){
		console.log("userid"+userId);
		window.location.href="/SecureDataStorage-V3/DeleteOwner?userId="+userId;
	}
</script>
</head>
<body>

<div id="outerwrapper">

<div id="head">

  
   <div id="logo">
      <div align="center" style="font-size:34px;color:white;font-family-verdana;padding:30px 40px 30px 20px;line-height:1.2em;font-weight:bold;">ENHANCED SEARCHING AND RANKING SYSTEM TO PERSONALIZE THE USER INFORMATION IN CLOUD DATA STORAGE 
	   <div align="center"> </div> 
	  </div>
   </div><!-- end of logo -->
   


<div id="navigation">
<div id="navbar_link">
<ul>
	<li><a href="adminHomePage.jsp">HOME</a></li>
	<li><a href="FileDetailsForServer">VIEW FILE LISTS</a></li>
	<li class="currentpage"><a href="OwnerDetailsForServer">VIEW DATA OWNERS LISTS</a></li>
	<li><a href="UserDetailsForServer">VIEW DATA USERS LISTS</a></li>
	<li><a href="serverlogin.jsp">SIGN OUT</a></li>
</ul>

</div>
<!-- end of navbar_link--></div>
<!-- end of navigation--></div>
<!-- end of header-->


<div id="innerwrapper">
<table>

			<thead>
				<tr>
					<th>User Name</th>
					<th>Password</th>
					<th>Type of User</th>
					<th>Email ID</th>
					<th>Phone</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				 <c:forEach items="${users}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.typeofuser}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td><button name="Delete" onclick="deleteFile(${user.userid})">Delete</button></td>
						
				</tr>
				</c:forEach> 
			</tbody>
		</table>

</div>
<div id="footer">
<p align="center"><span style="float: center;">&nbsp;&nbsp;&nbsp;</span>
</p>
</div>
</div>

</body>
</html>