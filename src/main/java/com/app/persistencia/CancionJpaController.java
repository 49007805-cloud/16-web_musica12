package com.app.persistencia;

import com.app.logica.Cancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CancionJpaController {

	// "F�brica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qu� unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que est� en persistence.xml.
	public CancionJpaController() {
		emf = Persistence.createEntityManagerFactory("actividad14");
	}

	// M�todo que devuelve un nuevo EntityManager (una conexi�n lista para
	// trabajar).
	// Pod�s pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transacci�n porque no modifica nada en la base.
	public Cancion buscarUno(int id) {
		EntityManager em = getEntityManager();
		try {
			// find busca por clave primaria. Si no existe, devuelve null.
			return em.find(Cancion.class, id);
		} finally {
			em.close();
		}
	}

	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Cancion> buscarTodos() {
		EntityManager em = getEntityManager();
		try {
			// Consulta JPQL: "traeme todos los objetos Artista"
			return em.createQuery("SELECT c FROM Cancion c", Cancion.class).getResultList();
		} finally {
			em.close();
		}
	}

	// M�todo para crear (persistir) una Canci�n en la base de datos.
	public void crearCan(Cancion canc) {
		EntityManager em = null;
		try {
			// Abrimos la conexi�n (pedimos un empleado).
			em = getEntityManager();

			// Iniciamos una transacci�n: empieza la "promesa".
			em.getTransaction().begin();

			// Guardamos el objeto en la base (pero todav�a no est� confirmado).
			em.persist(canc);

			// Confirmamos la transacci�n: ahora s� el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			if (em != null) {
	            em.close();
	        }

		}
	}
	// EDITAR: modifica los datos de una canción existente.
	public void editarCan(Cancion canc) {
	    EntityManager em = null;

	    try {
	        em = getEntityManager();
	        em.getTransaction().begin();

	        em.merge(canc);

	        em.getTransaction().commit();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}

	// ELIMINAR: elimina una canción por su ID.
	public void eliminarCan(int id) {
	    EntityManager em = null;

	    try {
	        em = getEntityManager();
	        em.getTransaction().begin();

	        Cancion canc = em.find(Cancion.class, id);

	        if (canc != null) {
	            em.remove(canc);
	        }

	        em.getTransaction().commit();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
}
