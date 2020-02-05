use codecs;
create table SANCTIONS
(
    ID int auto_increment,
    NAME VARCHAR(255) not null,
    METRIC VARCHAR(32) not null,
    constraint SANCTIONS_pk
        primary key (ID)
);

ALTER TABLE SANCTIONS
    MODIFY COLUMN NAME VARCHAR(255)
        CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;

ALTER TABLE RECORD DROP COLUMN SEVERITY_OF_CRIME;

