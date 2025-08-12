package io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PedidoTableModel
        extends AbstractTableModel {

    private List<Pedido> pedidos;
    private final String[] colunas = {"ID", "Data Criação", "Cliente", "Usuário", "Status","Materiais Usados", "Ativo"};

    public PedidoTableModel() {
        this.pedidos = new ArrayList<>();
    }

    public Pedido getPedidoEm(int rowIndex) {
        return pedidos.get(rowIndex);
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                pedido.getId();
            case 1 ->
                pedido.getDataCriacao();
            case 2 ->
                pedido.getCliente();
            case 3 ->
                pedido.getUsuario();
            case 4 ->
                pedido.getStatus();
            case 5 ->
                pedido.getListaDeMateriaisUsados();
            case 6 ->
                pedido.getAtivo() != null && pedido.getAtivo() ? "Sim" : "Não";
            default ->
                null;
        };
    }
}
