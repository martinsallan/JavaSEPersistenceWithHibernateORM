package io.github.guisso.javasepersistencewithhibernateorm.beta.categoria;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
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
}