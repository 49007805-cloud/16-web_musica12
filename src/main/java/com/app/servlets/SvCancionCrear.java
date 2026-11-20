package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SvCancionCrear")
public class SvCancionCrear extends HttpServlet {

    private ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
 
        try {
            String titulo = request.getParameter("titulo");

            float duracion = Float.parseFloat(
                request.getParameter("duracion")
            );

            int anio = Integer.parseInt(
                request.getParameter("anio")
            );

            Cancion cancion = new Cancion(titulo, duracion, anio);

            controladora.crearCancion(cancion);

            response.sendRedirect(request.getContextPath() + "/SvCancionListar");

        } catch (Exception e) {

            String error = URLEncoder.encode(
                e.getMessage() != null ? e.getMessage() : "Error al crear la canción.",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(
                request.getContextPath() + "/SvCancionListar?error=" + error
            );
        }
    }
}