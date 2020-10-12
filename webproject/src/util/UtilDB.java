/**
 */
package util;

import java.util.List;

import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.ListUser;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static ListUser findUser(String keyword) {
     ListUser user = new ListUser();
     boolean flag = false;
      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> employees = session.createQuery("FROM ListUser").list();
         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
        	 ListUser employee = (ListUser) iterator.next();
            if (employee.getName().startsWith(keyword)) {
              user = employee;
              flag = true;
              break;
            }
         }
         if(!flag) {
        	 System.out.println("TEST");
        	 createUser(keyword);
        	 findUser(keyword);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return user;
   }
   public static ListUser findUserNoAdd(String keyword) {
	     ListUser user = new ListUser();
	   
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;

	      try {
	         tx = session.beginTransaction();
	         List<?> employees = session.createQuery("FROM ListUser").list();
	         for (Iterator<?> iterator = employees.iterator(); iterator.hasNext();) {
	        	 ListUser employee = (ListUser) iterator.next();
	            if (employee.getName().startsWith(keyword)) {
	               user = employee;
	            }
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx != null)
	            tx.rollback();
	         e.printStackTrace();
	      } finally {
	         session.close();
	      }
	      return user;
	   }
   public static void createUser(String userName) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new ListUser(userName));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   public static void addList(ListUser l, String s)
   {
	      Session session = getSessionFactory().openSession();
	      Transaction tx = null;
	      try {
	          tx = session.beginTransaction();
	          l.addList(s);
	          session.saveOrUpdate(l);
	          tx.commit();
	       } catch (HibernateException e) {
	          if (tx != null)
	             tx.rollback();
	          e.printStackTrace();
	       } finally {
	          session.close();
	       }
	      
   }
}
