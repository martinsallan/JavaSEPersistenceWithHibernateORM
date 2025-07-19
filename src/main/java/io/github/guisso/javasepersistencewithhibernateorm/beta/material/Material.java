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

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Material entity
 *
 * @author stefo
 */
@Entity
public class Material 
       extends ProjectEntity
       implements Serializable {
    
    private static final long SerialVersionUID = 1L;
    
    @Column(nullable = false, length = 45)
    private String nome;
    
    @Column(nullable = false, length = 45)
    private String tipo;
    
    @Column(nullable = false)
    private double quantidadeEmEstoque;
    
    @Column(nullable = false, length = 10)
    private String unidade;
    
    @Column(nullable = false)
    private double nivelMinimo;
    
    @Column(nullable = false)
    private Boolean excluido = false;
    
    private Boolean ativo = true;
    
    //<editor-fold defaultstate="collapsed" desc="GettersNSetters">
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null || nome.isBlank() || nome.length() > 45) {
            throw new IllegalArgumentException("O nome deve ser válido e ter até 45 caracteres.");
        }
        this.nome = nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank() || tipo.length() > 45) {
            throw new IllegalArgumentException("O tipo deve ser válido e ter até 45 caracteres.");
        }
        this.tipo = tipo;
    }
    
    public double getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    public void setQuantidadeEmEstoque(double quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
    public String getUnidade() {
        return unidade;
    }
    public void setUnidade(String unidade) {
        if (unidade == null || unidade.isBlank() || unidade.length() > 10) {
            throw new IllegalArgumentException("A unidade deve ser válida e ter até 10 caracteres.");
        }
        this.unidade = unidade;
    }
    
    public double getNivelMinimo() {
        return nivelMinimo;
    }
    public void setNivelMinimo(double nivelMinimo) {
        if (nivelMinimo < 0) {
            throw new IllegalArgumentException("O nível mínimo não pode ser negativo.");
        }
        this.nivelMinimo = nivelMinimo;
    }
    
    public Boolean getExcluido() {
        return excluido;
    }
    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public boolean precisaReposicao() {
        return this.quantidadeEmEstoque <= this.nivelMinimo;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="hashCode,equals,toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nome);
        hash = 61 * hash + Objects.hashCode(this.tipo);
        hash = 61 * hash + Double.hashCode(this.quantidadeEmEstoque);
        hash = 61 * hash + Objects.hashCode(this.unidade);
        hash = 61 * hash + Double.hashCode(this.nivelMinimo);
        hash = 61 * hash + Objects.hashCode(this.excluido);
        hash = 61 * hash + Objects.hashCode(this.ativo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Material{" 
                + "nome=" + nome 
                + ", tipo=" + tipo 
                + ", quantidadeEmEstoque=" + quantidadeEmEstoque 
                + ", unidade=" + unidade 
                + ", nivelMinimo=" + nivelMinimo 
                + ", " + (excluido ? "Excluido " : "Valido ") 
                + ", " + (ativo != null && ativo ? "Ativo " : "Inativo ") 
                + '}';
    }
//</editor-fold>
}
