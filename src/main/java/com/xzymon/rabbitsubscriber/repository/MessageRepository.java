package com.xzymon.rabbitsubscriber.repository;

import com.xzymon.rabbitsubscriber.domain.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MessageRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void insertWithEntityManager(Message message) {
		entityManager.persist(message);
	}
}
