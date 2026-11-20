
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

@WebServlet("/SvCancionEliminar")
public class SvCancionEliminar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            controladora.eliminarCancion(id);

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar"
            );

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al eliminar la canción.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar?error=" + error
            );
        }
    }
}
