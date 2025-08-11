package io.github.guisso.javasepersistencewithhibernateorm.beta.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

@Entity
public class Pedido 
        extends ProjectEntity
            implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public enum StatusPedido {
        EM_PRODUCAO,
        FINALIZADO,
        CANCELADO
    }
     
    @Column (nullable = false, length = 100)
    private String cliente;
     
    @Column (length = 520)
    private String listaDeMateriaisUsados;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPedido status;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(nullable = false)
    private LocalDate dataCriacao;

    @Column(nullable = false, length = 100)
    private String usuario;
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCliente() {
        return cliente;
    }

    public String getListaDeMateriaisUsados() {
        return listaDeMateriaisUsados;
    }

    public StatusPedido getStatus() {
        return status;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    
    public String getUsuario() {
       return usuario;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setListaDeMateriaisUsados(String listaDeMateriaisUsados) {
        this.listaDeMateriaisUsados = listaDeMateriaisUsados;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public void setDataCriacao(LocalDate dataCriacao) {
       this.dataCriacao = dataCriacao;
    }
    
    public void setUsuario(String usuario) {
       this.usuario = usuario;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.cliente);
        hash = 47 * hash + Objects.hashCode(this.listaDeMateriaisUsados);
        hash = 47 * hash + Objects.hashCode(this.status);
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
        
        return hashCode() == obj.hashCode();
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + getId() +
                ", dataCriacao=" + dataCriacao + // Adicionado
                ", cliente='" + cliente + '\'' +
                ", usuario='" + usuario + '\'' + // Adicionado
                ", listaDeMateriaisUsados='" + listaDeMateriaisUsados + '\'' +
                ", status=" + status +
                '}';
    }
    
    //</editor-fold>
    
}
