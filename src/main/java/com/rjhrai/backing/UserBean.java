package com.rjhrai.backing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = -6239437588285327644L;
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);

    private String name;

    public UserBean() {
    }

    @PostConstruct
    public void postContruct() {
        name = System.getProperty("user.name");;
        log.info("* UserBean created" );
    }

    public String getName() {
        log.info("* getName() called" );
        return name;
    }

    public void setName(String name) {
        log.info("* setName() called" );
        this.name = name;
    }
}