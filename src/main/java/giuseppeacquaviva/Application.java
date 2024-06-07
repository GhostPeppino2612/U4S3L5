package giuseppeacquaviva;

import giuseppeacquaviva.dao.ElementoDAO;
import giuseppeacquaviva.dao.PrestitoDAO;
import giuseppeacquaviva.dao.UtenteDAO;
import giuseppeacquaviva.entities.Elemento;
import giuseppeacquaviva.entities.Libro;
import giuseppeacquaviva.entities.Prestito;
import giuseppeacquaviva.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4s3l5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementoDAO ed = new ElementoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);

        Libro libro = new Libro("1234567890","Java Programming", 2022, 500, "John Doe", "Informatica");
       // ed.save(libro);


        System.out.println(ed.findByCodiceISBN("1234567890"));


        Utente utente = new Utente("Mario","Rossi", LocalDate.now().minusYears(20), "T123");
        // ud.save(utente);

        Prestito prestito = new Prestito(LocalDate.now().minusDays(2), utente, libro);
        pd.save(prestito);

        Utente utenteFromDB = ed.findByNumeroTessera(numeroTessera);

        em.close();
        emf.close();
    }
}
