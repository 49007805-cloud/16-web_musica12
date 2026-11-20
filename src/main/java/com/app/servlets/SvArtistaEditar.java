
package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvArtistaEditar")
public class SvArtistaEditar extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    // GET: buscar el artista y mostrar el formulario de edición.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Artista artista = controladora.buscarUnArtista(id);

            if (artista == null) {
                throw new Exception("No existe un artista con el ID indicado.");
            }

            request.setAttribute("artista", artista);

            request.getRequestDispatcher("/WEB-INF/vistas/editarArtista.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al buscar el artista.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + error
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
            String nombre = request.getParameter("nombre");
            String generoMusical = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            int edad = Integer.parseInt(request.getParameter("edad"));

            Artista artista = new Artista(
                nombre, generoMusical, nacionalidad, edad, null
            );


            controladora.editarArtista(id, nombre, generoMusical, nacionalidad, edad);

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar"
            );

        } catch (Exception e) {
            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al editar el artista.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + error
            );
        }
    }
}
