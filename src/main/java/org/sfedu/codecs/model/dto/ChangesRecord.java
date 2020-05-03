package org.sfedu.codecs.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.sfedu.codecs.constants.*;
import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.ChangesEntity;
import org.sfedu.codecs.utils.CodecsDateDeSerializer;
import org.sfedu.codecs.utils.CodecsDateSerializer;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ChangesRecord implements DTOObject<ChangesEntity> {

    private static final long serialVersionUID = 1222074571641809270L;
    private long id;
    private String name;
    private CodecsChangesInPart changesInPart;
    private ChangesPerformanceType performanceType;
    private CrimeSeverity crimeSeverity;
    @JsonDeserialize(using = CodecsDateDeSerializer.class)
    @JsonSerialize(using = CodecsDateSerializer.class)
    private Calendar activationDate;
    private ChangesDirection direction;
    @JsonDeserialize(using = CodecsDateDeSerializer.class)
    @JsonSerialize(using = CodecsDateSerializer.class)
    private Calendar date;
    private String url;
    private ArticleRecord record;
    private ChangesMethod method;

    private List<SanctionChangesRecord> primarySanctions;

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

    public ChangesMethod getMethod() {
        return method;
    }

    public void setMethod(ChangesMethod method) {
        this.method = method;
    }

    public List<SanctionChangesRecord> getPrimarySanctions() {
        return primarySanctions;
    }

    public void setPrimarySanctions(List<SanctionChangesRecord> primarySanctions) {
        this.primarySanctions = primarySanctions;
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
        entity.setMethod(this.method);
        entity.setCrimeSeverity(this.crimeSeverity);
        if (deepCopy) {
            if (this.record != null) {
                entity.setRecord(this.record.toDB(false));
            }
            if (!CollectionUtils.isEmpty(this.primarySanctions)){
                entity.setPrimarySanctions(this.primarySanctions
                        .stream()
                        .map(it->it.toDB(false))
                .collect(Collectors.toList()));
            }
        }
        return entity;
    }
}
