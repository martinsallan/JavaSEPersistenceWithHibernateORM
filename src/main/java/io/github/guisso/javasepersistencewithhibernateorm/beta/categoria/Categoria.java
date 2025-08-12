package io.github.guisso.javasepersistencewithhibernateorm.beta.categoria;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author stefo
 */

@Entity
public class Categoria
        extends ProjectEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 255)
    private String descricao;
    
    @Column(nullable = false)
    private Boolean ativo = true;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank() || nome.length() > 50) {
            throw new IllegalArgumentException("O nome da categoria deve ser válido e ter até 50 caracteres.");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null && descricao.length() > 255) {
            throw new IllegalArgumentException("A descrição não pode exceder 255 caracteres.");
        }
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode, equals, toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.ativo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        return Objects.equals(this.nome, other.nome);
    }

    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + getId()
                + ", nome=" + nome
                + ", descricao=" + descricao
                + ", ativo=" + (ativo ? "sim" : "nao")
                + '}';
    }
    //</editor-fold>
}