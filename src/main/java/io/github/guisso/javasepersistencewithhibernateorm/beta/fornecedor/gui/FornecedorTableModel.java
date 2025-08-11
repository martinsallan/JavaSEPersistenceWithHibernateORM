/*
 * Copyright (C) 2025 marti
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.guisso.javasepersistencewithhibernateorm.beta.fornecedor.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.fornecedor.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marti
 */
public class FornecedorTableModel extends AbstractTableModel{

    private List<Fornecedor> fornecedores;
    private final String[] colunas = {"ID","Nome","Telefone","Email"};
    
    public FornecedorTableModel(){
        this.fornecedores = new ArrayList<>();
    }

    public Fornecedor getFornecedorEm(int rowIndex) {
        return fornecedores.get(rowIndex);
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return fornecedores.size();
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
        Fornecedor fornecedor = fornecedores.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> fornecedor.getId();
            case 1 -> fornecedor.getNome();
            case 2 -> fornecedor.getTelefone();
            case 3 -> fornecedor.getEmail();
            default -> null;
        };
    }
}
