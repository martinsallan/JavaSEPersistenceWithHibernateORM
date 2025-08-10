/*
 * Copyright (C) 2025 iagor
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
package io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.GUI;

import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author iagor
 */
public class ClienteTableModel extends AbstractTableModel{

    private List<Cliente> clientes;
    private final String[] colunas = {"ID","Nome","Endereço","Contato","Ativo"};
    
    public ClienteTableModel(){
        this.clientes = new ArrayList<>();
    }

    public Cliente getClienteEm(int rowIndex) {
        return clientes.get(rowIndex);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        this.fireTableDataChanged();
    }
    
    
    
    @Override
    public int getRowCount() {
        return clientes.size();
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
        Cliente cliente = clientes.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> cliente.getId();
            case 1 -> cliente.getNome();
            case 2 -> cliente.getEndereco();
            case 3 -> cliente.getContato();
            case 4 -> cliente.getAtivo() != null && cliente.getAtivo() ? "Sim" : "Não";
            default -> null;
        };
    }

    
    
}
