package org.sfedu.codecs;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.sfedu.codecs.constants.*;
import org.sfedu.codecs.model.db.RecordEntity;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.repository.db.RecordRepository;
import org.sfedu.codecs.repository.db.UserRepository;
import org.sfedu.codecs.repository.es.ArticleESRepository;
import org.sfedu.codecs.service.UserService;
import org.sfedu.codecs.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;

@SpringBootTest
@PropertySource("classpath:application.properties")
class CodecsApplicationTests {

    @Autowired
    private UserRepository repository;


    @Autowired
    private RecordRepository recordRepository;

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
    }

    @Test
    public void articteTest() {
        RecordEntity article = new RecordEntity();
        article.setChangesInPart(CodecsChangesInPart.PUNISHMENT);
        article.setName(random(25));
        article.setActivationDate(Calendar.getInstance());
        article.setDate(Calendar.getInstance());
        article.setDirection(ChangesDirection.NEUTRAL);
        article.setPerformanceType(ChangesPerformanceType.DELAYED);
        article.setRecordType(CodecsRecordType.ARTICLE);
        article.setUrl(random(255));
        article = recordRepository.save(article);

        RecordEntity part1 = new RecordEntity();
        part1.setChangesInPart(CodecsChangesInPart.OFFENSE);
        part1.setName(random(25));
        part1.setActivationDate(Calendar.getInstance());
        part1.setDate(Calendar.getInstance());
        part1.setDirection(ChangesDirection.POSITIVE);
        part1.setPerformanceType(ChangesPerformanceType.NOW);
        part1.setRecordType(CodecsRecordType.PART);
        part1.setUrl(random(255));
        part1.setParent(article);
        part1 = recordRepository.save(part1);

        for (int i = 0; i < 5; i++) {
            RecordEntity point = new RecordEntity();
            point.setChangesInPart(CodecsChangesInPart.OFFENSE);
            point.setName(random(25));
            point.setActivationDate(Calendar.getInstance());
            point.setDate(Calendar.getInstance());
            point.setDirection(ChangesDirection.POSITIVE);
            point.setPerformanceType(ChangesPerformanceType.NOW);
            point.setRecordType(CodecsRecordType.POINT);
            point.setUrl(random(255));
            point.setParent(part1);
            point = recordRepository.save(point);
        }
       final RecordEntity partResponse1 = recordRepository.getOne(article.getRecordId());
        if (!CollectionUtils.isEmpty(partResponse1.getChildren())) {
            Assert.isTrue(partResponse1.getChildren().stream().allMatch(it -> CodecsRecordType.PART == it.getRecordType()), "not all are points");
            for (RecordEntity parts: partResponse1.getChildren()){
                Assert.isTrue(parts.getChildren().stream().allMatch(it -> CodecsRecordType.POINT == it.getRecordType()), "not all are points");
            }
        }
    }

    void createInitialAdminUser() throws Exception {
        String login = "admin";
        String password = "passwd00";
        UserEntity entity = new UserEntity();
        entity.setLogin(login);
        entity.setPassword(PasswordUtils.getHash(password));
        entity.setActive(true);
        entity.setRole(UserRoles.ADMIN);
        repository.save(entity);
    }

}
