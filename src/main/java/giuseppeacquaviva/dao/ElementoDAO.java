package giuseppeacquaviva.dao;

import giuseppeacquaviva.entities.Elemento;

import javax.persistence.EntityManager;
import java.util.List;

public class ElementoDAO {
    private EntityManager em;
    public ElementoDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Elemento elemento) {
        em.getTransaction().begin();
        em.persist(elemento);
        em.getTransaction().commit();
        System.out.println("Elemento " + elemento.getTitolo() + " salvato correttamente nel db");
    }
    public void delete(String codiceISBN) {
        em.getTransaction().begin();
        Elemento elemento = em.createQuery("SELECT e FROM Elemento e WHERE e.codiceISBN = :codiceISBN", Elemento.class)
                .setParameter("codiceISBN", codiceISBN)
                .getSingleResult();
        if (elemento != null) {
            em.remove(elemento);
        }
        em.getTransaction().commit();
        System.out.println("Elemento " + elemento.getTitolo() + " eliminato correttamente nel db");
    }
    public Elemento findByCodiceISBN(String codiceISBN) {
        Elemento elemento = em.createQuery("SELECT e FROM Elemento e WHERE e.codiceISBN = :codiceISBN", Elemento.class)
                .setParameter("codiceISBN", codiceISBN)
                .getSingleResult();
        return elemento;
    }

    public List<Elemento> findByAnnoPubblicazione(int annoPubblicazione) {
        List<Elemento> elementi = em.createQuery("SELECT e FROM Elemento e WHERE e.annoPubblicazione = :annoPubblicazione", Elemento.class)
                .setParameter("annoPubblicazione", annoPubblicazione)
                .getResultList();
        return elementi;
    }

    public List<Elemento> findByTitoloContaining(String titolo) {
        List<Elemento> elementi = em.createQuery("SELECT e FROM Elemento e WHERE e.titolo LIKE :titolo", Elemento.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
        return elementi;
    }
    public Elemento findByNumeroTessera(String numeroTessera) {
        Elemento elemento = em.createQuery("SELECT e FROM Elemento e WHERE e.numeroTessera = :numeroTessera", Elemento.class)
                .setParameter("numeroTessera", numeroTessera)
                .getSingleResult();
        return elemento;
    }
}
