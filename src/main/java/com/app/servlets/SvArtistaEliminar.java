
package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaEliminar")
public class SvArtistaEliminar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            controladora.eliminarArtista(id);

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar"
            );

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al eliminar el artista.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + error
            );
        }
    }
}
