/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Tecnico;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Wanderson
 */
public class DAOTecnico {
    
    public void armazenar(Tecnico tecnico) throws Exception {
        
        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(tecnico);
        session.getTransaction().commit();
    
    }

    public void alterar(Tecnico tecnico) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.merge(tecnico);
        session.getTransaction().commit();
  
    }

    /*public void excluir(Tecnico tecnico) {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        try {

            session.beginTransaction();
            session.delete(tecnico);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Técnico excluído com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }*/

    public List listar(String hpl) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List listaTecnicos = session.createQuery("From Tecnico "+hpl).list();
        return listaTecnicos;
            
    }
    
}