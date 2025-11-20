package com.bryan.aplicacionweb.manejosesiones.controllers;

import com.bryan.aplicacionweb.manejosesiones.models.DetalleCarro;
import com.bryan.aplicacionweb.manejosesiones.models.ItemCarro;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/generar-pdf")
public class GenerarPdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        DetalleCarro detalle = (DetalleCarro) req.getSession().getAttribute("carro");

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=carrito.pdf");

        PdfWriter writer = new PdfWriter(resp.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Carrito de Compras").setBold().setFontSize(18));
        document.add(new Paragraph(" "));

        if (detalle != null) {

            Table table = new Table(5);
            table.addCell("Id");
            table.addCell("Nombre");
            table.addCell("Precio");
            table.addCell("Cantidad");
            table.addCell("Subtotal");

            for (ItemCarro item : detalle.getItems()) {
                table.addCell(String.valueOf(item.getProducto().getIdProducto()));
                table.addCell(item.getProducto().getNombreProducto());
                table.addCell(String.valueOf(item.getProducto().getPrecio()));
                table.addCell(String.valueOf(item.getCantidad()));
                table.addCell(String.valueOf(item.getSubtotal()));
            }

            double subtotal = detalle.getTotal();
            double iva = subtotal * 0.15;
            double total = subtotal + iva;

            table.addCell(new Cell(1,4).add(new Paragraph("Subtotal")));
            table.addCell(String.valueOf(subtotal));

            table.addCell(new Cell(1,4).add(new Paragraph("IVA 15%")));
            table.addCell(String.valueOf(iva));

            table.addCell(new Cell(1,4).add(new Paragraph("TOTAL")));
            table.addCell(String.valueOf(total));

            document.add(table);
        }

        document.close();
    }
}
