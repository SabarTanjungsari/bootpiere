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
public abstract class Audit <T>{
    @CreatedBy
    protected T createdby;

    @LastModifiedBy
    protected T updatedby;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updated;

    public T getCreatedby() {
        return createdby;
    }

    public void setCreatedby(T createdby) {
        this.createdby = createdby;
    }

    public T getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(T updatedby) {
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
