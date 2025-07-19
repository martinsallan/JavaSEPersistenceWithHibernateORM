package io.github.felipedevmp.javasepersistencewithhibernateorm.beta.pedido;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;

public class PedidoRepository 
    extends Repository<Pedido>{
    
   @Override
    public String getJpqlFindAll() {
        return "SELECT p FROM Pedido p";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT p FROM Pedido p WHERE p.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Pedido p WHERE p.id = :id";
    } 
    
}
