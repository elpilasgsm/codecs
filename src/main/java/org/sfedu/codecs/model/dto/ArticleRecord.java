package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.constants.ChangesDirection;
import org.sfedu.codecs.constants.ChangesPerformanceType;
import org.sfedu.codecs.constants.CodecsChangesInPart;
import org.sfedu.codecs.constants.CodecsRecordType;
import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.RecordEntity;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleRecord implements DTOObject<RecordEntity> {

    private static final long serialVersionUID = 1222074571641809270L;
    private long recordId;
    private String name;
    private CodecsRecordType recordType;
    private CodecsChangesInPart changesInPart;
    private ChangesPerformanceType performanceType;
    private Calendar activationDate;
    private ChangesDirection direction;
    private Calendar date;
    private String url;
    private ArticleRecord parent;
    private List<ArticleRecord> children;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodecsRecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(CodecsRecordType recordType) {
        this.recordType = recordType;
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

    public ArticleRecord getParent() {
        return parent;
    }

    public void setParent(ArticleRecord parent) {
        this.parent = parent;
    }

    public List<ArticleRecord> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleRecord> children) {
        this.children = children;
    }

    @Override
    public RecordEntity toDB(boolean deepCopy) {
        RecordEntity entity = new RecordEntity();
        entity.setUrl(this.url);
        entity.setRecordType(this.recordType);
        entity.setChangesInPart(this.changesInPart);
        entity.setPerformanceType(this.performanceType);
        entity.setActivationDate(this.activationDate);
        entity.setDirection(this.direction);
        entity.setDate(this.date);
        entity.setRecordId(this.recordId);
        entity.setName(this.name);
        if (deepCopy) {
            if (this.parent != null) {
                entity.setParent(this.parent.toDB(false));
            }
            if (this.children != null) {
                entity.setChildren(children.stream().map(it -> it.toDB(false)).collect(Collectors.toList()));
            }
        }
        return entity;
    }
}
