package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.constants.CodecsRecordType;
import org.sfedu.codecs.constants.CrimeSeverity;
import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.RecordEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleRecord implements DTOObject<RecordEntity> {

    private static final long serialVersionUID = 1222074571641809270L;
    private long recordId;
    private String name;
    private CodecsRecordType recordType;
    private String url;
    private CrimeSeverity crimeSeverity;
    private ArticleRecord parent;
    private List<ArticleRecord> children;
    private List<ChangesRecord> changes;
    private String abbreviation;

    public CrimeSeverity getCrimeSeverity() {
        return crimeSeverity;
    }

    public void setCrimeSeverity(CrimeSeverity crimeSeverity) {
        this.crimeSeverity = crimeSeverity;
    }

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

    public List<ChangesRecord> getChanges() {
        return changes;
    }

    public void setChanges(List<ChangesRecord> changes) {
        this.changes = changes;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public RecordEntity toDB(boolean deepCopy) {
        RecordEntity entity = new RecordEntity();
        entity.setUrl(this.url);
        entity.setRecordType(this.recordType);
        entity.setRecordId(this.recordId);
        entity.setName(this.name);
        entity.setCrimeSeverity(this.crimeSeverity);
        entity.setAbbreviation(this.abbreviation);
        if (deepCopy) {
            if (this.parent != null) {
                entity.setParent(this.parent.toDB(false));
            }
            if (this.children != null) {
                entity.setChildren(children.stream().map(it -> it.toDB(false)).collect(Collectors.toList()));
            }
            if (this.changes != null) {
                entity.setChanges(this.changes.stream().map(it -> it.toDB(false)).collect(Collectors.toList()));
            }
        }
        return entity;
    }
}
