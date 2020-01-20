CREATE SCHEMA IF NOT EXISTS codecs;

use codecs;

CREATE TABLE USERS
(
    USER_ID     int(11)     NOT NULL AUTO_INCREMENT,
    LOGIN       varchar(32) NOT NULL,
    PASSWORD    varchar(64) NOT NULL,
    ACTIVE      char(1)     NOT NULL DEFAULT 'Y',
    RESET_TOKEN varchar(64)          DEFAULT NULL,
    USER_ROLE   varchar(32)          DEFAULT NULL,
    PRIMARY KEY (USER_ID)
);

