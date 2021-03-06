package com.aspiresys.task1.model.dao.dataFromWeb;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataFromWebDaoImpl implements DataFromWebDao {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public int save(Object object) {
		Serializable serializable = 0;
		Session session = getSession();
		try {
			serializable = session.save(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) serializable;
	}

	@Override
	public Boolean isPresent(String chan, String bean) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + bean + " WHERE channelName = ?0");
			query.setParameter(0, chan);
			listOfObjects = query.getResultList();
			return listOfObjects.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			return listOfObjects.isEmpty();
		}
	}
}
