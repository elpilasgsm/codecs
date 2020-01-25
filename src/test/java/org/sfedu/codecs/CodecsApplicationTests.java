package org.sfedu.codecs;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.sfedu.codecs.constants.*;
import org.sfedu.codecs.model.db.ChangesEntity;
import org.sfedu.codecs.model.db.RecordEntity;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.repository.db.ChangesRepository;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
@PropertySource("classpath:application.properties")
class CodecsApplicationTests {

    @Autowired
    private UserRepository repository;


    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private ChangesRepository changesRepository;


    @Autowired
    private UserService userService;

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
        for (int bn = 0; bn < 12; bn++) {
            RecordEntity article = new RecordEntity();
            article.setName(random(25));
            article.setRecordType(CodecsRecordType.ARTICLE);
            article.setUrl(String.format("https://%s", random(10)));
            article.setCrimeSeverity(CrimeSeverity.REGULAR);
            article.setChanges(getListOfChanges(5, article));
            article = recordRepository.save(article);

            RecordEntity part1 = new RecordEntity();
            part1.setName(random(25));
            part1.setRecordType(CodecsRecordType.PART);
            part1.setUrl(String.format("https://%s", random(10)));
            part1.setParent(article);
            part1.setCrimeSeverity(CrimeSeverity.EXTRA);
            part1.setChanges(getListOfChanges(5, part1));
            part1 = recordRepository.save(part1);
            for (int i = 0; i < 5; i++) {

                RecordEntity point = new RecordEntity();
                point.setName(random(25));
                point.setCrimeSeverity(CrimeSeverity.MIDDLE);
                point.setRecordType(CodecsRecordType.POINT);
                point.setUrl(String.format("https://%s", random(10)));
                point.setParent(part1);

                point.setChanges(getListOfChanges(5, point));
                recordRepository.save(point);
                //  changesRepository.save(changesEntity);
            }
            final RecordEntity partResponse1 = recordRepository.getOne(article.getRecordId());
            if (!CollectionUtils.isEmpty(partResponse1.getChildren())) {
                Assert.isTrue(partResponse1.getChildren().stream().allMatch(it -> CodecsRecordType.PART == it.getRecordType()), "not all are points");
                for (RecordEntity parts : partResponse1.getChildren()) {
                    Assert.isTrue(parts.getChildren().stream().allMatch(it -> CodecsRecordType.POINT == it.getRecordType()), "not all are points");
                }
            }
        }
    }

    private List<ChangesEntity> getListOfChanges(int size, RecordEntity point) {
        List<ChangesEntity> listOFChanges = new ArrayList<>();
        for (int u = 0; u < size; u++) {
            listOFChanges.add(getRandomChanges(point));
        }
        return listOFChanges;
    }

    private ChangesEntity getRandomChanges(RecordEntity parent) {
        ChangesEntity changesEntity = new ChangesEntity();
        changesEntity.setCrimeSeverity(CrimeSeverity.MIDDLE);
        changesEntity.setActivationDate(Calendar.getInstance());
        changesEntity.setRecord(parent);
        changesEntity.setDate(Calendar.getInstance());
        changesEntity.setPerformanceType(ChangesPerformanceType.DELAYED);
        changesEntity.setChangesInPart(CodecsChangesInPart.PUNISHMENT);
        changesEntity.setName(random(23));
        changesEntity.setDirection(ChangesDirection.POSITIVE);
        changesEntity.setUrl(String.format("https://%s", random(10)));
        return changesEntity;
    }

    @Test
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
