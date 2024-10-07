/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Cliente;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Wanderson
 */
public class DAOCliente {
 
    public void armazenar(Cliente cliente) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
         
    }

    public void alterar(Cliente cliente) throws Exception {
        
        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();
        
        session.beginTransaction();
        session.merge(cliente);
        session.getTransaction().commit();
 
    }

    public void excluir(Cliente cliente) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();
    
        session.beginTransaction();
        session.delete(cliente);
        session.getTransaction().commit();
            
    }

    public List listar(String hpl) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List listaClientes = session.createQuery("From Cliente "+hpl).list();
        return listaClientes;
            
    }
    
    public Cliente listarId(int id) throws Exception {

        Session session = DAOHibernateUtil.getSessionFactory().openSession();
        
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        return cliente;

    }
    
}
