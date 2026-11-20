package com.app.presentacion;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import java.util.List;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
	public static void main(String[] args) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = formato.parse("20-12-1995");
        Date fecha2 = formato.parse("19-12-1992");
		Artista art1 = new Artista("Juan", "Rock", "Argentino", 21,fecha);
		Artista art2 = new Artista("Martin", "Jazz", "Aleman", 22,fecha2);
		Cancion can1 = new Cancion("Civil War", 7.40f, 1991);
		Cancion can2 = new Cancion("Wonderwall", 3.40f, 1995);
		ControladoraLogica conLog = new ControladoraLogica();
		try {
			conLog.crearArtista(art1);
			conLog.crearArtista(art2);
			conLog.crearCancion(can1);
			conLog.crearCancion(can2);

		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}
		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		List<Artista> artistas = conLog.listarArtistas();
		for (Artista a : artistas) {
			System.out.println(a);
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Artista encontrado = conLog.buscarUnArtista(1);
		if (encontrado != null) {
			System.out.println(encontrado);
		} else {
			System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Artista noExiste = conLog.buscarUnArtista(999);
		if (noExiste != null) {
			System.out.println(noExiste);
		} else {
			System.out.println("No existe un artista con ese id");
		}
		// ----- LECTURA DE Canciones -----
		System.out.println("=== TODOS LAS CANCIONES ===");
		List<Cancion> canciones = conLog.listarCanciones();
		for (Cancion a : canciones) {
			System.out.println(a);
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Cancion encontrado2 = conLog.buscarUnaCancion(1);
		if (encontrado2 != null) {
			System.out.println(encontrado2);
		} else {
			System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Cancion noExiste2 = conLog.buscarUnaCancion(999);
		if (noExiste2 != null) {
			System.out.println(noExiste2);
		} else {
			System.out.println("No existe un artista con ese id");
		}
	}

}
