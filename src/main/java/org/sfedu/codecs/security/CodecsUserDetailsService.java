package org.sfedu.codecs.security;

import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.repository.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CodecsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CodecsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findUserEntityByLogin(s);
        if (entity == null) {
            throw new UsernameNotFoundException("No such user!");
        }
        return new User(entity.getLogin(),
                entity.getPassword(),
                entity.isActive(),
                true,
                true,
                true, Collections.singleton(entity.getRole()));
    }
}
