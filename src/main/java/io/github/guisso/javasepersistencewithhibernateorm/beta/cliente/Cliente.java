/*
 * Copyright (C) 2025 iagor
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


import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Cliente entity
 *
 * @author iagor
 */

@Entity
public class Cliente 
       extends ProjectEntity
       implements  Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    @Column(nullable = false, length = 45)
    private String nome;
    
    @Column(nullable = false, length = 120)
    private String endereco;
    
    @Column(nullable = false, length = 11)
    private String contato;
    
    @Column(nullable = false)
    private Boolean excluido = false;
    
    private Boolean ativo = true;
    
//    @Collum(nullable = true)
//    private List<Pedido> pedidos; 

    //<editor-fold defaultstate="collapsed" desc="GettersNSetters">
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isBlank() || nome.length()> 45){
            throw new IllegalArgumentException("O nome precisa ser válido e possuir até 45 caracteres");
        }        
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if(endereco == null || endereco.isBlank() ||  endereco.length()> 120){
            throw new IllegalArgumentException("O endereco precisa ser válido e possuir até 100 caracteres");
        }
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        String contatoValido = contato.replaceAll("\\D", "");
        if(contatoValido == null || contatoValido.isEmpty() || contatoValido.length() != 11){
            throw new IllegalArgumentException("O telefone de contato deve possuir 11 dígitos válidos somente.");
        }
        this.contato = contatoValido;
    }
    
    public Boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode,equals,toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.endereco);
        hash = 29 * hash + Objects.hashCode(this.contato);
        hash = 29 * hash + Objects.hashCode(this.excluido);
        hash = 29 * hash + Objects.hashCode(this.ativo);
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
        return "Cliente{" 
                + "nome=" + nome 
                + ", endereco=" + endereco 
                + ", contato=" + contato 
                + ", " + (excluido ? "Excluido " : "Valido ") 
                + ", " + (ativo!=null && ativo ? "Ativo ": "Inativo ") + '}';
    }
    
//</editor-fold>

       
}
