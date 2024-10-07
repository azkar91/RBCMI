/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Ocorrencia;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Wanderson
 */
public class DAOOcorrencia {
    
     public void armazenar(Ocorrencia ocorrencia) throws Exception {
        
        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(ocorrencia);
        session.getTransaction().commit();

    }

    public void alterar(Ocorrencia ocorrencia) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(ocorrencia);
        session.getTransaction().commit();

    }
    
    public void excluir(Ocorrencia ocorrencia) {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        try {

            session.beginTransaction();
            session.delete(ocorrencia);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Ocorrência excluída!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Houve algum problema com a exclusão da ocorrência, detalhes:\n" + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            
        }
    }

    public List listar(String hql) throws Exception {
        
        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List listaOcorrencias = session.createQuery("From Ocorrencia " + hql).list();
        return listaOcorrencias;
        
    }
    
}