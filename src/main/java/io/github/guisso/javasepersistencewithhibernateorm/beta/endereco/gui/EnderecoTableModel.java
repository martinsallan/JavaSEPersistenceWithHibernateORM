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
package io.github.guisso.javasepersistencewithhibernateorm.beta.endereco.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.endereco.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marti
 */
public class EnderecoTableModel extends AbstractTableModel{

    private List<Endereco> enderecos;
    private final String[] colunas = {"ID","Rua","Numero","Bairro", "Cidade", "CEP"};
    
    public EnderecoTableModel(){
        this.enderecos = new ArrayList<>();
    }

    public Endereco getEnderecoEm(int rowIndex) {
        return enderecos.get(rowIndex);
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return enderecos.size();
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
        Endereco endereco = enderecos.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> endereco.getId();
            case 1 -> endereco.getRua();
            case 2 -> endereco.getNumero();
            case 3 -> endereco.getBairro();
            case 4 -> endereco.getCidade();
            case 5 -> endereco.getCep();
            default -> null;
        };
    }
}
