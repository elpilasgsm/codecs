package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.constants.UserRoles;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4556512137301420462L;
    private Long id;
    private String login;
    private String status;
    private UserRoles userRoles;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
