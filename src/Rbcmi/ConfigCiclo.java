/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rbcmi;

import Beans.Caso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
//import jcolibri.method.retrieve.NNretrieval.similarity.local.11

/**
 *
 * @author wanderson
 */



public class ConfigCiclo {
    
    private JTable tabela;
    private int k;
    private List<Double> pesos;
    private List<String> valores; 
    
    public ConfigCiclo() {
    }
    
    public ConfigCiclo(int k) {
        this.k = k;
    }
    
    public void setTabela(JTable tabela) {
        this.tabela = tabela;
    }
    
    private Double converteStringDouble(String str) {
        Double valor;
        try {
            valor = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Valor informado: '" + str + "', não é um decimal válido.\nUtilize apenas números e '.' (ponto) para separar as casas decimais.", "Erro", JOptionPane.ERROR_MESSAGE);
            valor = null;
        }
        return valor;
    }
    
    private Integer converteStringInt(String str) {
        Integer valor;
        try {
            valor = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Valor informado: '" + str + "', não é um inteiro válido. Utilize apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            valor = null;
        }
        return valor;
    }
    
    private void getDadosCategoria() {
        
        DefaultTableModel dtm = (DefaultTableModel)tabela.getModel();
        pesos = new ArrayList();
        valores = new ArrayList();
        
        for (int i=0; i<dtm.getRowCount(); i++) {
            if (dtm.getValueAt(i, 1) == null) {
                JOptionPane.showMessageDialog(null, "Valor do atributo: '" + (String)dtm.getValueAt(i, 0) + "', está em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
                valores = null;
                return;
            }
            valores.add((String)dtm.getValueAt(i, 1));
            try {
                pesos.add(Double.parseDouble((String)dtm.getValueAt(i, 2)));
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Peso do atributo: '" + (String)dtm.getValueAt(i, 0) + "', está em branco!", "Erro", JOptionPane.ERROR_MESSAGE);
                pesos = null;
                return;
            }
        }
        
    }
    
    private boolean getDadosLivre() {
        
        DefaultTableModel dtm = (DefaultTableModel)tabela.getModel();
        pesos = new ArrayList();
        valores = new ArrayList();
        Double pesoTemp;
        boolean valida = false;
        
        for (int i=0; i<dtm.getRowCount(); i++) {
             try {
                pesoTemp = Double.parseDouble((String)dtm.getValueAt(i, 2));
                if ((dtm.getValueAt(i, 1) != null)) {
                    valores.add((String)dtm.getValueAt(i, 1));
                    pesos.add(pesoTemp);
                    valida = true;
                } else {
                    valores.add(null);
                    pesos.add(null);
                }
            } catch (NullPointerException npe) {
                valores.add(null);
                pesos.add(null);
            }
        }  
        
        return valida;
        
    }
    
    public void similarityQueryLivre() {
        
        boolean temvalor = getDadosLivre();
        
        if(!temvalor) {
            JOptionPane.showMessageDialog(null, "Nenhum atributo foi inserido ao caso, verifique os pesos e valores!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;
        
        if(valores.get(0) != null) {
            atributo = new Attribute("monRiscoscores", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(0));
            caso.setMonRiscoscores(valores.get(0));
            System.out.println(valores.get(0) + "PESO: " + pesos.get(0));
        }
        
        if(valores.get(1) != null) {
            atributo = new Attribute("soArquitetura", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(1));
            caso.setSoArquitetura(Integer.parseInt(valores.get(1)));
        }
        
        if(valores.get(2) != null) {
            atributo = new Attribute("bipBios", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(2));
            caso.setBipBios(valores.get(2));
        }
        
        if(valores.get(3) != null) {
            atributo = new Attribute("gabBotaopower", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(3));
            caso.setGabBotaopower(valores.get(3));
        }
        
        if(valores.get(4) != null) {
            atributo = new Attribute("gabCabeamentointerno", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(4));
            caso.setGabCabeamentointerno(valores.get(4));
        }
        
        if(valores.get(5) != null) {
            atributo = new Attribute("condCabeamentoenergia", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(5));
            caso.setCondCabeamentoenergia(valores.get(5));
        }
        
        if(valores.get(6) != null) {
            atributo = new Attribute("dataSistema", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(6));
            caso.setDataSistema(valores.get(6));
        }
        
        if(valores.get(7) != null) {
            atributo = new Attribute("hdBios", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(7));
            caso.setHdBios(valores.get(7));
        }
        
        if(valores.get(8) != null) {
            atributo = new Attribute("endDns", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(8));
            caso.setEndDns(valores.get(8));
        }
        
        if(valores.get(9) != null) {
            atributo = new Attribute("endIp", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(9));
            caso.setEndIp(valores.get(9));
        }
        
        if(valores.get(10) != null) {
            atributo = new Attribute("equipProtecaoenergia", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(10));
            caso.setEquipProtecaoenergia(valores.get(10));
        }
        
        if(valores.get(11) != null) {
            atributo = new Attribute("hdEspacolivre", Caso.class);
            funcao = new Interval(20); //testar
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(11));
            Double hdEsp = converteStringDouble(valores.get(11));
            if (hdEsp == null) return;
            caso.setHdEspacolivre(hdEsp);
        }
        
        if(valores.get(12) != null) {
            atributo = new Attribute("estConexaorede", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(12));
            caso.setEstConexaorede(valores.get(12));
        }
        
        if(valores.get(13) != null) {
            atributo = new Attribute("estSoimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(13));
            caso.setEstSoimpressora(valores.get(13));
        }
        
        if(valores.get(14) != null) {
            atributo = new Attribute("estRjConector", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(14));
            caso.setEstRjConector(valores.get(14));
        }
        
        if(valores.get(15) != null) {
            atributo = new Attribute("estDriverimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(15));
            caso.setEstDriverimpressora(valores.get(15));
        }
        
        if(valores.get(16) != null) {
            atributo = new Attribute("estDriverrede", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(16));
            caso.setEstDriverrede(valores.get(16));
        }
        
        if(valores.get(17) != null) {
            atributo = new Attribute("estDriversom", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(17));
            caso.setEstDriversom(valores.get(17));
        }
        
        if(valores.get(18) != null) {
            atributo = new Attribute("estDrivervideo", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(18));
            caso.setEstDrivervideo(valores.get(18));
        }
        
        if(valores.get(19) != null) {
            atributo = new Attribute("estLedimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(19));
            caso.setEstLedimpressora(valores.get(19));
        }
        
        if(valores.get(20) != null) {
            atributo = new Attribute("estLedhd", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(20));
            caso.setEstLedhd(valores.get(20));
        }
        
        if(valores.get(21) != null) {
            atributo = new Attribute("estMonitor", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(21));
            caso.setEstMonitor(valores.get(21));
        }
        
        if(valores.get(22) != null) {
            atributo = new Attribute("estDispositivoaudio", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(22));
            caso.setEstDispositivoaudio(valores.get(22));
        }
        
        if(valores.get(23) != null) {
            atributo = new Attribute("estSistemaoperacional", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(23));
            caso.setEstSistemaoperacional(valores.get(23));
        }
        
        if(valores.get(24) != null) {
            atributo = new Attribute("estAntivirus", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(24));
            caso.setEstAntivirus(valores.get(24));
        }
        
        if(valores.get(25) != null) {
            atributo = new Attribute("estGeralimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(25));
            caso.setEstGeralimpressora(valores.get(25));
        }
        
        if(valores.get(26) != null) {
            atributo = new Attribute("gabLedfuncionamento", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(26));
            caso.setGabLedfuncionamento(valores.get(26));
        }
        
        if(valores.get(27) != null) {
            atributo = new Attribute("infraRede", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(27));
            caso.setInfraRede(valores.get(27));
        }
        
        if(valores.get(28) != null) {
            atributo = new Attribute("msgErro", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(28));
            caso.setMsgErro(valores.get(28));
        }
        
        if(valores.get(29) != null) {
            atributo = new Attribute("nivelCartuchoimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(29));
            caso.setNivelCartuchoimpressora(valores.get(29));
        }
        
        if(valores.get(30) != null) {
            atributo = new Attribute("hwNovoinstalado", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(30));
            caso.setHwNovoinstalado(valores.get(30));
        }
        
        if(valores.get(31) != null) {
            atributo = new Attribute("filaImpressao", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(31));
            caso.setFilaImpressao(valores.get(31));
        }
        
        if(valores.get(32) != null) {
            atributo = new Attribute("qtdDispositivoaudio", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(32));
            caso.setQtdDispositivoaudio(valores.get(32));
        }
        
        if(valores.get(33) != null) {
            atributo = new Attribute("qtdRam", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(33));
            Double qtdram = converteStringDouble(valores.get(33));
            if (qtdram == null) return;
            caso.setQtdRam(qtdram);
        }
        
        if(valores.get(34) != null) {
            atributo = new Attribute("qtdCores", Caso.class);
            funcao = new Interval(1); //testar
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(34));
            Integer cpucore = converteStringInt(valores.get(34));
            if (cpucore == null) return;
            caso.setQtdCores(cpucore);
        }
        
        if(valores.get(35) != null) {
            atributo = new Attribute("soInstalado", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(35));
            caso.setSoInstalado(valores.get(35));
        }
        
        if(valores.get(36) != null) {
            atributo = new Attribute("pcReinicia", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(36));
            caso.setPcReinicia(valores.get(36));
        }
        
        if(valores.get(37) != null) {
            atributo = new Attribute("sitSomnaofunciona", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(37));
            caso.setSitSomnaofunciona(valores.get(37));
        }
    
        if(valores.get(38) != null) {
            atributo = new Attribute("gabSujeira", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(38));
            caso.setGabSujeira(valores.get(38));
        }
        
        if(valores.get(39) != null) {
            atributo = new Attribute("tempVga", Caso.class);
            funcao = new Interval(10);
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(39));
            Double tempvga = converteStringDouble(valores.get(39));
            if (tempvga == null) return;
            caso.setTempVga(tempvga);
        }
        
        if(valores.get(40) != null) {
            atributo = new Attribute("tempCpu", Caso.class);
            funcao = new Interval(10); //testar
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(40));
            Double cputemp = converteStringDouble(valores.get(40));
            if (cputemp == null) return;
            caso.setTempCpu(cputemp);
        }
        
        if(valores.get(41) != null) {
            atributo = new Attribute("tipoConexaoimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(41));
            caso.setTipoConexaoimpressora(valores.get(41));
        }
        
        if(valores.get(42) != null) {
            atributo = new Attribute("tipoConexaointernet", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(42));
            caso.setTipoConexaointernet(valores.get(42));
        }
        
        if(valores.get(43) != null) {
            atributo = new Attribute("tipoConexaorede", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(43));
            caso.setTipoConexaorede(valores.get(43));
        }
        
        if(valores.get(44) != null) {
            atributo = new Attribute("tipoSoimpressora", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(44));
            caso.setTipoSoimpressora(valores.get(44));
        }
        
        if(valores.get(45) != null) {
            atributo = new Attribute("tipoVga", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(45));
            caso.setTipoVga(valores.get(45));
        }
        
        if(valores.get(46) != null) {
            atributo = new Attribute("cpuVelocidade", Caso.class);
            funcao = new Interval(0.5); //testar
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(46));
            Double cpuvel = converteStringDouble(valores.get(46));
            if (cpuvel == null) return;
            caso.setCpuVelocidade(cpuvel);
        }
        
        if(valores.get(47) != null) {
            atributo = new Attribute("psuVoltagem", Caso.class);
            funcao = new Equal();
            config.addMapping(atributo, funcao);
            config.setWeight(atributo, pesos.get(47));
            caso.setPsuVoltagem(valores.get(47));
        }
        
        query.setDescription(caso);
        executaQuery(config, query);
        
    }
    
    public void similarityQuerySom() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("estDriversom", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setEstDriversom(valores.get(0));

        atributo = new Attribute("estDispositivoaudio", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setEstDispositivoaudio(valores.get(1));

        atributo = new Attribute("qtdDispositivoaudio", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setQtdDispositivoaudio(valores.get(2));

        atributo = new Attribute("sitSomnaofunciona", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setSitSomnaofunciona(valores.get(3));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    public void similarityQueryVideo() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("monRiscoscores", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setMonRiscoscores(valores.get(0));

        atributo = new Attribute("estDrivervideo", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setEstDrivervideo(valores.get(1));

        atributo = new Attribute("estMonitor", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setEstMonitor(valores.get(2));

        atributo = new Attribute("gabSujeira", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setGabSujeira(valores.get(3));
        
        atributo = new Attribute("tempVga", Caso.class);
        funcao = new Interval(10);
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        Double tempvga = converteStringDouble(valores.get(4));
        if (tempvga == null) return;
        caso.setTempVga(tempvga);
        
        atributo = new Attribute("tipoVga", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setTipoVga(valores.get(5));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    public void similarityQueryImpressao() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("estSoimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setEstSoimpressora(valores.get(0));
        
        atributo = new Attribute("estDriverimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setEstDriverimpressora(valores.get(1));
        
        atributo = new Attribute("estLedimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setEstLedimpressora(valores.get(2));

        atributo = new Attribute("estGeralimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setEstGeralimpressora(valores.get(3));

        atributo = new Attribute("nivelCartuchoimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        caso.setNivelCartuchoimpressora(valores.get(4));
        
        atributo = new Attribute("filaImpressao", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setFilaImpressao(valores.get(5));
        
        atributo = new Attribute("tipoConexaoimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setTipoConexaoimpressora(valores.get(6));
        
        atributo = new Attribute("tipoSoimpressora", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(7));
        caso.setTipoSoimpressora(valores.get(7));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
        
    public void similarityQueryInicializacao() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("bipBios", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setBipBios(valores.get(0));

        atributo = new Attribute("gabCabeamentointerno", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setGabCabeamentointerno(valores.get(1));

        atributo = new Attribute("dataSistema", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setDataSistema(valores.get(2));

        atributo = new Attribute("hdBios", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setHdBios(valores.get(3));
        
        atributo = new Attribute("gabLedfuncionamento", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        caso.setGabLedfuncionamento(valores.get(4));
        
        atributo = new Attribute("msgErro", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setMsgErro(valores.get(5));
        
        atributo = new Attribute("hwNovoinstalado", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setHwNovoinstalado(valores.get(6));
        
        atributo = new Attribute("gabSujeira", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setGabSujeira(valores.get(6));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    public void similarityQueryTrava() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("soArquitetura", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setSoArquitetura(Integer.parseInt(valores.get(0)));

        atributo = new Attribute("hdEspacolivre", Caso.class);
        funcao = new Interval(20); //testar
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        Double hdEsp = converteStringDouble(valores.get(1));
        if (hdEsp == null) return;
        caso.setHdEspacolivre(hdEsp);

        atributo = new Attribute("estLedhd", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setEstLedhd(valores.get(2));

        atributo = new Attribute("estSistemaoperacional", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setEstSistemaoperacional(valores.get(3));
        
        atributo = new Attribute("estAntivirus", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        caso.setEstAntivirus(valores.get(4));
        
        atributo = new Attribute("msgErro", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setMsgErro(valores.get(5));
        
        atributo = new Attribute("hwNovoinstalado", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setHwNovoinstalado(valores.get(6));
        
        atributo = new Attribute("qtdRam", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(7));
        Double qtdram = converteStringDouble(valores.get(7));
        if (qtdram == null) return;
        caso.setQtdRam(qtdram);
        
        atributo = new Attribute("qtdCores", Caso.class);
        funcao = new Interval(1); //testar
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(8));
        Integer cpucore = converteStringInt(valores.get(8));
        if (cpucore == null) return;
        caso.setQtdCores(cpucore);
        
        atributo = new Attribute("soInstalado", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(9));
        caso.setSoInstalado(valores.get(9));
        
        atributo = new Attribute("pcReinicia", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(10));
        caso.setPcReinicia(valores.get(10));
        
        atributo = new Attribute("gabSujeira", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(11));
        caso.setGabSujeira(valores.get(11));
        
        atributo = new Attribute("tempCpu", Caso.class);
        funcao = new Interval(10); //testar
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(12));
        Double cputemp = converteStringDouble(valores.get(12));
        if (cputemp == null) return;
        caso.setTempCpu(cputemp);
        
        atributo = new Attribute("cpuVelocidade", Caso.class);
        funcao = new Interval(0.5); //testar
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(13));
        Double cpuvel = converteStringDouble(valores.get(13));
        if (cpuvel == null) return;
        caso.setCpuVelocidade(cpuvel);
        
        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    public void similarityQueryNaoliga() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("gabBotaopower", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setGabBotaopower(valores.get(0));

        atributo = new Attribute("gabCabeamentointerno", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setGabCabeamentointerno(valores.get(1));

        atributo = new Attribute("condCabeamentoenergia", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setCondCabeamentoenergia(valores.get(2));

        atributo = new Attribute("equipProtecaoenergia", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setEquipProtecaoenergia(valores.get(3));
        
        atributo = new Attribute("gabLedfuncionamento", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        caso.setGabLedfuncionamento(valores.get(4));
        
        atributo = new Attribute("hwNovoinstalado", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setHwNovoinstalado(valores.get(5));
        
        atributo = new Attribute("psuVoltagem", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setPsuVoltagem(valores.get(6));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    public void similarityQueryRede() {
        
        getDadosCategoria();
        if (pesos == null || valores == null) return;
        
        NNConfig config = new NNConfig();
        Caso caso = new Caso();
        CBRQuery query = new CBRQuery();
        Attribute atributo;
        LocalSimilarityFunction funcao;

        atributo = new Attribute("endDns", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(0));
        caso.setEndDns(valores.get(0));

        atributo = new Attribute("endIp", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(1));
        caso.setEndIp(valores.get(1));

        atributo = new Attribute("estConexaorede", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(2));
        caso.setEstConexaorede(valores.get(2));
        
        atributo = new Attribute("estRjConector", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(3));
        caso.setEstRjConector(valores.get(3));
        
        atributo = new Attribute("estDriverrede", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(4));
        caso.setEstDriverrede(valores.get(4));
        
        atributo = new Attribute("infraRede", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(5));
        caso.setInfraRede(valores.get(5));
        
        atributo = new Attribute("tipoConexaointernet", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(6));
        caso.setTipoConexaointernet(valores.get(6));
        
        atributo = new Attribute("tipoConexaorede", Caso.class);
        funcao = new Equal();
        config.addMapping(atributo, funcao);
        config.setWeight(atributo, pesos.get(7));
        caso.setTipoConexaorede(valores.get(7));

        query.setDescription(caso);
        executaQuery(config, query);
    
    }
    
    private void executaQuery(NNConfig config, CBRQuery query) {

        RbcMi rbcmi = RbcMi.getInstance();
        try {
            rbcmi.configure();
            rbcmi.preCycle();
            rbcmi.setSimConfig(config);
            rbcmi.setQtdK(k);
            rbcmi.cycle(query);
            rbcmi.postCycle();
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao executar a pesquisa (Query) na base de casos:\n '" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }
    
}
