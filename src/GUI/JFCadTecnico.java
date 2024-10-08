/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.Tecnico;
import DAO.DAOTecnico;
import javax.swing.JOptionPane;

/**
 *
 * @author Wanderson
 */
public class JFCadTecnico extends javax.swing.JFrame {

    /**
     * Creates new form JFCadTecnico
     */
    public JFCadTecnico() {
        initComponents();
    }
    
    public JFCadTecnico(JFEditarOcorrencia jfEO) {
        initComponents();
        editarOcorrencia = jfEO;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpTecnico = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        lbTelefone1 = new javax.swing.JLabel();
        lbTelefone2 = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfCpf = new javax.swing.JTextField();
        tfTelefone1 = new javax.swing.JTextField();
        tfTelefone2 = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        btCadastrar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbObs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Técnico");

        jpTecnico.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbNome.setText("Nome");

        lbCpf.setText("CPF");

        lbTelefone1.setText("Telefone 1");

        lbTelefone2.setText("Telefone 2");

        lbEmail.setText("Email");

        tfNome.setToolTipText("Nome completo do técnico.");

        tfCpf.setToolTipText("Aceita todos os caracters, porém é recomendável utilizar somente números.");

        tfTelefone1.setToolTipText("Telefone principal.");

        tfTelefone2.setToolTipText("Telefone secundário.");

        tfEmail.setToolTipText("Endereço de e-mail.");

        javax.swing.GroupLayout jpTecnicoLayout = new javax.swing.GroupLayout(jpTecnico);
        jpTecnico.setLayout(jpTecnicoLayout);
        jpTecnicoLayout.setHorizontalGroup(
            jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTecnicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTecnicoLayout.createSequentialGroup()
                        .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTelefone2)
                            .addComponent(lbEmail))
                        .addGap(18, 18, 18)
                        .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTecnicoLayout.createSequentialGroup()
                                .addComponent(tfTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfEmail)))
                    .addGroup(jpTecnicoLayout.createSequentialGroup()
                        .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTelefone1)
                            .addComponent(lbCpf)
                            .addComponent(lbNome))
                        .addGap(18, 18, 18)
                        .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome)
                            .addGroup(jpTecnicoLayout.createSequentialGroup()
                                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(tfTelefone1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jpTecnicoLayout.setVerticalGroup(
            jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTecnicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(tfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone1)
                    .addComponent(tfTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefone2)
                    .addComponent(tfTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btCadastrar.setText("Cadastrar");
        btCadastrar.setToolTipText("Cadastra as informações no sistema.");
        btCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastrarActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.setToolTipText("Fecha a janela atual.");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbObs.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbObs.setText("(Utilizar somente números para telefones e CPF)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbObs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCadastrar)
                    .addComponent(btCancelar)
                    .addComponent(lbObs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastrarActionPerformed
        // TODO add your handling code here:
        armazenar();
    }//GEN-LAST:event_btCadastrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCadastrar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JPanel jpTecnico;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbTelefone1;
    private javax.swing.JLabel lbTelefone2;
    private javax.swing.JTextField tfCpf;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfTelefone1;
    private javax.swing.JTextField tfTelefone2;
    // End of variables declaration//GEN-END:variables
    private JFEditarOcorrencia editarOcorrencia;
    
    public void armazenar(){
        
        if (tfNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nome do técnico está em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Tecnico tecnico = new Tecnico();
        DAOTecnico daoTecnico = new DAOTecnico();
        
        tecnico.setCpfTecnico(tfCpf.getText());
        tecnico.setEmailTecnico(tfEmail.getText());
        tecnico.setNmTecnico(tfNome.getText());
        tecnico.setTel1tecnico(tfTelefone1.getText());
        tecnico.setTel2tecnico(tfTelefone2.getText());
        
        try {
            daoTecnico.armazenar(tecnico);
            JOptionPane.showMessageDialog(null, "Técnico cadastrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            if (editarOcorrencia != null) editarOcorrencia.adicionarTecnico(tecnico);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Houve algum problema com o cadastro do técnico, detalhes:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
       
        this.dispose();
        
    }
    
}
