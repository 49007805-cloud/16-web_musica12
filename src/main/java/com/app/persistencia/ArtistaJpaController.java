package com.app.persistencia;

import java.util.List;
import com.app.logica.Artista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ArtistaJpaController {

	// "Fabrica" de conexiones (EntityManagerFactory).
	// Sirve para crear EntityManager, que son los que realmente hablan con la BD.
	private EntityManagerFactory emf = null;

	// En el constructor decimos con que unidad de persistencia vamos a trabajar.
	// Esa unidad esta definida en persistence.xml con el nombre "actividad14".
	public ArtistaJpaController() {
		emf = Persistence.createEntityManagerFactory("actividad14");
	}

	// Metodo que crea y devuelve un nuevo EntityManager.
	// Pensalo como: "dame un empleado que se conecte a la base para trabajar".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transaccion porque no modifica nada en la base.
	public Artista buscarUno(int id) {
		EntityManager em = getEntityManager();
		try {
			// find busca por clave primaria. Si no existe, devuelve null.
			return em.find(Artista.class, id);
		} finally {
			em.close();
		}
	}

	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Artista> buscarTodos() {
		EntityManager em = getEntityManager();
		try {
			// Consulta JPQL: "traeme todos los objetos Artista"
			return em.createQuery("SELECT a FROM Artista a", Artista.class).getResultList();
		} finally {
			em.close();
		}
	}

	// Metodo para guardar un Artista en la base de datos.
	public void crearArt(Artista art) {
		EntityManager em = null;
		try {
			// Abrimos la conexi�n (pedimos un empleado).
			em = getEntityManager();

			// Iniciamos una transacci�n: empieza la "promesa".
			em.getTransaction().begin();

			// Guardamos el objeto en la base (pero todav�a no est� confirmado).
			em.persist(art);

			// Confirmamos la transacci�n: ahora s� el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexi�n (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
	}

	// EDITAR: modifica los datos de un artista existente.
	public void editarArt(Artista art) {
		EntityManager em = null;

		try {
			em = getEntityManager();
			em.getTransaction().begin();

			em.merge(art);

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// ELIMINAR: elimina un artista por su ID.
	public void eliminarArt(int id) {
		EntityManager em = null;

		try {
			em = getEntityManager();
			em.getTransaction().begin();

			Artista art = em.find(Artista.class, id);

			if (art != null) {
				em.remove(art);
			}

			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
