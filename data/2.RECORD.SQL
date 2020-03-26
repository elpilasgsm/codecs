use codecs;

CREATE TABLE RECORD
(
    RECORD_ID          int(11)      NOT NULL AUTO_INCREMENT,
    NAME               varchar(1024) NOT NULL,
    CODECS_RECORD_TYPE varchar(64)  NOT NULL,
    SEVERITY_OF_CRIME  varchar(64) DEFAULT NULL,
    URL                varchar(255) NOT NULL,
    MEMBER_OF          int(11)     default null,
    PRIMARY KEY (RECORD_ID)
);

alter table RECORD
    add constraint RECORD_RECORD_RECORD_ID_fk
        foreign key (MEMBER_OF) references RECORD (RECORD_ID);