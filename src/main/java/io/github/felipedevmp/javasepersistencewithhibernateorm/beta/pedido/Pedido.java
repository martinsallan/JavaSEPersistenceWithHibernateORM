package io.github.felipedevmp.javasepersistencewithhibernateorm.beta.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setListaDeMateriaisUsados(String listaDeMateriaisUsados) {
        this.listaDeMateriaisUsados = listaDeMateriaisUsados;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
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
                ", cliente='" + cliente + '\'' +
                ", listaDeMateriaisUsados='" + listaDeMateriaisUsados + '\'' +
                ", status=" + status +
                '}';
    }
    
    //</editor-fold>
    
}
