/*
 * Copyright (C) 2025 stefo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.guisso.javasepersistencewithhibernateorm.beta.material;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * Repository for Material operations
 * 
 * @author stefo
 * @since 0.1, Jul 7, 2025
 */

public class MaterialRepository 
        extends Repository<Material> {
    
    @Override
    public String getJpqlFindAll() {
        return "SELECT m FROM Material m";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT m FROM Material m WHERE m.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Material m WHERE m.id = :id";
    }
    
    // Métodos de Lixeira
    
    // Tenta mover um item para lixeira
    public void softDelete(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Material m = em.find(Material.class, id);
            if (m != null) {
                m.setAtivo(false); // Marca como inativo
                em.merge(m);
            }

            tx.commit();
        }
    }
    
    
    public void softDelete(Material material) {
        softDelete(material.getId());
    }

    // Tenta mover um item para fora da lixeira
    public void restore(Long id) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            Material m = em.find(Material.class, id);
            if (m != null) {
                m.setAtivo(true);
                em.merge(m);
            }

            tx.commit();
        }
    }

    public void restore(Material material) {
        restore(material.getId());
    }

    // Retorna todos os itens da lixeira atual
    public List<Material> findAllInTrash() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT m FROM Material m WHERE m.ativo = false", Material.class)
                    .getResultList();
        }
    }

    // Deleta permanentemente um item por ID
    public void hardDelete(Long id) {
        super.delete(id); 
    }

    // Deleta permanentemente um item por Material
    public void hardDelete(Material material) {
        hardDelete(material.getId());
    }
    
    public List<Material> findAllActive() {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT m FROM Material m WHERE m.ativo = true", Material.class)
                    .getResultList();
        }
    }
    
    public List<Material> findByNome(String nome) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT m FROM Material m WHERE m.nome LIKE :nome AND m.ativo = true", Material.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }
}
