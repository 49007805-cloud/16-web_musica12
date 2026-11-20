
package com.app.logica;

import com.app.persistencia.ControladoraPersistencia;
import java.util.List;

public class ControladoraLogica {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    // ----- VALIDACIONES DE ARTISTA -----
    private void validarArtista(Artista art) throws Exception {
        if (art.getNombre() == null || art.getNombre().isEmpty()) {
            throw new Exception("El artista debe tener un nombre.");
        }

        if (art.getGeneroMusical() == null || art.getGeneroMusical().isEmpty()) {
            throw new Exception("El artista debe tener un genero musical.");
        }

        if (art.getEdad() <= 0) {
            throw new Exception("La edad del artista debe ser mayor a 0.");
        }

        if (art.getEdad() >= 100) {
            throw new Exception("La edad del artista debe ser menor a 100.");
        }
    }

    // ----- ARTISTA -----

    public void crearArtista(Artista art) throws Exception {
        validarArtista(art);
        controlPersis.crearArtista(art);
    }

    public Artista buscarUnArtista(int id) {
        return controlPersis.buscarArtista(id);
    }

    public List<Artista> listarArtistas() {
        return controlPersis.buscarTodosLosArtistas();
    }

    // EDITAR ARTISTA
    public void editarArtista(int id, String nombre, String generoMusical,
            String nacionalidad, int edad) throws Exception {

        Artista existente = controlPersis.buscarArtista(id);

        if (existente == null) {
            throw new Exception("No existe un artista con el ID indicado.");
        }

        Artista art = new Artista(nombre, generoMusical, nacionalidad, edad, null);

        validarArtista(art);

        existente.setNombre(nombre);
        existente.setGeneroMusical(generoMusical);
        existente.setNacionalidad(nacionalidad);
        existente.setEdad(edad);

        controlPersis.editarArtista(existente);
    }
    // ELIMINAR ARTISTA
    public void eliminarArtista(int id) throws Exception {
        Artista existente = controlPersis.buscarArtista(id);

        if (existente == null) {
            throw new Exception("No existe un artista con el ID indicado.");
        }

        controlPersis.eliminarArtista(id);
    }

    // ----- CANCIÓN -----

    private void validarCancion(Cancion can) throws Exception {
        if (can.getTitulo() == null || can.getTitulo().isEmpty()) {
            throw new Exception("La cancion debe tener un titulo.");
        }

        if (can.getDuracion() <= 0) {
            throw new Exception("La duracion debe ser mayor a 0 segundos.");
        }

        if (can.getAnio() < 1900 || can.getAnio() > 2026) {
            throw new Exception("El año de la cancion debe ser valido.");
        }
    }

    public void crearCancion(Cancion can) throws Exception {
        validarCancion(can);
        controlPersis.crearCancion(can);
    }

    public Cancion buscarUnaCancion(int id) {
        return controlPersis.buscarCancion(id);
    }

    public List<Cancion> listarCanciones() {
        return controlPersis.buscarTodasLasCanciones();
    }

    // EDITAR CANCIÓN
    public void editarCancion(int id, String titulo, float duracion, int anio) throws Exception {

        Cancion existente = controlPersis.buscarCancion(id);

        if (existente == null) {
            throw new Exception("No existe una cancion con el ID indicado.");
        }

        Cancion can = new Cancion(titulo, duracion, anio);

        validarCancion(can);

        existente.setTitulo(titulo);
        existente.setDuracion(duracion);
        existente.setAnio(anio);

        controlPersis.editarCancion(existente);
    }

    // ELIMINAR CANCIÓN
    public void eliminarCancion(int id) throws Exception {
        Cancion existente = controlPersis.buscarCancion(id);

        if (existente == null) {
            throw new Exception("No existe una cancion con el ID indicado.");
        }

        controlPersis.eliminarCancion(id);
    }
}
