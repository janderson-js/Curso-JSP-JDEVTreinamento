<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Mensagem de Erro!! Entre em contato com a equipe de suporte do sistema!! teste</h1>
		<%
			out.print(request.getAttribute("msg"));
		%>
	</body>
</html>