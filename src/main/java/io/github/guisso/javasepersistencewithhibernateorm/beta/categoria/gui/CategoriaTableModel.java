package io.github.guisso.javasepersistencewithhibernateorm.beta.categoria.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.categoria.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CategoriaTableModel extends AbstractTableModel {

    private final List<String> colunas = List.of("ID", "Nome", "Descrição");
    private List<Categoria> categorias = new ArrayList<>();

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        fireTableDataChanged(); 
    }

    public Categoria getCategoriaEm(int rowIndex) {
        return categorias.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = categorias.get(rowIndex);
        switch (columnIndex) {
            case 0: return categoria.getId();
            case 1: return categoria.getNome();
            case 2: return categoria.getDescricao();
            default: return null;
        }
    }
}