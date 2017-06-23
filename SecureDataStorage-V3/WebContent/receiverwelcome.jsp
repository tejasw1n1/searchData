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

</head>
<body>

<div id="outerwrapper">

<div id="head">

<div id="logo">
      <div align="center" style="font-size:34px;color:white;font-family-verdana;padding:30px 40px 30px 20px;line-height:1.2em;font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ENHANCED SEARCHING AND RANKING SYSTEM TO PERSONALIZE THE USER INFORMATION IN CLOUD DATA STORAGE  
	   <div align="center"> </div> 
	  </div>
   </div><!-- end of logo -->

<div id="navigation">
<div id="navbar_link">
<ul>
	<li><a href="userHomePage.jsp">HOME</a></li>
	<li class="searchpage"><a href="/SecureDataStorage-V3/search.jsp">SEARCH</a></li>
	<li class="currentpage"><a href="#">FILE LIST</a></li>
	<li><a href="userlogin.jsp">SIGN OUT</a></li>
</ul>
</div>
<!-- end of navbar_link--></div>
<!-- end of navigation--></div>
<!-- end of header-->


<div id="innerwrapper">
	<c:if test="${empty files}">
		<h3 style="text-align: center;">Results Not Found</h3>
	</c:if>
	<c:if test="${not empty files}">
	<table>
			<thead>
				<tr>
					<th>File Id</th>
					<th>File Name</th>
					<th>File Owner</th>
					<th>Date</th>
					<th>Rank</th>
					<th>Encrypted Formate</th>
					<th>File Request</th>
					<th>Download</th>
				</tr>
			</thead>
			<tbody>
				 <c:forEach items="${files}" var="file">
					<tr>
						<td>${file.fileid}</td>
						<td>${file.filename}</td>
						<td>${file.ownerName}</td>
						<td>${file.uploadeddate}</td>
						<td>${file.rank}</td>
						<td>${file.encryptedformate}</td>
						<td><a href="filerequest.jsp?filename=${file.filename}&&fileid=${file.fileid}">File Request</a></td>
						<td><a href="download.jsp?filename=${file.filename}&&fileid=${file.fileid}">download</a></td>
				
					</tr>
				</c:forEach> 
				
			</tbody>
		</table>
	</c:if>
</div>
<div id="footer">
<p align="center"><span style="float: center;">&nbsp;&nbsp;&nbsp;</span>
</p>
</div>
</div>

</body>
</html>