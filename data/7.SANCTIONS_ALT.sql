use codecs;
alter table SANCTIONS_CHANGES drop foreign key ALT_CHA_SANCTIONS_ID_fk;

alter table SANCTIONS_CHANGES
    add constraint SANCTIONS_CHANGES_ALT_FK
        foreign key (ALT_CHANGE_ID) references SANCTIONS_CHANGES (ID);

