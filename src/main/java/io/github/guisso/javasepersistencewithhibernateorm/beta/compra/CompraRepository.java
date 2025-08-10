package io.github.guisso.javasepersistencewithhibernateorm.beta.compra;
import io.github.guisso.javasepersistencewithhibernateorm.beta.repository.Repository;

public class CompraRepository
        extends Repository<Compra> {

    @Override
    public String getJpqlFindAll() {
        return "SELECT c FROM Compra c";
    }

    @Override
    public String getJpqlFindById() {
        return "SELECT c FROM Compra c WHERE c.id = :id";
    }

    @Override
    public String getJpqlDeleteById() {
        return "DELETE FROM Compra c WHERE c.id = :id";
    }
}
