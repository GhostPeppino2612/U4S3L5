package giuseppeacquaviva.dao;

import giuseppeacquaviva.entities.Prestito;
import giuseppeacquaviva.entities.Utente;
import org.hibernate.type.descriptor.java.LocaleTypeDescriptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class PrestitoDAO {
    private EntityManager em;
    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Prestito prestito) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(prestito);
        t.commit();
        System.out.println("Prestito " + prestito.getId() + " salvato correttamente nel db");

    }

    public List<Prestito> findByUtenteAndDataRestituzioneEffettivaIsNull(Utente utente) {
        List<Prestito> prestiti = em.createQuery("SELECT p FROM Prestito p WHERE p.utente = :utente AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("utente", utente)
                .getResultList();
        return prestiti;
    }

    public List<Prestito> findByDataRestituzionePrevistaBeforeAndDataRestituzioneEffettivaIsNull(LocalDate data) {
        List<Prestito> prestiti = em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :data AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("data", data)
                .getResultList();
        return prestiti;
    }
}
