<%--
  Created by IntelliJ IDEA.
  User: bryan
  Date: 14/11/2025
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.bryan.aplicacionweb.manejosesiones.models.*" %>
<%-- Traemos la sesion Scriplets--%>
<%
    DetalleCarro detalleCarro = (DetalleCarro) session.getAttribute("carro");
%>
<html>
<head>
    <title>Carro de Compras</title>
</head>
<body>
<h1>Carro de Compras</h1>
<%
    if (detalleCarro == null || detalleCarro.getItems().isEmpty()) {%>
<p> Lo sentimos no hay productos en el carro de compras!</p>
    <%} else {%>
        <table>
            <tr>
                <td>id Producto</td>
                <td>nombre</td>
                <td>precio</td>
                <td>cantidad</td>
                <td>subtotal</td>
            <tr>
            <%
            for(ItemCarro item : detalleCarro.getItems()){%>
            <tr>
            <td><%=item.getProducto().getIdProducto()%></td>
            <td><%=item.getProducto().getNombre()%></td>
            <td><%=item.getProducto().getPrecio()%></td>
            <td><%=item.getCantidad()%></td>
            <td><%=item.getSubtotal()%></td>
            <tr>
            <% }%>
            <tr>
            <td colspan="4" style="text-align:right">Total: </td>
            <td><%=detalleCarro.getTotal()%></td>
        </tr>
            </table>


    <%}%>
<p><a href="<%=request.getContextPath()%>/productos">SEGUIR COMPRANDO</a> </p>
<p><a href="<%=request.getContextPath()%>/index.html">Volver</a> </p>
</body>
</html>
