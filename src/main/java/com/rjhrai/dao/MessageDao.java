package com.rjhrai.dao;

import com.rjhrai.backing.MessageBean;
import com.rjhrai.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageDao {
    private static final Logger log = LoggerFactory.getLogger(MessageDao.class);
    @PersistenceContext(name="my-persistence")
    private EntityManager em;

    @PostConstruct
    public void init() {
        log.info("****************** MessageDao created");
    }

    public Message create(Message message) {
        em.persist(message);
        return  message;
    }

    public void update(Message message) {
        Message existing = findById(message.getId());
        existing.setText(message.getText());
    }

    public Message findById(Long id) {
        return em.find(Message.class, id);
    }

    public List<Message> list() {
         List<Message> list = em
                .createQuery("FROM com.rjhrai.model.Message m", Message.class)
                .getResultList();
         return list;
    }

    public void delete(Long id) {
        em.createQuery("DELETE FROM Message WHERE id = :myId")
        .setParameter("myId", id)
        .executeUpdate();
    }

    public void deleteAll() {
        em.createQuery("DELETE FROM Message")
        .executeUpdate();
    }
}
