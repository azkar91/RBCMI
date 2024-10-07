/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.Caso;
import DAO.DAOCaso;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wanderson
 */
public class JFListaCaso extends javax.swing.JFrame {

    /**
     * Creates new form JFListaCaso
     */
    public JFListaCaso() {
        initComponents();
        listarCasos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spPrincipal = new javax.swing.JScrollPane();
        jtCasos = new javax.swing.JTable();
        btAbrir = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Casos");

        jtCasos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Diagnóstico", "Ação Corretiva"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCasos.setToolTipText("Tabela com todos os casos resumidos cadastrados no sistema para consulta.");
        spPrincipal.setViewportView(jtCasos);
        jtCasos.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtCasos.getColumnModel().getColumn(1).setPreferredWidth(400);
        jtCasos.getColumnModel().getColumn(2).setPreferredWidth(400);

        btAbrir.setText("Detalhes");
        btAbrir.setToolTipText("Abre detalhes do caso selecionado na tabela acima.");
        btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        btFechar.setText("Fechar");
        btFechar.setToolTipText("Fecha a janela atual.");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAbrir)
                    .addComponent(btFechar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirActionPerformed
        // TODO add your handling code here:
        //fazer mais coisas aqui depois
        detalhesCaso();
    }//GEN-LAST:event_btAbrirActionPerformed

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btFechar;
    private javax.swing.JTable jtCasos;
    private javax.swing.JScrollPane spPrincipal;
    // End of variables declaration//GEN-END:variables
    private HashMap hmCaso;
    private JFDetalhesCaso detCaso;
    
    private void listarCasos() {
        
        DefaultTableModel dtm = (DefaultTableModel)jtCasos.getModel();
        dtm.setNumRows(0);
        
        DAOCaso daoCaso = new DAOCaso();
        Caso caso;
        List listaCaso;
        hmCaso = new HashMap();
        
        try {
            listaCaso = daoCaso.listar("order by idCaso desc");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Houve algum problema com a listagem dos casos, detalhes:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);            
            return;
        }
        
        for(int i=0; i<listaCaso.size(); i++) { //insere resultado nas tabelas
           
            caso = (Caso)listaCaso.get(i);
            hmCaso.put(caso.getIdCaso(), caso);
            dtm.addRow(new Object[]{caso.getIdCaso(), caso.getResultado().getDesResultado(), caso.getSolucao().getDesSolucao()});     
            
        }
    }
    
    private void detalhesCaso() {
        
        DefaultTableModel dtm = (DefaultTableModel)jtCasos.getModel();
        int[] l = jtCasos.getSelectedRows();
        
        if(l.length == 1) {
            int idCaso = (Integer)dtm.getValueAt(l[0], 0);
            Caso caso = (Caso)hmCaso.get(idCaso);
            detCaso = new JFDetalhesCaso(caso);
            detCaso.setLocationRelativeTo(this);
            detCaso.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione no minímo/máximo um caso!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
