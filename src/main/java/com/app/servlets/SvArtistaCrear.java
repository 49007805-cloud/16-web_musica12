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

@WebServlet("/SvArtistaCrear")
public class SvArtistaCrear extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String generoMusical = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");

            int edad = Integer.parseInt(request.getParameter("edad"));

            // El formulario no solicita fecha de nacimiento.
            Artista artista = new Artista(
                nombre,
                generoMusical,
                nacionalidad,
                edad,
                null
            );

            controladora.crearArtista(artista);

            response.sendRedirect(request.getContextPath() + "/SvArtistaListar");

        } catch (Exception e) {

            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al crear el artista.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvArtistaListar?error=" + error
            );
        }
    }
}