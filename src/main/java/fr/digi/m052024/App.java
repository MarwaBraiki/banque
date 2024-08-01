package fr.digi.m052024;

import fr.digi.m052024.entites.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Create a new client
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");

        // Persist the client
        em.persist(client);

        tx.commit();

        // Fetch and print clients
        em.createQuery("SELECT c FROM Client c", Client.class)
                .getResultList()
                .forEach(c -> System.out.println(c.getNom() + " " + c.getPrenom()));

        em.close();
        emf.close();
    }
}
