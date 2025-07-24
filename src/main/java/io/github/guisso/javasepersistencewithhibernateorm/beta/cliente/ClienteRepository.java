/*
 * Copyright (C) 2025 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
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
package io.github.guisso.javasepersistencewithhibernateorm.beta.cliente;

import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.*;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * Repository for Cliente operations
 *
 * @author iago RR;
 * @version 0.1
 * @since 0.1, Jul 7, 2025
 */
public class ClienteRepository
        extends Repository<Cliente> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT a FROM Cliente a";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT a FROM Cliente a WHERE a.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Cliente a WHERE a.id = :id";
    }

    // Métodos da lixeira - Implementados no Cliente REPOSITORY
    // Evitam conflitos e problemas por falta de implementação nas demais entities
    
    // SOFT DELETE Manual - troca o boleano de excluido
    public void softDelete(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if(c != null){
                c.setExcluido(Boolean.TRUE);
                em.merge(c);
            }
            tx.commit();
        }
    }
    
    // sobrecarga de método, para softDelete com o objeto já buscado
    public void sofDelete(Cliente cliente){
        // uma simples chamada para o código do softDelete(long id)
        softDelete(cliente.getId());
    }
    
    // RESTORE - similar ao softdelete mas seta o boleano para false
    public void restore(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Cliente c = em.find(Cliente.class, id);
            if(c != null){
                c.setExcluido(Boolean.FALSE);
                em.merge(c);
            }
            tx.commit();
        }
    }
    
    //sobrecarga de método do restore
    public void restore(Cliente cliente){
        restore(cliente.getId());
    }
    
    // BUSCA - busca os elementos da lixeira método similar a busca por getJpqlFindById()
    public List<Cliente> findALLInTrash(){
        try(EntityManager em = DataSourceFactory.getEntityManager()){
            // Consulta JPQL que busca clientes marcados excluídos 
            return em.createQuery("SELECT a FROM Cliente a WHERE a.excluido = true",
                    Cliente.class).getResultList();
        }
    }
    
    // HARD DELETE - chama o método original superclasse Repository, deletebyId
    public void hardDelete(Long id){
        super.delete(id);
    }
    
    //sobrecarga de método do hardDelete
    public void hardDelete(Cliente c){
        hardDelete(c.getId());
    }
    
}
