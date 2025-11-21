<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.bryan.aplicacionweb.manejosesiones.models.*" %>

<%
    DetalleCarro detalleCarro = (DetalleCarro) session.getAttribute("carro");
%>

<html>
<head>
    <title>Carro de Compras</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

</head>
<body class="bg-light">

<div class="container mt-4 p-4 bg-white rounded shadow">

    <h1 class="text-center mb-4">Carro de Compras</h1>

    <%
        if (detalleCarro == null || detalleCarro.getItems() == null || detalleCarro.getItems().isEmpty()) {
    %>

    <div class="alert alert-warning text-center">
        Lo sentimos, no hay productos en el carro de compras.
    </div>

    <% } else {

        double subtotal = detalleCarro.getTotal();
        double iva = subtotal * 0.15;
        double totalFinal = subtotal + iva;
    %>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Producto</th>
            <th>Nombre</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>

        <%
            for(ItemCarro item : detalleCarro.getItems()) {
        %>
        <tr>
            <td><%= item.getProducto().getIdProducto() %></td>
            <td><%= item.getProducto().getNombreProducto() %></td>
            <td>$<%= item.getProducto().getPrecio() %></td>
            <td><%= item.getCantidad() %></td>
            <td>$<%= item.getSubtotal() %></td>
        </tr>
        <% } %>

        <tr class="fw-bold">
            <td colspan="4" class="text-end">Subtotal:</td>
            <td>$<%= subtotal %></td>
        </tr>

        <tr class="fw-bold">
            <td colspan="4" class="text-end">IVA (15%):</td>
            <td>$<%= iva %></td>
        </tr>

        <tr class="fw-bold table-success">
            <td colspan="4" class="text-end">TOTAL:</td>
            <td>$<%= totalFinal %></td>
        </tr>

        </tbody>
    </table>

    <form action="<%=request.getContextPath()%>/generar-pdf" method="GET">
        <button type="submit" class="btn btn-danger mb-3">Descargar PDF</button>
    </form>

    <% } %>

    <a href="<%=request.getContextPath()%>/productos" class="btn btn-primary">Seguir Comprando</a>
    <a href="<%=request.getContextPath()%>/index.html" class="btn btn-secondary">Volver</a>

</div>

</body>
</html>
