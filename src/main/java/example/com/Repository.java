package example.com;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//Define scope (CDI)
@ApplicationScoped
public class Repository {

   @PersistenceContext
   private EntityManager entityManager;

   public Repository () {


   }
}

