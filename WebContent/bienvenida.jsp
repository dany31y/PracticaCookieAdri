<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.TreeMap,java.util.Set" session="false"%>
<%
String nombre=(String)request.getParameter("user");
String password=(String)request.getParameter("pass");
String profesion=(String)request.getAttribute("profesion");
String contadorVisitas=(String)request.getAttribute("contadorVisitas");
String alfarero="";
String brujo="";
String curtidor="";
//Ahora vemos qué profesion ha seleccionado el Usuario
if(profesion!=null){
	switch(profesion) {
		case "Alfarero":
			alfarero="checked=\"on\"";
			break;
		case "Brujo":
			brujo="checked=\"on\"";
			break;
		case "Curtidor":
			curtidor="checked=\"on\"";
			break;
		}
}
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
<meta name="author" content="Adrian Morales">
<title>BIENVENIDA PRACTICA 2</title>      
<body>
    <br>
    <form action="Servlet" method="post">
    <input hidden name="uidPage" value="2">
    <input hidden name="user" value=<%=nombre%>>
    <input hidden name="pass" value=<%=password%>>
    <span>Bienvenido: <%=nombre %></span>
    <br>
      <input type="radio" name="profesion" <%=alfarero%>value="Alfarero">Alfarero
      <br>
      <input type="radio" name="profesion"<%=brujo%>value="Brujo">Brujo
      <br>
      <input type="radio" name="profesion"<%=curtidor%>value="Curtidor">Curtidor
      <br>
      <span>Numero de Visitas: <%=contadorVisitas%></span>
      <br>
      <input type="submit" value="Desconectar"/> 
    </form>           
</body>