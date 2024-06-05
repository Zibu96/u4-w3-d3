package giovannighirardelli;

import giovannighirardelli.dao.EventoDAO;
import giovannighirardelli.dao.LocationDAO;
import giovannighirardelli.dao.PartecipazioneDAO;
import giovannighirardelli.dao.PersonaDAO;
import giovannighirardelli.entities.*;
import giovannighirardelli.exeptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();
        EventoDAO ed = new EventoDAO(em);
        PersonaDAO pd = new PersonaDAO(em);
        PartecipazioneDAO ptd = new PartecipazioneDAO(em);
        LocationDAO ld = new LocationDAO(em);

        try {
            Evento evFromDb = ed.findById(152);
            System.out.println(evFromDb.getTitolo());
        } catch (
                NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Evento evFromDb = ed.findById(152);
        Persona gianni = new Persona("Gianni", "Ghirardelli", "gianni@gmail.com", LocalDate.of(1996, 9, 28), Sesso.M);

//        pd.save(gianni);
        Persona perFromDb = pd.findById("4cc96570-0e5b-4ef8-a1df-033c641834fc");
        System.out.println(perFromDb.getNome());

        Partecipazione prova1 = new Partecipazione(perFromDb, evFromDb, StatoPartecipazione.CONFERMATA);

//        ptd.save(prova1);
        perFromDb.getPartecipazioneList().forEach(System.out::println);
        evFromDb.getPartecipazioneList().forEach(System.out::println);


        Location loc = new Location("Campovolo", "Reggio Emilia");
        Location locFromDb = ld.findById("a8a4ebb5-ead0-412f-a186-84ec78c792dc");

//        ld.save(loc);

        Evento newEv = new Evento("Concertone", LocalDate.now(), "Un bel concerto", TipoEvento.PUBBLICO, 852, locFromDb);
//        ed.save(newEv);
    }
}
