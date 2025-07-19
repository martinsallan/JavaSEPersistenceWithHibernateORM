/*
 * Copyright (C) 2025 Allan Martins
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

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Fornecedor entity
 * 
 * @author Allan Martins
 */

@Entity
public class Fornecedor 
        extends ProjectEntity 
        implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false, length = 45)
    private String nome;
    
    @Column(nullable = false, length = 11)
    private String telefone;
    
    @Column(nullable = false, length = 120)
    private String email;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank() || nome.length() > 45) {
            throw new IllegalArgumentException("O nome tem que ser válido e ter até 45 caracteres");
        }    
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (nome == null || telefone.isBlank() || telefone.length() > 11) {
            throw new IllegalArgumentException("O telefone tem que ser válido e ter até 11 caracteres");        
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (nome == null || email.isBlank() || email.length() > 120) {
            throw new IllegalArgumentException("O nome tem que ser válido e ter até 120 caracteres");
        }    
        this.email = email;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.telefone);
        hash = 29 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Fornecedor{" 
                + "Nome = " + nome 
                + ", Telefone = " + telefone 
                + ", Email = " + email + "}"; 
    }
    
    //</editor-fold>
}