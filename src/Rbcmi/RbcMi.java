/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rbcmi;

import Beans.Caso;
import GUI.JDAdaptaCaso;
import GUI.JDConsultaCaso;
import GUI.JFCadCaso;
import GUI.JFprincipal;
import GUI.JDResultadoQuery;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.selection.SelectCases;

/**
 *
 * @author Wanderson
 */
public class RbcMi implements StandardCBRApplication {

    private static RbcMi _instance = null;
    private CBRCaseBase _caseBase;
    private RbcmiConnector rbcmiConnector;
    private NNConfig simConfig;
    private int k;
    private JFCadCaso JFcc;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFprincipal telaPrincipal = new JFprincipal();
        telaPrincipal.setVisible(true);
        
    }
    
    public static RbcMi getInstance() {
        
        if (_instance == null) _instance = new RbcMi();
        return _instance;
        
    }

    @Override
    public void configure() throws ExecutionException {
        rbcmiConnector = new RbcmiConnector();
        rbcmiConnector.initRbcmi();
        _caseBase = new LinealCaseBase();
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        // Load cases from connector into the case base
        _caseBase.init(rbcmiConnector);		
        // Print the cases
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        for(CBRCase c: cases)
                System.out.println(c);
        return _caseBase;
    }

    @Override
    public void cycle(CBRQuery query) throws ExecutionException {
	
        simConfig.setDescriptionSimFunction(new Average());

        //executa NN
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);

        //busca os k casos na base
        Collection<CBRCase> casosSelecionados = SelectCases.selectTopK(eval, k);
        
        //mostra resultado
        JDResultadoQuery telaResultado = new JDResultadoQuery(null, true);
        telaResultado.preparaArray(eval, casosSelecionados);
        telaResultado.setLocationRelativeTo(JFcc);
        telaResultado.setVisible(true);
        
        CBRCase casoAdpatar = telaResultado.getCaso();
        if(casoAdpatar == null) return;
        
        //adptar
        Caso casoAtual = (Caso)query.getDescription();
        JDAdaptaCaso telaAdpta = new JDAdaptaCaso(null, true);
        telaAdpta.preparaAdpatacao(casoAdpatar, casoAtual);
        telaAdpta.setLocationRelativeTo(JFcc);
        telaAdpta.setVisible(true);
        
        CBRCase casoInserir = telaAdpta.getCaso();
        if (casoInserir == null) return;
        
        //verifica se é isso mesmo
        JDConsultaCaso telaConfirma = new JDConsultaCaso(null, true);
        telaConfirma.setCaso(casoInserir);
        telaConfirma.setLocationRelativeTo(JFcc);
        telaConfirma.setVisible(true);
        
        if (!telaConfirma.confirmado()) return;
        
        //aprende novo caso
        ArrayList<CBRCase> casosParaReter = new ArrayList<>();
        casosParaReter.add(casoInserir);
        _caseBase.learnCases(casosParaReter);
        
        JFcc.setSolucaoResultado(casoInserir);
        JOptionPane.showMessageDialog(null, "Caso armazenado na base!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
    }

    @Override
    public void postCycle() throws ExecutionException {
        rbcmiConnector.close();
    }
    
    public void setSimConfig(NNConfig sconfig) {
        simConfig = sconfig;
    }
    
    public void setQtdK(int qtdk) {
        k = qtdk;
    }
    
    public void setFrameCadCaso(JFCadCaso jf) {
        JFcc = jf;
    }
    
}
