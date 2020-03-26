use codecs;
create table SANCTIONS
(
    ID     int auto_increment,
    NAME   VARCHAR(1024) not null,
    METRIC VARCHAR(32)  not null,
    constraint SANCTIONS_pk
        primary key (ID)
);

ALTER TABLE SANCTIONS
    MODIFY COLUMN NAME VARCHAR(255)
        CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

ALTER TABLE SANCTIONS
    MODIFY COLUMN METRIC VARCHAR(255)
        CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;


ALTER TABLE RECORD
    DROP COLUMN SEVERITY_OF_CRIME;

create table SANCTIONS_CHANGES
(
    ID          int auto_increment,
    SANCTION_ID int              not null,
    CHANGE_ID   int               null,
    ALT_CHANGE_ID   int               null,
    FROM_VALUE      int              null,
    TO_VALUE        int              null,
    OPTIONAL    CHAR default 'N' null,
    constraint SANCTIONS_CHANGES_pk
        primary key (ID),
    constraint CHANGES_SANCTIONS_ID_fk
        foreign key (CHANGE_ID) references CHANGES (CHANGES_ID),
    constraint ALT_CHA_SANCTIONS_ID_fk
        foreign key (ALT_CHANGE_ID) references CHANGES (CHANGES_ID),
    constraint SANCTIONS_CHANGES_SANCTIONS_ID_fk
        foreign key (SANCTION_ID) references SANCTIONS (ID)
);

INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (1, 'Штраф', 'МРОТ/рубли');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (2, 'Штраф', 'Доход за период');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (3, 'Штраф', 'Заработная плата за период');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (4, 'Штраф', 'кратный');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (5, 'Лишение права занимать определенную должность или заниматься определенной деятельностью', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (6, 'Обязательные работы', 'Часы');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (7, 'Исправительные работы', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (8, 'Ограничение по военной службе', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (9, 'Ограничение свободы', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (10, 'Принудительные работы', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (11, 'Арест', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (16, 'Содержание в дисциплинарной воинской части', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (17, 'Лишение свободы на определенный срок', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (18, 'Пожизненное лишение свободы', 'Месяц');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (19, 'Смертная казнь', '-');
INSERT INTO SANCTIONS (ID, NAME, METRIC) VALUES (20, 'Лишение специального воинского или почетного звания, классного чина и государственных наград', '-');