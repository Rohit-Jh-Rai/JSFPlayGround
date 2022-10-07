package com.rjhrai.backing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(EmployeeBean.class);

    private String empName = System.getProperty("user.name");
    private String gender;
    private Date dob;
    private String address;
    private String emailAddress;
    private String mobileNumber;
    private String maritalStatus;
    private String designation;
    private String department;
    private boolean employeeType;
    private String status;
    private String password;
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public String getPassword() {
        return password;
    }
    public String getStatus() {
        return status;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public boolean isEmployeeType() {
        return employeeType;
    }
    public void setEmployeeType(boolean employeeType) {
        this.employeeType = employeeType;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeBean() {
    }

    @PostConstruct
    public void postContruct() {
        empName = System.getProperty("user.name");
        log.info("* UserBean created");
    }

    // Validate Email
    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Email Address is Valid");
            throw new ValidatorException(message);
        }
    }

    public void login(ActionEvent evt) {
        if (empName.equals(password)) {
            log.info("Login successful");
            status = "Login successful";
        } else {
            status = "Login failed";
            log.info("Login failed");
        }
    }

    // Action Methods
    public String storeEmployeeInfo() {
        boolean stored = true;
        FacesMessage message = null;
        String outcome = null;
        if (stored) {
            message = new FacesMessage("Employee Information is stored Successfully.");
            outcome = "/employee/success";
        } else {
            message = new FacesMessage("Employee Information is NOT stored Successfully.");
            outcome = "/employee/employee";
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        return outcome;
    }
}
