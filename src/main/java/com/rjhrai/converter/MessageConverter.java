package com.rjhrai.converter;

import com.rjhrai.backing.MessageDetailBean;
import com.rjhrai.model.Message;
import com.rjhrai.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("messageConverter")
@SessionScoped
public class MessageConverter implements Converter, Serializable {
    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(MessageConverter.class);

    @Inject
    MessageService messageService;

    @PostConstruct
    public void init() {
        log.info("************************ MessageConverter postconstruct" );
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        try {
            return messageService.findById(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid MessageID", e.getMessage()));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Message) {
            return String.valueOf(((Message) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + "is not a valid Message entity"));
        }
    }
}
