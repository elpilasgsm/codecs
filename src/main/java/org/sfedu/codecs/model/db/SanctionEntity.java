package org.sfedu.codecs.model.db;

import org.sfedu.codecs.model.IDBObject;
import org.sfedu.codecs.model.dto.SanctionRecord;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "SANCTIONS")
public class SanctionEntity implements IDBObject<SanctionRecord> {
    private static final long serialVersionUID = 1222074571641809270L;
    @Id
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "METRIC")
    private String metric;

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

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
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
