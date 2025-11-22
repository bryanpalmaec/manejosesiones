<%@ page import="com.bryan.aplicacionweb.manejosesiones.models.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bryan.aplicacionweb.manejosesiones.models.Producto" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: bryan
  Date: 21/11/2025
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
    String fechaElaboracion = producto.getFechaElaboracion() != null ?
            producto.getFechaElaboracion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    String fechaCaducidad = producto.getFechaCaducidad() != null ?
            producto.getFechaCaducidad().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>
<html>
<head>
    <title>Formulario</title>
</head>
<body>
<form action="%=request.getContextPath()%>/producto/form" method="post">
    <div>
    </div>
</form>
</body>
</html>
