package org.sfedu.codecs.model.dto;

import org.sfedu.codecs.model.DTOObject;
import org.sfedu.codecs.model.db.SanctionChangesEntity;

public class SanctionChangesRecord implements DTOObject<SanctionChangesEntity> {

    private static final long serialVersionUID = -623372816222782340L;

    private long id;

    private Integer from;

    private Integer to;


    private boolean optional;
    private boolean alternate;

    private ChangesRecord change;

    private SanctionRecord sanction;

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

    public ChangesRecord getChange() {
        return change;
    }

    public void setChange(ChangesRecord change) {
        this.change = change;
    }

    public SanctionRecord getSanction() {
        return sanction;
    }

    public void setSanction(SanctionRecord sanction) {
        this.sanction = sanction;
    }

    public boolean isAlternate() {
        return alternate;
    }

    public void setAlternate(boolean alternate) {
        this.alternate = alternate;
    }

    @Override
    public SanctionChangesEntity toDB(boolean deepCopy) {
        final SanctionChangesEntity record = new SanctionChangesEntity();
        record.setId(this.id);
        record.setFrom(this.from);
        record.setTo(this.to);
        record.setOptional(this.optional);
        record.setSanctionEntity(this.getSanction().toDB());
        return record;
    }
}
