/*
 * Copyright (C) 2025 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
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
package io.github.guisso.javasepersistencewithhibernateorm.beta;

import io.github.guisso.javasepersistencewithhibernateorm.beta.fornecedor.Fornecedor;
import io.github.guisso.javasepersistencewithhibernateorm.beta.fornecedor.FornecedorRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.Cliente;
import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.ClienteRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.Pedido;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.PedidoRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.Pedido.StatusPedido;
import io.github.guisso.javasepersistencewithhibernateorm.beta.material.Material;
import io.github.guisso.javasepersistencewithhibernateorm.beta.material.MaterialRepository;
import java.time.LocalDate;

/**
 * Runs tests of the "Beta" version
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1
 * @since 0.1, Jul 1, 2025
 */
public class Program {
    public static void FornecedorTests() {
        FornecedorRepository fornecedorRepository = new FornecedorRepository();
        Long id;
        
        Fornecedor c1 = new Fornecedor(); 
        c1.setNome("Allan Ravide");
        c1.setTelefone("38999997777");
        c1.setEmail("martinsallan111@gmail.com");
        fornecedorRepository.saveOrUpdate(c1);
        System.out.println(">" + c1);
        
        Fornecedor c2 = new Fornecedor();
        c2.setNome("Noah Delgado");
        c2.setTelefone("38988886666");
        c2.setEmail("martinsallan222@gmail.com");
        fornecedorRepository.saveOrUpdate(c2);
        System.out.println(">" + c2);
        
        c2.setNome("Iago Madureira");
        fornecedorRepository.saveOrUpdate(c2);        
    }
    public static void ClienteTests(){
        ClienteRepository clienteRepository = new ClienteRepository();
        Long id;
        
        Cliente c1 = new Cliente(); 
        c1.setNome("Carlos Miguel");
        c1.setEndereco("Rua Machado de Assiss, Sao Carlos, 1105");
        c1.setContato("38999997777");
        clienteRepository.saveOrUpdate(c1);
        System.out.println(">" + c1);
        
        Cliente c2 = new Cliente();
        c2.setNome("Joao Pedro");
        c2.setEndereco("Rua Sao Carlos, Miguelito, 1250");
        c2.setContato("38988886666");
        clienteRepository.saveOrUpdate(c2);
        System.out.println(">" + c2);
        
        c2.setNome("Noah Carlos");
        clienteRepository.saveOrUpdate(c2);
    }
    public static void PedidoTests(){
        PedidoRepository pedidoRepository = new PedidoRepository();
     
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente("Empresa de Teste LTDA");
        novoPedido.setListaDeMateriaisUsados("1x Chapa de MDF, 2x Parafusos, 1L de Tinta");
        novoPedido.setStatus(StatusPedido.EM_PRODUCAO);

        Long id = pedidoRepository.saveOrUpdate(novoPedido);

        //Salva as alterações
        pedidoRepository.saveOrUpdate(novoPedido);


        //Buscar por ID
        System.out.println("Teste de Busca por ID");
        Pedido pedidoDoBanco = pedidoRepository.findById(id);
        System.out.println("Pedido encontrado no banco de dados:");
        System.out.println("> " + pedidoDoBanco);
    }
    public static void MaterialTests(){
        MaterialRepository materialRepository = new MaterialRepository();

        Material m1 = new Material();
        m1.setNome("Parafuso");
        m1.setTipo("Metal");
        m1.setQuantidadeEmEstoque(100);
        m1.setUnidade("un");
        m1.setNivelMinimo(50);
        materialRepository.saveOrUpdate(m1);
        System.out.println(">" + m1);

        Material m2 = new Material();
        m2.setNome("Madeira MDF");
        m2.setTipo("Madeira");
        m2.setQuantidadeEmEstoque(20);
        m2.setUnidade("m2");
        m2.setNivelMinimo(25);
        materialRepository.saveOrUpdate(m2);
        System.out.println(">" + m2);

        m2.setQuantidadeEmEstoque(30);
        materialRepository.saveOrUpdate(m2);
        System.out.println("Atualizado >" + m2);

        // Teste de reposição
        System.out.println("Material " + m1.getNome() + (m1.precisaReposicao() ? " precisa" : " não precisa") + " de reposição.");
        System.out.println("Material " + m2.getNome() + (m2.precisaReposicao() ? " precisa" : " não precisa") + " de reposição.");

        // Teste de exclusão (opcional)
        // boolean excluiu = materialRepository.delete(m1);
        // System.out.println("> " + (excluiu ? "Excluiu com sucesso" : "Falha na exclusão"));
    }
}