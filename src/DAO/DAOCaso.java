/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Caso;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Wanderson
 */
public class DAOCaso {
    
     /* public void armazenar(Caso caso) {
        
        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        try {

            session.beginTransaction();
            session.save(caso);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Caso armazenado com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    public void alterar(Caso caso) {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        try {

            session.beginTransaction();
            session.merge(caso);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Caso alterado com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    } 

    public void excluir(Caso caso) {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        try {

            session.beginTransaction();
            session.delete(caso);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Caso excluído com sucesso");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            
        }
    } */

    public List listar(String hql) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List listaCasos = session.createQuery("From Caso " + hql).list();
        return listaCasos;

    }
    
}
