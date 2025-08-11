package io.github.guisso.javasepersistencewithhibernateorm.beta.compra;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Compra
        extends ProjectEntity
            implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private LocalDate dataCompra;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(length = 50)
    private String formaPagamento;

    @Column(nullable = false, length = 100)
    private String fornecedor;

    @Column(nullable = false, length = 100)
    private String usuario;


    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getId()); // Usa o ID da entidade pai
        hash = 71 * hash + Objects.hashCode(this.dataCompra);
        hash = 71 * hash + Objects.hashCode(this.valorTotal);
        hash = 71 * hash + Objects.hashCode(this.fornecedor);
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
        
        // A comparação pelo hashCode já considera os campos mais relevantes
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + getId() +
                ", dataCompra=" + dataCompra +
                ", valorTotal=" + valorTotal +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", fornecedor='" + fornecedor + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
    //</editor-fold>
}