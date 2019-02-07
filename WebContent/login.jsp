<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.TreeMap,java.util.Set" session="false"%>
<%
String salidaIncorrecta=(String)request.getAttribute("salidaIncorrecta");
String profesion=(String)request.getParameter("profesion");
String contadorVisitas=(String)request.getAttribute("contadorVisitas");
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
<meta name="author" content="Adrian Morales">
<title>LOGIN PRACTICA 2</title>      
<body>
	<div align="center" style="color:red">
       <%=salidaIncorrecta%>
       <br>
    </div> 
    <form action="Servlet" method="post">
    <input hidden name="uidPage" value="1">
    <input hidden name="profesion" value=<%=profesion%>>
    <span hidden><%=contadorVisitas %></span>
      <span>Usuario</span><input type="text" name="user"/>
      <br> 
      <span>Contraseña</span><input type="password" name="pass"/> 
      <br>
      <input type="submit" value="Autenticar"/> 
    </form>     
</body>
</html>