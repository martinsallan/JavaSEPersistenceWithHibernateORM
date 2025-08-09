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
package io.github.guisso.javasepersistencewithhibernateorm.beta.usuario;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Usuario entity
 *
 * @author iagor
 */


@Entity
public class Usuario 
       extends ProjectEntity
       implements  Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    public enum FuncaoUsuario {
        ADMIN,
        OPERADOR
        // caso seja necessário outras funcoes
    }
    
    @Column(nullable = false, length = 45)
    private String nome;
    
    @Column(nullable = false, unique = true, length = 20)
    private String login;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private FuncaoUsuario funcao;
    
    @Column(nullable = false, length = 11)
    private String senhaHash;
    
    @Column(nullable = false)
    private Boolean excluido = false;
    
    private Boolean ativo = true;
   

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

    public Boolean getAtivo(){
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(login == null || login.trim().isEmpty()){
            throw new IllegalArgumentException("O login não pode ser nulo ou vazio.");
        }
        
        String loginTratado = login.trim().toLowerCase();
        
        if(loginTratado.length() < 3 || loginTratado.length() > 20) {
            throw new IllegalArgumentException("O login deve ter entre 3 e 20 caracteres.");
        }
        
        if(!loginTratado.matches("[a-z0-9_]+$")){
            throw new IllegalArgumentException("O login deve conter apenas letras minúsculas, numeros e underscore .");
        }
       
        this.login = loginTratado;
    }

    public FuncaoUsuario getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoUsuario funcao) {
        this.funcao = funcao;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
    
    
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode,equals,toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
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
                + ", funcao=" + funcao
                + ", " + (excluido ? "Excluido " : "Valido ") 
                + ", " + (ativo!=null && ativo ? "Ativo ": "Inativo ") + '}';
    }
    
//</editor-fold>

       
}


