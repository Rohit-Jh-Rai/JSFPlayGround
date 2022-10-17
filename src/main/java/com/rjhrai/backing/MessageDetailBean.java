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

@Named("messageDetailBean")
@ViewScoped
public class MessageDetailBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(MessageDetailBean.class);

    private Message message;
    @Inject
    private MessageService messageService;

    public MessageDetailBean() {
        log.info("************************ MessageDetailBean constructor" );
        message = new Message();
    }

    @PostConstruct
    public void init() {
        log.info("************************ MessageDetailBean postconstruct" );
    }

    public void initMessage() {
        if (message == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }
        message = messageService.findById(message.getId());
        if (message == null) {
            String message = "Bad request. Unknown message.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("************************* MessageDetailBean predestroy" );
    }

    public String save() {
        log.info("MessageBean submitted" );
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().forEach((k,v) -> System.out.println(k + ":" + v));

        String msg = String.format("Saved: " + message.getId() + " : " + message.getText());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));

        messageService.update(message);
        return "message";
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}