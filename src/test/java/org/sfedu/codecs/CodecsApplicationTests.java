package org.sfedu.codecs;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.sfedu.codecs.constants.UserRoles;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.model.es.ArticleDoc;
import org.sfedu.codecs.repository.db.UserRepository;
import org.sfedu.codecs.repository.es.ArticleESRepository;
import org.sfedu.codecs.service.UserService;
import org.sfedu.codecs.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.UUID;

@SpringBootTest
class CodecsApplicationTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    protected ArticleESRepository articleESRepository;

    protected static String random(int l) {
        return RandomStringUtils.randomAlphanumeric(l);
    }

    @Test
    void contextLoads() throws Exception {
        String login = random(20);
        String password = random(32);
        UserEntity entity = new UserEntity();
        entity.setLogin(login);
        entity.setPassword(PasswordUtils.getHash(password));
        entity.setActive(true);
        entity.setRole(UserRoles.MODERATOR);
        entity = repository.save(entity);

        Assert.isTrue(entity.getUserId() != 0, "ID is 0");
        final UserEntity fromDB = repository.findUserEntityByLogin(login);
        Assert.notNull(fromDB, "Can't find by login");
        Assert.isTrue(PasswordUtils.getHash(password).equals(fromDB.getPassword()), "Hash is not the same");

        userService.getById(fromDB.getUserId());

        repository.delete(fromDB);

        ArticleDoc articleDoc = new ArticleDoc();
        articleDoc.setId(UUID.randomUUID().toString());
        articleDoc.setName(random(32));
        articleESRepository.save(articleDoc);

        articleESRepository.findById(articleDoc.getId());

    }


}
