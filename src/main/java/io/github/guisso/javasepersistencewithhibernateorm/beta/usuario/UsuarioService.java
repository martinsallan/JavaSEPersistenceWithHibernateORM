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
package io.github.guisso.javasepersistencewithhibernateorm.beta.usuario;

/**
 *
 * @author iagor
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioService {
        
    private BCryptPasswordEncoder passwordEncoder;
    private UsuarioRepository usuarioRepository;
    
    public UsuarioService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.usuarioRepository = new UsuarioRepository();
    }
    
    /*
      Cadastra novo usuario, criptografando a senha
    */
    
    public void cadastrarUsuario(String nome,String login, String senhaPure) throws Exception{
        
        if(usuarioRepository.findByLogin(login)!= null){
            throw new Exception ("O login '" +login+ "' já está em uso.");
        }
        
        // criptografa a senha
        String senhaHash = passwordEncoder.encode(senhaPure);
        
        // cria a entidade usuario com senha já criptografada
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setLogin(login);
        novoUsuario.setSenhaHash(senhaHash); // salva a senha hash, não a senha pura
        
        usuarioRepository.saveOrUpdate(novoUsuario);
        
    }
    
    public boolean autenticarUsuario(String login, String senhaPura){
        //busca o usuario pelo login
        Usuario usuario = usuarioRepository.findByLogin(login);
        
        if(usuario!=null){
            // A biblioteca compara a senha pura com o hash do banco
            // o metedo matches() faz o heavy lifting
            return passwordEncoder.matches(senhaPura,usuario.getSenhaHash());
        }
        
        // se o usuario não existe, autenticação falha
        return false;
    }
    
    // método para realizar a criptografia da senha no salvamento/alteração no GUI
    public String criptografarSenha(String senhaPura){
        return passwordEncoder.encode(senhaPura);
    }
}
