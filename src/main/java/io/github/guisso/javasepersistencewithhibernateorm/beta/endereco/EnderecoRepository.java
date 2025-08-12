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
package io.github.guisso.javasepersistencewithhibernateorm.beta.endereco;

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

public class EnderecoRepository
        extends Repository<Endereco> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT a FROM Endereco a";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT a FROM Endereco a WHERE a.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Endereco a WHERE a.id = :id";
    }

    //Métodos da Lixeira
    public void softDelete(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            Endereco e = em.find(Endereco.class, id);
            if(e != null){
                e.setExcluido(Boolean.TRUE);
                em.merge(e);
            }
            tx.commit();
        }
    }
    
    public void softDelete(Endereco endereco){
        softDelete(endereco.getId());
    }
    
    public void restore(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Endereco e = em.find(Endereco.class, id);
            if(e != null){
                e.setExcluido(Boolean.FALSE);
                em.merge(e);
            }
            tx.commit();
        }
    }
    
    public void restore(Endereco endereco){
        restore(endereco.getId());
    }
    
    public List<Endereco> findALLInTrash(){
        try(EntityManager em = DataSourceFactory.getEntityManager()){
            return em.createQuery("SELECT a FROM Endereco a WHERE a.excluido = true",
                    Endereco.class).getResultList();
        }
    }
    
    public void hardDelete(Long id){
        super.delete(id);
    }
    
    public void hardDelete(Endereco e){
        hardDelete(e.getId());
    }
    
    public List<Endereco> findAllActive(){
         try (EntityManager em = DataSourceFactory.getEntityManager()) {
            return em.createQuery("SELECT a FROM Endereco a WHERE a.excluido = false", Endereco.class)
                    .getResultList();
        }
    }
    
    public List<Endereco> findByCEP(String cep) {
        try (EntityManager em = DataSourceFactory.getEntityManager()) {
            String jpql = "SELECT a FROM Endereco a WHERE LOWER(a.cep) LIKE LOWER(:cep) AND a.excluido = false";
            return em.createQuery(jpql, Endereco.class)
                    .setParameter("cep", "%" + cep + "%")
                    .getResultList();
        }
    }
}