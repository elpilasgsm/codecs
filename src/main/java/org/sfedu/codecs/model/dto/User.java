package org.sfedu.codecs.model.dto;

import org.apache.commons.lang3.StringUtils;
import org.sfedu.codecs.constants.UserRoles;
import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.UserEntity;

import java.io.Serializable;

public class User implements DTOObject<UserEntity> {
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

    @Override
    public UserEntity toDB(boolean deepCopy) {
        UserEntity entity = new UserEntity();
        if (StringUtils.isNoneBlank(this.status)) {
            entity.setActive("ACTIVE".equalsIgnoreCase(this.status));
        } else {
            entity.setActive(false);
        }
        entity.setLogin(this.login);
        entity.setRole(this.getUserRoles());
        entity.setUserId(this.id);
        if (StringUtils.isNoneBlank(this.password))
        entity.setPassword(this.password);
        return entity;
    }
}
