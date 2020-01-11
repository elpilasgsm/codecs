package org.sfedu.codecs.model.db;

import org.hibernate.annotations.Type;
import org.sfedu.codecs.constants.UserRoles;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 3822074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    @Type(type = "yes_no")
    private boolean active;

    @Column(name = "RESET_TOKEN")
    private String resetToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private UserRoles role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
