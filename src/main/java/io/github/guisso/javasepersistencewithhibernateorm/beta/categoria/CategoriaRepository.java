package io.github.guisso.javasepersistencewithhibernateorm.beta.categoria;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author stefo
 */

public class CategoriaRepository
        extends Repository<Categoria> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT c FROM Categoria c";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT c FROM Categoria c WHERE c.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Categoria c WHERE c.id = :id";
    }

    public List<Categoria> findByNome(String nome) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT c FROM Categoria c WHERE c.nome LIKE :nome", Categoria.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }
    
    public void softDelete(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Categoria c = em.find(Categoria.class, id);
            if (c != null) {
                c.setAtivo(false); // Marca como inativo
                em.merge(c);
            }

            tx.commit();
        }
    }

    public void softDelete(Categoria categoria) {
        softDelete(categoria.getId());
    }

    public void restore(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Categoria c = em.find(Categoria.class, id);
            if (c != null) {
                c.setAtivo(true); // Marca como ativo
                em.merge(c);
            }

            tx.commit();
        }
    }

    public void restore(Categoria categoria) {
        restore(categoria.getId());
    }
    
    public List<Categoria> findAllInTrash() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT c FROM Categoria c WHERE c.ativo = false", Categoria.class)
                    .getResultList();
        }
    }

    public List<Categoria> findAllActive() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT c FROM Categoria c WHERE c.ativo = true", Categoria.class)
                    .getResultList();
        }
    }

    public void hardDelete(Long id) {
        super.delete(id);
    }

    public void hardDelete(Categoria categoria) {
        hardDelete(categoria.getId());
    }
}