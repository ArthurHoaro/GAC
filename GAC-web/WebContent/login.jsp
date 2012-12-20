<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<form name="loginform" method="post" action="site.Login">
<br><br>
<table align="center"><tr><td><h2>Login Authentication</h2></td></tr></table>
<table width="300px" align="center" style="border:1px solid #000000;background-color:#efefef;">
<tr><td colspan=2></td></tr>
<tr><td colspan=2> </td></tr>
  <tr>
  <td><b>Login Name</b></td>
  <td><input type="text" name="userName" value=""></td>
  </tr>
  <tr>
  <td><b>Password</b></td>
  <td><input type="password" name="password" value=""></td>
  </tr>
  <tr>
  <td></td>
  <td><input type="submit" name="Submit" value="Submit"></td>
  </tr>
  <tr><td colspan=2> </td></tr>
</table>
</form>
<f:view>

</f:view>
</body>
</html>