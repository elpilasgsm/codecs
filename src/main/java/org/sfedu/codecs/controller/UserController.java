package org.sfedu.codecs.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.model.dto.User;
import org.sfedu.codecs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;

@Controller
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public User put(@RequestBody User user) {
        return userService.create(user);
    }


    @ResponseBody
    @RequestMapping(value = "/{user-id}", method = RequestMethod.GET)
    public User get(@PathVariable("user-id") Long id, HttpServletResponse response) throws IOException {
        UserEntity entity = userService.getById(id);
        if (entity == null) {
            response.sendError(HttpResponseStatus.NOT_FOUND.code());
            return null;
        } else {
            User u = new User();
            u.setId(entity.getUserId());
            u.setLogin(entity.getLogin());
            u.setStatus(entity.isActive() ? "ACTIVE" : "INACTIVE");
            return u;
        }
    }
}

