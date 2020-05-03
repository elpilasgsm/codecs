package org.sfedu.codecs.model.db;

import org.sfedu.codecs.constants.*;
import org.sfedu.codecs.model.IDBObject;
import org.sfedu.codecs.model.dto.ChangesRecord;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "CHANGES")
public class ChangesEntity implements IDBObject<ChangesRecord> {
    private static final long serialVersionUID = 1222074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHANGES_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SEVERITY_OF_CRIME")
    @Enumerated(EnumType.STRING)
    private CrimeSeverity crimeSeverity;

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

    @Column(name = "METHOD")
    @Enumerated(EnumType.STRING)
    private ChangesMethod method;

    @Column(name = "CHANGES_DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Column(name = "URL")
    private String url;

    @JoinColumn(name = "MEMBER_OF")
    @ManyToOne(fetch = FetchType.LAZY)
    private RecordEntity record;


    @OneToMany(mappedBy = "changesEntity", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<SanctionChangesEntity> primarySanctions = new ArrayList<>();


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

    public CrimeSeverity getCrimeSeverity() {
        return crimeSeverity;
    }

    public void setCrimeSeverity(CrimeSeverity crimeSeverity) {
        this.crimeSeverity = crimeSeverity;
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

    public RecordEntity getRecord() {
        return record;
    }

    public void setRecord(RecordEntity record) {
        this.record = record;
    }

    public ChangesMethod getMethod() {
        return method;
    }

    public void setMethod(ChangesMethod method) {
        this.method = method;
    }

    public List<SanctionChangesEntity> getPrimarySanctions() {
        return primarySanctions;
    }

    public void setPrimarySanctions(List<SanctionChangesEntity> primarySanctions) {
        this.primarySanctions = primarySanctions;
    }

    @Override
    public ChangesRecord toDTO(boolean deepCopy) {
        ChangesRecord record = new ChangesRecord();
        record.setUrl(this.url);
        record.setActivationDate(this.activationDate);
        record.setChangesInPart(this.changesInPart);
        record.setDate(this.date);
        record.setDirection(this.direction);
        record.setPerformanceType(this.performanceType);
        record.setName(this.name);
        record.setMethod(this.method);
        record.setCrimeSeverity(this.crimeSeverity);
        record.setId(this.id);
        if (deepCopy) {
            if (this.record != null) {
                record.setRecord(this.record.toDTO(
                        false));
            }
            if (!CollectionUtils.isEmpty(this.primarySanctions)) {
                record.setPrimarySanctions(this.primarySanctions
                        .stream()
                        .map(it -> it.toDTO(true))
                        .collect(Collectors.toList()));
            }
        }
        return record;
    }
}
