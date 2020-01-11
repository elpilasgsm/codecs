package org.sfedu.codecs.service;

import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.model.dto.User;
import org.sfedu.codecs.repository.db.UserRepository;
import org.sfedu.codecs.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zaporozhec
 * Service to work with users that will get access to the system.
 */
@Service
public class UserService extends AbstractCodecsService<UserEntity> {

    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;

    }

    public User create(User user) {
        UserEntity entity = new UserEntity();
        entity.setLogin(user.getLogin());
        try {
            entity.setPassword(PasswordUtils.getHash(user.getPassword()));
        } catch (Exception e) {
            return null;
        }
        entity.setRole(user.getUserRoles());
        entity.setActive(true);
        userRepository.save(entity);
        user.setId(entity.getUserId());
        user.setPassword(null);
        return user;
    }
}
