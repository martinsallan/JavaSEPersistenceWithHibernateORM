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
package io.github.guisso.javasepersistencewithhibernateorm.beta.usuario;

import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.*;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.DataSourceFactory;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Repository for Usuario operations
 *
 * @author iago RR;
 * @version 0.1
 * @since 0.1, Jul 7, 2025
 */
public class UsuarioRepository
        extends Repository<Usuario> {

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

    // Métodos da lixeira - Implementados no Usuario REPOSITORY
    // Evitam conflitos e problemas por falta de implementação nas demais entities
    
    // SOFT DELETE Manual - troca o boleano de excluido
    public void softDelete(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Usuario u = em.find(Usuario.class, id);
            if(u != null){
                u.setExcluido(Boolean.TRUE);
                em.merge(u);
            }
            tx.commit();
        }
    }
    
    // sobrecarga de método, para softDelete com o objeto já buscado
    public void sofDelete(Usuario usuario){
        // uma simples chamada para o código do softDelete(long id)
        softDelete(usuario.getId());
    }
    
    // RESTORE - similar ao softdelete mas seta o boleano para false
    public void restore(Long id){
        try(EntityManager em = DataSourceFactory.getEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Usuario u = em.find(Usuario.class, id);
            if(u != null){
                u.setExcluido(Boolean.FALSE);
                em.merge(u);
            }
            tx.commit();
        }
    }
    
    //sobrecarga de método do restore
    public void restore(Usuario usuario){
        restore(usuario.getId());
    }
    
    // BUSCA - busca os elementos da lixeira método similar a busca por getJpqlFindById()
    public List<Usuario> findALLInTrash(){
        try(EntityManager em = DataSourceFactory.getEntityManager()){
            // Consulta JPQL que busca clientes marcados excluídos 
            return em.createQuery("SELECT a FROM Cliente a WHERE a.excluido = true",
                    Usuario.class).getResultList();
        }
    }
    
    // HARD DELETE - chama o método original superclasse Repository, deletebyId
    public void hardDelete(Long id){
        super.delete(id);
    }
    
    //sobrecarga de método do hardDelete
    public void hardDelete(Usuario u){
        hardDelete(u.getId());
    }
    
    // método para 
    public Usuario findByLogin(String login){
        
        try(EntityManager em = DataSourceFactory.getEntityManager()){
            String jpql = "SELECT u FROM Usuario u WHERE u.login = :login";
            
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            
            query.setParameter("login",login);
            
            return query.getSingleResultOrNull();
        }catch(Exception e){
            
            e.printStackTrace();;
            return null;
            
        }
        
    }
    
}
