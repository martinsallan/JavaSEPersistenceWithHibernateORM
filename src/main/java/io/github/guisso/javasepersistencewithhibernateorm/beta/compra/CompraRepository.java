package io.github.guisso.javasepersistencewithhibernateorm.beta.compra;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class CompraRepository
        extends Repository<Compra> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT c FROM Compra c";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT c FROM Compra c WHERE c.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Compra c WHERE c.id = :id";
    }
    
    public List<Compra> findAllActive() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT c FROM Compra c WHERE c.ativo = true", Compra.class)
                     .getResultList();
        }
    }

    public List<Compra> findAllInTrash() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT c FROM Compra c WHERE c.ativo = false", Compra.class)
                     .getResultList();
        }
    } 

    public void softDelete(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            Compra c = em.find(Compra.class, id);
            if (c != null) {
                c.setAtivo(false); // Marca como inativo
                em.merge(c);
            }
            
            tx.commit();
        }
    }

    public void restore(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            Compra c = em.find(Compra.class, id);
            if (c != null) {
                c.setAtivo(true); // Marca como ativo
                em.merge(c);
            }
            
            tx.commit();
        }
    }
    public void hardDelete(Long id) {
        super.delete(id);
    }
}
