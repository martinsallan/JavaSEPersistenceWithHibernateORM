package io.github.guisso.javasepersistencewithhibernateorm.beta.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;


public class PedidoRepository
        extends Repository<Pedido> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT p FROM Pedido p";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT p FROM Pedido p WHERE p.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Pedido p WHERE p.id = :id";
    }

    public void softDelete(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Pedido p = em.find(Pedido.class, id);
            if (p != null) {
                p.setAtivo(false); // Marca como inativo
                em.merge(p);
            }

            tx.commit();
        }
    }

    /**
     * Sobrecarga para mover um pedido para a lixeira usando o objeto.
     *
     * @param pedido O objeto Pedido a ser movido.
     */
    public void softDelete(Pedido pedido) {
        softDelete(pedido.getId());
    }

    public void restore(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Pedido p = em.find(Pedido.class, id);
            if (p != null) {
                p.setAtivo(true); // Marca como ativo
                em.merge(p);
            }

            tx.commit();
        }
    }

    public void restore(Pedido pedido) {
        restore(pedido.getId());
    }

    public List<Pedido> findAllInTrash() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.ativo = false", Pedido.class)
                    .getResultList();
        }
    }

    public void hardDelete(Long id) {
        super.delete(id); 
    }

    public void hardDelete(Pedido pedido) {
        hardDelete(pedido.getId());
    }

    public List<Pedido> findAllActive() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT p FROM Pedido p WHERE p.ativo = true", Pedido.class)
                    .getResultList();
        }
    }

    public List<Pedido> findByCliente(String nomeCliente) {
        String jpql = "SELECT p FROM Pedido p WHERE LOWER(p.cliente) LIKE LOWER(:nomeCliente) AND p.ativo = true";

        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery(jpql, Pedido.class)
                    .setParameter("nomeCliente", "%" + nomeCliente + "%")
                    .getResultList();
        }
    }

}
