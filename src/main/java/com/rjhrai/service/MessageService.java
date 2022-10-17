package com.rjhrai.service;

import com.rjhrai.model.Message;

import java.util.List;

public interface MessageService {
    public Message create(Message message);
    public void update(Message message);
    public Message findById(Long id);
    public List<Message> listAll();
    public void deleteAll();

}
