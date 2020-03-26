package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.SanctionEntity;

public class SanctionRecord implements DTOObject<SanctionEntity> {

    private static final long serialVersionUID = 1222074571641809270L;

    private long id;

    private String name;

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
    public SanctionEntity toDB(boolean deepCopy) {
        SanctionEntity db = new SanctionEntity();
        db.setId(this.id);
        db.setMetric(this.metric);
        db.setName(this.name);
        return db;
    }

}
