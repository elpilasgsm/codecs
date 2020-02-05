package org.sfedu.codecs.model.db;

import org.sfedu.codecs.constants.CodecsRecordType;
import org.sfedu.codecs.constants.CrimeSeverity;
import org.sfedu.codecs.constants.SanctionsMetric;
import org.sfedu.codecs.model.DBObject;
import org.sfedu.codecs.model.dto.ArticleRecord;
import org.sfedu.codecs.model.dto.SanctionRecord;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "SANCTIONS")
public class SanctionEntity implements DBObject<SanctionRecord> {
    private static final long serialVersionUID = 1222074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "METRIC")
    @Enumerated(EnumType.STRING)
    private SanctionsMetric metric;

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

    public SanctionsMetric getMetric() {
        return metric;
    }

    public void setMetric(SanctionsMetric metric) {
        this.metric = metric;
    }

    @Override
    public SanctionRecord toDTO(boolean deepCopy) {
        SanctionRecord db = new SanctionRecord();
        db.setId(this.id);
        db.setMetric(this.metric);
        db.setName(this.name);
        return db;
    }
}
