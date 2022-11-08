package com.rjhrai.service.impl;

import com.rjhrai.dao.MessageDao;
import com.rjhrai.model.Message;
import com.rjhrai.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MessageServiceImpl implements MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Inject
    private MessageDao messageDao;

    @PostConstruct
    public void init() {
        log.info("****************** MessageServiceImpl created");
    }

    @Override
    public Message create(Message message) {
        return messageDao.create(message);
    }

    @Override
    public void update(Message message) {
        messageDao.update(message);
    }

    @Override
    public Message findById(Long id) {
        return messageDao.findById(id);
    }

    @Override
    public List<Message> listAll() {
        List<Message> list =  messageDao.list();
        return list;
    }
    @Override
    public void delete(Long id) {
        messageDao.delete(id);
    }
    @Override
    public void deleteAll() {
        messageDao.deleteAll();
    }


}
