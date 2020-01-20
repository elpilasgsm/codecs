package org.sfedu.codecs.model.db;

import org.sfedu.codecs.constants.ChangesDirection;
import org.sfedu.codecs.constants.ChangesPerformanceType;
import org.sfedu.codecs.constants.CodecsChangesInPart;
import org.sfedu.codecs.constants.CodecsRecordType;
import org.sfedu.codecs.model.DBObject;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "RECORD")
public class RecordEntity implements DBObject {
    private static final long serialVersionUID = 1222074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private long recordId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODECS_RECORD_TYPE")
    @Enumerated(EnumType.STRING)
    private CodecsRecordType recordType;

    @Column(name = "CHANGES_PART")
    @Enumerated(EnumType.STRING)
    private CodecsChangesInPart changesInPart;

    @Column(name = "CHANGES_TYPE")
    @Enumerated(EnumType.STRING)
    private ChangesPerformanceType performanceType;

    @Column(name = "ACTIVATE_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar activationDate;

    @Column(name = "DIRECTION")
    @Enumerated(EnumType.STRING)
    private ChangesDirection direction;

    @Column(name = "CHANGES_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column(name = "URL")
    private String url;

    @JoinColumn(name = "MEMBER_OF")
    @ManyToOne(fetch = FetchType.LAZY)
    private RecordEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<RecordEntity> children;

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

    public RecordEntity getParent() {
        return parent;
    }

    public void setParent(RecordEntity parent) {
        this.parent = parent;
    }

    public List<RecordEntity> getChildren() {
        return children;
    }

    public void setChildren(List<RecordEntity> children) {
        this.children = children;
    }
}
