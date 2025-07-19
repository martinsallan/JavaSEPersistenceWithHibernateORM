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

import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.Cliente;
import io.github.guisso.javasepersistencewithhibernateorm.beta.cliente.ClienteRepository;
//import io.github.guisso.javasepersistencewithhibernateorm.beta.credencial.Credencial;
//import io.github.guisso.javasepersistencewithhibernateorm.beta.credencial.CredencialRepository;
//import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.Usuario;
//import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.UsuarioRepository;


/**
 * Runs tests of the "Beta" version
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1
 * @since 0.1, Jul 1, 2025
 */
public class Program {

    public static void betaTests() {

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
        
        
//        boolean excluiu = clienteRepository.delete(c2);
//        System.out.println("> " + (excluiu ? "Excluiu" : "Nao excluiu"));
//        
//        AlunoRepository alunoRepository = new AlunoRepository();
//        Long id;
//
//        Aluno a1 = new Aluno();
//        a1.setNome("Ana Zaira");
//        a1.setMatricula(123456789);
//        a1.setNascimento(LocalDate.of(1999, 7, 1));
//        
//        alunoRepository.saveOrUpdate(a1);
//        System.out.println("> " + a1);
//        
//        a1.setNome("Beariz Yana");
//        
//        id = alunoRepository.saveOrUpdate(a1);
//        System.out.println("> " + a1);
//        
//        Aluno a2 = alunoRepository.findById(id);
//        System.out.println("> " + a2);
//        
//        a2.setId(null);
//        a2.setNome("Cecília Xerxes");
//        
//        alunoRepository.saveOrUpdate(a2);
//        System.out.println("> " + a2);
//        
////        boolean excluded = alunoRepository.delete(id);
//        boolean excluded = alunoRepository.delete(a2);
//
//        System.out.println("> " + (excluded ? "Excluded" : "Exclusion fails..."));

//        CredencialRepository credencialRepository = new CredencialRepository();
//        
//        Credencial c1 = new Credencial();
//        c1.setEmail("y@mail.com");
//        c1.setSenha("123456");
//        
//        credencialRepository.saveOrUpdate(c1);
//        if(true) return;
        
//        UsuarioRepository usuarioRepository = new UsuarioRepository();
//        
//        Usuario u1 = new Usuario();
//        u1.setNome("Ana Zaira");
//        
//        u1.setCredencial(c1); // <---------------------------------------------
//        c1.setUsuario(u1); // <---------------------------------------------
//        
//        usuarioRepository.saveOrUpdate(u1);
//        
//        Usuario u2 = usuarioRepository.findById(1L);
//        
//        System.out.println("> " + u2.getCredencial().getEmail());
    }
}
