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
<style>
	ul {
		list-style-type: none;
		padding: 0;
		margin: 0;
	}
</style>
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
	<li><a href="userHomePage.jsp">HOME</a></li>
	<li class="currentpage"><a href="/SecureDataStorage-V3/search.jsp">SEARCH</a></li>
	<li class="fileListPage"><a href="/SecureDataStorage-V3/FileDetailsForReceiver">FILE LIST</a></li>
	<li><a href="userlogin.jsp">SIGN OUT</a></li>
</ul>
</div>
<!-- end of navbar_link--></div>
<!-- end of navigation--></div>
<!-- end of header-->


<div id="innerwrapper">
	<form action="Search" method="post" style="padding: 10px; margin: 10px; text-align: center; autocomplete:on">
		Keyword  <input type="text" name="searchText" style="width: 500px;"/>
		<select name="sort">
			<option value="">Select</option>
			<option value="rank">Rank</option>
			<option value="dateasc">Date(Asc)</option>
			<option value="datedesc">Date(Desc)</option>
		</select>
		<br />
			<span style="text-align: center">OR</span>
		<br />
		Cluster &nbsp&nbsp<input type="text" name="cluster" style="width: 587px;"/>
		<br />
  		<input type="submit" value="Search" style="width: 100px; padding: 10px; margin: 10px; "/>
	</form>
</div>
<div id="footer">
<p align="center"><span style="float: center;">&nbsp;&nbsp;&nbsp;</span>
</p>
</div>
</div>
</body>
</html>