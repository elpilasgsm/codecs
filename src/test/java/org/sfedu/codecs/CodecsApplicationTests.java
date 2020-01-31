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
import org.sfedu.codecs.service.UserService;
import org.sfedu.codecs.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    private final static Random rnd = new Random(123L);

    @Test
    void contextLoads() throws Exception {
        String login = random(20);
        String password = random(32);
        UserEntity entity = new UserEntity();
        entity.setLogin(login);
        entity.setPassword(PasswordUtils.getHash(password));
        entity.setActive(true);
        entity.setRole(random(UserRoles.class));
        entity = repository.save(entity);
        Assert.isTrue(entity.getUserId() != 0, "ID is 0");
        final UserEntity fromDB = repository.findUserEntityByLogin(login);
        Assert.notNull(fromDB, "Can't find by login");
        Assert.isTrue(PasswordUtils.getHash(password).equals(fromDB.getPassword()), "Hash is not the same");

        userService.getById(fromDB.getUserId());
    }

    private static <E extends Enum> E random(Class<E> eClass) {
        Enum[] enums = eClass.getEnumConstants();
        return (E) enums[rnd.nextInt(enums.length)];
    }

    @Test
    public void articteTest() {
        int inter = rnd.nextInt(5) + 2;
        for (int bn = 0; bn < inter; bn++) {
            RecordEntity article = new RecordEntity();
            article.setName(random(25));
            article.setAbbreviation(random(3));
            article.setRecordType(CodecsRecordType.ARTICLE);
            article.setUrl(String.format("https://%s", random(10)));
            article.setCrimeSeverity(random(CrimeSeverity.class));
            article.setChanges(getListOfChanges(rnd.nextInt(5) + 2, article));
            article = recordRepository.save(article);

            RecordEntity part1 = new RecordEntity();
            part1.setAbbreviation(random(3));
            part1.setRecordType(CodecsRecordType.PART);
            part1.setUrl(String.format("https://%s", random(10)));
            part1.setParent(article);
            part1.setCrimeSeverity(random(CrimeSeverity.class));
            part1.setChanges(getListOfChanges(rnd.nextInt(5) + 2, part1));
            part1 = recordRepository.save(part1);
            int iter2 = rnd.nextInt(5) + 2;
            for (int i = 0; i < iter2; i++) {
                RecordEntity point = new RecordEntity();
                point.setCrimeSeverity(random(CrimeSeverity.class));
                point.setRecordType(CodecsRecordType.POINT);
                point.setUrl(String.format("https://%s", random(10)));
                point.setParent(part1);
                point.setAbbreviation(random(3));
                point.setChanges(getListOfChanges(rnd.nextInt(70) + 30, point));
                recordRepository.save(point);
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
        changesEntity.setCrimeSeverity(random(CrimeSeverity.class));
        changesEntity.setRecord(parent);
        changesEntity.setMethod(random(ChangesMethod.class));
        //changesEntity.setPerformanceType(ChangesPerformanceType.DELAYED);
        changesEntity.setDate(Calendar.getInstance());
        changesEntity.getDate().add(Calendar.YEAR, -rnd.nextInt(30) + 1);
        changesEntity.setPerformanceType(random(ChangesPerformanceType.class));
        changesEntity.setChangesInPart(random(CodecsChangesInPart.class));
        changesEntity.setName(random(23));
        changesEntity.setDirection(random(ChangesDirection.class));
        changesEntity.setUrl(String.format("https://%s", random(10)));
        if (ChangesPerformanceType.DELAYED == changesEntity.getPerformanceType()) {
            changesEntity.setActivationDate(changesEntity.getDate());
            changesEntity.getActivationDate().add(Calendar.MONTH, rnd.nextInt(4) + 1);
        }

        return changesEntity;
    }

    //@Test
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
