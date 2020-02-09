package org.sfedu.codecs.model.db;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.sfedu.codecs.model.IDBObject;
import org.sfedu.codecs.model.dto.SanctionChangesRecord;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "SANCTIONS_CHANGES")
public class SanctionChangesEntity implements IDBObject<SanctionChangesRecord> {

    private static final long serialVersionUID = 6139691281892088780L;

    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "FROM_VALUE")
    private Integer from;

    @Column(name = "TO_VALUE")
    private Integer to;

    @Column(name = "OPTIONAL")
    @Type(type = "yes_no")
    private boolean optional;

    @JoinColumn(name = "CHANGE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChangesEntity changesEntity;

    @JoinColumn(name = "ALT_CHANGE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChangesEntity alternateChanges;

    @JoinColumn(name = "SANCTION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SanctionEntity sanctionEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }


    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public ChangesEntity getChangesEntity() {
        return changesEntity;
    }

    public void setChangesEntity(ChangesEntity changesEntity) {
        this.changesEntity = changesEntity;
    }

    public ChangesEntity getAlternateChanges() {
        return alternateChanges;
    }

    public void setAlternateChanges(ChangesEntity alternateChanges) {
        this.alternateChanges = alternateChanges;
    }

    public SanctionEntity getSanctionEntity() {
        return sanctionEntity;
    }

    public void setSanctionEntity(SanctionEntity sanctionEntity) {
        this.sanctionEntity = sanctionEntity;
    }

    @Override
    public SanctionChangesRecord toDTO(boolean deepCopy) {
        final SanctionChangesRecord record = new SanctionChangesRecord();
        record.setId(this.id);
        record.setFrom(this.from);
        record.setTo(this.to);
        record.setOptional(this.optional);
        return record;
    }
}
