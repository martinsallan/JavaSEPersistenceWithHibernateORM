package io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.gui;

import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.Pedido;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.PedidoRepository;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.gui.PedidoTableModel;
import io.github.guisso.javasepersistencewithhibernateorm.beta.pedido.StatusPedido;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class exibirPedido extends javax.swing.JFrame {

    private PedidoRepository pedidoRepository;
    private PedidoTableModel ativosTableModel;
    private PedidoTableModel lixeiraTableModel;
    private Pedido pedidoSelecionado;

    public exibirPedido() {
        initComponents();
        this.pedidoRepository = new PedidoRepository();
        this.ativosTableModel = new PedidoTableModel();
        this.tblAtivos.setModel(ativosTableModel);

        this.lixeiraTableModel = new PedidoTableModel();
        this.tblLixeira.setModel(lixeiraTableModel);

        carregarTabelas();
    }

    private void carregarTabelas() {
        List<Pedido> ativos = pedidoRepository.findAllActive();
        ativosTableModel.setPedidos(ativos);

        List<Pedido> excluidos = pedidoRepository.findAllInTrash();
        lixeiraTableModel.setPedidos(excluidos);
    }

    private void limparFormulario() {
        txt_usuario.setText("");
        txt_cliente.setText("");
        txt_data.setText("");
        txt_materiais.setText("");
        em_producao.setSelected(false);
        finalizado.setSelected(false);
        cancelado.setSelected(false);

        tblAtivos.clearSelection();

        this.pedidoSelecionado = null;
    }

    private void preencherFormulario(Pedido pedido) {
        if (pedido == null) {
            limparFormulario();
            return;
        }

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        txt_usuario.setText(pedido.getUsuario());
        txt_cliente.setText(pedido.getCliente());
        txt_data.setText(pedido.getDataCriacao().format(formatador));
        txt_materiais.setText(pedido.getListaDeMateriaisUsados());

        switch (pedido.getStatus()) {
            case EM_PRODUCAO ->
                em_producao.setSelected(true);
            case FINALIZADO ->
                finalizado.setSelected(true);
            case CANCELADO ->
                cancelado.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_cliente = new javax.swing.JTextField();
        txt_data = new javax.swing.JTextField();
        txt_materiais = new javax.swing.JTextField();
        em_producao = new javax.swing.JCheckBox();
        finalizado = new javax.swing.JCheckBox();
        cancelado = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        lblFormulario = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtivos = new javax.swing.JTable();
        btnAlterar = new javax.swing.JToggleButton();
        btnDeletarLixeira = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLixeira = new javax.swing.JTable();
        btnDeletarBancodeDados = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        lblAvisos = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cadastro de Pedidos");

        jLabel2.setText("Usuario");

        jLabel3.setText("Cliente");

        jLabel4.setText("Data do Pedido");

        jLabel5.setText("Materiais Usados");

        jLabel6.setText("Status");

        em_producao.setText("Em Produção");

        finalizado.setText("Finalizado");

        cancelado.setText("Cancelado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblFormulario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFormulario.setForeground(new java.awt.Color(255, 0, 51));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(jLabel1))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_materiais)
                                    .addComponent(txt_data)
                                    .addComponent(txt_cliente)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(em_producao)
                                                .addGap(18, 18, 18)
                                                .addComponent(finalizado)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelado)))
                                        .addGap(0, 94, Short.MAX_VALUE))))
                            .addComponent(lblFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_materiais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(em_producao)
                    .addComponent(finalizado)
                    .addComponent(cancelado))
                .addGap(18, 18, 18)
                .addComponent(lblFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblAtivos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAtivos);

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnDeletarLixeira.setText("Deletar");
        btnDeletarLixeira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarLixeiraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlterar)
                .addGap(18, 18, 18)
                .addComponent(btnDeletarLixeira)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterar)
                    .addComponent(btnDeletarLixeira))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ativo", jPanel8);

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
        jScrollPane2.setViewportView(tblLixeira);

        btnDeletarBancodeDados.setText("Deletar");
        btnDeletarBancodeDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarBancodeDadosActionPerformed(evt);
            }
        });

        jToggleButton4.setText("Restaurar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(480, Short.MAX_VALUE)
                .addComponent(jToggleButton4)
                .addGap(18, 18, 18)
                .addComponent(btnDeletarBancodeDados)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeletarBancodeDados)
                    .addComponent(jToggleButton4))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lixeira", jPanel9);

        lblAvisos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAvisos.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvisos, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvisos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            lblFormulario.setText("");
            String usuario = txt_usuario.getText();
            String cliente = txt_cliente.getText();
            String dataTexto = txt_data.getText();
            String materiais = txt_materiais.getText();
            StatusPedido statusSelecionado = null;
            if (em_producao.isSelected()) {
                statusSelecionado = StatusPedido.EM_PRODUCAO;
            } else if (finalizado.isSelected()) {
                statusSelecionado = StatusPedido.FINALIZADO;
            } else if (cancelado.isSelected()) {
                statusSelecionado = StatusPedido.CANCELADO;
            }

            if (usuario.trim().isEmpty() || cliente.trim().isEmpty() || dataTexto.trim().isEmpty() || statusSelecionado == null) {
                lblFormulario.setText("Todos os campos, incluindo o Status, são obrigatórios.");
                Timer timer = new Timer(3000, e -> {
                    lblAvisos.setText("");
                });

                timer.setRepeats(false);

                timer.start();
              
                return;
            }
            LocalDate dataPedido;
            try {
                dataPedido = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de data inválido!\nUse o formato dd/mm/aaaa.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido pedido;
            String mensagemSucesso;

            if (pedidoSelecionado != null && pedidoSelecionado.getId() != null) {
                pedido = pedidoSelecionado;
                mensagemSucesso = "Pedido alterado com sucesso!";
            } else {
                pedido = new Pedido();
                pedido.setAtivo(true);
                mensagemSucesso = "Pedido incluído com sucesso!";
            }

            pedido.setUsuario(usuario);
            pedido.setCliente(cliente);
            pedido.setDataCriacao(dataPedido);
            pedido.setListaDeMateriaisUsados(materiais);
            pedido.setStatus(statusSelecionado);

            pedidoRepository.saveOrUpdate(pedido);

            JOptionPane.showMessageDialog(this, mensagemSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            carregarTabelas();
            limparFormulario();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o pedido: " + e.getMessage(), "Erro Inesperado", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int selectedRow = tblAtivos.getSelectedRow();
        lblAvisos.setText("");

        if (selectedRow != -1) {
            int modelRow = tblAtivos.convertRowIndexToModel(selectedRow);
            pedidoSelecionado = ativosTableModel.getPedidoEm(modelRow);

            preencherFormulario(pedidoSelecionado);
        } else {
            lblAvisos.setText("Por favor, selecione um pedido na tabela para alterar.");
            Timer timer = new Timer(3000, e -> {
                lblAvisos.setText("");
            });

            timer.setRepeats(false);

            timer.start();
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnDeletarLixeiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarLixeiraActionPerformed
        int selectedRow = tblAtivos.getSelectedRow();
        lblAvisos.setText("");

        if (selectedRow == -1) {
            lblAvisos.setText("Por favor, selecione um pedido na tabela para excluir.");
            Timer timer = new Timer(3000, e -> {
                lblAvisos.setText("");
            });

            timer.setRepeats(false);

            timer.start();
            return;
        }
        int response = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja mover este pedido para a lixeira?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            try {
                int modelRow = tblAtivos.convertRowIndexToModel(selectedRow);
                Pedido pedidoParaExcluir = ativosTableModel.getPedidoEm(modelRow);
                pedidoRepository.softDelete(pedidoParaExcluir.getId());

                JOptionPane.showMessageDialog(this, "Pedido movido para a lixeira com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarTabelas();
                limparFormulario();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeletarLixeiraActionPerformed

    private void btnDeletarBancodeDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarBancodeDadosActionPerformed
        int selectedRow = tblLixeira.getSelectedRow();

        lblAvisos.setText("");

        if (selectedRow == -1) {
            lblAvisos.setText("Por favor, selecione um pedido na lixeira para excluir permanentemente.");
            Timer timer = new Timer(3000, e -> {
                lblAvisos.setText("");
            });

            timer.setRepeats(false);

            timer.start();
            return;
        }

        int response = JOptionPane.showConfirmDialog(
                this,
                "Esta ação é IRREVERSÍVEL e apagará o pedido para sempre.\nTem certeza que deseja continuar?",
                "Exclusão Permanente",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE); // Ícone de aviso

        if (response == JOptionPane.YES_OPTION) {
            try {
                int modelRow = tblLixeira.convertRowIndexToModel(selectedRow);
                Pedido pedidoParaExcluir = lixeiraTableModel.getPedidoEm(modelRow);

                pedidoRepository.hardDelete(pedidoParaExcluir.getId());

                JOptionPane.showMessageDialog(this, "Pedido excluído permanentemente!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarTabelas();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir o pedido do banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDeletarBancodeDadosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new exibirPedido().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAlterar;
    private javax.swing.JToggleButton btnDeletarBancodeDados;
    private javax.swing.JToggleButton btnDeletarLixeira;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cancelado;
    private javax.swing.JCheckBox em_producao;
    private javax.swing.JCheckBox finalizado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JLabel lblAvisos;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JTable tblAtivos;
    private javax.swing.JTable tblLixeira;
    private javax.swing.JTextField txt_cliente;
    private javax.swing.JTextField txt_data;
    private javax.swing.JTextField txt_materiais;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
