package com.code.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit <U>{
    @CreatedBy
    protected U createdby;

    @LastModifiedBy
    protected U updatedby;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;

    public U getCreatedby() {
        return createdby;
    }

    public void setCreatedby(U createdby) {
        this.createdby = createdby;
    }

    public U getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(U updatedby) {
        this.updatedby = updatedby;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
