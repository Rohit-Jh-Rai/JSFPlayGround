package com.rjhrai.backing;

import com.rjhrai.model.Message;
import com.rjhrai.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

@Named("messageDetailBeanJDNI")
@ViewScoped
public class MessageDetailUsingJNDIBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(MessageDetailUsingJNDIBean.class);

    private Message message;

    private MessageService messageService;

    public MessageDetailUsingJNDIBean() {
        log.info("************************ MessageDetailUsingJNDIBean constructor" );
        messageService = getMessageServiceFromJNDI(MessageService.class);
        message = new Message();
    }

    private MessageService getMessageServiceFromJNDI(Class clazz)  {
        log.info("Looking up {} from JDNI...", clazz.getCanonicalName() );
        String jdniName="java:global/JSFRichEx1/MessageServiceImpl!com.rjhrai.service.MessageService";
        Object lookup = lookup(clazz, jdniName);
        return (MessageService) lookup;
    }

    private static <T> T lookup(Class<T> ejbClass, String jndiName) {
        try {
            // Do not use ejbClass.cast(). It will fail on local/remote interfaces.
            return (T) new InitialContext().lookup(jndiName);
        } catch (NamingException e) {
            throw new IllegalArgumentException(String.format("Cannot find EJB class %s in JNDI %s", ejbClass, jndiName), e);
        }
    }

    @PostConstruct
    public void init() {
        log.info("************************ MessageDetailUsingJNDIBean postconstruct" );
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
        log.info("************************* MessageDetailUsingJNDIBean predestroy" );
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