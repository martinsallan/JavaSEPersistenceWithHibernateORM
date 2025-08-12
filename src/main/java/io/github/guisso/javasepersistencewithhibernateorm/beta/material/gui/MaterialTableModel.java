package io.github.guisso.javasepersistencewithhibernateorm.beta.material.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.material.Material;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

/**
 * @author stefo
 */

public class MaterialTableModel 
        extends AbstractTableModel {

    private List<Material> materiais;
    private final String[] colunas = {"ID","Nome","Tipo","Quantidade Em Estoque", "Unidade", "Nível Mínimo"};
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public MaterialTableModel() {
        this.materiais = new ArrayList<>();
    }
    
    public MaterialTableModel(List<Material> materiais) {
        this.materiais = materiais;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GettersNSetters">
    public Material getMaterialEm(int rowIndex) {
        return materiais.get(rowIndex);
    }
    
    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
        this.fireTableDataChanged();
    }

    //</editor-fold>
    
    @Override
    public int getRowCount() {
        return materiais.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column){
        return colunas[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Material material = materiais.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> material.getId();
            case 1 -> material.getNome();
            case 2 -> material.getTipo();
            case 3 -> material.getQuantidadeEmEstoque();
            case 4 -> material.getUnidade();
            case 5 -> material.getNivelMinimo();
            default -> null;
        };
    }
    
}
