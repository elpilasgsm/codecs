use codecs;

CREATE TABLE CHANGES
(
    CHANGES_ID        int(11)      NOT NULL AUTO_INCREMENT,
    NAME              varchar(255) NOT NULL,
    CHANGES_PART      varchar(64)  NOT NULL,
    CHANGES_TYPE      varchar(64)  NOT NULL,
    ACTIVATE_DATE     date         NULL,
    DIRECTION         varchar(32)  NOT NULL,
    SEVERITY_OF_CRIME varchar(64) DEFAULT NULL,
    CHANGES_DATE      date        default null,
    URL               varchar(255) NOT NULL,
    MEMBER_OF         int(11)      NOT NULL,
    PRIMARY KEY (CHANGES_ID)
);

ALTER TABLE CHANGES
    add constraint CHANGES_ID_RECORD_ID_fk
        foreign key (MEMBER_OF) REFERENCES RECORD (RECORD_ID);