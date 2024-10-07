/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rbcmi;

import Beans.Caso;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jcolibri.cbrcore.*;
import jcolibri.exception.InitializingException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

/**
 *
 * @author wanderson
 */
public class RbcmiConnector implements Connector  {
    
    private String descriptionClassName;
    private String solutionClassName;
    private String justOfSolutionClassName;
    private String resultClassName;
    SessionFactory sessionFactory;
    
    public void initRbcmi() {
        
        descriptionClassName = "Caso";
        solutionClassName = "Solucao";
        resultClassName = "Resultado";
        justOfSolutionClassName = null;
        sessionFactory = DAO.DAOHibernateUtil.getSessionFactory();
        
    }

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteCases(Collection<CBRCase> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public Collection<CBRCase> retrieveAllCases(){
		
            java.util.ArrayList<CBRCase> res = new java.util.ArrayList<CBRCase>();

            try 
            {
                    Session session;// = sessionFactory.openSession();				
                    Transaction transaction; //= session.beginTransaction();

                    List descList = null;
                    HashMap<Object, CaseComponent> solList = null;
                    HashMap<Object, CaseComponent> justSolList = null;
                    HashMap<Object, CaseComponent> resList = null;


                    if(solutionClassName != null)
                    {
                            session = sessionFactory.openSession();	
                            transaction = session.beginTransaction();
                            solList = new HashMap<Object, CaseComponent>();
                            List l = session.createQuery("from " + solutionClassName).list();
                            transaction.commit();
                            session.close();
                            for(Iterator iter = l.iterator(); iter.hasNext();)
                            {
                                CaseComponent cc = (CaseComponent)iter.next();
                                solList.put(cc.getIdAttribute().getValue(cc), cc);
                            }
                    }
                    if(justOfSolutionClassName != null)
                    {
                            session = sessionFactory.openSession();	
                            transaction = session.beginTransaction();

                            justSolList = new HashMap<Object, CaseComponent>();
                            List l = session.createQuery("from " + justOfSolutionClassName).list();
                            transaction.commit();
                            session.close();

                            for(Iterator iter = l.iterator(); iter.hasNext();)
                            {
                                    CaseComponent cc = (CaseComponent)iter.next();
                                    justSolList.put(cc.getIdAttribute().getValue(cc), cc);
                            }
                    }
                    if(resultClassName != null)
                    {
                            session = sessionFactory.openSession();	
                            transaction = session.beginTransaction();

                            resList = new HashMap<Object, CaseComponent>();
                            List l = session.createQuery("from " + resultClassName).list();
                            transaction.commit();
                            session.close();

                            for(Iterator iter = l.iterator(); iter.hasNext();)
                            {
                                    CaseComponent cc = (CaseComponent)iter.next();
                                    resList.put(cc.getIdAttribute().getValue(cc), cc);
                            }
                    }

                    session = sessionFactory.openSession();	
                    transaction = session.beginTransaction();
                    descList = session.createQuery("from "+ descriptionClassName).list();			
                    transaction.commit();
                    session.close();

                    for(Iterator iter = descList.iterator(); iter.hasNext();)
                    {
                            CBRCase _case = new CBRCase();
                            CaseComponent desc = (CaseComponent)iter.next();
                            _case.setDescription(desc);

                            if(solutionClassName != null)
                            {
                                    CaseComponent cc = solList.get(desc.getIdAttribute().getValue(desc));
                                    if(cc != null)
                                            _case.setSolution(cc);
                            }
                            if(justOfSolutionClassName != null)
                            {
                                    CaseComponent cc = justSolList.get(desc.getIdAttribute().getValue(desc));
                                    if(cc != null)
                                            _case.setJustificationOfSolution(cc);
                            }						
                            if(resultClassName != null)
                            {
                                    CaseComponent cc = resList.get(desc.getIdAttribute().getValue(desc));
                                    if(cc != null)
                                            _case.setResult(cc);
                            }

                            res.add(_case);

                    }

                    //transaction.commit();
                    //session.close();

            } catch (Exception e) {
                    org.apache.commons.logging.LogFactory.getLog(this.getClass()).error(e);
            }
            org.apache.commons.logging.LogFactory.getLog(this.getClass()).info(res.size()+" cases read from the database.");
            return res;
	}*/
    
    @Override
    public Collection<CBRCase> retrieveAllCases(){
		
            java.util.ArrayList<CBRCase> res = new java.util.ArrayList<CBRCase>();

            try 
            {
                    Session session;// = sessionFactory.openSession();				
                    Transaction transaction; //= session.beginTransaction();

                    List descList = null;

                    session = sessionFactory.openSession();	
                    transaction = session.beginTransaction();
                    descList = session.createQuery("from "+ descriptionClassName).list();			
                    transaction.commit();
                    //session.close();

                    for(Iterator iter = descList.iterator(); iter.hasNext();)
                    {
                        CBRCase _case = new CBRCase();
                        CaseComponent desc = (CaseComponent)iter.next();
                        _case.setDescription(desc);
                        Caso caso = (Caso)desc;
                        Hibernate.initialize(caso.getResultado());
                        Hibernate.initialize(caso.getSolucao());
                        _case.setResult(caso.getResultado());
                        _case.setSolution(caso.getSolucao());
                        res.add(_case);
                    }

                    //transaction.commit();
                    session.close();

            } catch (Exception e) {
                    org.apache.commons.logging.LogFactory.getLog(this.getClass()).error(e);
            }
            org.apache.commons.logging.LogFactory.getLog(this.getClass()).info(res.size()+" cases read from the database.");
            return res;
	}

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#retrieveSomeCases(jcolibri.cbrcore.CaseBaseFilter)
     */
    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see jcolibri.cbrcore.Connector#storeCases(java.util.Collection)
     */
    @Override
    public void storeCases(Collection<CBRCase> cases) {


        for(CBRCase c: cases)
        {
                Session session = sessionFactory.openSession();	
                Transaction transaction = session.beginTransaction();
                       
                if(c.getSolution()!= null)
                        session.saveOrUpdate(c.getSolution());
                transaction.commit();
                session.close();

                session = sessionFactory.openSession();	
                transaction = session.beginTransaction();
                if(c.getJustificationOfSolution() != null)
                        session.saveOrUpdate(c.getJustificationOfSolution());
                transaction.commit();
                session.close();

                session = sessionFactory.openSession();	
                transaction = session.beginTransaction();
                if(c.getResult() != null)
                        session.saveOrUpdate(c.getResult());
                transaction.commit();
                session.close();
                
                session = sessionFactory.openSession();	
               transaction = session.beginTransaction();
                        session.save(c.getDescription());
                transaction.commit();
                session.close();
        }


        org.apache.commons.logging.LogFactory.getLog(this.getClass()).info(cases.size()+" cases stored into the database.");

    }
    
}
