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
import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.UsuarioRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.usuario.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author iagor
 */
public class UsuarioListagem extends javax.swing.JFrame {

    // atributos principais
    private UsuarioRepository usuarioRepository;
    private UsuarioTableModel regularesTableModel;
    private UsuarioTableModel lixeiraTableModel;
    private Usuario usuarioSelecionado; // atual usuario em edição
    
    
    /**
     * Creates new form UsuarioListagem
     */
    public UsuarioListagem() {
        initComponents();
        
        // inicializa os componentes lógicos
        this.usuarioRepository = new UsuarioRepository();
        
        // configura os tableModels
        this.regularesTableModel = new UsuarioTableModel();
        this.tblListagem.setModel(regularesTableModel);
        
        this.lixeiraTableModel = new UsuarioTableModel();
        this.tblLixeira.setModel(lixeiraTableModel);
        
        carregarTabelas();

        adicionarListenerSelecaoTabela();
    
    
    }

    private void carregarTabelas(){
        // busca os dados e atualiza as tabelas
        regularesTableModel.setUsuarios(usuarioRepository.findAll());
        lixeiraTableModel.setUsuarios(usuarioRepository.findALLInTrash());
        
    }
    
    private void adicionarListenerSelecaoTabela(){
        tblListagem.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // O evento é disparado duas vezes, pegamos apenas o final
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblListagem.getSelectedRow();
                    if (selectedRow != -1) {
                        // Converte o índice da view para o do modelo (importante para ordenação)
                        int modelRow = tblListagem.convertRowIndexToModel(selectedRow);
                        // Pega o objeto Usuario daquela linha
                        usuarioSelecionado = regularesTableModel.getUsuarioAt(modelRow);
                        
                        // Preenche o formulário com os dados
                        preencherFormulario(usuarioSelecionado);
                    }
                }
            }
        });
    }
    
    private void preencherFormulario(Usuario usuario) {
        if (usuario != null) {
            txtNomeLabel.setText(usuario.getNome());
            txtLoginLabel.setText(usuario.getLogin());
            chk_Ativo.setSelected(usuario.getAtivo());
            // NUNCA mostre a senha ou o hash. Apenas um placeholder.
            txtSenhaLabel.setText("••••••••"); 
        }
    }
    
    private void limparFormulario() {
        usuarioSelecionado = null;
        txtNomeLabel.setText("");
        txtLoginLabel.setText("");
        txtSenhaLabel.setText("");
        chk_Ativo.setSelected(false);
        tblListagem.clearSelection(); // Remove a seleção da tabela
    }
    
    // MÉTODOS REFERENTES A BOTÕES
    
    // 1. Botao salvar/alterar (o mesmo botao pode fazer ambos)
    private void btn_SalvarActionPerformed(java.awt.event.ActionEvent evt) {
        // Se não há usuário selecionado, é um novo cadastro
        if (usuarioSelecionado == null) {
            // Lógica de CADASTRO
            // Você pode criar um novo método ou chamar o do seu botão "Cadastrar"
            //btn_CadastrarActionPerformed(evt);
            return;
        }
        
        // Lógica de ALTERAÇÃO
        try {
            // Pega os dados do formulário e atualiza o objeto selecionado
            usuarioSelecionado.setNome(txtNomeLabel.getText());
            usuarioSelecionado.setLogin(txtLoginLabel.getText());
            usuarioSelecionado.setAtivo(chk_Ativo.isSelected());
            
            // Se o campo de senha foi alterado, atualiza a senha
            String novaSenha = new String(txtSenhaLabel.getPassword());
            if (!novaSenha.equals("••••••••") && !novaSenha.isEmpty()) {
                // A criptografia acontece no service, é preciso instanciar
                UsuarioService service = new UsuarioService();
                // O ideal seria o service ter um método "alterarSenha"
                // Por simplicidade, vamos criar um novo hash aqui
                String novoHash = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(novaSenha);
                usuarioSelecionado.setSenhaHash(novoHash);
            }
            
            // Salva as alterações no banco
            usuarioRepository.saveOrUpdate(usuarioSelecionado);
            
            JOptionPane.showMessageDialog(this, "Usuário alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpa o formulário e recarrega a tabela
            limparFormulario();
            carregarTabelas();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     // 2. Botão Deletar (da aba de ativos) - Manda para a lixeira
    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selectedRows = tblListagem.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um ou mais usuários para mover para a lixeira.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja mover " + selectedRows.length + " usuário(s) para a lixeira?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            for (int row : selectedRows) {
                int modelRow = tblListagem.convertRowIndexToModel(row);
                Long id = regularesTableModel.getUsuarioAt(modelRow).getId();
                usuarioRepository.softDelete(id);
            }
            carregarTabelas(); // Atualiza ambas as tabelas
            limparFormulario();
        }
    }

    
    // METODOS DE DELEÇÃO E RESTAURAÇÂO DA LIXEIRA
    // Botão Restaurar (da aba lixeira)
    private void btn_RestaurarActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selectedRows = tblLixeira.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um ou mais usuários para restaurar.");
            return;
        }

        // Lógica similar ao softdelete, mas chamando repository.restore(id)
        for (int row : selectedRows) {
            int modelRow = tblLixeira.convertRowIndexToModel(row);
            Long id = lixeiraTableModel.getUsuarioAt(modelRow).getId();
            usuarioRepository.restore(id);
        }
        carregarTabelas();
    }
    
    // Botão Deletar Permanente (da aba lixeira)
    private void btn_DeletarPermanenteActionPerformed(java.awt.event.ActionEvent evt) {
        int[] selectedRows = tblLixeira.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecione um ou mais usuários para excluir permanentemente.");
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja excluir permanentemente " + selectedRows.length + " usuário(s)?", "Confirmação", JOptionPane.WARNING_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            
            // Chama o método repository.hardDelete(id)
            for (int row : selectedRows) {
                int modelRow = tblLixeira.convertRowIndexToModel(row);
                Long id = lixeiraTableModel.getUsuarioAt(modelRow).getId();
                usuarioRepository.hardDelete(id);
            }
            carregarTabelas();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cadastro_pnl = new javax.swing.JPanel();
        lbl_Nome = new javax.swing.JLabel();
        txtNomeLabel = new javax.swing.JTextField();
        lbl_Login = new javax.swing.JLabel();
        txtLoginLabel = new javax.swing.JTextField();
        lbl_Endereco = new javax.swing.JLabel();
        txtSenhaLabel = new javax.swing.JPasswordField();
        chk_Ativo = new javax.swing.JCheckBox();
        btn_Salvar = new javax.swing.JButton();
        btn_Buscar = new javax.swing.JButton();
        btn_Cadastrar = new javax.swing.JButton();
        lbl_Funcao = new javax.swing.JLabel();
        cmbFuncao = new javax.swing.JComboBox<>();
        Listagem_tbd3 = new javax.swing.JTabbedPane();
        Resultados_pnl3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();
        btn_Deletar = new javax.swing.JButton();
        btn_Alterar = new javax.swing.JButton();
        Lixeira_pnl = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblLixeira = new javax.swing.JTable();
        btn_DeletarPermanente = new javax.swing.JButton();
        btn_Restaurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_Nome.setText("Nome");

        txtNomeLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeLabelActionPerformed(evt);
            }
        });

        lbl_Login.setText("Login");

        lbl_Endereco.setText("Senha");

        txtSenhaLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaLabelActionPerformed(evt);
            }
        });

        chk_Ativo.setText("Ativo");
        chk_Ativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_AtivoActionPerformed(evt);
            }
        });

        btn_Salvar.setText("Salvar");

        btn_Buscar.setText("Buscar");

        btn_Cadastrar.setText("Cadastrar");

        lbl_Funcao.setText("Funcao");

        cmbFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout Cadastro_pnlLayout = new javax.swing.GroupLayout(Cadastro_pnl);
        Cadastro_pnl.setLayout(Cadastro_pnlLayout);
        Cadastro_pnlLayout.setHorizontalGroup(
            Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtLoginLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Cadastro_pnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Cadastrar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Buscar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Salvar))
                    .addComponent(txtSenhaLabel)
                    .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                        .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Nome)
                            .addComponent(lbl_Login)
                            .addComponent(chk_Ativo)
                            .addComponent(lbl_Endereco))
                        .addGap(352, 400, Short.MAX_VALUE))
                    .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                        .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Funcao)
                            .addComponent(cmbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Cadastro_pnlLayout.setVerticalGroup(
            Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lbl_Nome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbl_Login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbl_Funcao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lbl_Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenhaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chk_Ativo)
                .addGap(22, 22, 22)
                .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Salvar)
                    .addComponent(btn_Cadastrar)
                    .addComponent(btn_Buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblListagem);

        btn_Deletar.setText("Deletar");

        btn_Alterar.setText("Alterar");

        javax.swing.GroupLayout Resultados_pnl3Layout = new javax.swing.GroupLayout(Resultados_pnl3);
        Resultados_pnl3.setLayout(Resultados_pnl3Layout);
        Resultados_pnl3Layout.setHorizontalGroup(
            Resultados_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Resultados_pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Resultados_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Resultados_pnl3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Alterar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Deletar)))
                .addContainerGap())
        );
        Resultados_pnl3Layout.setVerticalGroup(
            Resultados_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Resultados_pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Resultados_pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Deletar)
                    .addComponent(btn_Alterar))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        Listagem_tbd3.addTab("tab1", Resultados_pnl3);

        tblLixeira.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tblLixeira);

        btn_DeletarPermanente.setText("Deletar");

        btn_Restaurar.setText("Restaurar");

        javax.swing.GroupLayout Lixeira_pnlLayout = new javax.swing.GroupLayout(Lixeira_pnl);
        Lixeira_pnl.setLayout(Lixeira_pnlLayout);
        Lixeira_pnlLayout.setHorizontalGroup(
            Lixeira_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Lixeira_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Lixeira_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Lixeira_pnlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Restaurar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_DeletarPermanente)))
                .addContainerGap())
        );
        Lixeira_pnlLayout.setVerticalGroup(
            Lixeira_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Lixeira_pnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Lixeira_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DeletarPermanente)
                    .addComponent(btn_Restaurar))
                .addContainerGap(169, Short.MAX_VALUE))
        );

        Listagem_tbd3.addTab("tab2", Lixeira_pnl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cadastro_pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(Listagem_tbd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cadastro_pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addComponent(Listagem_tbd3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeLabelActionPerformed

    private void txtSenhaLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaLabelActionPerformed

    private void chk_AtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_AtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_AtivoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuarioListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioListagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cadastro_pnl;
    private javax.swing.JPanel Listagem_pnl1;
    private javax.swing.JPanel Listagem_pnl2;
    private javax.swing.JTabbedPane Listagem_tbd1;
    private javax.swing.JTabbedPane Listagem_tbd2;
    private javax.swing.JTabbedPane Listagem_tbd3;
    private javax.swing.JPanel Lixeira_pnl;
    private javax.swing.JPanel Resultados_pnl1;
    private javax.swing.JPanel Resultados_pnl2;
    private javax.swing.JPanel Resultados_pnl3;
    private javax.swing.JButton btn_Alterar;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Cadastrar;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JButton btn_DeletarPermanente;
    private javax.swing.JButton btn_Restaurar;
    private javax.swing.JButton btn_Salvar;
    private javax.swing.JCheckBox chk_Ativo;
    private javax.swing.JComboBox<String> cmbFuncao;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_Endereco;
    private javax.swing.JLabel lbl_Funcao;
    private javax.swing.JLabel lbl_Login;
    private javax.swing.JLabel lbl_Nome;
    private javax.swing.JTable tblListagem;
    private javax.swing.JTable tblLixeira;
    private javax.swing.JTextField txtLoginLabel;
    private javax.swing.JTextField txtNomeLabel;
    private javax.swing.JPasswordField txtSenhaLabel;
    // End of variables declaration//GEN-END:variables
}
