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
package io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.GUI;

import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author iagor
 */
public class UsuarioTableModel extends AbstractTableModel{

    private List<Usuario> usuarios;
    
    // define o nome das colunas
    private final String[]colunas = {"ID", "Nome", "Login","Funcao", "Ativo"};
    
    public UsuarioTableModel(){
        this.usuarios = new ArrayList<>();
    }
    
    public void setUsuarios(List<Usuario> usuarios){
        this.usuarios = usuarios;
        // Notifica a JTable que os dados mudaram, para que ela se redesenhe
        this.fireTableDataChanged();
    }
    
    public Usuario getUsuarioAt(int rowIndex){
        return usuarios.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch(columnIndex){
            case 0: return usuario.getId();
            case 1: return usuario.getNome();
            case 2: return usuario.getLogin();
            case 3: return usuario.getFuncao();
            case 4: return usuario.getAtivo() ? "Sim" : "Nao";
            default: return null;
        }
    
    }
    
    
}
