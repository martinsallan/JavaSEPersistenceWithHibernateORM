/*
 * Copyright (C) 2025 felip
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
package io.github.guisso.javasepersistencewithhibernateorm.beta.compra.gui;
import io.github.guisso.javasepersistencewithhibernateorm.beta.compra.Compra; // Importe a sua classe Compra
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author felip
 */
public class CompraTableModel 
        extends AbstractTableModel{
    
    private List<Compra> compras;
     private final String[] colunas = {"ID", "Data", "Fornecedor", "Valor Total", "Nota Fiscal", "Itens", "Usuário", "Ativo"};

    public CompraTableModel() {
        this.compras = new ArrayList<>();
    }

    public Compra getCompraEm(int rowIndex) {
        return compras.get(rowIndex);
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return compras.size();
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
        Compra compra = compras.get(rowIndex);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return switch (columnIndex) {
            case 0 -> compra.getId();
            case 1 -> compra.getDataCompra() != null ? compra.getDataCompra().format(formatador) : "N/D";
            case 2 -> compra.getFornecedor();
            case 3 -> String.format("R$ %.2f", compra.getValorTotal());
            case 4 -> compra.getNumeroNotaFiscal();
            case 5 -> compra.getItens();
            case 6 -> compra.getUsuario();
            case 7 -> compra.getAtivo() != null && compra.getAtivo() ? "Sim" : "Não";
            default -> null;
        };
    }
}
