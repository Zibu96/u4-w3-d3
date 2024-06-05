package giovannighirardelli;

import giovannighirardelli.dao.EventoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();
        EventoDAO ed = new EventoDAO(em);
        ;
    }
}
