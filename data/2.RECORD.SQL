CREATE SCHEMA IF NOT EXISTS codecs;

use codecs;

CREATE TABLE RECORD
(
    RECORD_ID          int(11)      NOT NULL AUTO_INCREMENT,
    NAME               varchar(255) NOT NULL,
    CODECS_RECORD_TYPE varchar(64)  NOT NULL,
    CHANGES_PART       varchar(64)  NOT NULL,
    CHANGES_TYPE       varchar(64)  NOT NULL,
    ACTIVATE_DATE      date         NULL,
    DIRECTION          varchar(32)  NOT NULL,
    CHANGES_DATE       date         default null,
    URL                varchar(255) NOT NULL,
    MEMBER_OF int(11) default null,
    PRIMARY KEY (RECORD_ID)
);

alter table RECORD
    add constraint RECORD_RECORD_RECORD_ID_fk
        foreign key (MEMBER_OF) references RECORD (RECORD_ID);