
package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvCancionEditar")
public class SvCancionEditar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    // GET: buscar la canción y mostrar el formulario de edición.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Cancion cancion = controladora.buscarUnaCancion(id);

            if (cancion == null) {
                throw new Exception("No existe una canción con el ID indicado.");
            }

            request.setAttribute("cancion", cancion);

            request.getRequestDispatcher("/WEB-INF/vistas/editarCancion.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al buscar la canción.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar?error=" + error
            );
        }
    }

    // POST: recibir los datos y guardar los cambios.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            Cancion cancion = controladora.buscarUnaCancion(id);

            controladora.editarCancion(id, titulo, duracion, anio);

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar"
            );

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al editar la canción.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar?error=" + error
            );
        }
    }
}
