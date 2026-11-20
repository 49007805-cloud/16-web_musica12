package com.app.persistencia;

import java.util.List;
import com.app.logica.Artista;
import com.app.logica.Cancion;

public class ControladoraPersistencia {
	// Artista: Creamos una instancia del controlador que sabe c�mo hablar con la
	// base de datos
	ArtistaJpaController artistaJpa = new ArtistaJpaController();

	// Cancion: Creamos una instancia del controlador que sabe c�mo hablar con la
	// base de datos
	CancionJpaController cancionJpa = new CancionJpaController();
	// INSTANCIAR OBJETO DE CANCIONJPACONTROLER AL IGUAL QUE SE HIZO CON ARTISTA

	// BUSCAR UN ARTISTA
	public Artista buscarArtista(int id) {
		return artistaJpa.buscarUno(id);
	}

	// BUSCAR TODOS LOS ARTISTAS
	public List<Artista> buscarTodosLosArtistas() {
		return artistaJpa.buscarTodos();
	}

	public Cancion buscarCancion(int id) {
		return cancionJpa.buscarUno(id);
	}

	// BUSCAR TODOS LOS ARTISTAS
	public List<Cancion> buscarTodasLasCanciones() {
		return cancionJpa.buscarTodos();
	}

	// Este m�todo es el que se usa desde otras capas para crear un ARTISTA
	public void crearArtista(Artista artista) {
		// Delegamos la tarea al controlador especializado
		artistaJpa.crearArt(artista);
	}

	// Este m�todo es el que se usa desde otras capas para crear un ARTISTA
	public void crearCancion(Cancion cancion) {
		// Delegamos la tarea al controlador especializado
		cancionJpa.crearCan(cancion);
	}
	// EDITAR ARTISTA
	public void editarArtista(Artista artista) {
	    artistaJpa.editarArt(artista);
	}

	// ELIMINAR ARTISTA
	public void eliminarArtista(int id) {
	    artistaJpa.eliminarArt(id);
	}

	// EDITAR CANCION
	public void editarCancion(Cancion cancion) {
	    cancionJpa.editarCan(cancion);
	}

	// ELIMINAR CANCION
	public void eliminarCancion(int id) {
	    cancionJpa.eliminarCan(id);
	}
}
