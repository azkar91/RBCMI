/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Beans.Ocorrencia;
import DAO.DAOOcorrencia;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wanderson
 */
public class JFListaOcorrencia extends javax.swing.JFrame {

    /**
     * Creates new form JFListaOcorrencia
     */
    public JFListaOcorrencia() {
        initComponents();
        ((DefaultTableCellRenderer)jtCasos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        jtCasos.getColumnModel().getColumn(0).setPreferredWidth(10);
        jtCasos.getColumnModel().getColumn(1).setPreferredWidth(220);
        jtCasos.getColumnModel().getColumn(2).setPreferredWidth(220);
        jtCasos.getColumnModel().getColumn(3).setPreferredWidth(190);
        listarOcorrencias();
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
        setTitle("Consulta de Ocorrências");

        jtCasos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Problema", "Cliente", "Técnico", "Data Abertura", "Data Fechamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCasos.setToolTipText("Tabela de ocorrências cadastradas no sistema, selecione somente uma e use o botão abaixo para abrí-la.");
        spPrincipal.setViewportView(jtCasos);

        btAbrir.setText("Abrir Ocorência");
        btAbrir.setToolTipText("Abre a ocorrência selecionada na tebela acima.");
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
                        .addComponent(btAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        editarOcorrencia();
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
    private HashMap hmOcorrencia;
    private JFEditarOcorrencia editaOcorrencia;
    
    public void listarOcorrencias() {
        
        DefaultTableModel dtm = (DefaultTableModel)jtCasos.getModel();
        dtm.setNumRows(0);
        
        DAOOcorrencia daoOcorrencia = new DAOOcorrencia();
        Ocorrencia ocorrencia;
        List listaOcorrencia;
        
        try {
            listaOcorrencia = daoOcorrencia.listar("order by dtFechamento desc");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Houve algum problema com a listagem das ocorrências, detalhes:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);            
            return;
        }
        
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd/MM/yyyy");
        hmOcorrencia = new HashMap();
        
        for(int i=0; i<listaOcorrencia.size(); i++) { //insere resultado nas tabelas
           
            ocorrencia = (Ocorrencia)listaOcorrencia.get(i);
            hmOcorrencia.put(ocorrencia.getIdOcorrencia(), ocorrencia);
           
            String problema = ocorrencia.getProbOcorrencia();
            String tecnico = "---";
            String dtFechar = "--/--/----";
            
            if (ocorrencia.getTecnico() != null) tecnico = ocorrencia.getTecnico().getNmTecnico();            
            if (ocorrencia.getDtFechamento() != null) dtFechar = "" + df.format(ocorrencia.getDtFechamento());
            if (problema.equals("")) problema = "Não informado"; 
           
            dtm.addRow(new Object[]{ocorrencia.getIdOcorrencia(), problema, ocorrencia.getCliente().getNmCliente(), tecnico, df.format(ocorrencia.getDtAbertura()), dtFechar});     
            
        }
    }
    
    private void editarOcorrencia() {
        
        DefaultTableModel dtm = (DefaultTableModel)jtCasos.getModel();
        int[] l = jtCasos.getSelectedRows();
        
        if(l.length == 1) {
            int idOcorrencia = (Integer)dtm.getValueAt(l[0], 0);
            Ocorrencia oco = (Ocorrencia)hmOcorrencia.get(idOcorrencia);
            editaOcorrencia = new JFEditarOcorrencia(oco, this);
            editaOcorrencia.setLocationRelativeTo(this);
            editaOcorrencia.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione no minímo/máximo uma ocorrência!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
