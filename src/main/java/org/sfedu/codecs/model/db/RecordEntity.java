package org.sfedu.codecs.model.db;

import org.sfedu.codecs.constants.CodecsRecordType;
import org.sfedu.codecs.model.IDBObject;
import org.sfedu.codecs.model.dto.ArticleRecord;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "RECORD")
public class RecordEntity implements IDBObject<ArticleRecord> {
    private static final long serialVersionUID = 1222074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private long recordId;

    @Column(name = "NAME")
    private String name;


    @Column(name = "ABBREVIATION")
    private String abbreviation;

    @Column(name = "CODECS_RECORD_TYPE")
    @Enumerated(EnumType.STRING)
    private CodecsRecordType recordType;

    @Column(name = "URL")
    private String url;

    @JoinColumn(name = "MEMBER_OF")
    @ManyToOne(fetch = FetchType.LAZY)
    private RecordEntity parent;

    @OrderBy("abbreviation asc")
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<RecordEntity> children;

    @OneToMany(mappedBy = "record", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ChangesEntity> changes;

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

    public List<ChangesEntity> getChanges() {
        return changes;
    }

    public void setChanges(List<ChangesEntity> changes) {
        this.changes = changes;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public ArticleRecord toDTO(boolean deepCopy) {

        ArticleRecord record = new ArticleRecord();
        record.setUrl(this.url);
        record.setRecordType(this.recordType);
        record.setName(this.name);
        record.setRecordId(this.recordId);
        record.setAbbreviation(this.abbreviation);
        if (deepCopy) {
            if (this.parent != null) {
                record.setParent(this.parent.toDTO(
                        false));
            }
            if (this.children != null) {
                record.setChildren(children.stream().map(it -> it.toDTO(false)).collect(Collectors.toList()));
            }
            if (this.changes != null) {
                record.setChanges(changes.stream().map(it -> it.toDTO(false)).collect(Collectors.toList()));
            }
        }
        return record;
    }
}
