<%--
  Created by IntelliJ IDEA.
  User: bryan
  Date: 21/11/2025
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bryan.aplicacionweb.manejosesiones.models.Producto" %>
<%
    Producto producto = (Producto) request.getAttribute("producto");
    boolean isEdit = producto != null && producto.getIdProducto() != null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Editar Producto" : "Crear Producto" %></title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-4 p-4 bg-white shadow rounded">

    <h2 class="mb-3"><%= isEdit ? "Editar Producto" : "Crear Producto" %></h2>

    <form action="<%= request.getContextPath() %>/guardar" method="post">

        <%-- Campo oculto solo para edición --%>
        <% if (isEdit) { %>
        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto() %>"/>
        <% } %>

        <div class="mb-3">
            <label class="form-label">Nombre del Producto *</label>
            <input type="text" name="nombreProducto" class="form-control" required
                   value="<%= (producto != null) ? producto.getNombreProducto() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">ID Categoría *</label>
            <input type="number" name="idCategoria" class="form-control" required
                   value="<%= (producto != null && producto.getCategoria() != null)
                            ? producto.getCategoria().getIdCategoria() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Stock *</label>
            <input type="number" name="stock" class="form-control" min="0" required
                   value="<%= (producto != null) ? producto.getStock() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Precio *</label>
            <input type="number" name="precio" class="form-control" step="0.01" min="0" required
                   value="<%= (producto != null) ? producto.getPrecio() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Código *</label>
            <input type="text" name="codigo" maxlength="15" class="form-control" required
                   value="<%= (producto != null) ? producto.getCodigo() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Descripción (opcional)</label>
            <input type="text" name="descripcion" maxlength="60" class="form-control"
                   value="<%= (producto != null) ? producto.getDescripcion() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Fecha de Elaboración *</label>
            <input type="date" name="fechaElaboracion" class="form-control" required
                   value="<%= (producto != null) ? producto.getFechaElaboracion() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label">Fecha de Caducidad *</label>
            <input type="date" name="fechaCaducidad" class="form-control" required
                   value="<%= (producto != null) ? producto.getFechaCaducidad() : "" %>">
        </div>

        <button type="submit" class="btn btn-primary">
            <%= isEdit ? "Guardar Cambios" : "Crear Producto" %>
        </button>

        <a href="<%= request.getContextPath() %>/productos" class="btn btn-secondary">
            Cancelar
        </a>

    </form>
</div>

</body>
</html>
