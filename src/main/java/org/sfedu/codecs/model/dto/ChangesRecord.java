package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.constants.ChangesDirection;
import org.sfedu.codecs.constants.ChangesPerformanceType;
import org.sfedu.codecs.constants.CodecsChangesInPart;
import org.sfedu.codecs.constants.CrimeSeverity;
import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.ChangesEntity;

import java.util.Calendar;

public class ChangesRecord implements DTOObject<ChangesEntity> {

    private static final long serialVersionUID = 1222074571641809270L;
    private long id;
    private String name;
    private CodecsChangesInPart changesInPart;
    private ChangesPerformanceType performanceType;
    private CrimeSeverity crimeSeverity;
    private Calendar activationDate;
    private ChangesDirection direction;
    private Calendar date;
    private String url;
    private ArticleRecord record;

    public CrimeSeverity getCrimeSeverity() {
        return crimeSeverity;
    }

    public void setCrimeSeverity(CrimeSeverity crimeSeverity) {
        this.crimeSeverity = crimeSeverity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodecsChangesInPart getChangesInPart() {
        return changesInPart;
    }

    public void setChangesInPart(CodecsChangesInPart changesInPart) {
        this.changesInPart = changesInPart;
    }

    public ChangesPerformanceType getPerformanceType() {
        return performanceType;
    }

    public void setPerformanceType(ChangesPerformanceType performanceType) {
        this.performanceType = performanceType;
    }

    public Calendar getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Calendar activationDate) {
        this.activationDate = activationDate;
    }

    public ChangesDirection getDirection() {
        return direction;
    }

    public void setDirection(ChangesDirection direction) {
        this.direction = direction;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArticleRecord getRecord() {
        return record;
    }

    public void setRecord(ArticleRecord record) {
        this.record = record;
    }

    @Override
    public ChangesEntity toDB(boolean deepCopy) {
        ChangesEntity entity = new ChangesEntity();
        entity.setUrl(this.url);

        entity.setChangesInPart(this.changesInPart);
        entity.setPerformanceType(this.performanceType);
        entity.setActivationDate(this.activationDate);
        entity.setDirection(this.direction);
        entity.setDate(this.date);
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCrimeSeverity(this.crimeSeverity);
        if (deepCopy) {
            if (this.record != null) {
                entity.setRecord(this.record.toDB(false));
            }
        }
        return entity;
    }
}
