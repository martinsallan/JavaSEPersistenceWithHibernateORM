/*
 * Copyright (C) 2025 Allan Martins;
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
package io.github.guisso.javasepersistencewithhibernateorm.beta.fornecedor;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
/**
 * Repository for Fornecedor operations
 *
 * @author Allan Martins;
 */

public class FornecedorRepository
        extends Repository<Fornecedor> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT a FROM Fornecedor a";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT a FROM Fornecedor a WHERE a.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Fornecedor a WHERE a.id = :id";
    }

    //Métodos da Lixeira
    public void softDelete(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            Fornecedor f = em.find(Fornecedor.class, id);
            if(f != null){
                f.setExcluido(Boolean.TRUE);
                em.merge(f);
            }
            tx.commit();
        }
    }
    
    public void softDelete(Fornecedor fornecedor){
        softDelete(fornecedor.getId());
    }
    
    public void restore(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Fornecedor f = em.find(Fornecedor.class, id);
            if(f != null){
                f.setExcluido(Boolean.FALSE);
                em.merge(f);
            }
            tx.commit();
        }
    }
    
    public void restore(Fornecedor fornecedor){
        restore(fornecedor.getId());
    }
    
    public List<Fornecedor> findALLInTrash(){
        try(EntityManager em = DataSourceFactory.getEntityManager()){
            return em.createQuery("SELECT a FROM Fornecedor a WHERE a.excluido = true",
                    Fornecedor.class).getResultList();
        }
    }
    
    public void hardDelete(Long id){
        super.delete(id);
    }
    
    public void hardDelete(Fornecedor f){
        hardDelete(f.getId());
    }
    
    public List<Fornecedor> findAllActive(){
         try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT a FROM Fornecedor a WHERE a.excluido = false", Fornecedor.class)
                    .getResultList();
        }
    }
    
    public List<Fornecedor> findByNome(String nome) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            String jpql = "SELECT a FROM Fornecedor a WHERE LOWER(a.nome) LIKE LOWER(:nome) AND a.excluido = false";
            return em.createQuery(jpql, Fornecedor.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
    }
}