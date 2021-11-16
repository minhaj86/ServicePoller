package com.servicepoller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class BaseDAO<T extends BaseEntity> {
    final static Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	final Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ServiceUrl.class);
	final StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	final SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
    private final Class<?> entityClass;
    
    public BaseDAO(Class clazz) {
    	this.entityClass = clazz;
    }

	@SuppressWarnings("unchecked")
	T findById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		T e = (T) session.get(this.entityClass, id);
		session.close();
		return e;
	}


	@SuppressWarnings("unchecked")
	List<T> find() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<T> criteria = (CriteriaQuery<T>) builder.createQuery(this.entityClass);
	    criteria.from(this.entityClass);
	    List<T> data = session.createQuery(criteria).getResultList();
	    session.close();
	    return data;
	}

	@SuppressWarnings("unchecked")
	T add(T entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
	    session.close();
	    return entity;
	}

	@SuppressWarnings("unchecked")
	T edit(int id, T entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
	    session.close();
	    return entity;
	}



}
