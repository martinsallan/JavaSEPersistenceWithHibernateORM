package io.github.felipedevmp.javasepersistencewithhibernateorm.beta.pedido;

import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.ProjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
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
    
    @Column(nullable = false, length = 20)
    private StatusPedido status;
     
    
    
}
