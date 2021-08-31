package com.aspiresys.task1.model.dao.channelContent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aspiresys.task1.beans.Channel.ChannelBean;

@Repository
public class ChannelContentDaoImpl implements ChannelContentDao {

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
	public Boolean isContentPresent(String text, ChannelBean channelBean, String bean) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + bean + " WHERE programee = ?0 AND channelId.channelId = ?1");
			query.setParameter(0, text);
			query.setParameter(0, channelBean.getChannelId());
			listOfObjects = query.getResultList();
			return listOfObjects.isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			return listOfObjects.isEmpty();
		}
	}

}
