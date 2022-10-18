package com.rjhrai.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

@FacesConverter("employeeNameConverter")
public class EmployeeConverter implements Converter, Serializable {
    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(EmployeeConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }
        return submittedValue + " van ui";
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof String) {
            return (String) modelValue + " naar ui";
        } else {
            throw new ConverterException(new FacesMessage(modelValue + "is not a valid String"));
        }
    }
}
