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

import io.github.guisso.javasepersistencewithhibernateorm.beta.endereco.*;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author marti
 */
public class exibirEndereco extends javax.swing.JFrame {

    //<editor-fold defaultstate="collapsed" desc="Atributos de Controle da Tela">
    private EnderecoRepository enderecoRepository;
    private EnderecoTableModel regularesTableModel;
    private EnderecoTableModel lixeiraTableModel;
    private Endereco enderecoSelecionado;//Controla o modo edição/novo
    //</editor-fold>
    
    public exibirEndereco(){
        initComponents();
        inicializarComponentesLogicos();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Métodos de Suporte da GUI">
    private void inicializarComponentesLogicos(){
        this.enderecoRepository = new EnderecoRepository();

        this.regularesTableModel = new EnderecoTableModel();
        this.tblEnderecos.setModel(regularesTableModel);

        this.lixeiraTableModel = new EnderecoTableModel();
        this.tblLixeira.setModel(lixeiraTableModel);

        carregarTabelas();
        adicionarListenerSelecaoTabela();
    }

    private void carregarTabelas() {
        //Busca os dados no repositório e os define nos modelos, que atualizam as tabelas
        regularesTableModel.setEnderecos(enderecoRepository.findAllActive());
        lixeiraTableModel.setEnderecos(enderecoRepository.findALLInTrash());
    }

    private void adicionarListenerSelecaoTabela() {
        // Adiciona um "ouvinte" para o evento de seleção de linha na tabela de regulares
        tblEnderecos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tblEnderecos.getSelectedRow() != -1) {
                int viewRow = tblEnderecos.getSelectedRow();
                int modelRow = tblEnderecos.convertRowIndexToModel(viewRow);

                // Armazena o objeto Endereco da linha selecionada
                enderecoSelecionado = regularesTableModel.getEnderecoEm(modelRow);

                // Preenche o formulário com os dados do objeto
                preencherFormulario(enderecoSelecionado);
                
                controlarEstadoFormulario(false); 
            }
        });
    }

    private void preencherFormulario(Endereco endereco) {
        if (endereco != null) {
            txtCEP.setText(endereco.getCep());
            txtRua.setText(endereco.getRua());
            txtNumero.setText(endereco.getNumero());
            txtBairro.setText(endereco.getBairro());
            txtCidade.setText(endereco.getCidade());
        }
    }

    private void limparFormulario() {
        // Volta para o modo "novo cadastro"
        enderecoSelecionado = null;

        // Limpa os campos de texto
        txtCEP.setText("");
        txtRua.setText("");
        txtNumero.setText("");
        txtBairro.setText("");
        txtCidade.setText("");

        // Remove a seleção da tabela
        tblEnderecos.clearSelection();
        
        controlarEstadoFormulario(true);
    }
    
    private void controlarEstadoFormulario(boolean habilitado) {
        txtCEP.setEnabled(habilitado);
        txtRua.setEnabled(habilitado);
        txtNumero.setEnabled(habilitado);
        txtBairro.setEnabled(habilitado);
        txtCidade.setEnabled(habilitado);
        btnSalvar.setEnabled(habilitado);

        btnAlterar.setEnabled(!habilitado);
        btnDeletar.setEnabled(!habilitado);
        btnBuscar.setEnabled(!habilitado);
        btnAlterar.setEnabled(!habilitado && enderecoSelecionado != null);

        btnDeletar.setEnabled(!habilitado || enderecoSelecionado == null);
        btnBuscar.setEnabled(!habilitado || enderecoSelecionado == null);
    }
    //</editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cadastro_pnl = new javax.swing.JPanel();
        listagem_tbd = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEnderecos = new javax.swing.JTable();
        btnAlterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLixeira = new javax.swing.JTable();
        btnRestaurar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCEP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CEP", "Rua", "Número", "Bairro", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEnderecos);

        btnAlterar.setLabel("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnDeletar.setLabel("Deletar");
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnDeletar))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        listagem_tbd.addTab("Ativos", jPanel1);

        tblLixeira.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CEP", "Rua", "Número", "Bairro", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblLixeira);

        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRestaurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRestaurar)
                    .addComponent(btnExcluir))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        listagem_tbd.addTab("Lixeira", jPanel2);

        jLabel1.setText("CEP");

        jLabel2.setText("Número");

        jLabel3.setText("Rua");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel4.setText("Bairro");

        jLabel5.setText("Cidade");

        javax.swing.GroupLayout Cadastro_pnlLayout = new javax.swing.GroupLayout(Cadastro_pnl);
        Cadastro_pnl.setLayout(Cadastro_pnlLayout);
        Cadastro_pnlLayout.setHorizontalGroup(
            Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Cadastro_pnlLayout.createSequentialGroup()
                        .addContainerGap(75, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                                .addComponent(txtRua)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCEP)
                                .addComponent(txtCidade)
                                .addComponent(txtBairro)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(listagem_tbd, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        Cadastro_pnlLayout.setVerticalGroup(
            Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cadastro_pnlLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Cadastro_pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar)
                            .addComponent(btnSalvar)))
                    .addComponent(listagem_tbd))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cadastro_pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cadastro_pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //<editor-fold defaultstate="collapsed" desc="Event Handlers">
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try{
            String CepBusca = txtCEP.getText().trim();
            if(!CepBusca.isEmpty()){
                List<Endereco> resultados = enderecoRepository.findByCEP(CepBusca);
                regularesTableModel.setEnderecos(resultados);
                 if (resultados.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Nenhum endereco encontrado com o CEP informado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                //Se a busca for vazia, recarrega todos os endereços ativos
                carregarTabelas();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocorreu um erro durante a busca: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try{
            //Se fornecedorSelecionado é nulo, cria um novo.Senão, atualiza o existente.
            Endereco endereco = (enderecoSelecionado == null) ? new Endereco() : enderecoSelecionado;

            //Pega os dados dos campos de texto e popula o objeto
            endereco.setCep(txtCEP.getText());
            endereco.setRua(txtRua.getText());
            endereco.setNumero(txtNumero.getText());
            endereco.setBairro(txtBairro.getText());
            endereco.setCidade(txtCidade.getText());
            //Validação de campos vazios
            if(endereco.getCep().trim().isEmpty() || endereco.getRua().trim().isEmpty() || endereco.getNumero().trim().isEmpty() || endereco.getBairro().trim().isEmpty() || endereco.getCidade().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Campos Vazios", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Salva no banco (o repositório faz o INSERT ou UPDATE)
            enderecoRepository.saveOrUpdate(endereco);

            JOptionPane.showMessageDialog(this, "Endereco salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();
            carregarTabelas();//Atualiza a tela

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao salvar endereco: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();//Ajuda a depurar erros no console
        }    
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        int[] selectedRows = tblLixeira.getSelectedRows();
        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Selecione um ou mais endereços para restaurar.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja restaurar " + selectedRows.length + " endereço(s)?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if(resposta == JOptionPane.YES_OPTION){
            for (int row : selectedRows){
                Long id = (Long) tblLixeira.getValueAt(row, 0);
                enderecoRepository.restore(id);
            }
            carregarTabelas();
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int selectedRow = tblEnderecos.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Selecione um endereco na tabela para alterar.");
            return;
        }
        
        controlarEstadoFormulario(true);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        int[] selectedRows = tblEnderecos.getSelectedRows();
        if (selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Selecione um ou mais endereços para mover para a lixeira.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Deseja mover " + selectedRows.length + " endereço(s) para a lixeira?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if(resposta == JOptionPane.YES_OPTION){
            for(int row : selectedRows){
                Long id = (Long) tblEnderecos.getValueAt(row, 0);//Pega o ID da coluna 0
                enderecoRepository.softDelete(id);
            }
            carregarTabelas();
            limparFormulario();
        }
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int[] selectedRows = tblLixeira.getSelectedRows();
        if(selectedRows.length == 0){
            JOptionPane.showMessageDialog(this, "Selecione um ou mais enderecos para excluir permanentemente.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Esta ação é irreversível!\nDeseja excluir permanentemente " + selectedRows.length + " endereço(s)?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(resposta == JOptionPane.YES_OPTION){
            for(int row : selectedRows){
                Long id = (Long) tblLixeira.getValueAt(row, 0);
                enderecoRepository.hardDelete(id);
            }
            carregarTabelas();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed
    //</editor-fold>
    
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
            java.util.logging.Logger.getLogger(exibirEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(exibirEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(exibirEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(exibirEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new exibirEndereco().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cadastro_pnl;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane listagem_tbd;
    private javax.swing.JTable tblEnderecos;
    private javax.swing.JTable tblLixeira;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCEP;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}