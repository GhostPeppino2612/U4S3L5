package giuseppeacquaviva.dao;

import giuseppeacquaviva.entities.Elemento;
import giuseppeacquaviva.entities.Utente;

import javax.persistence.EntityManager;

    public class UtenteDAO {
        private EntityManager em;
        public UtenteDAO(EntityManager em) {
            this.em = em;
        }
        public void save(Utente utente) {
            em.getTransaction().begin();
            em.persist(utente);
            em.getTransaction().commit();
            System.out.println("Utente " + utente.getCognome() + " salvato correttamente nel db");
        }
}
