package com.rjhrai.backing;

import com.rjhrai.model.Message;
import com.rjhrai.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

@Named("messageBean")
@ViewScoped
public class MessageBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(MessageBean.class);

    private Message message;
    private List<Message> messages;

    @Inject
    private MessageService messageService;

    public MessageBean() {
        log.info("************************ MessageBean constructor" );
        message = new Message();
        messages = new LinkedList<>();
    }

    @PostConstruct
    public void init() {
        log.info("************************ MessageBean postconstruct" );
        messages = messageService.listAll();
    }

    @PreDestroy
    public void destroy() {
        log.info("************************* MessageBean predestroy" );
    }

    public void submit(ActionEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().forEach((k,v) -> System.out.println(k + ":" + v));

        String msg = String.format("Submitted: input1=%s", message.getText());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));

        messageService.create(message);
        messages.add(message);
        message = new Message();
        log.info("MessageBean submitted" );
    }

    public String doEdit(Message message, boolean doRedirect) {
        log.info("MessageBean edit: " + message.getId() );
         // commandbutton does a POST: f:viewparam and f:viewaction in messageDetail will not work unless you do a redirect:
        return "/message/messageDetail?"+ (doRedirect?"faces-redirect=true&":"") + "id="+message.getId();
    }

    public void doReset(ActionEvent event) {
        log.info("reset" );
        messageService.deleteAll();
        messages.clear();
    }

    public Message getMessage() {
        return message;
    }

    public List<Message> getMessages() {
        return messages;
    }
}