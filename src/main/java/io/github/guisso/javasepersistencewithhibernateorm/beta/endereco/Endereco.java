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
package io.github.guisso.javasepersistencewithhibernateorm.beta.endereco;

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
public class Endereco 
        extends ProjectEntity 
        implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false, length = 120)
    private String rua;
    
    @Column(nullable = false, length = 120)
    private String numero;
    
    @Column(nullable = false, length = 120)
    private String bairro;
    
    @Column(nullable = false, length = 120)
    private String cidade;
    @Column(nullable = false, length = 120)
    
    private String cep;
    @Column(nullable = false)
    
    private Boolean excluido = false;
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {    
        this.excluido = excluido;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.rua);
        hash = 29 * hash + Objects.hashCode(this.numero);
        hash = 29 * hash + Objects.hashCode(this.bairro);
        hash = 29 * hash + Objects.hashCode(this.cidade);
        hash = 29 * hash + Objects.hashCode(this.cep);
        hash = 29 * hash + Objects.hashCode(this.excluido);
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
        return "Endereco{" 
                + "Rua = " + rua 
                + ", Numero = " + numero 
                + ", Bairro = " + bairro
                + ", Cidade = " + cidade 
                + ", CEP = " + cep + "}"
                + ", " + (excluido ? "Excluido " : "Valido "); 
    }
    
    //</editor-fold>
}